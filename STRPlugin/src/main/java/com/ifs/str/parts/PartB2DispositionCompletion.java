/**
 * 
 */
package com.ifs.str.parts;

import com.ifs.forms.xml.SetType;
import com.ifs.fstream.annotations.FormData;
import com.ifs.fstream.annotations.FormField;
import com.ifs.fstream.annotations.FormFieldImplicit;
import com.ifs.fstream.annotations.FormData.Type;

/**
 * <b>STR - Part B2</b>
 * <p>
 * Information about how the transaction was completed
 * </p>
 * 
 * @author AWAK001
 *
 */
@FormData(type = Type.ChildFieldSet, setType = SetType.dispositions, parentSetType = SetType.transactions)
public class PartB2DispositionCompletion implements PartInterface {

	/**
	 * 
	 */
	public PartB2DispositionCompletion() {
	}
	
	private String sequenceNumber;
	
	public String getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(String sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	private String transactionKey;
	
	
	@FormField(name = "str_disposition_serial_")
	private Integer txnDispositionSerialNo;

	@FormField(name = "str_b2_txn_disposition_on_behalf_indicator_")
	private String txnDispositionOnBehalfIndicator;

	@FormField(name = "str_b2_txn_disposition_type_")
	private String txnDispositionType;

	@FormField(name = "str_b2_txn_disposition_type_desc_")
	private String txnDispositionTypeDesc;

	@FormField(name = "str_b2_txn_disposition_policy_no_")
	private String txnDispositionPolicyNo;

	@FormField(name = "str_b2_txn_disposition_amount_")
	private String txnDispositionAmount;

	@FormField(name = "str_b2_txn_disposition_currency_code_")
	private String txnDispositionCurrencyCode;

	//AML 4160 Fields is not needed.
	//@FormField(name = "str_b2_txn_disposition_other_name_")
	//private String txnDispositionOtherName;

	@FormField(name = "str_b2_txn_disposition_other_acc_no_")
	private String txnDispositionOtherAccNo;

	@FormFieldImplicit
	private PartCDispositionAccount account;

	@FormFieldImplicit
	private PartEOnBehalfOfEntity onBehalfOfEntity;

	@FormFieldImplicit
	private PartFOnBehalfOfIndividual onBehalfOfIndividual;

	/**
	 * @return the txnDispositionSerialNo
	 */
	public Integer getTxnDispositionSerialNo() {
		return txnDispositionSerialNo;
	}

	/**
	 * @param txnDispositionSerialNo the txnDispositionSerialNo to set
	 */
	public void setTxnDispositionSerialNo(Integer txnDispositionSerialNo) {
		this.txnDispositionSerialNo = txnDispositionSerialNo;
	}

	/**
	 * @return the txnDispositionOnBehalfIndicator
	 */
	public String getTxnDispositionOnBehalfIndicator() {
		return txnDispositionOnBehalfIndicator;
	}

	/**
	 * @param txnDispositionOnBehalfIndicator the txnDispositionOnBehalfIndicator to
	 *                                        set
	 */
	public void setTxnDispositionOnBehalfIndicator(String txnDispositionOnBehalfIndicator) {
		this.txnDispositionOnBehalfIndicator = txnDispositionOnBehalfIndicator;
	}

	/**
	 * @return the txnDispositionType
	 */
	public String getTxnDispositionType() {
		return txnDispositionType;
	}

	/**
	 * @param txnDispositionType the txnDispositionType to set
	 */
	public void setTxnDispositionType(String txnDispositionType) {
		this.txnDispositionType = txnDispositionType;
	}

	/**
	 * @return the txnDispositionTypeDesc
	 */
	public String getTxnDispositionTypeDesc() {
		return txnDispositionTypeDesc;
	}

	/**
	 * @param txnDispositionTypeDesc the txnDispositionTypeDesc to set
	 */
	public void setTxnDispositionTypeDesc(String txnDispositionTypeDesc) {
		this.txnDispositionTypeDesc = txnDispositionTypeDesc;
	}

	/**
	 * @return the txnDispositionPolicyNo
	 */
	public String getTxnDispositionPolicyNo() {
		return txnDispositionPolicyNo;
	}

	/**
	 * @param txnDispositionPolicyNo the txnDispositionPolicyNo to set
	 */
	public void setTxnDispositionPolicyNo(String txnDispositionPolicyNo) {
		this.txnDispositionPolicyNo = txnDispositionPolicyNo;
	}

	/**
	 * @return the txnDispositionAmount
	 */
	public String getTxnDispositionAmount() {
		return txnDispositionAmount;
	}

	/**
	 * @param txnDispositionAmount the txnDispositionAmount to set
	 */
	public void setTxnDispositionAmount(String txnDispositionAmount) {
		this.txnDispositionAmount = txnDispositionAmount;
	}

	/**
	 * @return the txnDispositionCurrencyCode
	 */
	public String getTxnDispositionCurrencyCode() {
		return txnDispositionCurrencyCode;
	}

	/**
	 * @param txnDispositionCurrencyCode the txnDispositionCurrencyCode to set
	 */
	public void setTxnDispositionCurrencyCode(String txnDispositionCurrencyCode) {
		this.txnDispositionCurrencyCode = txnDispositionCurrencyCode;
	}

//	/**
//	 * @return the txnDispositionOtherName
//	 */
//	public String getTxnDispositionOtherName() {
//		return txnDispositionOtherName;
//	}
//
//	/**
//	 * @param txnDispositionOtherName the txnDispositionOtherName to set
//	 */
//	public void setTxnDispositionOtherName(String txnDispositionOtherName) {
//		this.txnDispositionOtherName = txnDispositionOtherName;
//	}

	/**
	 * @return the txnDispositionOtherAccNo
	 */
	public String getTxnDispositionOtherAccNo() {
		return txnDispositionOtherAccNo;
	}

	/**
	 * @param txnDispositionOtherAccNo the txnDispositionOtherAccNo to set
	 */
	public void setTxnDispositionOtherAccNo(String txnDispositionOtherAccNo) {
		this.txnDispositionOtherAccNo = txnDispositionOtherAccNo;
	}

	/**
	 * @return the account
	 */
	public PartCDispositionAccount getAccount() {
		return account;
	}

	/**
	 * @param account the account to set
	 */
	public void addCDispositionAccount(PartCDispositionAccount account) {
		this.account = account;
	}

	/**
	 * @return the onBehalfOfEntity
	 */
	public PartEOnBehalfOfEntity getOnBehalfOfEntity() {
		return onBehalfOfEntity;
	}

	/**
	 * @param onBehalfOfEntity the onBehalfOfEntity to set
	 */
	public void addOnBehalfOfEntity(PartEOnBehalfOfEntity onBehalfOfEntity) {
		this.onBehalfOfEntity = onBehalfOfEntity;
	}

	/**
	 * @return the onBehalfOfIndividual
	 */
	public PartFOnBehalfOfIndividual getOnBehalfOfIndividual() {
		return onBehalfOfIndividual;
	}

	/**
	 * @param onBehalfOfIndividual the onBehalfOfIndividual to set
	 */
	public void addOnBehalfOfIndividual(PartFOnBehalfOfIndividual onBehalfOfIndividual) {
		this.onBehalfOfIndividual = onBehalfOfIndividual;
	}
	
	public String getTransactionKey() {
		return transactionKey;
	}

	public void setTransactionKey(String transactionKey) {
		this.transactionKey = transactionKey;
	}

	@Override
	public String toString() {
		return "PartB2DispositionCompletion [sequenceNumber=" + sequenceNumber + ", transactionKey=" + transactionKey
				+ ", txnDispositionSerialNo=" + txnDispositionSerialNo + ", txnDispositionOnBehalfIndicator="
				+ txnDispositionOnBehalfIndicator + ", txnDispositionType=" + txnDispositionType
				+ ", txnDispositionTypeDesc=" + txnDispositionTypeDesc + ", txnDispositionPolicyNo="
				+ txnDispositionPolicyNo + ", txnDispositionAmount=" + txnDispositionAmount
				+ ", txnDispositionCurrencyCode=" + txnDispositionCurrencyCode 
				 + ", txnDispositionOtherAccNo=" + txnDispositionOtherAccNo + ", account="
				+ account + ", onBehalfOfEntity=" + onBehalfOfEntity + ", onBehalfOfIndividual=" + onBehalfOfIndividual
				+ "]";
	}
}
