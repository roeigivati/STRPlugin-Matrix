package com.ifs.handler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.actimize.forms.services.OutgoingFormsService;
import com.actimize.infrastructure.plugin.api.Context;
import com.actimize.infrastructure.util.SpringUtil;
import com.ifs.forms.dao.Form;
import com.ifs.forms.dao.STRConnectionDao;
import com.ifs.forms.xml.FormDataRaw;
import com.ifs.fstream.FStream;
import com.ifs.infra.AppConfig;
import com.ifs.str.STRData;
import com.ifs.str.parts.AlertSTRCount;
import com.ifs.str.parts.CaseAlert;
import com.ifs.str.parts.PartABranch;
import com.ifs.str.parts.PartAReportingFI;
import com.ifs.str.parts.PartB1TransactionInitiation;
import com.ifs.str.parts.PartB2DispositionCompletion;
import com.ifs.str.parts.PartCDispositionAccount;
import com.ifs.str.parts.PartDTransactionConductor;
import com.ifs.str.parts.PartGDescription;
import com.ifs.str.parts.PartHAction;
import com.thoughtworks.xstream.XStream;

public class STRHandler {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * @deprecated
	 * @param context
	 * @param xml
	 * @param formID
	 * @param caseInternalID
	 * @param userInternalID
	 * @param STRTypeID
	 */
	private void generateXMLHibernate(Context context, String xml, String formID, Integer caseInternalID, Integer userInternalID, Integer STRTypeID) {
		
		logger.info("generate XML --->" + formID + "-----" + caseInternalID + "----" +userInternalID);
		Session session = null;
		try {
			session = new AppConfig(context).getSession();
			session.beginTransaction();
			Form form = new Form();
			//form.setInternalId(9999);
			form.setFormIdentifier(formID);
			form.setAlertInternalId(caseInternalID);
			form.setName("F"+formID);
			form.setStatus("In process");
			form.setFormTypeInternalId(STRTypeID);
			form.setCreateDate(new Date());
			form.setCreateUser(userInternalID);
			form.setLastUpdateUser(userInternalID);
			form.setXml(xml);
			session.save(form);
			session.getTransaction().commit();

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			if (session != null && session.getTransaction() != null)
				session.getTransaction().rollback();
		} finally {
			if (session != null)
				session.close();
		}

	}
	
	/**
	 * Method to retrieve the OutgoingFormService.
	 * @return
	 */
	private OutgoingFormsService getOutgoingFormsService() {
		OutgoingFormsService formService = null;
		logger.debug("Enterning RequestResponse.getOutgoingFormsService()");
	    if (formService == null) {
	    	formService = (OutgoingFormsService)SpringUtil.lookupComponent("alertsOutgoingFormsService");
	    }
	    logger.debug("Exiting RequestResponse.getOutgoingFormsService()");
	    return formService;
	  }
	
	/**
	 * Method to create the Fintrac Form with provided Form name and Form ID. 
	 * @param context
	 * @param xml - XML content of the form. 
	 * @param formID - The form id is a combination of letter "F" + running sequence. 
	 * @param caseInternalID - The case internal id of the case for which the form is created. 
	 * @param userInternalID - 
	 * @param STRTypeID - The FINTRAC form type internal ID.
	 */
	private void generateXML(Context context, String xml, String formID, Long caseInternalID, Integer STRTypeID) {			
		logger.debug("Generate XML --->" + formID + "-----" + caseInternalID );
		OutgoingFormsService formService = getOutgoingFormsService();
		formService.createForm(caseInternalID, STRTypeID, xml, "F"+formID, "F"+formID, null, null);
	}
		
	public void executeCaseSTR(Context context, String caseID) {
		logger.info("Enter executeCaseSTR");
		STRConnectionDao connectionDao = new STRConnectionDao(context);
		List<CaseAlert> caseAlerts = connectionDao.getAlertsByCaseID(caseID);		
		executeCase(context, caseAlerts.get(0).getCaseInternalId());
		logger.info("End executeCaseSTR");
	}
	
	private void executeCase (Context context, String caseInternalID) {
		logger.info("Enter executeCase --->"+caseInternalID);
		STRConnectionDao connectionDao = new STRConnectionDao(context);
	
		List<AlertSTRCount>  alertSTRCounts = connectionDao.getSTRCountByAlertID(caseInternalID);
		HashMap<String, List<String>> branchMap = new HashMap<String, List<String>>();
		for (AlertSTRCount alertSTRCount : alertSTRCounts) {
			if( branchMap.containsKey(alertSTRCount.getBranchKey())) {
				branchMap.get(alertSTRCount.getBranchKey()).add(alertSTRCount.getAlertID());
			} else {
				ArrayList<String> arrayList = new ArrayList<String>();
				arrayList.add(alertSTRCount.getAlertID());
				branchMap.put(alertSTRCount.getBranchKey(), arrayList);
			}
		}
		
		PartAReportingFI reportingFI = connectionDao.getPartAReportingFI(caseInternalID);
		HashMap<String, PartABranch> branchesMap = connectionDao.getPartABranch(branchMap.keySet());	
		for (String branchNumber : branchMap.keySet()) {
			STRData strData = new STRData();
			String caseBU = reportingFI.getCaseBU();
			strData.setReportingFI(reportingFI);	
			PartABranch aBranch = branchesMap.get(branchNumber);
			if (aBranch != null ) {
				strData.setBranch(aBranch);
			} else {
				// Requirement, even if there is no branch information in BRANCH table,
				//the branch number from transactions needs to be populated.
				PartABranch emptyBranch = new PartABranch();
				emptyBranch.setBranchLocationNo(branchNumber);
				emptyBranch.setBranchKey(branchNumber);
				strData.setBranch(emptyBranch);
			}
			
			// PART G
			PartGDescription description = new PartGDescription();
			description.setSuspiciousActivityDesc("");
			strData.setDescription(description);

			// PART H
			PartHAction action = new PartHAction();
			action.setActionTakenDesc("");
			strData.setAction(action);

			LinkedList<PartB1TransactionInitiation> b1TransactionInitiations = connectionDao.getPartB1Transaction(inClauseFormat(branchMap.get(branchNumber)), branchNumber);
			
			// For every Transaction
			if (b1TransactionInitiations.size() <= 99) {			
				transactionList(b1TransactionInitiations, context, inClauseFormat(branchMap.get(branchNumber)), strData, caseInternalID, caseBU);
			} else if (b1TransactionInitiations.size() > 99 ){
				for (List<PartB1TransactionInitiation> splittedB1 : partitionBasedOnSize(b1TransactionInitiations, 99)) {
					transactionList(splittedB1, context, inClauseFormat(branchMap.get(branchNumber)), strData, caseInternalID, caseBU);
				}
			}
		}
	}
	
	
	private void transactionList(List<PartB1TransactionInitiation> b1TransactionInitiations, Context context, String alertIDS, STRData strData, String caseInternalID, String caseBU) {
		logger.info("Enter the transactionList");
		STRConnectionDao connectionDao = new STRConnectionDao(context);
		
		String returnXML = "";
		HashMap<String, PartDTransactionConductor> partDconductorHashmap = new HashMap<String, PartDTransactionConductor>();
		HashMap<String, List<PartB2DispositionCompletion>> partB2DispositionsMap = new HashMap<String, List<PartB2DispositionCompletion>>();
		HashMap<String, PartCDispositionAccount> partCDispositionsAccountMap = new HashMap<String, PartCDispositionAccount>();
		HashSet<String>transactionKeys = new HashSet<String>();
		HashSet<String>conductorKeys = new HashSet<String>();
		
		
		for (PartB1TransactionInitiation partB1TransactionInitiation : b1TransactionInitiations) {					
			String partyIDForTransaction = "";
			//PARTD SECTION IS ONLY APPLICABLE FOR CONDUCTOR ADDED THROUGH CONDUCTOR PLUGIN
			//partyIDForTransaction = partB1TransactionInitiation.getPartyKey();
			if (partB1TransactionInitiation.getConductorKey() != null && !partB1TransactionInitiation.getConductorKey().equalsIgnoreCase("")) {
				partyIDForTransaction = partB1TransactionInitiation.getConductorKey();				
			}
			conductorKeys.add(partyIDForTransaction);
			transactionKeys.add(partB1TransactionInitiation.getTransactionKey());			
		}

		partCDispositionsAccountMap = connectionDao.getPartCDispostionAccount(transactionKeys);
		partDconductorHashmap = connectionDao.getPartDConductor(conductorKeys, caseBU);
		partB2DispositionsMap = connectionDao.getPartB2DispositionComplete(transactionKeys, alertIDS);
		
		
		
		for (PartB1TransactionInitiation partB1TransactionInitiation : b1TransactionInitiations) {					
			String partyIDForTransaction = "";
			//PARTD SECTION IS ONLY APPLICABLE FOR CONDUCTOR ADDED THROUGH CONDUCTOR PLUGIN
			//partyIDForTransaction = partB1TransactionInitiation.getPartyKey();
			if (partB1TransactionInitiation.getConductorKey() != null && !partB1TransactionInitiation.getConductorKey().equalsIgnoreCase("")) {
				partyIDForTransaction = partB1TransactionInitiation.getConductorKey();
			}
			PartDTransactionConductor conductor = new PartDTransactionConductor();			
			conductor = partDconductorHashmap.get(partyIDForTransaction);
			if (conductor != null) {
				PartDTransactionConductor newCond = new PartDTransactionConductor();				
				try {
					newCond = (PartDTransactionConductor)conductor.clone();
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}				
				newCond.setSuspectInitial(partB1TransactionInitiation.getTransactionCode());
				partB1TransactionInitiation.addConductor(newCond);
			} else {
				conductor = new PartDTransactionConductor();
				conductor.setSuspectInitial(partB1TransactionInitiation.getTransactionCode());
				partB1TransactionInitiation.addConductor(conductor);	
			}						
			
			List<PartB2DispositionCompletion> b2DispositionCompletions = new ArrayList<PartB2DispositionCompletion>();
			b2DispositionCompletions = partB2DispositionsMap.get(partB1TransactionInitiation.getTransactionKey());
			logger.debug("The conductor ID is ---> "+ partyIDForTransaction);
			PartCDispositionAccount cDispositionAccount = new PartCDispositionAccount();
			cDispositionAccount = partCDispositionsAccountMap.get(partB1TransactionInitiation.getTransactionKey());
			b2DispositionCompletions.get(0).addCDispositionAccount(cDispositionAccount);
			partB1TransactionInitiation.addDispositions(b2DispositionCompletions);
			

			// THIS BELOW SELECTION IS NOT NEEDED ANY MORE AS THE ONBEHALF SELECTION WILL BE DONE BY BNC SO SECTION E & F THROUGH THIS PLUGIN.
			//String partB2SectionDecider = getStringValue(b2DispositionCompletions.get(0).getTxnDispositionOnBehalfIndicator());
			//			String partB2SectionDecider = "";
			//			
			//			logger.error("On behalf indicator ----->" + partB2SectionDecider);
			//			
			//			
			//
			//			if (partB2SectionDecider.equalsIgnoreCase("C")) { // Not Applicable	
			//				// NO PART E & PART F to add
			//			} else if (partB2SectionDecider.equalsIgnoreCase("F")) { // Another Individual // PART F ONLY 				
			//				logger.info("PART F ONBEHALF INDIVIDUAL");
			//				List<PartFOnBehalfOfIndividual> partFOnBehalfOfIndividuals = connectionDao.getPartFPartyIndividual(partyIDForTransaction);
			//				b2DispositionCompletions.get(0).addOnBehalfOfIndividual(partFOnBehalfOfIndividuals.get(0));					
			//			} else if (partB2SectionDecider.equalsIgnoreCase("E")) { // An Entity (Other Than an Individual)
			//				logger.info("PART E ONBEHALF ENTITY");
			//				List<PartEOnBehalfOfEntity> partEOnBehalfOfEntities = connectionDao.getPartEPartyEntity(partyIDForTransaction);
			//				b2DispositionCompletions.get(0).addOnBehalfOfEntity(partEOnBehalfOfEntities.get(0));
			//			} else if (partB2SectionDecider.equalsIgnoreCase("G")) {  // An Employee Depositing Cash to an Employer's Business Account
			//				// NO PART E & PART F to add				
			//			}
			
		}
		
		strData.setTransactions(b1TransactionInitiations);
		
		String formID = connectionDao.getSTRFormID();
		// Retrieving User ID is not needed.
		//String userIdentifier = context.getCurrentUser().getUserIdentifier();
		//Integer userInternalID = connectionDao.getUserID(userIdentifier);
		Integer formTypeID = connectionDao.getSTRTypeID();

		FStream stream = new FStream();
		FormDataRaw dataRaw = stream.getDataRaw(strData);
		XStream xStream = null;
		try {
			xStream = AppConfig.getXStream();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		returnXML = xStream.toXML(dataRaw);		
		Boolean validCaseID = connectionDao.validateCaseID(Long.valueOf(caseInternalID));
		if (validCaseID) {
			generateXML(context, returnXML, formID, Long.valueOf(caseInternalID), formTypeID);
		} else {
			logger.error("Wrong Case ID used to create forms Create STR approach"+caseInternalID);
		}
	}
	
	private String getStringValue(Object obj) {
		if (obj!=null)
			return obj.toString();
		return "";
	}
	
	static <T> Collection<List<T>> partitionBasedOnSize(List<T> inputList, int size) {
        final AtomicInteger counter = new AtomicInteger(0);
        return inputList.stream()
                    .collect(Collectors.groupingBy(s -> counter.getAndIncrement()/size))
                    .values();
	}	
	
	private static String inClauseFormat (List<String> input) {
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
