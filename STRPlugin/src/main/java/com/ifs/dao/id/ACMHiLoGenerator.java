package com.ifs.dao.id;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.HibernateException;
import org.hibernate.LockOptions;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerationException;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.type.Type;

import com.ifs.infra.AppConfig;

public class ACMHiLoGenerator implements IdentifierGenerator, Configurable {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	public static List<ACMHiLoGenerator> hiLoGeneratorList;
	public static final String MAX_LO = "max_lo";
	public static final int DEFAULT_MAX_LO_SIZE = 100;
	public static final String COLUMN = "column";
	protected String query;
	protected String update;
	protected volatile long hi;
	protected volatile int lo;
	protected int maxLo;
	protected Class returnClass;
	private String columnName;

	public ACMHiLoGenerator() {
	}

	/**
	 * @param type
	 * @return
	 */
	public static ACMHiLoGenerator getGenerator(final Class type) {
		return (ACMHiLoGenerator) AppConfig.getClassIDGenerator(type);
	}

	/**
	 *
	 */
	public void configure(final Type type, final Properties params, final Dialect dialect) {
		this.columnName = ConfigurationHelper.getString("column", (Map) params, "hi_metaobject");
		this.query = "select " + this.columnName + " from "
				+ dialect.appendLockHint(LockOptions.UPGRADE, "hibernate_unique_key") + dialect.getForUpdateString();
		this.update = "update hibernate_unique_key set " + this.columnName + " = ? where " + this.columnName + " = ?";
		this.maxLo = ConfigurationHelper.getInt("max_lo", (Map) params, 100);
		this.lo = this.maxLo + 1;
		this.returnClass = type.getReturnedClass();
		ACMHiLoGenerator.hiLoGeneratorList.add(this);
	}

	/**
	 * @return
	 */
	public String getIdentifier() {
		return this.columnName;
	}

	/**
	 * @return
	 */
	public synchronized Number generate() {
		try {
			if (this.lo > this.maxLo) {
				this.hi = this.generate(this.maxLo);
				this.lo = 1;
			}
			return this.createNumber(this.hi + this.lo++, this.returnClass);
		} catch (IdentifierGenerationException e) {
			throw new RuntimeException("wrong Id type", (Throwable) e);
		} catch (Exception e2) {
			throw new RuntimeException("cant get a new ID from the database", e2);
		}
	}

	/**
	 *
	 */
	public synchronized Serializable generate(final SessionImplementor session, final Object obj)
			throws HibernateException {
		if (this.lo > this.maxLo) {
			this.hi = this.generate(this.maxLo);
			this.lo = 1;
		}
		return this.createNumber(this.hi + this.lo++, this.returnClass);
	}

	/**
	 * @param maxLo
	 * @return
	 */
	private synchronized long generate(final int maxLo) {
		//final DataSource dataSource = (DataSource) SpringUtil.lookupComponent("dataSource");
		Connection conn = null;
		try {
			//TODO: Change this back for production remove the test call
			conn = new AppConfig().getConnection();
			//conn = new AppConfig().getConnectionTest();
			try {
				conn.setAutoCommit(false);
				int rows;
				long result;
				do {
					result = this.selectCurrentHiValueFromDB(conn);
					final long nextChunk = this.calculateNextChunk(result, maxLo);
					final long nextHi = nextChunk * maxLo;
					rows = this.updateNextValueInDB(conn, result, nextHi);
					result = (nextChunk - 1L) * maxLo;
				} while (rows == 0);
				conn.commit();
				return result;
			} catch (SQLException sqlEx) {
				try {
					conn.rollback();
				} catch (Exception ex2) {
				}
				throw new RuntimeException(sqlEx);
			}
		} catch (Exception ex) {
			throw new RuntimeException("can't create a new ID", ex);
		} finally {
			AppConfig.releaseDBObjects(conn, (Statement) null, (ResultSet) null);
		}
	}

	/**
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	private long selectCurrentHiValueFromDB(final Connection conn) throws SQLException {
		PreparedStatement ps = null;
		long result;
		try {
			ps = conn.prepareStatement(this.query);
			this.prepareSelectSQL(ps);
			final ResultSet rs = ps.executeQuery();
			if (!rs.next()) {
				final String err = "Error reading hi value for generator " + this.getIdentifier();
				this.logger.info(err);
				throw new IdentifierGenerationException(err);
			}
			result = rs.getLong(1);
			rs.close();
		} catch (SQLException sqle) {
			this.logger.error("could not read a hi value", (Throwable) sqle);
			throw sqle;
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException ex) {
				}
			}
		}
		return result;
	}

	protected void prepareSelectSQL(final PreparedStatement ps) throws SQLException {
	}

	protected int updateNextValueInDB(final Connection conn, final long currentHiValue, final long nextHiValue)
			throws SQLException {
		int returnedRows = 0;
		final PreparedStatement ps = conn.prepareStatement(this.update);
		try {
			this.prepareUpdateSQL(ps, currentHiValue, nextHiValue);
			returnedRows = ps.executeUpdate();
		} catch (SQLException sqle) {
			this.logger.error("error updating hi value for generator: " + this.getIdentifier(), (Throwable) sqle);
			throw sqle;
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException ex) {
				}
			}
		}
		return returnedRows;
	}

	protected void prepareUpdateSQL(final PreparedStatement ps, final long currentHiValue, final long nextHiValue)
			throws SQLException {
		ps.setLong(1, nextHiValue);
		ps.setLong(2, currentHiValue);
	}

	private long calculateNextChunk(final long previousId, final int maxLo) {
		final long used = previousId / maxLo;
		final int rest = (int) (previousId % maxLo);
		long result;
		if (rest == 0) {
			result = used + 1L;
		} else {
			result = used + 2L;
		}
		return result;
	}

	private Number createNumber(final long value, final Class clazz) throws IdentifierGenerationException {
		if (clazz == Long.class) {
			return new Long(value);
		}
		if (clazz == Integer.class) {
			final int intValue = (int) value;
			if (intValue < 0) {
				this.logger.info("hilo generator " + this.getIdentifier()
						+ " generated a negative number, this probably indicates an arithmetic overflow from long to int. The generated long value is "
						+ value + " but the resulting integer value is " + intValue);
			}
			return new Integer(intValue);
		}
		if (clazz == Short.class) {
			final short shortValue = (short) value;
			if (shortValue < 0) {
				this.logger.info("hilo generator " + this.getIdentifier()
						+ " generated a negative number, this probably indicates an arithmetic overflow from long to short. The generated long value is "
						+ value + " but the resulting short value is " + shortValue);
			}
			return new Short(shortValue);
		}
		throw new IdentifierGenerationException("this id generator generates long, integer, short");
	}

	public synchronized void reset() {
		this.lo = this.maxLo + 1;
	}

	public static void resetAllHiLoGenerators() {
		for (final ACMHiLoGenerator generator : ACMHiLoGenerator.hiLoGeneratorList) {
			generator.reset();
		}
	}

	static {
		ACMHiLoGenerator.hiLoGeneratorList = new LinkedList<ACMHiLoGenerator>();
	}
}