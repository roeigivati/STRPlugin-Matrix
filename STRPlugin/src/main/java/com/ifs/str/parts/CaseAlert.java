package com.ifs.str.parts;

public class CaseAlert {

	private String caseInternalId;
	
	private String caseId;
	
	private String alertId;
	
	private String alertInternalId;

	public String getCaseInternalId() {
		return caseInternalId;
	}

	public void setCaseInternalId(String caseInternalId) {
		this.caseInternalId = caseInternalId;
	}

	@Override
	public String toString() {
		return "CaseAlert [caseInternalId=" + caseInternalId + ", caseId=" + caseId + ", alertId=" + alertId
				+ ", alertInternalId=" + alertInternalId + "]";
	}

	private String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	private String getAlertId() {
		return alertId;
	}

	public void setAlertId(String alertId) {
		this.alertId = alertId;
	}

	private String getAlertInternalId() {
		return alertInternalId;
	}

	public void setAlertInternalId(String alertInternalId) {
		this.alertInternalId = alertInternalId;
	}
	
	
}
