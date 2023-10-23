/**
 * 
 */
package com.ifs.plugin.actone.str;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.actimize.infrastructure.plugin.api.Action;
import com.actimize.infrastructure.plugin.api.Context;
import com.ifs.handler.STRHandler;

/**
 * @author AWAK001
 *
 */
public class ApplicationAction implements Action {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 
	 */
	public ApplicationAction() {

	}

	public void execute(Context context) throws Exception {
		logger.info("INSIDE EXECUTE ---> ");
		HttpServletRequest req = context.getServletRequest();
		String CaseID = req.getParameter("DetailsMailedItem");		
		logger.info("stringCaseID "+CaseID);		
		STRHandler strHandler = new STRHandler();
		strHandler.executeCaseSTR(context, CaseID);		
	}
}