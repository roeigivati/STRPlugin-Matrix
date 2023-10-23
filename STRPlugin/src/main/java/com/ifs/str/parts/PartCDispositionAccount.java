/**
 * 
 */
package com.ifs.str.parts;

import java.util.Date;

import com.ifs.forms.xml.FieldType;
import com.ifs.forms.xml.SetType;
import com.ifs.fstream.annotations.FormData;
import com.ifs.fstream.annotations.FormField;
import com.ifs.fstream.annotations.FormData.Type;

/**
 * <b>STR - Part C</b>
 * <p>
 * Account information if the transaction involved an account
 * </p>
 * 
 * @author AWAK001
 *
 */
@FormData(type = Type.ChildFieldSet, setType = SetType.dispositions, parentSetType = SetType.transactions)
public class PartCDispositionAccount implements PartInterface {

	/**
	 * 
	 */
	public PartCDispositionAccount() {
	}
	
	private String accountKey;

	@FormField(name = "str_c_acc_branch_no_")
	private String accBranchNo;

	@FormField(name = "str_c_acc_no_")
	private String accNo;

	@FormField(name = "str_c_acc_type_")
	private String accType;

	@FormField(name = "str_c_acc_type_desc_")
	private String accTypeDesc;

	@FormField(name = "str_c_acc_currency_code_")
	private String accCurrencyCode;

	@FormField(name = "str_c_acc_date_opened_", type = FieldType.Date, format = "yyyy-MM-dd")
	private String accDateOpened;

	@FormField(name = "str_c_acc_date_closed_", type = FieldType.Date, format = "yyyy-MM-dd")
	private String accDateClosed;

	@FormField(name = "str_c_acc_status_")
	private String accStatus;

	@FormField(name = "str_c_acc_holder_name_a_")
	private String accHolderNameA;

	@FormField(name = "str_c_acc_holder_name_b_")
	private String accHolderNameB;

	@FormField(name = "str_c_acc_holder_name_c_")
	private String accHolderNameC;

	@FormField(name = "str_c_inst_transit_acc_no_")
	private String instTransitAccNo;

	@Override
	public String toString() {
		return "PartCDispositionAccount [accBranchNo=" + accBranchNo + ", accNo=" + accNo + ", accType=" + accType
				+ ", accTypeDesc=" + accTypeDesc + ", accCurrencyCode=" + accCurrencyCode + ", accDateOpened="
				+ accDateOpened + ", accDateClosed=" + accDateClosed + ", accStatus  =" + accStatus + ", accHolderNameA="
				+ accHolderNameA + ", accHolderNameB=" + accHolderNameB + ", accHolderNameC=" + accHolderNameC
				+ ", instTransitAccNo=" + instTransitAccNo + "]";
	}

	public String getAccBranchNo() {
		return accBranchNo;
	}

	public void setAccBranchNo(String accBranchNo) {
		this.accBranchNo = accBranchNo;
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public String getAccTypeDesc() {
		return accTypeDesc;
	}

	public void setAccTypeDesc(String accTypeDesc) {
		this.accTypeDesc = accTypeDesc;
	}

	public String getAccCurrencyCode() {
		return accCurrencyCode;
	}

	public void setAccCurrencyCode(String accCurrencyCode) {
		this.accCurrencyCode = accCurrencyCode;
	}

	public String getAccDateOpened() {
		return accDateOpened;
	}

	public void setAccDateOpened(String accDateOpened) {
		this.accDateOpened = accDateOpened;
	}

	public String getAccDateClosed() {
		return accDateClosed;
	}

	public void setAccDateClosed(String accDateClosed) {
		this.accDateClosed = accDateClosed;
	}

	public String getAccStatus() {
		return accStatus;
	}

	public void setAccStatus(String accStatus) {
		this.accStatus = accStatus;
	}

	public String getAccHolderNameA() {
		return accHolderNameA;
	}

	public void setAccHolderNameA(String accHolderNameA) {
		this.accHolderNameA = accHolderNameA;
	}

	public String getAccHolderNameB() {
		return accHolderNameB;
	}

	public void setAccHolderNameB(String accHolderNameB) {
		this.accHolderNameB = accHolderNameB;
	}

	public String getAccHolderNameC() {
		return accHolderNameC;
	}

	public void setAccHolderNameC(String accHolderNameC) {
		this.accHolderNameC = accHolderNameC;
	}

	public String getInstTransitAccNo() {
		return instTransitAccNo;
	}

	public void setInstTransitAccNo(String instTransitAccNo) {
		this.instTransitAccNo = instTransitAccNo;
	}

	public String getAccountKey() {
		return accountKey;
	}

	public void setAccountKey(String accountKey) {
		this.accountKey = accountKey;
	}
}
