package com.ifs.plugin.actone.str;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.actimize.dashboard.model.DDQResultSet;
import com.actimize.dashboard.model.DataRow;
import com.actimize.dashboard.model.Query;
import com.actimize.infrastructure.plugin.api.Context;
import com.actimize.infrastructure.plugin.api.PageController;
import com.actimize.infrastructure.plugin.api.PageModel;
import com.actimize.infrastructure.plugin.api.exceptions.PluginException;
import com.actimize.infrastructure.vtl.VTLUtils;
import com.ifs.handler.STRHandler;
import com.ifs.str.parts.CaseSTRCount;

/**
 *
 */
public class ApplicationController implements PageController {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	public String getPageLocation(Context paramContext) {		
		HttpServletRequest req = paramContext.getServletRequest();	
		Boolean eligibilityFlag = (Boolean)req.getAttribute("eligibility");
		Boolean noTransactionFlag = (Boolean)req.getAttribute("noTransaction");
		logger.info("eligibility vtl ---> "+eligibilityFlag);
		logger.info("noTransactionFlag vtl ---> "+noTransactionFlag);		
		if (eligibilityFlag != null && eligibilityFlag.equals(Boolean.FALSE)) {
			return "errormessage.vtl";
		} else if (noTransactionFlag != null && noTransactionFlag.equals(Boolean.TRUE)) {
			return "messageTransaction.vtl";
		} else {
			return "message.vtl";			
		}
	}

	public void handleSubmit(Context ctx, PageModel pg) throws PluginException {
		logger.info("Inside handleSubmit");
	}

	public void preparePageModel(Context context, PageModel paramPageModel) throws PluginException {
		logger.info("Inside preparePageModel");
		try {
			paramPageModel.setTitle("Create STR Wizard");
			HttpServletRequest request = context.getServletRequest();	
			String caseID = request.getParameter("DetailsMailedItem");
			logger.info("caseID -->"+caseID);
			request.setAttribute("caseId",caseID);			
			Query query1 = new VTLUtils().getQuery("BNC_CASE_STR_ELIGIBILITY");	
			query1.setParameter("case_id", caseID);
			DDQResultSet rs1 = query1.execute();
			List<DataRow> rows1 = rs1.getRows();			
			if (rows1.isEmpty()) {
				request.setAttribute("eligibility", Boolean.FALSE);
			} else {
				request.setAttribute("eligibility", Boolean.TRUE);
				Query query2 = new VTLUtils().getQuery("BNC_CASE_STR_FORM_EXIST");		
				query2.setParameter("case_id", caseID);
				DDQResultSet rs2 = query2.execute();
				List<DataRow> rows2 = rs2.getRows();
				if (rows2.isEmpty()) {
					request.setAttribute("formExists", "false");
				} else {
					request.setAttribute("formExists", "true");
				}
				
				Query query3 = new VTLUtils().getQuery("BNC_CASE_STR_COUNT_BY_99");		
				query3.setParameter("case_id", caseID);
				DDQResultSet rs3 = query3.execute();
				List<DataRow> rows3 = rs3.getRows();
				if (rows3.isEmpty()) {
					request.setAttribute("noTransaction", Boolean.TRUE);
				} else {
					request.setAttribute("noTransaction", Boolean.FALSE);
					String htmlString = "";
					List<CaseSTRCount> caseSTRCounts = new ArrayList<CaseSTRCount>();
					for (DataRow row : rows3) {
						String branchKey = (String)row.getValue("branchKey");
						Integer transactionCount = (Integer)row.getValue("transactionCount");
						Integer strCount = (Integer)row.getValue("strcount");
						//	String alertID = (String)row.getValue("alertID");
						CaseSTRCount caseSTRCount = new CaseSTRCount(branchKey, transactionCount, strCount);
						caseSTRCounts.add(caseSTRCount);
						htmlString = htmlString.concat(caseSTRCount.toString());
					}
					request.setAttribute("caseSTRCounts", htmlString);
				}
			}
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	public void createSTR(Context context, PageModel model) throws PluginException {
		HttpServletRequest req = context.getServletRequest();
		String CaseID = req.getParameter("caseId");		
		logger.info("CaseID "+CaseID);		
		STRHandler strHandler = new STRHandler();
		strHandler.executeCaseSTR(context, CaseID);
		context.closeDialogAndRefreshParent();
	}	
}