package com.ifs.plugin.actone.str;

import java.io.File; 
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.actimize.infrastructure.plugin.api.exceptions.PluginException;
import com.ifs.infra.ConnectionManager;
import com.ifs.forms.dao.Form;
import com.ifs.forms.xml.ChildFieldSet;
import com.ifs.forms.xml.Field;
import com.ifs.forms.xml.FieldSet;
import com.ifs.forms.xml.FormDataRaw;
import com.ifs.forms.xml.Metadata;
import com.ifs.forms.xml.Property;
import com.ifs.forms.xml.SetType;
import com.ifs.fstream.FStream;
import com.ifs.infra.AppConfig;
import com.ifs.infra.AppConfigTest;
import com.ifs.str.STRData;
import com.ifs.str.parts.AlertSTRCount;
import com.ifs.str.parts.PartABranch;
import com.ifs.str.parts.PartAReportingFI;
import com.ifs.str.parts.PartB1TransactionInitiation;
import com.ifs.str.parts.PartB2DispositionCompletion;
import com.ifs.str.parts.PartCDispositionAccount;
import com.ifs.str.parts.PartDTransactionConductor;
import com.ifs.str.parts.PartEOnBehalfOfEntity;
import com.ifs.str.parts.PartFOnBehalfOfIndividual;
import com.ifs.str.parts.PartGDescription;
import com.ifs.str.parts.PartHAction;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class ApplicationControllerTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public ApplicationControllerTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(ApplicationControllerTest.class);
	}
	
	private void addTransaction(List<PartB1TransactionInitiation> transactions, int i) {
		PartB1TransactionInitiation initiation = new PartB1TransactionInitiation();
		initiation.setTxnAmount("10000"+i);
		initiation.setTxnCurrencyCode("CAD");
		
		List<PartB2DispositionCompletion> dispositions = new ArrayList<PartB2DispositionCompletion>();
		PartB2DispositionCompletion dispositionCompletion = new PartB2DispositionCompletion();
		dispositionCompletion.setTxnDispositionAmount("1000"+i);
		dispositionCompletion.setTxnDispositionCurrencyCode("USD");
		dispositions.add(dispositionCompletion);
		
		PartB2DispositionCompletion dispositionCompletion2 = new PartB2DispositionCompletion();
		dispositionCompletion2.setTxnDispositionAmount("999999"+i);
		dispositionCompletion2.setTxnDispositionCurrencyCode("INR");
		dispositions.add(dispositionCompletion2);
		
		initiation.addDispositions(dispositions);
		
		
		PartDTransactionConductor conductor = new PartDTransactionConductor();
		conductor.setSuspectClientNo("13246"+i);
		conductor.setSuspectGivenName("Louis"+i);
		initiation.addConductor(conductor);

		transactions.add(initiation);
		
	}
	
	public void atestClazz() {
		STRData strData = new STRData();
		PartAReportingFI reportingFI = new PartAReportingFI();
		reportingFI.setFiId("000001");
		reportingFI.setFiName("BNC Corp");
		strData.setReportingFI(reportingFI);
		PartABranch branch = new PartABranch();
		branch.setBranchKey("978365798");
		branch.setBranchName("downtown");
		
		strData.setBranch(branch);

		List<PartB1TransactionInitiation> transactions = new ArrayList<PartB1TransactionInitiation>();
		addTransaction(transactions, 1);
		addTransaction(transactions, 2);
		
				
		strData.setTransactions(transactions);
		
			
		PartGDescription description = new PartGDescription();
		description.setSuspiciousActivityDesc("This is a suspicious activity");
		strData.setDescription(description);
		
		PartHAction action = new PartHAction();
		action.setActionTakenDesc("The customer account has been closed");
		strData.setAction(action);
		
		
		FStream stream = new FStream();
		FormDataRaw dataRaw = stream.getDataRaw(strData);
	//	XStream xStream = AppConfig.getXStream();
	//	xml = xStream.toXML(dataRaw);
		//System.out.println(xml);
	}

	private String xml = null;
	
	public void testPrint() {
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		System.out.println("Current relative path is: " + s);
		
		File newFile = new File("test.txt"); 
		System.out.println(newFile.exists());
		File newFile1 = new File("../test/test.txt"); 
		System.out.println(newFile1.exists());
		File newFile3 = new File("../../test/test.txt"); 
		System.out.println(newFile3.exists());
		File newFile2 = new File(s+"/test/test.txt"); 
		System.out.println(newFile2.exists());
		
		
		System.out.println("Test Print");
		List<String> initialList = new ArrayList();
		initialList.add("INITIAL1");
		initialList.add("INITIAL2");
		String balaji = initialList.get(0);
		balaji = "FINAL1";
		for (String string : initialList) {
			System.out.println(string);
		}
		
	}
	
	
	/**
	 * Rigourous Test :-)
	 */
	public void atestApp() {
		assertTrue(true);
		// AppConfig appConfig = new AppConfig();
	//	XStream xStream = AppConfig.getXStream();
		FormDataRaw form = new FormDataRaw();

		Metadata metadata = new Metadata();
		metadata.addProperty(new Property("alertIdentifier", "0000002879"));
		metadata.addProperty(new Property("alertId", "0000002879"));
		form.addData(metadata);

		Field field = new Field();
		field.setString("str_fi_name", "BNC");
		form.addData(field);

		FieldSet transaction = new FieldSet();
		transaction.setDate("str_transaction_serial_1", "BNC", "MM/dd/yyyy", 1, SetType.transactions);
		form.addData(transaction);

		ChildFieldSet disposition = new ChildFieldSet();
		disposition.setDate("str_transaction_serial_1", "BNC", "MM/dd/yyyy", 1, SetType.transactions, 1,
				SetType.dispositions);
		form.addData(disposition);

		//String xml = xStream.toXML(form);
		//System.out.println(xml);

	}
	
	/**
	 * Rigourous Test :-)
	 */
	public void generateXML(String xml, String formID) {
		//testClazz();
		//ztestConnection();
		// AppConfig init = new AppConfig();
		Session session = null;
		try {
			session = new AppConfigTest().getSession();
			//Session session = AppConfig.getSessionFactory().withOptions().connection(connection).openSession();
			//SessionBuilder sb = SessionFactory.withOptions();
			//Session session = sb.connection(connection).openSession();
			
			System.out.println(session.isOpen());
			session.beginTransaction();
			Form form = new Form();
			//form.setInternalId(9999);
			form.setFormIdentifier(formID);
			form.setAlertInternalId(42139);
			form.setName("F"+formID);
			form.setStatus("In process");
			form.setFormTypeInternalId(3282302);
			form.setCreateDate(new Date());
			form.setCreateUser(1);
			form.setLastUpdateUser(1);
			form.setXml(xml);
			session.save(form);
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			if (session != null && session.getTransaction() != null)
				session.getTransaction().rollback();
		} finally {
			if (session != null)
				session.close();
		}

	}


	
	
	public void _testConnection() {
		InputStream input = AppConfig.class.getClassLoader().getResourceAsStream("config.properties");
		try {
			System.out.println(AppConfig.class.getClassLoader());
			System.out.println(new File("").getAbsolutePath());
			System.out.println(input.available());
			AppConfigTest.getSessionFactory();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String returnXML = "";
		List<AlertSTRCount>  alertSTRCounts = getSTRCountByAlertID("A2010030902869725");		
		for (AlertSTRCount alertSTRCount : alertSTRCounts) {
			STRData strData = new STRData();	
			
			//System.out.println(alertSTRCount.getBranchKey());
//			alertSTRCount.setBranchKey("2181");
			
			PartAReportingFI reportingFI = new PartAReportingFI();
			reportingFI.setFiId(""); // TODO			
			reportingFI.setFiName(""); // TODO
			strData.setReportingFI(reportingFI);
			
			System.out.println(" PART A BRANCH "+ alertSTRCount.getBranchKey());
			System.out.println();
			List<PartABranch> aBranchs = getPartABranch(alertSTRCount.getBranchKey());	
			strData.setBranch(aBranchs.get(0));
			
			
			System.out.println(" PART B1 TRANSACTION ");
			List<PartB1TransactionInitiation> b1TransactionInitiations = getPartB1Transaction(alertSTRCount.getAlertID(), alertSTRCount.getBranchKey());
			System.out.println("-------------------------------------------------------");
			System.out.println(b1TransactionInitiations.size());
			// For every Transaction
			for (PartB1TransactionInitiation partB1TransactionInitiation : b1TransactionInitiations) {
				
				System.out.println(partB1TransactionInitiation);
				String partyIDForTransaction = "";
				partyIDForTransaction = partB1TransactionInitiation.getPartyKey();
				if (partB1TransactionInitiation.getConductorKey() != null && !partB1TransactionInitiation.getConductorKey().equalsIgnoreCase("")) {
					partyIDForTransaction = partB1TransactionInitiation.getConductorKey();
				}
				System.out.println(" PART D CDONDUCTOR BRANCH ");
				List<PartDTransactionConductor> conductors = new ArrayList<PartDTransactionConductor>();
				conductors = getPartDConductor(partyIDForTransaction);
				System.out.println(conductors.size());				
				for (PartDTransactionConductor partDTransactionConductor : conductors) {
					System.out.println(partDTransactionConductor);
				}
				partB1TransactionInitiation.addConductor(conductors.get(0));
				System.out.println("-------------------------------------------------------");
				
				System.out.println("PART B2 DISPOSITION");
				List<PartB2DispositionCompletion> b2DispositionCompletions = new ArrayList<PartB2DispositionCompletion>();
				b2DispositionCompletions = getPartB2DispositionComplete(partB1TransactionInitiation.getTransactionKey(), alertSTRCount.getAlertID());
				System.out.println("-------------------------------------------------------");
				//System.out.println("----->"+b2DispositionCompletions.size());
				
				// For 4 scenario Part C is mandatory
				System.out.println(" PART C DISPOSITION ACCOUNT");
				List<PartCDispositionAccount> cDispositionAccounts = new ArrayList();
				cDispositionAccounts = getPartCDispostionAccount(partB1TransactionInitiation.getTransactionKey());
				b2DispositionCompletions.get(0).addCDispositionAccount(cDispositionAccounts.get(0));
				System.out.println("-------------------------------------------------------");
				// System.out.println("-->"+cDispositionAccounts.size());
				for (PartCDispositionAccount partCDispositionAccount : cDispositionAccounts) {
					//System.out.println(partCDispositionAccount);
				}
				
				String partB2SectionDecider = getStringValue(b2DispositionCompletions.get(0).getTxnDispositionOnBehalfIndicator());
				
				if (partB2SectionDecider.equalsIgnoreCase("C")) { // Not Applicable	
					// NO PART E & PART F to add
				} else if (partB2SectionDecider.equalsIgnoreCase("F")) { // Another Individual
					
					// PART F ONLY 
					
					System.out.println("PART F ONBEHALF INDIVIDUAL");
					List<PartFOnBehalfOfIndividual> partFOnBehalfOfIndividuals = getPartFPartyIndividual(partyIDForTransaction);
					System.out.println("-------------------------------------------------------");
				//	System.out.println("Part F -->"+partFOnBehalfOfIndividuals.size());
					b2DispositionCompletions.get(0).addOnBehalfOfIndividual(partFOnBehalfOfIndividuals.get(0));					
						
				} else if (partB2SectionDecider.equalsIgnoreCase("E")) { // An Entity (Other Than an Individual)
					
					System.out.println("PART E ONBEHALF ENTITY");
					List<PartEOnBehalfOfEntity> partEOnBehalfOfEntities = getPartEPartyEntity(partyIDForTransaction);
					b2DispositionCompletions.get(0).addOnBehalfOfEntity(partEOnBehalfOfEntities.get(0));
					System.out.println("-------------------------------------------------------");
					//System.out.println("Part E -->"+partEOnBehalfOfEntities.size());
				
				} else if (partB2SectionDecider.equalsIgnoreCase("G")) {  // An Employee Depositing Cash to an Employer's Business Account
					// NO PART E & PART F to add				
				}
				partB1TransactionInitiation.addDispositions(b2DispositionCompletions);
			}
			
			strData.setTransactions(b1TransactionInitiations);
			
			PartGDescription description = new PartGDescription();
			description.setSuspiciousActivityDesc("Plugin is a suspicious activity");
			strData.setDescription(description);
			
			PartHAction action = new PartHAction();
			action.setActionTakenDesc("Plugin customer account has been closed");
			strData.setAction(action);
			
			String formID =  getSTRFORMID();
			
			
			FStream stream = new FStream();
			FormDataRaw dataRaw = stream.getDataRaw(strData);
			//XStream xStream = AppConfig.getXStream();
			//returnXML = xStream.toXML(dataRaw);
			generateXML(returnXML, formID);
		}
		
//		System.out.println(alertSTRCounts.size());
	//	System.out.println(alertSTRCounts.get(0));
		
		

	}
	
	private String getStringValue(Object obj) {
		if (obj!=null)
			return obj.toString();
		return "";
	}
	
	private static String BRANCHKEY_BYALERTID = " Select" + 
			"	T.EXECUTION_BRANCH_KEY branchKey," + 
			"	count(*) transactionCount," + 
			"	AT.ALERT_ID alertID" + 
			" from" + 
			"	IMPL..ALERTED_TRANSACTIONS AT" + 
			" JOIN UDM.UDM_CDS.V_TRANSACTIONS T ON" + 
			"	AT.ALERT_ID in (?)" + 
			"	AND AT.TRANSACTION_ID = T.TRANSACTION_KEY AND T.EXECUTION_BRANCH_KEY IS NOT NULL " + 
			"	AND AT.LINK_FOR_STR = 'Y'" + 
			" GROUP BY" + 
			"	T.EXECUTION_BRANCH_KEY," + 
			"	AT.ALERT_ID";
	
	
	/**
	 * SQL query approach to pull data using DB connection object.
	 * @return List of WIStatusConfig objects which has the Case Step mapping.
	 */
	private List<AlertSTRCount> getSTRCountByAlertID(String alertID) {
		
		List<AlertSTRCount> alertSTRCounts = new ArrayList<AlertSTRCount>();
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = ConnectionManager.getConnection();
			System.out.println(BRANCHKEY_BYALERTID);
			preparedStatement = conn.prepareStatement(BRANCHKEY_BYALERTID);
			preparedStatement.setString(1, alertID);
			rs = preparedStatement.executeQuery();			
			while (rs.next()) {					
				AlertSTRCount alertSTR = new AlertSTRCount();
				alertSTR = (AlertSTRCount)setAllSetters(alertSTR,rs);
				alertSTRCounts.add(alertSTR);
			}			
		} catch (PluginException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(rs, preparedStatement, null, null, conn);
			
		}
		return alertSTRCounts;
	}
	
	
	private static String PARTABRANCH_QUERY = " SELECT B.BRANCH_KEY branchKey, " + 
			" B.BRANCH_NUMBER branchLocationNo, " + 
			" B.BRANCH_NAME branchName, " + 
			" rtrim(ltrim (" + 
			"   CASE WHEN A.BUILDING_NUMBER IS NULL THEN '' ELSE A.BUILDING_NUMBER + ' ' END +" + 
			"   CASE WHEN A.STREET IS NULL THEN '' ELSE A.STREET + ' ' END +" + 
			"   CASE WHEN A.APARTMENT_SUITE_NUMBER IS NULL THEN '' ELSE A.APARTMENT_SUITE_NUMBER END" + 
			" )) branchName," + 
			" A.CITY branchCity, " + 
			" S.STATE_PROVINCE_NAME branchState," + 
			" A.ZIP_POSTCODE branchZipcode, " + 
			" B.CONTACT_LAST_NAME contactLastName, " + 
			" B.CONTACT_FIRST_NAME contactFirstName, " + 
			" B.CONTACT_MIDDLE_NAME contactMiddleName, " + 
			" B.CONTACT_AREA_CD contactAreaCode, " + 
			" P.PHONE_NUMBER contactPhoneNumber, " + 
			" P.PHONE_NUMBER_EXT contactPhoneExt" + 
			" FROM UDM.UDM_CDS.V_BRANCH B" + 
			" OUTER APPLY (SELECT top 1 * FROM  UDM.UDM_CDS.V_ENTITY_ADDRESS_RELATION " + 
			" WHERE ENTITY_TYPE_CD = 'BRANCH' AND COALESCE(IS_TO_BE_DELETED, 0) = 0 AND (EXPIRATION_DATE IS NULL OR EXPIRATION_DATE > GETDATE() ) AND ADDRESS_RELATION_TYPE_CD = 'udmBranchAddress' AND REF_ENTITY_SK = B.ENTITY_SK ORDER BY EFFECTIVE_DATE DESC, EXPIRATION_DATE DESC) AS EAR" + 
			" LEFT OUTER JOIN UDM.UDM_CDS.V_ADDRESS A" + 
			"    ON EAR.ADDRESS_SK = A.ENTITY_SK" + 
			"    AND COALESCE(A.IS_TO_BE_DELETED,0) = 0" + 
			" LEFT OUTER JOIN UDM.UDM_CDS.V_STATE_PROVINCE S " + 
			"    ON A.STATE_PROVINCE_SK = S.ENTITY_SK" + 
			" OUTER APPLY (SELECT top 1 * FROM UDM.UDM_CDS.V_ENTITY_PHONE_RELATION " + 
			" WHERE ENTITY_TYPE_CD = 'BRANCH' AND COALESCE(IS_TO_BE_DELETED, 0) = 0 AND (EXPIRATION_DATE IS NULL OR EXPIRATION_DATE > GETDATE()) AND PHONE_RELATION_TYPE_CD = 'udmBranchContactPhoneNum' AND REF_ENTITY_SK = B.ENTITY_SK ORDER BY EFFECTIVE_DATE DESC, EXPIRATION_DATE DESC) EPR" + 
			" LEFT OUTER JOIN UDM.UDM_CDS.V_PHONE P " + 
			"    ON EPR.PHONE_SK = P.ENTITY_SK" + 
			"    AND COALESCE(P.IS_TO_BE_DELETED,0) = 0" + 
			" WHERE B.BRANCH_KEY = ? "
			;
	
	
	
	/**
	 * SQL query approach to pull data using DB connection object.
	 * @return List of WIStatusConfig objects which has the Case Step mapping.
	 */
	private List<PartABranch> getPartABranch(String branchKey) {
		
		List<PartABranch> partABranches = new ArrayList<PartABranch>();
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = ConnectionManager.getConnection();		
			System.out.println(PARTABRANCH_QUERY);
			preparedStatement = conn.prepareStatement(PARTABRANCH_QUERY);
			preparedStatement.setString(1, branchKey);
			rs = preparedStatement.executeQuery();			
			while (rs.next()) {					
				PartABranch partABranch = new PartABranch();
				partABranch = (PartABranch)setAllSetters(partABranch,rs);
				partABranches.add(partABranch);
			}			
		} catch (PluginException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(rs, preparedStatement, null, null, conn);
			
		}
		return partABranches;
	}
	
	private static String TRANSACTION_QUERY = "SELECT" + 
			"    P.PARTY_KEY partyKey," + 
			"	AT.CONDUCTOR_ID conductorKey, " + 
			"	T.EXECUTION_BRANCH_KEY branchKey," + 
			"	T.TRANSACTION_KEY transactionKey," + 
			"	T.EXECUTION_LOCAL_DATE_TIME txnDate ," + 
			"	(select cast(T.EXECUTION_LOCAL_DATE_TIME as time(0))) txnTime ," + 
			"	T.CUSTOM_DATE_01 txnPostingDate ," + 
			"	T.ORIG_CURR_AMOUNT txnAmount ," + 
			"	T.ORIG_CURR_CD txnCurrencyCode ," + 
			"	T.SAM_TRANS_TYPE_CD TRANSACTION_TYPE ," + 
			"	T.TRANSACTION_CODE_CD MEDIA ," + 
			"	T.CHANNEL_CD TRANSACTION_CHANNEL ," + 
			"	T.EXECUTION_BRANCH_KEY TRANSACTION_BRANCH_ID ," + 
			"	T.ACCOUNT_KEY ACCOUNT_ID ," + 
			"	T.CUSTOM_SMALL_STRING_06 txnNightDeposit ," + 
			"	T.CUSTOM_SMALL_STRING_11 txnInitialFundType ," + 
			"	T.CUSTOM_SMALL_STRING_12 txnInitialFundDesc ," + 
			"	T.CUSTOM_SMALL_STRING_04 txnConductedType ," + 
			"	T.CUSTOM_SMALL_STRING_05 txnConductedTypeDesc ," + 
			"	T.CUSTOM_SMALL_STRING_07 txnInitialFundOtherName ," + 
			"	T.CUSTOM_SMALL_STRING_08 txnInitialFundOtherAccNo ," + 
			"	T.CUSTOM_SMALL_STRING_02 SCOPE ," + 
			"	T.CUSTOM_SMALL_STRING_03 INTER_ACCESS ," + 
			"	T.OPP_ACCOUNT_KEY BENEFICIARY_ACCOUNT_ID ," + 
			"	T.OPP_COUNTRY_CD COUNTRY ," + 
			"	T.OPP_ORGANIZATION_KEY ORGANIZATION_UNIT ," + 
			"	T.SOURCE_SYSTEM_CD SOURCE" + 
			" FROM " + 
			"	IMPL..ALERTED_TRANSACTIONS AT" + 
			" JOIN UDM.UDM_CDS.V_TRANSACTIONS T ON" + 
			"	AT.ALERT_ID = ? " +
			" AND T.EXECUTION_BRANCH_KEY = ? "+ 
			"	AND AT.LINK_FOR_STR = 'Y'" + 
			"	AND AT.TRANSACTION_ID = T.TRANSACTION_KEY" + 
			"	JOIN UDM.UDM_CDS.V_ACCOUNT A" + 
			"	ON A.ACCOUNT_KEY = T.ACCOUNT_KEY" + 
			"	JOIN UDM.UDM_CDS.V_PARTY P" + 
			"	ON A.PRIMARY_PARTY_KEY = P.PARTY_KEY "    
			;
	
	/**
	 * SQL query approach to pull data using DB connection object.
	 */
	private List<PartB1TransactionInitiation> getPartB1Transaction(String alertID, String branchKey) {
		
		List<PartB1TransactionInitiation> partB1Transactions = new ArrayList<PartB1TransactionInitiation>();
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = ConnectionManager.getConnection();
			System.out.println(TRANSACTION_QUERY);
			preparedStatement = conn.prepareStatement(TRANSACTION_QUERY);
			preparedStatement.setString(1, alertID);
			preparedStatement.setString(2, branchKey);
			rs = preparedStatement.executeQuery();			
			while (rs.next()) {					
				PartB1TransactionInitiation partB1Transaction = new PartB1TransactionInitiation();
				partB1Transaction = (PartB1TransactionInitiation)setAllSetters(partB1Transaction,rs);
				partB1Transactions.add(partB1Transaction);
			}			
		} catch (PluginException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(rs, preparedStatement, null, null, conn);
			
		}
		return partB1Transactions;
	}
	
	/**
	 * 
	 */
	private static String PARTB2_DISPOSITION_COMPLETE = " SELECT " + 
			" T.TRANSACTION_KEY transactionKey " + 
			" ,T.ORIG_CURR_AMOUNT txnDispositionAmount" + 
			" ,T.ORIG_CURR_CD txnDispositionCurrencyCode" + 
			" ,dbo.getOnBehalfOf(T.CHANNEL_CD , A.ACCOUNT_TYPE_CD, AT.CONDUCTOR_ID) txnDispositionOnBehalfIndicator" + 
			" from " + 
			" IMPL..ALERTED_TRANSACTIONS AT" + 
			" JOIN UDM.UDM_CDS.V_TRANSACTIONS T" + 
			" ON AT.TRANSACTION_ID in (?) AND AT.ALERT_ID = ? " + 
			" AND AT.TRANSACTION_ID = T.TRANSACTION_KEY" + 
			" AND AT.LINK_FOR_STR = 'Y'" + 
			" JOIN UDM.UDM_CDS.V_ACCOUNT A" + 
			" ON A.ACCOUNT_KEY = T.ACCOUNT_KEY";
	
	/**
	 * SQL query approach to pull data using DB connection object.
	 */
	private List<PartB2DispositionCompletion> getPartB2DispositionComplete(String transactionKey, String alertKey) {
		
		List<PartB2DispositionCompletion> partB2DispositionCompleteList = new ArrayList<PartB2DispositionCompletion>();
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = ConnectionManager.getConnection();
			System.out.println(transactionKey + "---" + alertKey);
			System.out.println(PARTB2_DISPOSITION_COMPLETE);
			preparedStatement = conn.prepareStatement(PARTB2_DISPOSITION_COMPLETE);
			preparedStatement.setString(1, transactionKey);
			preparedStatement.setString(2, alertKey);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {					
				PartB2DispositionCompletion partB2DispostionComplete = new PartB2DispositionCompletion();
				partB2DispostionComplete = (PartB2DispositionCompletion)setAllSetters(partB2DispostionComplete,rs);
				partB2DispositionCompleteList.add(partB2DispostionComplete);				
			}			
		} catch (PluginException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(rs, preparedStatement, null, null, conn);
			
		}
		return partB2DispositionCompleteList;
	}
	
	
	/**
	 * 
	 */
	private static String PARTC_DISPOSITION_ACCOUNT = "	SELECT  " + 
			"A.ACCOUNT_NUMBER accNo " + 
			",A.HOLDING_BRANCH_KEY accBranchNo " + 
			",A.ACCOUNT_TYPE_CD accType " + 
			",A.ACCT_CURR_CD accCurrencyCode " + 
			",A.EFFECTIVE_DATE accDateOpened " + 
			",A.EXPIRATION_DATE accDateClosed " + 
			",A.ACCOUNT_STATUS_CD accStatus " + 
			",A.HOLDING_BRANCH_KEY accBranchNo " + 
			",dbo.getAccountHolder(T.ACCOUNT_KEY, A.PRIMARY_PARTY_KEY, 1) AS accHolderNameA " + 
			",dbo.getAccountHolder(T.ACCOUNT_KEY, A.PRIMARY_PARTY_KEY, 2) AS accHolderNameB " + 
			",dbo.getAccountHolder(T.ACCOUNT_KEY, A.PRIMARY_PARTY_KEY, 3) AS accHolderNameC " + 
			"from  " + 
			"IMPL..ALERTED_TRANSACTIONS AT " + 
			"JOIN UDM.UDM_CDS.V_TRANSACTIONS T " + 
			"ON AT.TRANSACTION_ID = ? " + 
			"AND AT.TRANSACTION_ID = T.TRANSACTION_KEY " + 
			"AND AT.LINK_FOR_STR = 'Y' " + 
			"JOIN UDM.UDM_CDS.V_ACCOUNT A " + 
			"ON A.ACCOUNT_KEY = T.ACCOUNT_KEY";
	
	/**
	 * SQL query approach to pull data using DB connection object.
	 */
	private List<PartCDispositionAccount> getPartCDispostionAccount(String transactionKey) {
		
		List<PartCDispositionAccount> partCDispositionAccounts = new ArrayList<PartCDispositionAccount>();
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = ConnectionManager.getConnection();
			preparedStatement = conn.prepareStatement(PARTC_DISPOSITION_ACCOUNT);
			preparedStatement.setString(1, transactionKey);
			rs = preparedStatement.executeQuery();			
			while (rs.next()) {					
				PartCDispositionAccount partCDispositionAccount = new PartCDispositionAccount();
				partCDispositionAccount = (PartCDispositionAccount)setAllSetters(partCDispositionAccount,rs);
				partCDispositionAccounts.add(partCDispositionAccount);
			}			
		} catch (PluginException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(rs, preparedStatement, null, null, conn);
			
		}
		return partCDispositionAccounts;
	}
	
	private static String PARTD_TRANSACTIONCONDUCTOR = "SELECT " + 
			"	P.PARTY_KEY SUSPECTCLIENTNO , " + 
			"	P.FIRST_NAME SUSPECTGIVENNAME , " + 
			"	P.LAST_NAME SUSPECTSURNAME , " + 
			"	NULLIF(P.NAME_PREFIX, " + 
			"	P.OTHER_NAME) SUSPECTINITIAL , " + 
			"	P.BIRTH_INCORP_DATE SUSPECTBIRTHDAY , " + 
			"	CO.COUNTRY_CD COUNTRYOFCITIZENSHIP ," + 
			"	PA.COUNTRY_CD SUSPECTCOUNTRYOFRESIDENCE , " + 
			"	PH.PHONE_AREA_CODE SUSPECTHOMEAREACODE , " + 
			"	PH.PHONE_NUMBER SUSPECTHOMEPHONE , " + 
			"	NULLIF(PA.ADDRESS_LINE_1, " + 
			"	'')+ ' ' + NULLIF(PA.ADDRESS_LINE_2, " + 
			"	'')+ ' ' + NULLIF(PA.ADDRESS_LINE_2, " + 
			"	'') SUSPECTADDRESS , " + 
			"	PA.CITY SUSPECTCITY , " + 
			"	PA.STATE_PROVINCE_CD SUSPECTSTATE , " + 
			"	PA.COUNTRY_CD SUSPECTCOUNTRY , " + 
			"	PA.ZIP_POSTCODE SUSPECTZIPCODE , " + 
			"	PD.DOCUMENT_TYPE_CD SUSPECTIDENTIFIER, " + 
			"	PD.DOCUMENT_DESCRIPTION SUSPECTIDENTIFIERDESC, " + 
			"	PD.DOCUMENT_NUMBER  SUSPECTIDNO, " + 
			"	PD.ISSUE_STATE_PROVINCE_CD SUSPECTIDISSUESTATE, " + 
			"	PD.ISSUE_COUNTRY_CD SUSPECTIDISSUECOUNTRY, " + 
			"	P.OCCUPATION_CD SUSPECTOCCUPATION , " + 
			"	P.EMPLOYER_DETAILS SUSPECTEMPLOYER , " + 
			"	PB.PHONE_AREA_CODE SUSPECTBUSINESSAREACODE , " + 
			"	PB.PHONE_NUMBER SUSPECTBUSINESSPHONE , " + 
			"	PB.PHONE_NUMBER_EXT SUSPECTBUSINESSPHONEEXT " + 
			"FROM " + 
			"	UDM.UDM_CDS.V_PARTY P  " + 
			"	OUTER APPLY (	SELECT TOP 1 PA.* FROM UDM.UDM_CDS.V_ADDRESS PA, UDM.UDM_CDS.V_ENTITY_ADDRESS_RELATION AR where AR.IS_PRIMARY = 1 and AR.ENTITY_TYPE_CD = 'PARTY' and AR.ADDRESS_RELATION_TYPE_CD = 'DEFAULT' AND AR.ADDRESS_SK = PA.ENTITY_SK AND AR.REF_ENTITY_SK = P.ENTITY_SK) PA " + 
			"	OUTER APPLY (	SELECT TOP 1 PD.* FROM UDM.UDM_CDS.V_DOCUMENT PD, UDM.UDM_CDS.V_ENTITY_DOCUMENT_RELATION PR where PR.ENTITY_TYPE_CD = 'PARTY' AND PR.DOCUMENT_SK = PD.ENTITY_SK AND PR.REF_ENTITY_SK = P.ENTITY_SK	ORDER BY DOCUMENT_TYPE_CD) PD  " + 
			"	OUTER APPLY (	SELECT TOP 1 PH.* FROM UDM.UDM_CDS.V_PHONE PH, UDM.UDM_CDS.V_ENTITY_PHONE_RELATION PR WHERE P.PARTY_KEY = PR.ENTITY_KEY and PR.ENTITY_TYPE_CD = 'PARTY' AND PR.PHONE_SK = PH.ENTITY_SK AND PR.PHONE_RELATION_TYPE_CD  = 'HOME') PH  " + 
			"	OUTER APPLY (	SELECT TOP 1 PH.* FROM UDM.UDM_CDS.V_PHONE PH, UDM.UDM_CDS.V_ENTITY_PHONE_RELATION PR WHERE P.PARTY_KEY = PR.ENTITY_KEY and PR.ENTITY_TYPE_CD = 'PARTY' AND PR.PHONE_SK = PH.ENTITY_SK AND PR.PHONE_RELATION_TYPE_CD  = 'BUSINESS') PB " + 
			"	OUTER APPLY (	SELECT TOP 1 C.* FROM UDM.UDM_CDS.V_COUNTRY C, UDM.UDM_CDS.V_ENTITY_COUNTRY_RELATION CR WHERE P.PARTY_KEY = CR.ENTITY_KEY and CR.ENTITY_TYPE_CD = 'PARTY' AND CR.COUNTRY_SK = C.ENTITY_SK AND CR.COUNTRY_RELATION_TYPE_CD  = 'CITIZENSHIP') CO " +
			"WHERE P.PARTY_KEY IN (?) ";


	/**
	 * SQL query approach to pull data using DB connection object.
	 */
	private List<PartDTransactionConductor> getPartDConductor(String partyID) {
		
		List<PartDTransactionConductor> partDTransactionConductors = new ArrayList<PartDTransactionConductor>();
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = ConnectionManager.getConnection();
			
			preparedStatement = conn.prepareStatement(PARTD_TRANSACTIONCONDUCTOR);
			preparedStatement.setString(1, partyID);
			rs = preparedStatement.executeQuery();			
			while (rs.next()) {					
				PartDTransactionConductor partDTransactionConductor = new PartDTransactionConductor();
				partDTransactionConductor = (PartDTransactionConductor)setAllSetters(partDTransactionConductor,rs);
				partDTransactionConductors.add(partDTransactionConductor);
			}			
		} catch (PluginException e) {
			e.printStackTrace();
		} catch (SQLException e) {
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
			"	P.CUSTOM_SMALL_STRING_04 BUSINESSTYPE , " + 
			"	P.FIRST_NAME ENTITYNAME , " + 
			"	P.BIRTH_INCORP_DATE DOB , " + 
			"	PD.DOCUMENT_NUMBER INCORPORATIONNO , " + 
			"	NULLIF(PA.ADDRESS_LINE_1, " + 
			"	'')+ ' ' + NULLIF(PA.ADDRESS_LINE_2, " + 
			"	'')+ ' ' + NULLIF(PA.ADDRESS_LINE_2, " + 
			"	'') ENTITYADDRESS , " + 
			"	PA.CITY ENTITYCITY , " + 
			"	PA.STATE_PROVINCE_CD ENTITYSTATE , " + 
			"	PA.COUNTRY_CD ENTITYCOUNTRY , " + 
			"	PA.ZIP_POSTCODE ENTITYZIPCODE , " + 
			"	PD.ISSUE_STATE_PROVINCE_CD ISSUESTATE , " + 
			"	PD.ISSUE_COUNTRY_CD ISSUECOUNTRY , " + 
			"	PB.PHONE_AREA_CODE ENTITYAREACODE , " + 
			"	PB.PHONE_NUMBER ENTITYPHONE , " + 
			"	PB.PHONE_NUMBER_EXT ENTITYPHONEEXT " + 
			"FROM " + 
			"	UDM.UDM_CDS.V_PARTY P " + 
			"  	OUTER APPLY (	SELECT TOP 1 PA.* FROM UDM.UDM_CDS.V_ADDRESS PA, UDM.UDM_CDS.V_ENTITY_ADDRESS_RELATION AR where AR.IS_PRIMARY = 1 AND P.PARTY_KEY = AR.ENTITY_KEY and AR.ENTITY_TYPE_CD = 'PARTY' and AR.ADDRESS_RELATION_TYPE_CD = 'DEFAULT'AND AR.ADDRESS_SK = PA.ENTITY_SK AND AR.REF_ENTITY_SK = P.ENTITY_SK) PA " + 
			"	OUTER APPLY (	SELECT TOP 1 PD.* FROM UDM.UDM_CDS.V_DOCUMENT PD, UDM.UDM_CDS.V_ENTITY_DOCUMENT_RELATION PR where P.PARTY_KEY = PR.ENTITY_KEY and PR.ENTITY_TYPE_CD = 'PARTY' AND PR.DOCUMENT_SK = PD.ENTITY_SK AND PR.REF_ENTITY_SK = P.ENTITY_SK AND DOCUMENT_TYPE_CD = 'EIN'	ORDER BY DOCUMENT_TYPE_CD) PD  " + 
			"	OUTER APPLY (	SELECT TOP 1 PH.* FROM UDM.UDM_CDS.V_PHONE PH, UDM.UDM_CDS.V_ENTITY_PHONE_RELATION PR WHERE P.PARTY_KEY = PR.ENTITY_KEY and PR.ENTITY_TYPE_CD = 'PARTY' AND PR.PHONE_SK = PH.ENTITY_SK AND PR.PHONE_RELATION_TYPE_CD  = 'HOME') PH  " + 
			"	OUTER APPLY (	SELECT TOP 1 PH.* FROM UDM.UDM_CDS.V_PHONE PH, UDM.UDM_CDS.V_ENTITY_PHONE_RELATION PR WHERE P.PARTY_KEY = PR.ENTITY_KEY and PR.ENTITY_TYPE_CD = 'PARTY' AND PR.PHONE_SK = PH.ENTITY_SK AND PR.PHONE_RELATION_TYPE_CD  = 'BUSINESS') PB" + 
		" WHERE P.PARTY_KEY IN (?) ";



	
	
	/**
	 * SQL query approach to pull data using DB connection object.
	 */
	private List<PartEOnBehalfOfEntity> getPartEPartyEntity(String partyID) {
		
		List<PartEOnBehalfOfEntity> partEPartyEntities = new ArrayList<PartEOnBehalfOfEntity>();
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = ConnectionManager.getConnection();
			
			preparedStatement = conn.prepareStatement(PARTE_BEHALFOFENTITY);
			preparedStatement.setString(1, partyID);
			rs = preparedStatement.executeQuery();			
			while (rs.next()) {					
				PartEOnBehalfOfEntity partEPartyEntity = new PartEOnBehalfOfEntity();
				partEPartyEntity = (PartEOnBehalfOfEntity)setAllSetters(partEPartyEntity,rs);
				partEPartyEntities.add(partEPartyEntity);
			}			
		} catch (PluginException e) {
			e.printStackTrace();
		} catch (SQLException e) {
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
			"	NULLIF(P.NAME_PREFIX," + 
			"	P.OTHER_NAME) INITIAL ," + 
			"	P.BIRTH_INCORP_DATE INDIVIDUALBIRTHDAY ," + 
			"	CO.COUNTRY_CD COUNTRYOFCITIZENSHIP ," + 
			"	PA.COUNTRY_CD COUNTRY_OF_RESIDENCE ," + 
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
			"	PD.DOCUMENT_TYPE_CD INDIVIDUALIDENTIFIER ," + 
			"	PD.DOCUMENT_DESCRIPTION INDIVIDUALIDENTIFIERDESC ," + 
			"	PD.DOCUMENT_NUMBER INDIVIDUALIDNO ," + 
			"	PD.ISSUE_STATE_PROVINCE_CD ISSUESTATE ," + 
			"	PD.ISSUE_COUNTRY_CD ISSUECOUNTRY ," + 
			"	P.OCCUPATION_CD INDIVIDUALOCCUPATION ," + 
			"	P.EMPLOYER_DETAILS INDIVIDUALEMPLOYER ," + 
			"	PB.PHONE_AREA_CODE BUSINESSAREACODE ," + 
			"	PB.PHONE_NUMBER BUSINESSPHONE ," + 
			"	PB.PHONE_NUMBER_EXT BUSINESSPHONEEXT" + 
			" FROM" + 
			"	UDM.UDM_CDS.V_PARTY P" + 
			"  	OUTER APPLY (	SELECT TOP 1 PA.* FROM UDM.UDM_CDS.V_ADDRESS PA, UDM.UDM_CDS.V_ENTITY_ADDRESS_RELATION AR where AR.IS_PRIMARY = 1 AND P.PARTY_KEY = AR.ENTITY_KEY and AR.ENTITY_TYPE_CD = 'PARTY' and AR.ADDRESS_RELATION_TYPE_CD = 'DEFAULT'AND AR.ADDRESS_SK = PA.ENTITY_SK AND AR.REF_ENTITY_SK = P.ENTITY_SK) PA" + 
			"	OUTER APPLY (	SELECT TOP 1 PD.* FROM UDM.UDM_CDS.V_DOCUMENT PD, UDM.UDM_CDS.V_ENTITY_DOCUMENT_RELATION PR where P.PARTY_KEY = PR.ENTITY_KEY and PR.ENTITY_TYPE_CD = 'PARTY' AND PR.DOCUMENT_SK = PD.ENTITY_SK AND PR.REF_ENTITY_SK = P.ENTITY_SK AND DOCUMENT_TYPE_CD = 'EIN'	ORDER BY DOCUMENT_TYPE_CD) PD " + 
			"	OUTER APPLY (	SELECT TOP 1 PH.* FROM UDM.UDM_CDS.V_PHONE PH, UDM.UDM_CDS.V_ENTITY_PHONE_RELATION PR WHERE P.PARTY_KEY = PR.ENTITY_KEY and PR.ENTITY_TYPE_CD = 'PARTY' AND PR.PHONE_SK = PH.ENTITY_SK AND PR.PHONE_RELATION_TYPE_CD  = 'HOME') PH " + 
			"	OUTER APPLY (	SELECT TOP 1 PH.* FROM UDM.UDM_CDS.V_PHONE PH, UDM.UDM_CDS.V_ENTITY_PHONE_RELATION PR WHERE P.PARTY_KEY = PR.ENTITY_KEY and PR.ENTITY_TYPE_CD = 'PARTY' AND PR.PHONE_SK = PH.ENTITY_SK AND PR.PHONE_RELATION_TYPE_CD  = 'BUSINESS') PB" + 
			"	OUTER APPLY (	SELECT TOP 1 C.* FROM UDM.UDM_CDS.V_COUNTRY C, UDM.UDM_CDS.V_ENTITY_COUNTRY_RELATION CR WHERE P.PARTY_KEY = CR.ENTITY_KEY and CR.ENTITY_TYPE_CD = 'PARTY' AND CR.COUNTRY_SK = C.ENTITY_SK AND CR.COUNTRY_RELATION_TYPE_CD  = 'CITIZENSHIP') CO" +
			" WHERE P.PARTY_KEY IN (?) ";

	
	
		/**
		 * SQL query approach to pull data using DB connection object.
		 */
		private List<PartFOnBehalfOfIndividual> getPartFPartyIndividual(String partyID) {
			
			List<PartFOnBehalfOfIndividual> partFOnBehalfOfIndividuals = new ArrayList<PartFOnBehalfOfIndividual>();
			Connection conn = null;
			ResultSet rs = null;
			PreparedStatement preparedStatement = null;
			try {
				conn = ConnectionManager.getConnection();
				System.out.println(PARTF_BEHALFOFINDIVIDUAL);
				preparedStatement = conn.prepareStatement(PARTF_BEHALFOFINDIVIDUAL);
				preparedStatement.setString(1, partyID);
				rs = preparedStatement.executeQuery();			
				while (rs.next()) {					
					PartFOnBehalfOfIndividual partFOnBehalfIndividual = new PartFOnBehalfOfIndividual();
					partFOnBehalfIndividual = (PartFOnBehalfOfIndividual)setAllSetters(partFOnBehalfIndividual,rs);
					partFOnBehalfOfIndividuals.add(partFOnBehalfIndividual);
				}			
			} catch (PluginException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeConnection(rs, preparedStatement, null, null, conn);
				
			}
			return partFOnBehalfOfIndividuals;
		}
		
		private String getSTRFORMID() {
			Connection conn = null;
			ResultSet rs = null;
			PreparedStatement preparedStatement = null;
			String formID = "";
			try {
				conn = ConnectionManager.getConnection();				
				preparedStatement = conn.prepareStatement("SELECT NEXT VALUE FOR IMPL..STRFORMIDSEQUENCE AS ID");				
				rs = preparedStatement.executeQuery();			
				while (rs.next()) {					
					formID = rs.getString("ID");					
				}			
			} catch (PluginException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeConnection(rs, preparedStatement, null, null, conn);
				
			}
			return formID;
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
    		e.printStackTrace();
    	}
    }

    
	private Object setAllSetters(Object ob, ResultSet rs) throws SQLException {
	    // MZ: Find the correct method
	    Class cls = ob.getClass();	    
	        for (java.lang.reflect.Field field : cls.getDeclaredFields()){
	            for (Method method : cls.getMethods())
	            {
	                if ((method.getName().startsWith("set")) && (method.getName().length() == (field.getName().length() + 3)))
	                {
	                    if (method.getName().toLowerCase().endsWith(field.getName().toLowerCase()))
	                    {
	                        // MZ: Method found, run it
	                        try {
	                            method.setAccessible(true);
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
	                        catch (IllegalAccessException | InvocationTargetException | SQLException e)
	                        {
	                            //System.err.println(""+e.getMessage());
	                        }
	                    }
	                }
	            }
	    }
	    return ob;
	}

}
