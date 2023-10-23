/**
 * 
 */
package com.ifs.str;

import java.util.List;

import com.ifs.fstream.annotations.FormData;
import com.ifs.fstream.annotations.FormFieldImplicit;
import com.ifs.str.parts.PartABranch;
import com.ifs.str.parts.PartAReportingFI;
import com.ifs.str.parts.PartB1TransactionInitiation;
import com.ifs.str.parts.PartGDescription;
import com.ifs.str.parts.PartHAction;
import com.ifs.str.parts.PartInterface;

/**
 * @author AWAK001
 *
 */
@FormData
public class STRData implements PartInterface {

	/**
	 * 
	 */
	public STRData() {
	}

	@FormFieldImplicit
	private PartAReportingFI reportingFI;

	@FormFieldImplicit
	private PartABranch branch;

	@FormFieldImplicit
	private List<PartB1TransactionInitiation> transactions;

	@FormFieldImplicit
	private PartGDescription description;

	@FormFieldImplicit
	private PartHAction action;

	/**
	 * @return the reportingFI
	 */
	public PartAReportingFI getReportingFI() {
		return reportingFI;
	}

	/**
	 * @param reportingFI the reportingFI to set
	 */
	public void setReportingFI(PartAReportingFI reportingFI) {
		this.reportingFI = reportingFI;
	}

	/**
	 * @return the branch
	 */
	public PartABranch getBranch() {
		return branch;
	}

	/**
	 * @param branch the branch to set
	 */
	public void setBranch(PartABranch branch) {
		this.branch = branch;
	}

	/**
	 * @return the transactions
	 */
	public List<PartB1TransactionInitiation> getTransactions() {
		return transactions;
	}

	/**
	 * @param transactions the transactions to set
	 */
	public void setTransactions(List<PartB1TransactionInitiation> transactions) {
		int i =0;
		for (PartB1TransactionInitiation initiation : transactions) {
			initiation.setTxnSerialNo(++i);
		}
		
		this.transactions = transactions;
	}

	/**
	 * @return the description
	 */
	public PartGDescription getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(PartGDescription description) {
		this.description = description;
	}

	/**
	 * @return the action
	 */
	public PartHAction getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(PartHAction action) {
		this.action = action;
	}

}
