/**
 * 
 */
package com.ifs.str.parts;

import com.ifs.fstream.annotations.FormData;
import com.ifs.fstream.annotations.FormField;
import com.ifs.fstream.annotations.FormData.Type;

/**
 * <b>STR - Part G</b>
 * <p>
 * Description of Suspicious Activity
 * </p>
 * 
 * @author AWAK001
 * 
 */
@FormData(type = Type.Field)
public class PartGDescription implements PartInterface {

	/**
	 * 
	 */
	public PartGDescription() {
	}

	@FormField(name = "str_g_suspicious_activity_desc")
	private String suspiciousActivityDesc;

	/**
	 * @return the suspiciousActivityDesc
	 */
	public String getSuspiciousActivityDesc() {
		return suspiciousActivityDesc;
	}

	/**
	 * @param suspiciousActivityDesc the suspiciousActivityDesc to set
	 */
	public void setSuspiciousActivityDesc(String suspiciousActivityDesc) {
		this.suspiciousActivityDesc = suspiciousActivityDesc;
	}
	
	
}
