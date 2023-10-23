/**
 * 
 */
package com.ifs.dao.id;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import org.hibernate.LockOptions;
import org.hibernate.dialect.Dialect;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.type.Type;
import org.slf4j.LoggerFactory;

import com.ifs.infra.AppConfig;

/**
 * @author AWAK001
 *
 */
public class ACMNewHiLoGenerator extends ACMHiLoGenerator {
	static org.slf4j.Logger logger = LoggerFactory.getLogger(ACMNewHiLoGenerator.class);
	
	public static final String SEQUENCE = "sequence";
	public static final int DEFAULT_NEW_MAX_LO_SIZE = 500;
	private String sequenceIdentifier;

	public void configure(Type type, Properties params, Dialect d) {
		this.sequenceIdentifier = ConfigurationHelper.getString("sequence", params, (String) null);
		this.query = "select next_value from " + d.appendLockHint(LockOptions.UPGRADE, "acm_hilo_generators")
				+ " where sequence_identifier = ? " + d.getForUpdateString();
		logger.info(this.query);
		this.update = "update acm_hilo_generators set next_value = ? where sequence_identifier = ? and next_value = ?";
		logger.info(this.update);
		this.maxLo = ConfigurationHelper.getInt("max_lo", params, 500);
		this.lo = this.maxLo + 1;
		this.returnClass = type.getReturnedClass();
		hiLoGeneratorList.add(this);
	}

	public String getIdentifier() {
		return this.sequenceIdentifier;
	}

	protected void prepareSelectSQL(PreparedStatement ps) throws SQLException {
		ps.setString(1, this.sequenceIdentifier);
	}

	protected void prepareUpdateSQL(PreparedStatement ps, long currentHiValue, long nextHiValue) throws SQLException {
		ps.setLong(1, nextHiValue);
		ps.setString(2, this.sequenceIdentifier);
		ps.setLong(3, currentHiValue);
	}
}