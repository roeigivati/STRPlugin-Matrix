/**
 * 
 */
package com.ifs.str.parts;

import java.util.List;

import com.ifs.forms.xml.FieldType;
import com.ifs.forms.xml.SetType;
import com.ifs.fstream.annotations.FormData;
import com.ifs.fstream.annotations.FormData.Type;
import com.ifs.fstream.annotations.FormField;
import com.ifs.fstream.annotations.FormFieldImplicit;

/**
 * <b>STR - Part B1</b>
 * <p>
 * Information about how the transaction was initiated
 * </p>
 * 
 * @author AWAK001
 *
 */
@FormData(type = Type.FieldSet, setType = SetType.transactions)
public class PartB1TransactionInitiation implements PartInterface {

	/**
	 * 
	 */
	public PartB1TransactionInitiation() {
	}

	private String transactionKey;
	
	private String partyKey;
	
	private String conductorKey;
	
	private String branchKey;

	private String transactionCode;



	@FormField(name = "str_transaction_serial_")
	private Integer txnSerialNo;

	@FormField(name = "str_b_txn_date_", type = FieldType.Date, format = "yyyy-MM-dd HH:mm:ss")
	private String txnDate;

	@FormField(name = "str_b_txn_time_")
	private String txnTime;

	@FormField(name = "str_b_txn_night_deposit_")
	private String txnNightDeposit;

	@FormField(name = "str_b_txn_posting_date_" , type = FieldType.Date, format = "yyyy-MM-dd HH:mm:ss")
	private String txnPostingDate;

	@FormField(name = "str_b_txn_initial_fund_type_")
	private String txnInitialFundType;

	@FormField(name = "str_b_txn_initial_fund_desc_")
	private String txnInitialFundDesc;

	@FormField(name = "str_b_txn_amount_")
	private String txnAmount;

	@FormField(name = "str_b_txn_currency_code_")
	private String txnCurrencyCode;

	//AML 4160 Fields is not needed.
	//@FormField(name = "str_b_txn_initial_fund_other_name_")
	//private String txnInitialFundOtherName;

	@FormField(name = "str_b_txn_initial_fund_other_acc_no_")
	private String txnInitialFundOtherAccNo;

	@FormField(name = "str_b_txn_conducted_type_")
	private String txnConductedType;

	@FormField(name = "str_b_txn_conducted_type_desc_")
	private String txnConductedTypeDesc;

	@FormField(name = "str_b_txn_suspect_id_no_")
	private String txnSuspectIdNo;

	@FormFieldImplicit
	private PartDTransactionConductor conductor;

	@FormFieldImplicit
	private List<PartB2DispositionCompletion> dispositions;

	public String getTransactionKey() {
		return transactionKey;
	}

	public void setTransactionKey(String transactionKey) {
		this.transactionKey = transactionKey;
	}
	public String getPartyKey() {
		return partyKey;
	}

	public void setPartyKey(String partyKey) {
		this.partyKey = partyKey;
	}

	public String getConductorKey() {
		return conductorKey;
	}

	public void setConductorKey(String conductorKey) {
		this.conductorKey = conductorKey;
	}

	public String getBranchKey() {
		return branchKey;
	}

	public void setBranchKey(String branchKey) {
		this.branchKey = branchKey;
	}
	/**
	 * @return the txnSerialNo
	 */
	public Integer getTxnSerialNo() {
		return txnSerialNo;
	}

	/**
	 * @param txnSerialNo the txnSerialNo to set
	 */
	public void setTxnSerialNo(Integer txnSerialNo) {
		this.txnSerialNo = txnSerialNo;
	}

	/**
	 * @return the txnDate
	 */
	public String getTxnDate() {
		return txnDate;
	}

	/**
	 * @param txnDate the txnDate to set
	 */
	public void setTxnDate(String txnDate) {
		this.txnDate = txnDate;
	}

	/**
	 * @return the txnTime
	 */
	public String getTxnTime() {
		return txnTime;
	}

	/**
	 * @param txnTime the txnTime to set
	 */
	public void setTxnTime(String txnTime) {
		this.txnTime = txnTime;
	}

	/**
	 * @return the txnNightDeposit
	 */
	public String getTxnNightDeposit() {
		return txnNightDeposit;
	}

	/**
	 * @param txnNightDeposit the txnNightDeposit to set
	 */
	public void setTxnNightDeposit(String txnNightDeposit) {
		this.txnNightDeposit = txnNightDeposit;
	}

	/**
	 * @return the txnPostingDate
	 */
	public String getTxnPostingDate() {
		return txnPostingDate;
	}

	/**
	 * @param txnPostingDate the txnPostingDate to set
	 */
	public void setTxnPostingDate(String txnPostingDate) {
		this.txnPostingDate = txnPostingDate;
	}

	/**
	 * @return the txnInitialFundType
	 */
	public String getTxnInitialFundType() {
		return txnInitialFundType;
	}

	/**
	 * @param txnInitialFundType the txnInitialFundType to set
	 */
	public void setTxnInitialFundType(String txnInitialFundType) {
		this.txnInitialFundType = txnInitialFundType;
	}

	/**
	 * @return the txnInitialFundDesc
	 */
	public String getTxnInitialFundDesc() {
		return txnInitialFundDesc;
	}

	/**
	 * @param txnInitialFundDesc the txnInitialFundDesc to set
	 */
	public void setTxnInitialFundDesc(String txnInitialFundDesc) {
		this.txnInitialFundDesc = txnInitialFundDesc;
	}

	/**
	 * @return the txnAmount
	 */
	public String getTxnAmount() {
		return txnAmount;
	}

	/**
	 * @param txnAmount the txnAmount to set
	 */
	public void setTxnAmount(String txnAmount) {
		this.txnAmount = txnAmount;
	}

	/**
	 * @return the txnCurrencyCode
	 */
	public String getTxnCurrencyCode() {
		return txnCurrencyCode;
	}

	/**
	 * @param txnCurrencyCode the txnCurrencyCode to set
	 */
	public void setTxnCurrencyCode(String txnCurrencyCode) {
		this.txnCurrencyCode = txnCurrencyCode;
	}

//	/**
//	 * @return the txnInitialFundOtherName
//	 */
//	public String getTxnInitialFundOtherName() {
//		return txnInitialFundOtherName;
//	}
//
//	/**
//	 * @param txnInitialFundOtherName the txnInitialFundOtherName to set
//	 */
//	public void setTxnInitialFundOtherName(String txnInitialFundOtherName) {
//		this.txnInitialFundOtherName = txnInitialFundOtherName;
//	}

	/**
	 * @return the txnInitialFundOtherAccNo
	 */
	public String getTxnInitialFundOtherAccNo() {
		return txnInitialFundOtherAccNo;
	}

	/**
	 * @param txnInitialFundOtherAccNo the txnInitialFundOtherAccNo to set
	 */
	public void setTxnInitialFundOtherAccNo(String txnInitialFundOtherAccNo) {
		this.txnInitialFundOtherAccNo = txnInitialFundOtherAccNo;
	}

	/**
	 * @return the txnConductedType
	 */
	public String getTxnConductedType() {
		return txnConductedType;
	}

	/**
	 * @param txnConductedType the txnConductedType to set
	 */
	public void setTxnConductedType(String txnConductedType) {
		this.txnConductedType = txnConductedType;
	}

	/**
	 * @return the txnConductedTypeDesc
	 */
	public String getTxnConductedTypeDesc() {
		return txnConductedTypeDesc;
	}

	/**
	 * @param txnConductedTypeDesc the txnConductedTypeDesc to set
	 */
	public void setTxnConductedTypeDesc(String txnConductedTypeDesc) {
		this.txnConductedTypeDesc = txnConductedTypeDesc;
	}

	/**
	 * @return the txnSuspectIdNo
	 */
	public String getTxnSuspectIdNo() {
		return txnSuspectIdNo;
	}

	/**
	 * @param txnSuspectIdNo the txnSuspectIdNo to set
	 */
	public void setTxnSuspectIdNo(String txnSuspectIdNo) {
		this.txnSuspectIdNo = txnSuspectIdNo;
	}

	public String getTransactionCode() {
		return transactionCode;
	}

	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}
	
	/**
	 * @return the conductor
	 */
	public PartDTransactionConductor getConductor() {
		return conductor;
	}

	/**
	 * @param conductor the conductor to set
	 */
	public void addConductor(PartDTransactionConductor conductor) {
		this.conductor = conductor;
	}

	/**
	 * @return the dispositions
	 */
	public List<PartB2DispositionCompletion> getDispositions() {
		return dispositions;
	}

	/**
	 * @param dispositions the dispositions to set
	 */
	public void addDispositions(List<PartB2DispositionCompletion> dispositions) {
		int i = 0;
		for (PartB2DispositionCompletion completion : dispositions) {
			completion.setTxnDispositionSerialNo(++i);
		}
		this.dispositions = dispositions;
	}

	@Override
	public String toString() {
		return "PartB1TransactionInitiation [transactionKey=" + transactionKey + ", partyKey=" + partyKey
				+ ", conductorKey=" + conductorKey + ", branchKey=" + branchKey + ", txnSerialNo=" + txnSerialNo
				+ ", txnDate=" + txnDate + ", txnTime=" + txnTime + ", txnNightDeposit=" + txnNightDeposit
				+ ", txnPostingDate=" + txnPostingDate + ", txnInitialFundType=" + txnInitialFundType
				+ ", txnInitialFundDesc=" + txnInitialFundDesc + ", txnAmount=" + txnAmount + ", txnCurrencyCode="
				+ txnCurrencyCode   
				+ ", txnInitialFundOtherAccNo=" + txnInitialFundOtherAccNo + ", txnConductedType=" + txnConductedType
				+ ", transactionCode=" + transactionCode
				+ ", txnConductedTypeDesc=" + txnConductedTypeDesc + ", txnSuspectIdNo=" + txnSuspectIdNo + "]";
	}



}
