package com.ifs.str.parts;

import com.ifs.forms.xml.FieldType;
import com.ifs.fstream.annotations.FormData;
import com.ifs.fstream.annotations.FormField;
import com.ifs.fstream.annotations.FormData.Type;

/**
 * <b>STR - Part A</b>
 * <p>
 * Information about where the transaction took place
 * </p>
 * 
 * @author AWAK001
 */
@FormData(type = Type.Field)
public class PartAReportingFI implements PartInterface {

	public PartAReportingFI() {
	}

		
//	@FormField(name = "str_a_rept_org")
//	private String reportOrig;
	
	@FormField(name = "str_a_fi_id")
	private String fiId;

	@FormField(name = "str_a_fi_name")
	private String fiName;

	@FormField(name = "str_a_fi_type")
	private String fiType;

	@FormField(name = "str_a1_txn_status")
	private String trxStatus;
	
	@FormField(name = "str_correction")
	private String reportType;
	
	@FormField(name = "str_reporting_date",  type = FieldType.Date, format = "yyyy-MM-dd HH:mm:ss")
	private String reportingDate;
	
	@FormField(name = "str_reporting_time")
	private String reportingTime;
	
	@FormField(name = "str_original_date",  type = FieldType.Date, format = "yyyy-MM-dd HH:mm:ss")
	private String originalDate;
	
	public PartAReportingFI(String fiId, String fiName, String fiType, String trxStatus, String reportType,
			String reportingDate, String reportingTime, String originalDate, String originalTime, String caseBU) {
		this.fiId = fiId;
		this.fiName = fiName;
		this.fiType = fiType;
		this.trxStatus = trxStatus;
		this.reportType = reportType;
		this.reportingDate = reportingDate;
		this.reportingTime = reportingTime;
		this.originalDate = originalDate;
		this.originalTime = originalTime;
		this.caseBU = caseBU;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getReportingDate() {
		return reportingDate;
	}

	public void setReportingDate(String reportingDate) {
		this.reportingDate = reportingDate;
	}

	public String getReportingTime() {
		return reportingTime;
	}

	public void setReportingTime(String reportingTime) {
		this.reportingTime = reportingTime;
	}

	public String getOriginalDate() {
		return originalDate;
	}

	public void setOriginalDate(String originalDate) {
		this.originalDate = originalDate;
	}

	public String getOriginalTime() {
		return originalTime;
	}

	public void setOriginalTime(String originalTime) {
		this.originalTime = originalTime;
	}

	@FormField(name = "str_original_time")
	private String originalTime;
	
	
	private String caseBU;

	public String getCaseBU() {
		return caseBU;
	}

	public void setCaseBU(String caseBU) {
		this.caseBU = caseBU;
	}

	/**
	 * @return the fiId
	 */
	public String getFiId() {
		return fiId;
	}

	/**
	 * @param fiId the fiId to set
	 */
	public void setFiId(String fiId) {
		this.fiId = fiId;
	}

	/**
	 * @return the fiName
	 */
	public String getFiName() {
		return fiName;
	}

	/**
	 * @param fiName the fiName to set
	 */
	public void setFiName(String fiName) {
		this.fiName = fiName;
	}

	/**
	 * @return the fiType
	 */
	public String getFiType() {
		return fiType;
	}

	/**
	 * @param fiType the fiType to set
	 */
	public void setFiType(String fiType) {
		this.fiType = fiType;
	}

	/**
	 * @return the trxStatus
	 */
	public String getTrxStatus() {
		return trxStatus;
	}

	/**
	 * @param trxStatus the trxStatus to set
	 */
	public void setTrxStatus(String trxStatus) {
		this.trxStatus = trxStatus;
	}

	@Override
	public String toString() {
		return "PartAReportingFI [fiId=" + fiId + ", fiName=" + fiName + ", fiType=" + fiType + ", trxStatus="
				+ trxStatus + ", caseBU="
						+ caseBU + "]";
	}

}
