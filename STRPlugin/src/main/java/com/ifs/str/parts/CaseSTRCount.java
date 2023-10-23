package com.ifs.str.parts;

import com.ifs.util.Utility;

public class CaseSTRCount {

	private String branchKey;
	
	private Integer transactionCount;
	
	private Integer strCount;

	public CaseSTRCount(String branchKey, Integer transactionCount, Integer strCount) {
		this.branchKey = branchKey;
		this.transactionCount = transactionCount;
		this.strCount = strCount;
	}

	@Override
	public String toString() {
		return " <tr><td>" + Utility.valueOf(branchKey," ") + "</td><td>" + transactionCount + "</td><td>" + strCount + "</td></tr>";
	}
	
	
	
	
}
