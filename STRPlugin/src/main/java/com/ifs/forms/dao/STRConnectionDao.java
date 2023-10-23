package com.ifs.forms.dao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.slf4j.LoggerFactory;

import com.actimize.infrastructure.plugin.api.Context;
import com.actimize.infrastructure.plugin.api.exceptions.PluginException;
import com.ifs.str.parts.AlertSTRCount;
import com.ifs.str.parts.CaseAlert;
import com.ifs.str.parts.PartABranch;
import com.ifs.str.parts.PartAReportingFI;
import com.ifs.str.parts.PartB1TransactionInitiation;
import com.ifs.str.parts.PartB2DispositionCompletion;
import com.ifs.str.parts.PartCDispositionAccount;
import com.ifs.str.parts.PartDTransactionConductor;
import com.ifs.str.parts.PartEOnBehalfOfEntity;
import com.ifs.str.parts.PartFOnBehalfOfIndividual;

public class STRConnectionDao {
	
	final static org.slf4j.Logger logger = LoggerFactory.getLogger(STRConnectionDao.class);
	
	private Context context;	
	
	public STRConnectionDao(Context con) {
		this.context = con;
	}
	
	
//	private static String FIND_ALERTS_BY_CASE_ID = " select" + 
//			"	child.alert_internal_id alertInternalId," + 
//			"	CAS.alert_internal_id caseInternalId," + 
//			"	child.alert_id alertId," + 
//			"	CAS.alert_id caseId" + 
//			" from" + 
//			"	ACTONE..v_acm_relations acm WITH (NOLOCK)," + 
//			"	ACTONE..alerts CAS WITH (NOLOCK)," + 
//			"	ACTONE..alerts child  WITH (NOLOCK)  " + 
//			" where" + 
//			"	CAS.alert_internal_id = acm.parent_join_id" + 
//			"	and CAS.alert_id = ? " + 
//			"	and acm.child_join_id = child.alert_internal_id";
	
	
//	private static String FIND_CASE_BY_CASE_ID = " select" + 			
//			"	CAS.alert_internal_id caseInternalId," + 			
//			"	CAS.alert_id caseId" + 
//			" from" + 
//			"	ACTONE..alerts CAS WITH (NOLOCK)" + 
//			" where" + 
//			"	CAS.alert_id = ? " ;
	
	
	private static String FIND_CASE_BY_CASE_ID = " SELECT " +
			" a.alert_internal_id caseInternalId , typ.alert_type_identifier FROM " +
			" ACTONE.dbo.alerts a(nolock), ACTONE.dbo.acm_md_alert_types typ (nolock) where alert_id in(?) " +
			" and a.alert_type_internal_id = typ.alert_type_internal_id	and typ.alert_type_identifier = 'BNC_CASE_ITEM' ";
	
	/**
	 * SQL query approach to pull data using DB connection object.
	 * @return List of WIStatusConfig objects which has the Case Step mapping.
	 */
	public List<CaseAlert> getAlertsByCaseID(String caseID) {
		logger.debug("Enter getAlertsByCaseID");
		List<CaseAlert> caseAlerts = new ArrayList<CaseAlert>();
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = new ConnectionManager(context).getIMPLConnection();
			logger.debug(FIND_CASE_BY_CASE_ID);
			logger.debug("Input ---->"+caseID);
			preparedStatement = conn.prepareStatement(FIND_CASE_BY_CASE_ID);
			
			preparedStatement.setString(1, caseID);
			rs = preparedStatement.executeQuery();			
			while (rs.next()) {					
				CaseAlert caseAlert = new CaseAlert();
				caseAlert = (CaseAlert)setAllSetters(caseAlert,rs);
				caseAlerts.add(caseAlert);
			}			
		} catch (PluginException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			closeConnection(rs, preparedStatement, null, null, conn);
			
		}
		return caseAlerts;
	}
	
	private static String VALIDATE_CASE_ID = "SELECT " +
		" a.alert_internal_id caseInternalID, typ.alert_type_identifier FROM " +
		" ACTONE.dbo.alerts a(nolock), ACTONE.dbo.acm_md_alert_types typ (nolock) where alert_internal_id in(?) " +
	    " and a.alert_type_internal_id = typ.alert_type_internal_id	and typ.alert_type_identifier = 'BNC_CASE_ITEM' ";
	
	public Boolean validateCaseID(Long inputCaseID) {
		Boolean validCase = Boolean.FALSE;		
		logger.error("Enter validateFormAlertID");		
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = new ConnectionManager(context).getIMPLConnection();
			logger.debug(VALIDATE_CASE_ID);
			logger.debug("Input ---->"+inputCaseID);
			preparedStatement = conn.prepareStatement(VALIDATE_CASE_ID);
			preparedStatement.setLong(1, inputCaseID);
			rs = preparedStatement.executeQuery();
			Long outputCaseID = 0L;
			while (rs.next()) {					
				outputCaseID = rs.getLong("caseInternalID");
			}
			logger.debug("Output ---->"+outputCaseID);
			if (outputCaseID.equals(inputCaseID)) {
				validCase = Boolean.TRUE;
			}
			logger.debug("RETURN VALUE --> "+validCase);
		} catch (PluginException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			closeConnection(rs, preparedStatement, null, null, conn);
			
		}		
		logger.debug("Exit validateFormAlertID");
		return validCase;
	}
	
	
	
	
	private static String BRANCHKEY_BYALERTID = " Select distinct" + 
			"				T.Custom_Small_String_15 branchKey," + 
			"				AT.ALERT_ID alertID" + 
			"			 from" + 
			"				IMPL..ALERTED_TRANSACTIONS AT" + 
			"			 JOIN UDM.UDM_CDS.V_TRANSACTIONS T  WITH (NOLOCK) ON" + 
			"				AT.ALERT_ID in (select " + 
			"								child.alert_id " + 
			"							 from" + 
			"								ACTONE..v_acm_relations acm WITH (NOLOCK)," + 
			"								ACTONE..alerts CAS WITH (NOLOCK)," + 
			"								ACTONE..alerts child  WITH (NOLOCK)  " + 
			"							 where" + 
			"								CAS.alert_internal_id = acm.parent_join_id" + 
			"								and CAS.alert_internal_id = ? " + 
			"								and acm.child_join_id = child.alert_internal_id" + 
			"				)" + 
			"				AND AT.TRANSACTION_ID = T.TRANSACTION_KEY  " + 
			"			    AND T.TENANT_CD = 'N/A'" + 
			"				AND AT.LINK_FOR_STR = 'Y'";
	
	
	/**
	 * SQL query approach to pull data using DB connection object.
	 * @return List of WIStatusConfig objects which has the Case Step mapping.
	 */
	public List<AlertSTRCount> getSTRCountByAlertID(String caseInternalID) {
		
		List<AlertSTRCount> alertSTRCounts = new ArrayList<AlertSTRCount>();
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = new ConnectionManager(context).getIMPLConnection();
			logger.debug(caseInternalID);
			logger.debug(BRANCHKEY_BYALERTID);		
			preparedStatement = conn.prepareStatement(BRANCHKEY_BYALERTID);
			preparedStatement.setString(1, caseInternalID);
			rs = preparedStatement.executeQuery();			
			while (rs.next()) {					
				AlertSTRCount alertSTR = new AlertSTRCount();
				alertSTR = (AlertSTRCount)setAllSetters(alertSTR,rs);
				alertSTRCounts.add(alertSTR);
			}			
		} catch (PluginException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			closeConnection(rs, preparedStatement, null, null, conn);
			
		}
		return alertSTRCounts;
	}
	
	
	private static String PARTA_REPORTING_BY_ALERT_ID = " select alert_id, " + 
			"CASE WHEN bunit_identifier = 'BNC_CA_TM_WM' " + 
			"THEN  " + 
			"'0085383'  ELSE '0001049' " + 
			"END AS fiId, " + 
			"CASE WHEN bunit_identifier = 'BNC_CA_TM_WM' " + 
			"THEN " + 
			"'Financière Banque Nationale' ELSE 'Banque Nationale du Canada' " + 
			"END AS fiName, " + 
			"CASE WHEN bunit_identifier = 'BNC_CA_TM_WM' " + 
			"THEN 'O' ELSE 'B' " + 
			"END AS fiType, bunit_identifier caseBU " + 
			"FROM  " + 
			"ACTONE..alerts WITH (NOLOCK) where alert_internal_id in (?) ";
	
	
	/**
	 * SQL query approach to pull data using DB connection object.
	 * @return List of WIStatusConfig objects which has the Case Step mapping.
	 */
	public PartAReportingFI getPartAReportingFI(String caseID) {
		
		List<PartAReportingFI> partAReportingFIS = new ArrayList<PartAReportingFI>();
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = new ConnectionManager(context).getIMPLConnection();
			logger.debug(PARTA_REPORTING_BY_ALERT_ID);
			preparedStatement = conn.prepareStatement(PARTA_REPORTING_BY_ALERT_ID);
			preparedStatement.setString(1, caseID);
			rs = preparedStatement.executeQuery();			
			while (rs.next()) {					
				PartAReportingFI partAReportingFI = new PartAReportingFI();
				partAReportingFI = (PartAReportingFI)setAllSetters(partAReportingFI,rs);
				partAReportingFIS.add(partAReportingFI);
			}			
		} catch (PluginException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			closeConnection(rs, preparedStatement, null, null, conn);
			
		}
		return partAReportingFIS.get(0);
	}
	
	private static String PARTABRANCH_QUERY_1 = " SELECT " +
			//+ "B.BRANCH_NUMBER branchKey, "  
			" B.BRANCH_NUMBER branchLocationNo, " + 
			" B.BRANCH_NAME branchName, " + 
			" case when A.ADDRESS_LINE_1 is null then COALESCE(A.ADDRESS_LINE_2,'') " +
			" when A.ADDRESS_LINE_1 is not null then COALESCE(A.ADDRESS_LINE_1, " +
			" '')+ ', ' + COALESCE(A.ADDRESS_LINE_2," +
			" '') end as branchAd," + 
			" A.CITY branchCity, " + 
			" S.STATE_PROVINCE_NAME branchState," + 
			" A.ZIP_POSTCODE branchZipcode, " + 
			" B.CONTACT_LAST_NAME contactLastName, " + 
			" B.CONTACT_FIRST_NAME contactFirstName, " + 
			" B.CONTACT_MIDDLE_NAME contactMiddleName, " + 
			" SUBSTRING(P.PHONE_NUMBER,1,3) contactAreaCode, " + 
			" SUBSTRING(P.PHONE_NUMBER,5,8) contactPhoneNumber, " + 
//			" P.PHONE_AREA_CODE contactAreaCode , " + 
//			" P.PHONE_NUMBER contactPhoneNumber , " + 
			" P.PHONE_NUMBER_EXT contactPhoneExt" + 
			" FROM UDM.UDM_CDS.V_BRANCH B" + 
			" OUTER APPLY (SELECT top 1 * FROM  UDM.UDM_CDS.V_ENTITY_ADDRESS_RELATION " + 
			" WHERE ENTITY_TYPE_CD = 'BRANCH' AND COALESCE(IS_TO_BE_DELETED, 0) = 0 AND (EXPIRATION_DATE IS NULL OR EXPIRATION_DATE > GETDATE() ) AND ADDRESS_RELATION_TYPE_CD = 'BRANCH' AND REF_ENTITY_SK = B.ENTITY_SK ORDER BY EFFECTIVE_DATE DESC, EXPIRATION_DATE DESC) AS EAR" + 
			" LEFT OUTER JOIN UDM.UDM_CDS.V_ADDRESS A" + 
			"    ON EAR.ADDRESS_SK = A.ENTITY_SK" + 
			"    AND COALESCE(A.IS_TO_BE_DELETED,0) = 0" + 
			" LEFT OUTER JOIN UDM.UDM_CDS.V_STATE_PROVINCE S " + 
			"    ON A.STATE_PROVINCE_SK = S.ENTITY_SK" + 
			" OUTER APPLY (SELECT top 1 * FROM UDM.UDM_CDS.V_ENTITY_PHONE_RELATION " + 
			" WHERE ENTITY_TYPE_CD = 'BRANCH' AND COALESCE(IS_TO_BE_DELETED, 0) = 0 AND (EXPIRATION_DATE IS NULL OR EXPIRATION_DATE > GETDATE()) AND PHONE_RELATION_TYPE_CD = 'BRANCH' AND REF_ENTITY_SK = B.ENTITY_SK ORDER BY EFFECTIVE_DATE DESC, EXPIRATION_DATE DESC) EPR" + 
			" LEFT OUTER JOIN UDM.UDM_CDS.V_PHONE P " + 
			"    ON EPR.PHONE_SK = P.ENTITY_SK" + 
			"    AND COALESCE(P.IS_TO_BE_DELETED,0) = 0" + 
			" WHERE B.BRANCH_NUMBER in (  "
			;
	
		private static String PARTABRANCH_QUERY_2 = ") AND B.EXPIRATION_DATE = '9999-12-31' ";
	
	
	/**
	 * SQL query approach to pull data using DB connection object.
	 * @return List of WIStatusConfig objects which has the Case Step mapping.
	 */
	public HashMap<String,PartABranch> getPartABranch(Set<String> branchNumbers) {
		HashMap<String,PartABranch> branchMap = new HashMap<String, PartABranch>();
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		PartABranch partABranch = null;
		try {
			conn = new ConnectionManager(context).getIMPLConnection();
			String PARTABRANCH_QUERY = PARTABRANCH_QUERY_1 + INCLAUSE(branchNumbers)+ PARTABRANCH_QUERY_2;
			logger.debug(PARTABRANCH_QUERY);
			preparedStatement = conn.prepareStatement(PARTABRANCH_QUERY);			
			rs = preparedStatement.executeQuery();			
			while (rs.next()) {
				partABranch = new PartABranch();
				partABranch = (PartABranch)setAllSetters(partABranch,rs);
				branchMap.put(partABranch.getBranchLocationNo(),partABranch);
			}			
		} catch (PluginException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			closeConnection(rs, preparedStatement, null, null, conn);
			
		}
		return branchMap;
	}
	
	private static String TRANSACTION_QUERY_1 = "SELECT" + 
			"   DISTINCT P.PARTY_KEY partyKey," + 
			"	AT.CONDUCTOR_ID conductorKey, " + 
			"	T.Custom_Small_String_15 branchKey," + 
			"	T.TRANSACTION_KEY transactionKey, TD.CR_DB + ':' + TD.DESCRIPTION  transactionCode, TD.DESCRIPTION, " + 
			"	format(T.EXECUTION_GLOBAL_DATE_TIME,'yyyy-MM-dd HH:mm:ss') txnDate ," + 
			"	format(T.EXECUTION_GLOBAL_DATE_TIME,'hh:mm tt')  txnTime ," +  
			"	format(T.CUSTOM_DATE_01 ,'yyyy-MM-dd HH:mm:ss')txnPostingDate ," + 
			"	abs(cast(round(T.ORIG_CURR_AMOUNT,2) as decimal(12,2))) txnAmount ," + 
			"	T.ORIG_CURR_CD txnCurrencyCode ," + 
			"	T.SAM_TRANS_TYPE_CD TRANSACTION_TYPE ," + 
			"	T.TRANSACTION_CODE_CD MEDIA ," + 
			"	T.CHANNEL_CD TRANSACTION_CHANNEL ," + 
			"	T.Custom_Small_String_15 TRANSACTION_BRANCH_ID ," + 
			"	T.ACCOUNT_KEY ACCOUNT_ID ," + 
			"	case when T.CUSTOM_SMALL_STRING_06 = 'Yes' then '1' " + 
			"		 when T.CUSTOM_SMALL_STRING_06 = 'Y' then '1' " + 
			" 		 when T.CUSTOM_SMALL_STRING_06 = 'N' then '0' " + 
			"  		 when T.CUSTOM_SMALL_STRING_06 = 'No' then '0' " + 
			"  	end as " + 
			"	txnNightDeposit," + 
			//"	T.CUSTOM_SMALL_STRING_11 txnInitialFundType ," + 
			
			//" 	FUND.FUND_TYPE_CODE txnInitialFundType, " +
			//"	T.CUSTOM_SMALL_STRING_12 txnInitialFundDesc ," + 
			//"	T.CUSTOM_SMALL_STRING_04 txnConductedType ," + 
			//"	COND.CODE txnConductedType ," +

			//"	T.CUSTOM_SMALL_STRING_05 txnConductedTypeDesc ," +  
			//"	T.CUSTOM_SMALL_STRING_08 txnInitialFundOtherAccNo ," + 
			//"	T.CUSTOM_SMALL_STRING_02 SCOPE ," + 
			//"	T.CUSTOM_SMALL_STRING_03 INTER_ACCESS ," + 
			"	T.OPP_ACCOUNT_KEY BENEFICIARY_ACCOUNT_ID ," + 
			"	T.OPP_COUNTRY_CD COUNTRY ," + 
			"	T.OPP_ORGANIZATION_KEY ORGANIZATION_UNIT ," + 
			"	T.SOURCE_SYSTEM_CD SOURCE" + 
			" FROM " + 
			"	IMPL..ALERTED_TRANSACTIONS AT" + 
			" JOIN UDM.UDM_CDS.V_TRANSACTIONS T ON" + 
			"	AT.ALERT_ID IN ( ";
				
			private static String TRANSACTION_QUERY_2 = ") " +
			" AND COALESCE(T.Custom_Small_String_15,'') = ? "+ 
			"	AND AT.LINK_FOR_STR = 'Y'" + 
			"	AND AT.TRANSACTION_ID = T.TRANSACTION_KEY AND T.TENANT_CD = 'N/A' " + 
			"	LEFT JOIN UDM.UDM_CDS.V_ACCOUNT A" + 
			"	ON A.ACCOUNT_KEY = T.ACCOUNT_KEY" + 
			"	LEFT JOIN UDM.UDM_CDS.V_PARTY P" + 
			"	ON A.PRIMARY_PARTY_KEY = P.PARTY_KEY LEFT JOIN UDM.UDM_CDS.V_TRANSACTION_CODE TD ON TD.TRANSACTION_CODE_CD = T.TRANSACTION_CODE_CD" +
			" ORDER BY TD.DESCRIPTION " 
//			"   LEFT JOIN IMPL.dbo.FUND_TYPE_MAPPING FUND ON " + 
//			"	T.CUSTOM_SMALL_STRING_11 = FUND.FUND_TYPE_DESC " + 
//			"   LEFT JOIN IMPL.dbo.TRANSACTION_CONDUCTED_TYPE COND ON " + 
//			"	T.CUSTOM_SMALL_STRING_04 = COND.CONDUCTED_DESC "    
			;
	
	/**
	 * SQL query approach to pull data using DB connection object.
	 */
	public LinkedList<PartB1TransactionInitiation> getPartB1Transaction(String alertID, String branchKey) {
		
		LinkedList<PartB1TransactionInitiation> partB1Transactions = new LinkedList<PartB1TransactionInitiation>();
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = new ConnectionManager(context).getIMPLConnection();
			String TRANSACTION_QUERY = TRANSACTION_QUERY_1 + alertID + TRANSACTION_QUERY_2;
			logger.debug(TRANSACTION_QUERY);
			preparedStatement = conn.prepareStatement(TRANSACTION_QUERY);
			logger.debug("BRANCH KEY --> "+ branchKey);
			if (branchKey == null) {
				preparedStatement.setString(1, "");
			} else {
				preparedStatement.setString(1, branchKey);
			}

			rs = preparedStatement.executeQuery();			
			while (rs.next()) {					
				PartB1TransactionInitiation partB1Transaction = new PartB1TransactionInitiation();
				partB1Transaction = (PartB1TransactionInitiation)setAllSetters(partB1Transaction,rs);
				partB1Transactions.add(partB1Transaction);
			}
		} catch (PluginException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			closeConnection(rs, preparedStatement, null, null, conn);
		}
		return partB1Transactions;
	}
	
	/**
	 * 
	 */
	private static String PARTB2_DISPOSITION_INITIAL_1 = " SELECT DISTINCT " + 
			" T.TRANSACTION_KEY transactionKey " + 
			" ,abs(cast(round(T.ORIG_CURR_AMOUNT,2) as decimal(12,2)))  txnDispositionAmount" + 
			" ,T.ORIG_CURR_CD txnDispositionCurrencyCode" + 
			" from " + 
			" IMPL..ALERTED_TRANSACTIONS AT" + 
			" JOIN UDM.UDM_CDS.V_TRANSACTIONS T WITH (NOLOCK) " + 
			" ON AT.TRANSACTION_ID in (";
	
	private static String PARTB2_DISPOSITION_INITIAL_2 = ") AND AT.ALERT_ID IN ( ";
	
	private static String PARTB2_DISPOSITION_INITIAL_3 = "  ) " + 
			" AND AT.TRANSACTION_ID = T.TRANSACTION_KEY AND T.TENANT_CD = 'N/A' " + 
			" AND AT.LINK_FOR_STR = 'Y'" + 
			" JOIN UDM.UDM_CDS.V_ACCOUNT A" + 
			" ON A.ACCOUNT_KEY = T.ACCOUNT_KEY";
	
	private static String PARTB2_DISPOSITION_COMPLETE = "select distinct txn.TRANSACTION_KEY,ch.CHANNEL_CD F_CHANNEL " + 
			" ,txn.CUSTOM_SMALL_STRING_09 as D_MEDIA " + 
			" ,stt.TRANSACTION_TYPE_CD C_TRANS_TYPE " + 
			" ,pt.PARTY_TYPE_CD G_PARTY_TYPE " + 
			" ,br.BRANCH_KEY H_BRANCH " + 
			" ,txn.CUSTOM_SMALL_STRING_18 as ACTUAL_MEDIA " + 
			" from UDM.UDM_CDS.TRANSACTIONS txn " + 
			" left join UDM.UDM_CDS.CHANNEL ch on txn.CHANNEL_SK = ch.ENTITY_SK " + 
			" left join UDM.UDM_CDS.SAM_TRANS_TYPE stt on txn.SAM_TRANS_TYPE_SK = stt.ENTITY_SK " + 
			" inner join UDM.UDM_CDS.ACCOUNT acc on txn.ACCOUNT_SK = acc.ENTITY_SK AND txn.TENANT_CD = 'N/A' " + 
			" inner join UDM.UDM_CDS.PARTY par on acc.PRIMARY_PARTY_SK = par.ENTITY_SK " + 
			" left join UDM.UDM_CDS.PARTY_TYPE pt on par.PARTY_TYPE_SK = pt.ENTITY_SK " + 
			" left join UDM.UDM_CDS.BRANCH br on txn.EXECUTION_BRANCH_SK = br.ENTITY_SK " + 
			" where txn.TRANSACTION_KEY = ? ";
	
	/**
	 * SQL query approach to pull data using DB connection object.
	 */
	public HashMap<String, List<PartB2DispositionCompletion>> getPartB2DispositionComplete(HashSet<String>transactionKeys , String alertIDS) {
		HashMap<String, List<PartB2DispositionCompletion>>  partB2DispositionsMap = new HashMap<String, List<PartB2DispositionCompletion>>();
		
		
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStat = null;
		try {
			conn = new ConnectionManager(context).getIMPLConnection();
			
			String PARTB2_DISPOSITION_INITIAL = PARTB2_DISPOSITION_INITIAL_1 + INCLAUSE(transactionKeys) + PARTB2_DISPOSITION_INITIAL_2 + alertIDS + PARTB2_DISPOSITION_INITIAL_3;
			logger.debug(PARTB2_DISPOSITION_INITIAL);
			
			preparedStatement = conn.prepareStatement(PARTB2_DISPOSITION_INITIAL);
			
			rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				PartB2DispositionCompletion partB2DispostionComplete = new PartB2DispositionCompletion();
				partB2DispostionComplete = (PartB2DispositionCompletion)setAllSetters(partB2DispostionComplete,rs);				
				if (!partB2DispositionsMap.containsKey(partB2DispostionComplete.getTransactionKey())) {
					List<PartB2DispositionCompletion> tempList = new ArrayList<PartB2DispositionCompletion>();
					partB2DispositionsMap.put(partB2DispostionComplete.getTransactionKey(),tempList);
				}
				partB2DispositionsMap.get(partB2DispostionComplete.getTransactionKey()).add(partB2DispostionComplete);

			}

			//			preparedStat = conn.prepareStatement(PARTB2_DISPOSITION_COMPLETE);
			//			preparedStat.setString(1, transactionKey);
			//			rs = preparedStat.executeQuery();
			//			while (rs.next()) {					
			//				partB2DispostionComplete = (PartB2DispositionCompletion)setAllSetters(partB2DispostionComplete,rs);
			//			}
			
			
		} catch (PluginException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			closeConnection(null, preparedStat, null, null, null);
			closeConnection(rs, preparedStatement, null, null, conn);			
		}
		return partB2DispositionsMap;
	}
	
	
	/**
	 * 
	 */
	private static String PARTC_DISPOSITION_ACCOUNT_1 = "	SELECT distinct T.TRANSACTION_KEY,"
//			+ "A.ACCOUNT_KEY instTransitAccNo, "  
			+ "CASE WHEN a.source_system_cd = '0025' THEN SUBSTRING(a.account_number,10,7) " + 
			"			ELSE " + 
			"			A.ACCOUNT_NUMBER " + 
			"			END accNo " + 
			" ,A.custom_small_string_04 accBranchNo " + 
			",case when P.PARTY_TYPE_CD = 'I' then 'A' " + 
			"			  when  P.PARTY_TYPE_CD = 'O' then 'B' " + 
			"			  end accType	 " + 
			",A.ACCT_CURR_CD accCurrencyCode " + 
			",case when A.ACCOUNT_TYPE_CD = 'CR/LIN' then '' else format(A.EFFECTIVE_DATE,'yyyy-MM-dd') end as accDateOpened " + 
			// AML-2283 - C7 Rule: if value = "1900-01-01" or value = "9999-12-31" then value = ""
			",case when A.ACCOUNT_TYPE_CD = 'CR/LIN' then '' "+
			" when format(A.EXPIRATION_DATE,'yyyy-MM-dd') = '1900-01-01' then '' " + 
			" when format(A.EXPIRATION_DATE,'yyyy-MM-dd') = '9999-12-31' then '' " + 
			" else format(A.EXPIRATION_DATE,'yyyy-MM-dd')  end as accDateClosed " + 
			",case when A.IS_ACTIVE = '1' then 'A' " + //A - ACTIVE, B - INACTIVE , C- DORMANT
			"  	   when A.IS_ACTIVE = '0' then 'A' " + // AML-2283 - C8 Rule: if value = "Inactive" then value = "Active". Even if the account is inactive, make the account active.
			" end as accStatus  " + 
			",IMPL.dbo.getPartCAccountHolder(T.TRANSACTION_KEY, 1) AS accHolderNameA " + 
			",IMPL.dbo.getPartCAccountHolder(T.TRANSACTION_KEY, 2) AS accHolderNameB " + 
			",IMPL.dbo.getPartCAccountHolder(T.TRANSACTION_KEY, 3) AS accHolderNameC " + 
			"from  " + 
			"IMPL.DBO.ALERTED_TRANSACTIONS AT " + 
			"JOIN UDM.UDM_CDS.V_TRANSACTIONS T WITH (NOLOCK) " + 
			"ON AT.TRANSACTION_ID in( ";
			
			private static String PARTC_DISPOSITION_ACCOUNT_2 = " )" + 
			"AND AT.TRANSACTION_ID = T.TRANSACTION_KEY " + 
			"AND AT.LINK_FOR_STR = 'Y' AND T.TENANT_CD = 'N/A' " + 
			"LEFT JOIN UDM.UDM_CDS.V_ACCOUNT A " + 
			"ON A.ACCOUNT_KEY = T.ACCOUNT_KEY " +
			"LEFT JOIN UDM.UDM_CDS.V_PARTY P " + 
			" ON A.PRIMARY_PARTY_KEY = P.PARTY_KEY " +
			" WHERE P.PARTY_TYPE_CD NOT IN ('CB','NCBUS_CORR','NCIND_CORR') ";
	
	/**
	 * SQL query approach to pull data using DB connection object.
	 */
	public HashMap<String, PartCDispositionAccount>  getPartCDispostionAccount(HashSet<String> transactionKeys) {
		
		HashMap<String, PartCDispositionAccount> partCDispositionsAccountMap = new HashMap<String, PartCDispositionAccount>();
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		String 	PARTC_DISPOSITION_ACCOUNT ="";
		try {
			conn = new ConnectionManager(context).getIMPLConnection();
			PARTC_DISPOSITION_ACCOUNT = PARTC_DISPOSITION_ACCOUNT_1 +  INCLAUSE(transactionKeys) + PARTC_DISPOSITION_ACCOUNT_2;
			logger.debug(PARTC_DISPOSITION_ACCOUNT);
			preparedStatement = conn.prepareStatement(PARTC_DISPOSITION_ACCOUNT);
			rs = preparedStatement.executeQuery();			
			while (rs.next()) {					
				PartCDispositionAccount partCDispositionAccount = new PartCDispositionAccount();
				partCDispositionAccount = (PartCDispositionAccount)setAllSetters(partCDispositionAccount,rs);
				partCDispositionsAccountMap.put(rs.getString("TRANSACTION_KEY"),partCDispositionAccount);				
			}			
		} catch (PluginException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			closeConnection(rs, preparedStatement, null, null, conn);
		}
		return partCDispositionsAccountMap;
	}
	
	private static String PARTD_TRANSACTIONCONDUCTOR_1 = "SELECT P.PARTY_KEY partyKey, " + 
			"	P.FIRST_NAME suspectGivenName , " + 
			"	P.LAST_NAME suspectSurname , " + 
			"	COALESCE(P.NAME_PREFIX, " + 
			"	P.OTHER_NAME) suspectInitial , " + // This field will be over ridden
			"	format(P.BIRTH_INCORP_DATE,'yyyy-MM-dd') suspectBirthday , " + 
			"	CO.COUNTRY_CD suspectCountryOfCitizenship ," + 
			"	RES.COUNTRY_CD suspectCountryOfResidence , " + 			
 			"	SUBSTRING(PH.PHONE_NUMBER, 1, 3) suspectHomeAreaCode , " + 
			"	SUBSTRING(PH.PHONE_NUMBER, 4, 3) + '-' + SUBSTRING(PH.PHONE_NUMBER, 7, 4) suspectHomePhone , " + 
			"	case" + 
			"		when PA.ADDRESS_LINE_1 is null then COALESCE(PA.ADDRESS_LINE_2," + 
			"		'')" + 
			"		when PA.ADDRESS_LINE_1 is not null then COALESCE(PA.ADDRESS_LINE_1," + 
			"		'')+ ', ' + COALESCE(PA.ADDRESS_LINE_2," + 
			"		'')" + 
			"	end as SUSPECTADDRESS , " + 
			"	PA.CITY SUSPECTCITY , " + 
			"	PA.STATE_PROVINCE_CD suspectState , " + 
			"	PA.COUNTRY_CD suspectCountry , " + 
			"	PA.ZIP_POSTCODE suspectZipCode , " + 
			"	PD.DOCUMENT_TYPE_CD suspectIdentifier, " + 
			"	PD.DOCUMENT_DESCRIPTION suspectIdentifierDesc, " + 
			"	PD.DOCUMENT_NUMBER  suspectIdNo, " + 
			"	PD.ISSUE_STATE_PROVINCE_CD suspectIdIssueState, " + 
			"	PD.ISSUE_COUNTRY_CD suspectIdIssueCountry, " + 
			"	O.OCCUPATION_DESC suspectOccupation , " + 
			"	P.EMPLOYER_DETAILS suspectEmployer , " + 			
			"	case" + 
			"		when PEA.ADDRESS_LINE_1 is null then COALESCE(PEA.ADDRESS_LINE_2," + 
			"		'')" + 
			"		when PEA.ADDRESS_LINE_1 is not null then COALESCE(PEA.ADDRESS_LINE_1," + 
			"		'')+ ', ' + COALESCE(PEA.ADDRESS_LINE_2," + 
			"		'')" + 
			"	end as suspectEmployerAddress , " + 
			"	PEA.CITY suspectEmployerCity , " + 
			"	PEA.STATE_PROVINCE_CD suspectEmployerState , " + 
			"	PEA.COUNTRY_CD suspectEmployerCountry , " + 
			"	PEA.ZIP_POSTCODE suspectEmployerZipCode, SRC.source_system_key suspectClientNo,  " + 
			// Logic to be implemented later"	, PEH.PHONE_AREA_CODE suspectEmployerAreaCode , " + 
			// Logic to be implemented later			"	PEH.PHONE_NUMBER suspectEmployerPhone , " + 
			// Logic to be implemented later			"	PEH.PHONE_NUMBER_EXT suspectEmployerPhoneExt , " + 			
			"	SUBSTRING(PB.PHONE_NUMBER, 1, 3) suspectBusinessAreaCode , " + 
			"	SUBSTRING(PB.PHONE_NUMBER, 4, 3) + '-' + SUBSTRING(PB.PHONE_NUMBER, 7, 4) suspectBusinessPhone , " + 
			"	PB.PHONE_NUMBER_EXT suspectBusinessPhoneExt " + 
			"FROM " + 
			"	UDM.UDM_CDS.V_PARTY P  " + 
			"	OUTER APPLY (	SELECT TOP 1 PA.* FROM UDM.UDM_CDS.V_ADDRESS PA, UDM.UDM_CDS.V_ENTITY_ADDRESS_RELATION AR where AR.IS_PRIMARY = 1 and AR.ENTITY_TYPE_CD = 'PARTY' and AR.ADDRESS_RELATION_TYPE_CD = 'MAINADDR' 	AND COALESCE(AR.IS_TO_BE_DELETED, 0) = 0 AND AR.ADDRESS_SK = PA.ENTITY_SK AND AR.REF_ENTITY_SK = P.ENTITY_SK ORDER BY AR.EFFECTIVE_DATE DESC) PA " + 
			"	OUTER APPLY (	SELECT TOP 1 PA.* FROM UDM.UDM_CDS.V_ADDRESS PA, UDM.UDM_CDS.V_ENTITY_ADDRESS_RELATION AR where AR.IS_PRIMARY = 1 and AR.ENTITY_TYPE_CD = 'PARTY' and AR.ADDRESS_RELATION_TYPE_CD = 'EMPLOYER' 	AND COALESCE(AR.IS_TO_BE_DELETED, 0) = 0 AND AR.ADDRESS_SK = PA.ENTITY_SK AND AR.REF_ENTITY_SK = P.ENTITY_SK) PEA " + 
			"	OUTER APPLY (	SELECT TOP 1 case PD.DOCUMENT_TYPE_CD when 'PC' then 'A' when 'CAM' then 'C' when 'PS' then 'D' when 'CN' then 'B'	when 'CRP' then 'F'	else 'E' end as DOCUMENT_TYPE_CD, case PD.DOCUMENT_TYPE_CD when 'PC' then '' when 'CAM' then '' when 'PS' then '' when 'CN' then ''	when 'CRP' then ''	else  dt.DOCUMENT_TYPE_DESC end as DOCUMENT_DESCRIPTION, case dt.DOCUMENT_TYPE_DESC when 'NAS' then '' else PD.Document_Number end as DOCUMENT_NUMBER,PD.Issue_State_Province_cd ISSUE_STATE_PROVINCE_CD, PD.Issue_Country_Cd ISSUE_COUNTRY_CD, case PD.DOCUMENT_TYPE_CD when 'PC' then 1 when 'CAM' then 2 when 'PS' then 3 when 'CN' then 4 when 'CRP' then 5	when 'CAS' then 7 else 6 end document_rank	FROM UDM.UDM_CDS.V_DOCUMENT PD,	UDM.UDM_CDS.V_ENTITY_DOCUMENT_RELATION PR, UDM.UDM_CDS.DOCUMENT_TYPE DT	where PR.ENTITY_TYPE_CD = 'PARTY' AND PR.DOCUMENT_KEY = PD.DOCUMENT_KEY AND PR.REF_ENTITY_SK = P.ENTITY_SK AND PR.IS_TO_BE_DELETED = 0 AND PD.DOCUMENT_TYPE_CD = DT.DOCUMENT_TYPE_CD order by case PD.DOCUMENT_TYPE_CD when 'PC' then 1 when 'CAM' then 2 when 'PS' then 3 when 'CN' then 4 when 'CRP' then 5 when 'CAS' then 7 else 6 end,PR.ROW_UPDATE_DATE desc) PD  " + 
			"	OUTER APPLY (	SELECT TOP 1 PH.PHONE_NUMBER FROM UDM.UDM_CDS.V_PHONE PH,UDM.UDM_CDS.V_ENTITY_PHONE_RELATION PR WHERE P.PARTY_KEY = PR.ENTITY_KEY AND PR.ENTITY_TYPE_CD = 'PARTY' AND PR.PHONE_SK = PH.ENTITY_SK AND COALESCE(PR.IS_TO_BE_DELETED, 0) = 0 AND PR.PHONE_RELATION_TYPE_CD in ('PERS_PHONE',	'PERS_CELLPHONE','PERS_PAGER','PERS_TDD/TTY') AND PR.EXPIRATION_DATE = '9999-12-31' order by case PR.PHONE_RELATION_TYPE_CD when 'PERS_PHONE' then 1 when 'PERS_CELLPHONE' then 2 when 'PERS_PAGER' then 3 when 'PERS_TDD/TTY' then 4 end) PH  " + 
//			"	OUTER APPLY (	SELECT TOP 1 PH.* FROM UDM.UDM_CDS.V_PHONE PH, UDM.UDM_CDS.V_ENTITY_PHONE_RELATION PR WHERE P.PARTY_KEY = PR.ENTITY_KEY and PR.ENTITY_TYPE_CD = 'PARTY' AND PR.PHONE_SK = PH.ENTITY_SK AND PR.PHONE_RELATION_TYPE_CD  = 'EMPLOYER' AND COALESCE(PR.IS_TO_BE_DELETED, 0) = 0) PEH  " + 
			"	OUTER APPLY (	SELECT TOP 1 PH.PHONE_NUMBER,PH.PHONE_NUMBER_EXT FROM UDM.UDM_CDS.V_PHONE PH,UDM.UDM_CDS.V_ENTITY_PHONE_RELATION PR	WHERE P.PARTY_KEY = PR.ENTITY_KEY AND PR.ENTITY_TYPE_CD = 'PARTY' AND PR.PHONE_SK = PH.ENTITY_SK AND COALESCE(PR.IS_TO_BE_DELETED, 0) = 0 AND PR.PHONE_RELATION_TYPE_CD in ('PROF_PHONE','PROF_CELLPHONE','PROF_PAGER','PROF_TDD/TTY') AND PR.EXPIRATION_DATE = '9999-12-31' order by case PR.PHONE_RELATION_TYPE_CD	when 'PROF_PHONE' then 1 when 'PROF_CELLPHONE' then 2 when 'PROF_PAGER' then 3 when 'PROF_TDD/TTY' then 4 end) PB " + 
			"	OUTER APPLY (	SELECT TOP 1 C.* FROM UDM.UDM_CDS.V_COUNTRY C, UDM.UDM_CDS.V_ENTITY_COUNTRY_RELATION CR WHERE P.ENTITY_SK = CR.REF_ENTITY_SK and CR.ENTITY_TYPE_CD = 'PARTY' AND CR.COUNTRY_SK = C.ENTITY_SK AND CR.COUNTRY_RELATION_TYPE_CD  = 'CITIZENSHIP') CO " +
			"	OUTER APPLY (	SELECT TOP 1 C.* FROM UDM.UDM_CDS.V_COUNTRY C, UDM.UDM_CDS.V_ENTITY_COUNTRY_RELATION CR WHERE P.ENTITY_SK = CR.REF_ENTITY_SK and CR.ENTITY_TYPE_CD = 'PARTY' AND CR.COUNTRY_SK = C.ENTITY_SK AND CR.COUNTRY_RELATION_TYPE_CD  = 'DOMICILE') RES " +
			"	OUTER APPLY (	SELECT TOP 1 O.* FROM UDM.UDM_CDS.V_OCCUPATION O WHERE P.OCCUPATION_SK = O.ENTITY_SK ) O ";

			private static String PARTD_CLIENT_NUMBER_FCC = " OUTER APPLY ( select top 1 substring(source_system_key,1,12) source_system_key from IMPL.dbo.TB_BNC_PARTY_XREF_SRC_KEYS SRC  where SRC.PARTY_KEY = P.PARTY_KEY and SRC.Source_System_Code = '0068' and SRC.End_Date = '9999-12-31' order by SRC.source_system_key asc) SRC WHERE P.PARTY_KEY IN (";
			
			private static String PARTD_CLIENT_NUMBER_OSS = " OUTER APPLY ( select top 1 source_system_key from IMPL.dbo.TB_BNC_PARTY_XREF_SRC_KEYS SRC  where SRC.PARTY_KEY = P.PARTY_KEY and SRC.Source_System_Code =  '0796' and SRC.End_Date = '9999-12-31' order by SRC.source_system_key asc) SRC WHERE P.PARTY_KEY IN (" ;
						
			private static String PARTD_TRANSACTIONCONDUCTOR_2 =  ") ";

	/**
	 * SQL query approach to pull data using DB connection object.
	 */
	public HashMap<String, PartDTransactionConductor> getPartDConductor(HashSet<String> conductorKeys , String caseBU) {
		HashMap<String, PartDTransactionConductor> partDTransactionConductors = new HashMap<String, PartDTransactionConductor>();

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;		
		try {
			conn = new ConnectionManager(context).getIMPLConnection();	
			String PARTD_TRANSACTIONCONDUCTOR = "";
			if (caseBU != null && caseBU.equals("BNC_CA_TM_WM")) {
				PARTD_TRANSACTIONCONDUCTOR = PARTD_TRANSACTIONCONDUCTOR_1 + PARTD_CLIENT_NUMBER_OSS + INCLAUSE(conductorKeys) + PARTD_TRANSACTIONCONDUCTOR_2;
			} else {
				PARTD_TRANSACTIONCONDUCTOR = PARTD_TRANSACTIONCONDUCTOR_1 + PARTD_CLIENT_NUMBER_FCC + INCLAUSE(conductorKeys) + PARTD_TRANSACTIONCONDUCTOR_2;				
			}
			logger.debug(PARTD_TRANSACTIONCONDUCTOR);
			preparedStatement = conn.prepareStatement(PARTD_TRANSACTIONCONDUCTOR);
			rs = preparedStatement.executeQuery();			
			while (rs.next()) {					
				PartDTransactionConductor tempConductor = new PartDTransactionConductor();
				tempConductor = (PartDTransactionConductor)setAllSetters(tempConductor,rs);
				logger.debug(tempConductor.toString());
				partDTransactionConductors.put(tempConductor.getPartyKey(), tempConductor);
			}
			
		} catch (PluginException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			closeConnection(rs, preparedStatement, null, null, conn);			
		}
		return partDTransactionConductors;
	}
	
	/**
	 * 
	 */
	private static String PARTE_BEHALFOFENTITY = " SELECT " + 
			"	P.PARTY_KEY CLIENT_NUMBER, " + 
			"	P.CUSTOM_SMALL_STRING_04 businessType , " + 
			"	P.FIRST_NAME entityName , " + 
			"	P.BIRTH_INCORP_DATE DOB , " + 
			"	PD.DOCUMENT_NUMBER INCORPORATIONNO , " + 
			"	NULLIF(PA.ADDRESS_LINE_1, " + 
			"	'')+ ' ' + NULLIF(PA.ADDRESS_LINE_2, " + 
			"	'')+ ' ' + NULLIF(PA.ADDRESS_LINE_2, " + 
			"	'') entityAddress , " + 
			"	PA.CITY entityCity , " + 
			"	PA.STATE_PROVINCE_CD entityState , " + 
			"	PA.COUNTRY_CD entityCountry , " + 
			"	PA.ZIP_POSTCODE entityZipCode , " + 
			"	PD.ISSUE_STATE_PROVINCE_CD issueState , " + 
			"	PD.ISSUE_COUNTRY_CD issueCountry , " + 
			"	PB.PHONE_AREA_CODE ENTITYAREACODE , " + 
			"	PB.PHONE_NUMBER entityPhone , " + 
			"	PB.PHONE_NUMBER_EXT entityPhoneExt " + 
			"FROM " + 
			"	UDM.UDM_CDS.V_PARTY P " + 
			"  	OUTER APPLY (	SELECT TOP 1 PA.* FROM UDM.UDM_CDS.V_ADDRESS PA, UDM.UDM_CDS.V_ENTITY_ADDRESS_RELATION AR where AR.IS_PRIMARY = 1 AND P.PARTY_KEY = AR.ENTITY_KEY and AR.ENTITY_TYPE_CD = 'PARTY' 	AND COALESCE(AR.IS_TO_BE_DELETED, 0) = 0 and AR.ADDRESS_RELATION_TYPE_CD = 'DEFAULT' AND AR.ADDRESS_SK = PA.ENTITY_SK AND AR.REF_ENTITY_SK = P.ENTITY_SK) PA " + 
			"	OUTER APPLY (	SELECT TOP 1 PD.* FROM UDM.UDM_CDS.V_DOCUMENT PD, UDM.UDM_CDS.V_ENTITY_DOCUMENT_RELATION PR where P.PARTY_KEY = PR.ENTITY_KEY and PR.ENTITY_TYPE_CD = 'PARTY' AND PR.DOCUMENT_SK = PD.ENTITY_SK AND PR.IS_TO_BE_DELETED = 0 AND PR.REF_ENTITY_SK = P.ENTITY_SK AND DOCUMENT_TYPE_CD = 'EIN'	ORDER BY DOCUMENT_TYPE_CD) PD  " + 
			"	OUTER APPLY (	SELECT TOP 1 PH.* FROM UDM.UDM_CDS.V_PHONE PH, UDM.UDM_CDS.V_ENTITY_PHONE_RELATION PR WHERE P.PARTY_KEY = PR.ENTITY_KEY and PR.ENTITY_TYPE_CD = 'PARTY' AND PR.PHONE_SK = PH.ENTITY_SK AND PR.PHONE_RELATION_TYPE_CD  = 'HOME' AND COALESCE(PR.IS_TO_BE_DELETED, 0) = 0 ) PH  " + 
			"	OUTER APPLY (	SELECT TOP 1 PH.* FROM UDM.UDM_CDS.V_PHONE PH, UDM.UDM_CDS.V_ENTITY_PHONE_RELATION PR WHERE P.PARTY_KEY = PR.ENTITY_KEY and PR.ENTITY_TYPE_CD = 'PARTY' AND PR.PHONE_SK = PH.ENTITY_SK AND PR.PHONE_RELATION_TYPE_CD  = 'BUSINESS' AND COALESCE(PR.IS_TO_BE_DELETED, 0) = 0 ) PB" + 
		" WHERE P.PARTY_KEY IN (?) ";

	/**
	 * SQL query approach to pull data using DB connection object.
	 */
	public List<PartEOnBehalfOfEntity> getPartEPartyEntity(String partyID) {		
		List<PartEOnBehalfOfEntity> partEPartyEntities = new ArrayList<PartEOnBehalfOfEntity>();
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = new ConnectionManager(context).getIMPLConnection();			
			preparedStatement = conn.prepareStatement(PARTE_BEHALFOFENTITY);
			preparedStatement.setString(1, partyID);
			rs = preparedStatement.executeQuery();			
			while (rs.next()) {					
				PartEOnBehalfOfEntity partEPartyEntity = new PartEOnBehalfOfEntity();
				partEPartyEntity = (PartEOnBehalfOfEntity)setAllSetters(partEPartyEntity,rs);
				partEPartyEntities.add(partEPartyEntity);
			}			
		} catch (PluginException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			closeConnection(rs, preparedStatement, null, null, conn);
			
		}
		return partEPartyEntities;
	}
	
	/**
	 * 
	 */
	private static String PARTF_BEHALFOFINDIVIDUAL = " SELECT" + 
			"	P.PARTY_KEY CLIENT_NUMBER ," + 
			"	P.FIRST_NAME GIVENNAME ," + 
			"	P.LAST_NAME SURNAME ," + 
			"	NULLIF(PCOALESCEPREFIX," + 
			"	P.OTHER_NAME) INITIAL ," + 
			"	format(P.BIRTH_INCORP_DATE,'yyyy-MM-dd') individualBirthday ," + 
			"	CO.COUNTRY_CD countryOfCitizenship ," + 
			"	PA.COUNTRY_CD countryOfResidence ," + 
			"	PH.PHONE_AREA_CODE HOMEAREACODE ," + 
			"	PH.PHONE_NUMBER HOMEPHONE ," + 
			"	NULLIF(PA.ADDRESS_LINE_1," + 
			"	'')+ ' ' + NULLIF(PA.ADDRESS_LINE_2," + 
			"	'')+ ' ' + NULLIF(PA.ADDRESS_LINE_2," + 
			"	'') ADDRESS ," + 
			"	PA.CITY CITY ," + 
			"	PA.STATE_PROVINCE_CD STATE ," + 
			"	PA.COUNTRY_CD COUNTRY ," + 
			"	PA.ZIP_POSTCODE ZIPCODE ," + 
			"	PD.DOCUMENT_TYPE_CD individualIdentifier ," + 
			"	PD.DOCUMENT_DESCRIPTION individualIdentifierDesc ," + 
			"	PD.DOCUMENT_NUMBER individualIdNo ," + 
			"	PD.ISSUE_STATE_PROVINCE_CD issueState ," + 
			"	PD.ISSUE_COUNTRY_CD issueCountry ," + 
			"	P.OCCUPATION_CD individualOccupation ," + 
			"	P.EMPLOYER_DETAILS individualEmployer ," + 
			"	PB.PHONE_AREA_CODE BUSINESSAREACODE ," + 
			"	PB.PHONE_NUMBER BUSINESSPHONE ," + 
			"	PB.PHONE_NUMBER_EXT BUSINESSPHONEEXT" + 
			" FROM" + 
			"	UDM.UDM_CDS.V_PARTY P" + 
			"  	OUTER APPLY (	SELECT TOP 1 PA.* FROM UDM.UDM_CDS.V_ADDRESS PA, UDM.UDM_CDS.V_ENTITY_ADDRESS_RELATION AR where AR.IS_PRIMARY = 1 AND COALESCE(AR.IS_TO_BE_DELETED, 0) = 0 AND P.PARTY_KEY = AR.ENTITY_KEY and AR.ENTITY_TYPE_CD = 'PARTY' and AR.ADDRESS_RELATION_TYPE_CD = 'DEFAULT' AND AR.ADDRESS_SK = PA.ENTITY_SK AND AR.REF_ENTITY_SK = P.ENTITY_SK) PA" + 
			"	OUTER APPLY (	SELECT TOP 1 PD.* FROM UDM.UDM_CDS.V_DOCUMENT PD, UDM.UDM_CDS.V_ENTITY_DOCUMENT_RELATION PR where P.PARTY_KEY = PR.ENTITY_KEY and PR.ENTITY_TYPE_CD = 'PARTY' AND PR.DOCUMENT_SK = PD.ENTITY_SK AND PR.IS_TO_BE_DELETED = 0 AND PR.REF_ENTITY_SK = P.ENTITY_SK AND DOCUMENT_TYPE_CD = 'EIN'	ORDER BY DOCUMENT_TYPE_CD) PD " + 
			"	OUTER APPLY (	SELECT TOP 1 PH.* FROM UDM.UDM_CDS.V_PHONE PH, UDM.UDM_CDS.V_ENTITY_PHONE_RELATION PR WHERE P.PARTY_KEY = PR.ENTITY_KEY and PR.ENTITY_TYPE_CD = 'PARTY' AND PR.PHONE_SK = PH.ENTITY_SK AND PR.PHONE_RELATION_TYPE_CD  = 'HOME' AND COALESCE(PR.IS_TO_BE_DELETED, 0) = 0 ) PH " + 
			"	OUTER APPLY (	SELECT TOP 1 PH.* FROM UDM.UDM_CDS.V_PHONE PH, UDM.UDM_CDS.V_ENTITY_PHONE_RELATION PR WHERE P.PARTY_KEY = PR.ENTITY_KEY and PR.ENTITY_TYPE_CD = 'PARTY' AND PR.PHONE_SK = PH.ENTITY_SK AND PR.PHONE_RELATION_TYPE_CD  = 'BUSINESS' AND COALESCE(PR.IS_TO_BE_DELETED, 0) = 0 ) PB" + 
			"	OUTER APPLY (	SELECT TOP 1 C.* FROM UDM.UDM_CDS.V_COUNTRY C, UDM.UDM_CDS.V_ENTITY_COUNTRY_RELATION CR WHERE P.PARTY_KEY = CR.ENTITY_KEY and CR.ENTITY_TYPE_CD = 'PARTY' AND CR.COUNTRY_SK = C.ENTITY_SK AND CR.COUNTRY_RELATION_TYPE_CD  = 'CITIZENSHIP') CO" +
			" WHERE P.PARTY_KEY IN (?) ";

	
	
		/**
		 * SQL query approach to pull data using DB connection object.
		 */
	public List<PartFOnBehalfOfIndividual> getPartFPartyIndividual(String partyID) {
			
			List<PartFOnBehalfOfIndividual> partFOnBehalfOfIndividuals = new ArrayList<PartFOnBehalfOfIndividual>();
			Connection conn = null;
			ResultSet rs = null;
			PreparedStatement preparedStatement = null;
			try {
				conn = new ConnectionManager(context).getIMPLConnection();
				preparedStatement = conn.prepareStatement(PARTF_BEHALFOFINDIVIDUAL);
				preparedStatement.setString(1, partyID);
				rs = preparedStatement.executeQuery();			
				while (rs.next()) {					
					PartFOnBehalfOfIndividual partFOnBehalfIndividual = new PartFOnBehalfOfIndividual();
					partFOnBehalfIndividual = (PartFOnBehalfOfIndividual)setAllSetters(partFOnBehalfIndividual,rs);
					partFOnBehalfOfIndividuals.add(partFOnBehalfIndividual);
				}			
			} catch (PluginException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			} finally {
				closeConnection(rs, preparedStatement, null, null, conn);
				
			}
			return partFOnBehalfOfIndividuals;
		}
	
	/**
	 * Method to generate a FORM ID using a running sequence. This is used in the CONTENT ID and Description of the Form.
	 * @return Form ID number
	 */
	public String getSTRFormID() {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		String formID = "";
		try {
			conn = new ConnectionManager(context).getIMPLConnection();
			preparedStatement = conn.prepareStatement("SELECT NEXT VALUE FOR IMPL..STRFORMIDSEQUENCE AS ID");
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				formID = rs.getString("ID");
			}
		} catch (PluginException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			closeConnection(rs, preparedStatement, null, null, conn);

		}
		return formID;
		
	}
	private static String GET_USER_ID = "SELECT USER_JOIN_ID FROM ACTONE..V_ACM_USERS2 WHERE USER_IDENTIFIER = ?";
	
	/**
	 * Method to fetch the prmary_key of the logged in user identifier.	
	 * @param userIdentifier
	 * @return primaryKey of the logged in user.
	 */
	public Integer getUserID(String userIdentifier) {
		logger.debug("Start of getUserID ----> "+ userIdentifier);
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		Integer userInternalId = 0;
		try {
			conn = new ConnectionManager(context).getIMPLConnection();
			preparedStatement = conn.prepareStatement(GET_USER_ID);
			preparedStatement.setString(1, userIdentifier);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				userInternalId = rs.getInt("user_join_id");				
			}
		} catch (PluginException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			closeConnection(rs, preparedStatement, null, null, conn);

		}
		logger.info("End of getUserID ");
		return userInternalId;
	}
	
	private static String GET_STR_FORM_INTERNAL_ID = "select form_type_internal_id from ACTONE.dbo.acm_md_form_types where upper(identifier) = upper('STAR_FINTRAC_Form')";
				
	/**
	 * Method to fetch the prmary_key of the logged in user identifier.	
	 * @param userIdentifier
	 * @return primaryKey of the logged in user.
	 */
	public Integer getSTRTypeID() {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		Integer form_type_internal_id = 0;
		try {
			conn = new ConnectionManager(context).getIMPLConnection();
			preparedStatement = conn.prepareStatement(GET_STR_FORM_INTERNAL_ID);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				form_type_internal_id = rs.getInt("form_type_internal_id");
				logger.debug("STR FORM internal type ID ----> "+ form_type_internal_id);
			}
		} catch (PluginException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			closeConnection(rs, preparedStatement, null, null, conn);

		}
		return form_type_internal_id;
	}

	

    /**
     * Closes the connection.
     *
     * @param resultSet - Holds the ResultSet object.
     * @param preparedStatement - Holds the PreparedStatement object.
     * @param callableStatement - Holds the CallableStatement object.
     * @param statement - Holds the Statement object.
     * @param connection - Holds the Connection object.
     * @throws SQLException
     */
     private void closeConnection(final ResultSet resultSet,
            final PreparedStatement preparedStatement,
            final CallableStatement callableStatement,
            final Statement statement, final Connection connection) {
    	try {
	        if (resultSet != null) {
	            resultSet.close();
	        }
	        if (preparedStatement != null) {
	            preparedStatement.close();
	        }
	        if (statement != null) {
	            statement.close();
	        }
	        if (callableStatement != null) {
	            callableStatement.close();
	        }
	        if (connection != null) {
	            connection.close();
	        }
    	} catch (SQLException e) {
			logger.error(e.getMessage());
    		e.printStackTrace();
    	}
    }

    
	private Object setAllSetters(Object ob, ResultSet rs) throws SQLException {
	    // MZ: Find the correct method
	    Class cls = ob.getClass();	    
        for (java.lang.reflect.Field field : cls.getDeclaredFields()){
            for (Method method : cls.getMethods()) {
                if ((method.getName().startsWith("set")) && (method.getName().length() == (field.getName().length() + 3))) {
                    if (method.getName().toLowerCase().endsWith(field.getName().toLowerCase())) {
                        // MZ: Method found, run it
                        try {
                            method.setAccessible(true);
                            if(isThere(rs,field.getName().toLowerCase())) {
		                            if(field.getType().getSimpleName().toLowerCase().endsWith("integer"))
		                                method.invoke(ob,rs.getInt(field.getName().toLowerCase()));
		                            else if(field.getType().getSimpleName().toLowerCase().endsWith("long"))
		                                method.invoke(ob,rs.getLong(field.getName().toLowerCase()));
		                            else if(field.getType().getSimpleName().toLowerCase().endsWith("string"))
		                                method.invoke(ob,rs.getString(field.getName().toLowerCase()));
		                            else if(field.getType().getSimpleName().toLowerCase().endsWith("boolean"))
		                                method.invoke(ob,rs.getBoolean(field.getName().toLowerCase()));
		                            else if(field.getType().getSimpleName().toLowerCase().endsWith("timestamp"))
		                                method.invoke(ob,rs.getTimestamp(field.getName().toLowerCase()));
		                            else if(field.getType().getSimpleName().toLowerCase().endsWith("date"))
		                                method.invoke(ob,rs.getDate(field.getName().toLowerCase()));
		                            else if(field.getType().getSimpleName().toLowerCase().endsWith("double"))
		                                method.invoke(ob,rs.getDouble(field.getName().toLowerCase()));
		                            else if(field.getType().getSimpleName().toLowerCase().endsWith("float"))
		                                method.invoke(ob,rs.getFloat(field.getName().toLowerCase()));
		                            else if(field.getType().getSimpleName().toLowerCase().endsWith("time"))
		                                method.invoke(ob,rs.getTime(field.getName().toLowerCase()));
		                            else 
		                            	method.invoke(ob,rs.getObject(field.getName().toLowerCase()));
                            }
                        }
                        catch (IllegalAccessException | InvocationTargetException | SQLException e) {	                			
                            e.printStackTrace();
                        }
                    }
                }
            }
	    }
	    return ob;
	}
	
	private static boolean isThere(ResultSet rs, String column) {
		try {
			rs.findColumn(column);
			return true;
		} catch (SQLException sqlex) {
			// DO NOTHING
		}
		return false;
	}
	
	private static String INCLAUSE (Set<String> input) {
		String returnString  = "''";
		if (input.size() > 0) {
			returnString = "'";
			for (String str : input) {
				returnString = returnString + str + "','";
			}
			returnString = returnString.substring(0,returnString.length()-2);
		}		
		return returnString;
	}
}