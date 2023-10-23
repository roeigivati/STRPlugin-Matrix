package com.ifs.str.parts;

public class AlertSTRCount {
	
	private String branchKey;
	
	private String alertID;

	public String getBranchKey() {
		return branchKey;
	}

	public void setBranchKey(String branchKey) {
		this.branchKey = branchKey;
	}

	public String getAlertID() {
		return alertID;
	}

	public void setAlertID(String alertID) {
		this.alertID = alertID;
	}

	@Override
	public String toString() {
		return "AlertSTRCount [branchKey=" + branchKey + ", alertID=" + alertID + "]";
	}
}