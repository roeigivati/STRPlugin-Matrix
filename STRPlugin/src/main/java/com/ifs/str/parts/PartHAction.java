/**
 * 
 */
package com.ifs.str.parts;

import com.ifs.fstream.annotations.FormData;
import com.ifs.fstream.annotations.FormField;
import com.ifs.fstream.annotations.FormData.Type;

/**
 * <b>STR - Part H</b>
 * <p>
 * Description of Action Taken
 * </p>
 * 
 * @author AWAK001
 * 
 */
@FormData(type = Type.Field)
public class PartHAction implements PartInterface {

	/**
	 * 
	 */
	public PartHAction() {
	}

	@FormField(name = "str_h_action_taken_desc")
	private String actionTakenDesc;

	/**
	 * @return the actionTakenDesc
	 */
	public String getActionTakenDesc() {
		return actionTakenDesc;
	}

	/**
	 * @param actionTakenDesc the actionTakenDesc to set
	 */
	public void setActionTakenDesc(String actionTakenDesc) {
		this.actionTakenDesc = actionTakenDesc;
	}

}
