/**
 * 
 */
package com.ifs.str.parts;

import com.ifs.forms.xml.FieldType;
import com.ifs.forms.xml.SetType;
import com.ifs.fstream.annotations.FormData;
import com.ifs.fstream.annotations.FormField;
import com.ifs.fstream.annotations.FormData.Type;

/**
 * <b>STR - Part D</b>
 * <p>
 * Information about the individual conducting the transaction
 * </p>
 * 
 * @author AWAK001
 *
 */
@FormData(type = Type.FieldSet, setType = SetType.transactions)
public class PartDTransactionConductor implements PartInterface,Cloneable {

	/**
	 * 
	 */
	public PartDTransactionConductor() {
	}
	
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return (PartDTransactionConductor)super.clone();
	}
	
	private String partyKey;
	
	public String getPartyKey() {
		return partyKey;
	}

	public void setPartyKey(String partyKey) {
		this.partyKey = partyKey;
	}

	@FormField(name = "str_d_suspect_surname_")
	private String suspectSurname;

	@FormField(name = "str_d_suspect_given_name_")
	private String suspectGivenName;

	@FormField(name = "str_d_suspect_initial_")
	private String suspectInitial;

	@FormField(name = "str_d_suspect_client_no_")
	private String suspectClientNo;

	@FormField(name = "str_d_suspect_birthday_", type = FieldType.Date, format = "yyyy-MM-dd")
	private String suspectBirthday;

	@FormField(name = "str_d_suspect_address_")
	private String suspectAddress;

	@FormField(name = "str_d_suspect_city_")
	private String suspectCity;

	@FormField(name = "str_d_suspect_state_")
	private String suspectState;

	@FormField(name = "str_d_suspect_country_")
	private String suspectCountry;

	@FormField(name = "str_d_suspect_zip_code_")
	private String suspectZipCode;

	@FormField(name = "str_d_suspect_zip_code_processed_")
	private String suspectZipCodeProcessed;

	@FormField(name = "str_d_suspect_country_of_residence_")
	private String suspectCountryOfResidence;

	@FormField(name = "str_d_suspect_country_of_citizenship_")
	private String suspectCountryOfCitizenship;

	@FormField(name = "str_d_suspect_home_area_code_")
	private String suspectHomeAreaCode;

	@FormField(name = "str_d_suspect_home_phone_")
	private String suspectHomePhone;

	@FormField(name = "str_d_suspect_identifier_")
	private String suspectIdentifier;

	@FormField(name = "str_d_suspect_identifier_shadow_")
	private String suspectIdentifierShadow;

	@FormField(name = "str_d_suspect_identifier_desc_")
	private String suspectIdentifierDesc;

	@FormField(name = "str_d_suspect_id_no_")
	private String suspectIdNo;

	@FormField(name = "str_d_suspect_id_issue_state_")
	private String suspectIdIssueState;

	@FormField(name = "str_d_suspect_id_issue_country_")
	private String suspectIdIssueCountry;

	@FormField(name = "str_d_suspect_occupation_")
	private String suspectOccupation;

	@FormField(name = "str_d_suspect_business_area_code_")
	private String suspectBusinessAreaCode;

	@FormField(name = "str_d_suspect_business_phone_")
	private String suspectBusinessPhone;

	@FormField(name = "str_d_suspect_business_phone_ext_")
	private String suspectBusinessPhoneExt;

	@FormField(name = "str_d_suspect_employer_")
	private String suspectEmployer;

	@FormField(name = "str_d_suspect_employer_area_code_")
	private String suspectEmployerAreaCode;

	@FormField(name = "str_d_suspect_employer_phone_")
	private String suspectEmployerPhone;

	@FormField(name = "str_d_suspect_employer_phone_ext_")
	private String suspectEmployerPhoneExt;

	@FormField(name = "str_d_suspect_employer_address_")
	private String suspectEmployerAddress;

	@FormField(name = "str_d_suspect_employer_city_")
	private String suspectEmployerCity;

	@FormField(name = "str_d_suspect_employer_state_")
	private String suspectEmployerState;

	@FormField(name = "str_d_suspect_employer_country_")
	private String suspectEmployerCountry;

	@FormField(name = "str_d_suspect_employer_zip_code_")
	private String suspectEmployerZipCode;

	@FormField(name = "str_d_suspect_employer_zip_code_processed_")
	private String suspectEmployerZipCodeProcessed;

	/**
	 * @return the suspectSurname
	 */
	public String getSuspectSurname() {
		return suspectSurname;
	}

	/**
	 * @param suspectSurname the suspectSurname to set
	 */
	public void setSuspectSurname(String suspectSurname) {
		this.suspectSurname = suspectSurname;
	}

	/**
	 * @return the suspectGivenName
	 */
	public String getSuspectGivenName() {
		return suspectGivenName;
	}

	/**
	 * @param suspectGivenName the suspectGivenName to set
	 */
	public void setSuspectGivenName(String suspectGivenName) {
		this.suspectGivenName = suspectGivenName;
	}

	/**
	 * @return the suspectInitial
	 */
	public String getSuspectInitial() {
		return suspectInitial;
	}

	/**
	 * @param suspectInitial the suspectInitial to set
	 */
	public void setSuspectInitial(String suspectInitial) {
		this.suspectInitial = suspectInitial;
	}

	/**
	 * @return the suspectClientNo
	 */
	public String getSuspectClientNo() {
		return suspectClientNo;
	}

	/**
	 * @param suspectClientNo the suspectClientNo to set
	 */
	public void setSuspectClientNo(String suspectClientNo) {
		this.suspectClientNo = suspectClientNo;
	}

	/**
	 * @return the suspectBirthday
	 */
	public String getSuspectBirthday() {
		return suspectBirthday;
	}

	/**
	 * @param suspectBirthday the suspectBirthday to set
	 */
	public void setSuspectBirthday(String suspectBirthday) {
		this.suspectBirthday = suspectBirthday;
	}

	/**
	 * @return the suspectAddress
	 */
	public String getSuspectAddress() {
		return suspectAddress;
	}

	/**
	 * @param suspectAddress the suspectAddress to set
	 */
	public void setSuspectAddress(String suspectAddress) {
		this.suspectAddress = suspectAddress;
	}

	/**
	 * @return the suspectCity
	 */
	public String getSuspectCity() {
		return suspectCity;
	}

	/**
	 * @param suspectCity the suspectCity to set
	 */
	public void setSuspectCity(String suspectCity) {
		this.suspectCity = suspectCity;
	}

	/**
	 * @return the suspectState
	 */
	public String getSuspectState() {
		return suspectState;
	}

	/**
	 * @param suspectState the suspectState to set
	 */
	public void setSuspectState(String suspectState) {
		this.suspectState = suspectState;
	}

	/**
	 * @return the suspectCountry
	 */
	public String getSuspectCountry() {
		return suspectCountry;
	}

	/**
	 * @param suspectCountry the suspectCountry to set
	 */
	public void setSuspectCountry(String suspectCountry) {
		this.suspectCountry = suspectCountry;
	}

	/**
	 * @return the suspectZipCode
	 */
	public String getSuspectZipCode() {
		return suspectZipCode;
	}

	/**
	 * @param suspectZipCode the suspectZipCode to set
	 */
	public void setSuspectZipCode(String suspectZipCode) {
		this.suspectZipCode = suspectZipCode;
	}

	/**
	 * @return the suspectZipCodeProcessed
	 */
	public String getSuspectZipCodeProcessed() {
		return suspectZipCodeProcessed;
	}

	/**
	 * @param suspectZipCodeProcessed the suspectZipCodeProcessed to set
	 */
	public void setSuspectZipCodeProcessed(String suspectZipCodeProcessed) {
		this.suspectZipCodeProcessed = suspectZipCodeProcessed;
	}

	/**
	 * @return the suspectCountryOfResidence
	 */
	public String getSuspectCountryOfResidence() {
		return suspectCountryOfResidence;
	}

	/**
	 * @param suspectCountryOfResidence the suspectCountryOfResidence to set
	 */
	public void setSuspectCountryOfResidence(String suspectCountryOfResidence) {
		this.suspectCountryOfResidence = suspectCountryOfResidence;
	}

	/**
	 * @return the suspectCountryOfCitizenship
	 */
	public String getSuspectCountryOfCitizenship() {
		return suspectCountryOfCitizenship;
	}

	/**
	 * @param suspectCountryOfCitizenship the suspectCountryOfCitizenship to set
	 */
	public void setSuspectCountryOfCitizenship(String suspectCountryOfCitizenship) {
		this.suspectCountryOfCitizenship = suspectCountryOfCitizenship;
	}

	/**
	 * @return the suspectHomeAreaCode
	 */
	public String getSuspectHomeAreaCode() {
		return suspectHomeAreaCode;
	}

	/**
	 * @param suspectHomeAreaCode the suspectHomeAreaCode to set
	 */
	public void setSuspectHomeAreaCode(String suspectHomeAreaCode) {
		this.suspectHomeAreaCode = suspectHomeAreaCode;
	}

	/**
	 * @return the suspectHomePhone
	 */
	public String getSuspectHomePhone() {
		return suspectHomePhone;
	}

	/**
	 * @param suspectHomePhone the suspectHomePhone to set
	 */
	public void setSuspectHomePhone(String suspectHomePhone) {
		this.suspectHomePhone = suspectHomePhone;
	}

	/**
	 * @return the suspectIdentifier
	 */
	public String getSuspectIdentifier() {
		return suspectIdentifier;
	}

	/**
	 * @param suspectIdentifier the suspectIdentifier to set
	 */
	public void setSuspectIdentifier(String suspectIdentifier) {
		this.suspectIdentifier = suspectIdentifier;
	}

	/**
	 * @return the suspectIdentifierShadow
	 */
	public String getSuspectIdentifierShadow() {
		return suspectIdentifierShadow;
	}

	/**
	 * @param suspectIdentifierShadow the suspectIdentifierShadow to set
	 */
	public void setSuspectIdentifierShadow(String suspectIdentifierShadow) {
		this.suspectIdentifierShadow = suspectIdentifierShadow;
	}

	/**
	 * @return the suspectIdentifierDesc
	 */
	public String getSuspectIdentifierDesc() {
		return suspectIdentifierDesc;
	}

	/**
	 * @param suspectIdentifierDesc the suspectIdentifierDesc to set
	 */
	public void setSuspectIdentifierDesc(String suspectIdentifierDesc) {
		this.suspectIdentifierDesc = suspectIdentifierDesc;
	}

	/**
	 * @return the suspectIdNo
	 */
	public String getSuspectIdNo() {
		return suspectIdNo;
	}

	/**
	 * @param suspectIdNo the suspectIdNo to set
	 */
	public void setSuspectIdNo(String suspectIdNo) {
		this.suspectIdNo = suspectIdNo;
	}

	/**
	 * @return the suspectIdIssueState
	 */
	public String getSuspectIdIssueState() {
		return suspectIdIssueState;
	}

	/**
	 * @param suspectIdIssueState the suspectIdIssueState to set
	 */
	public void setSuspectIdIssueState(String suspectIdIssueState) {
		this.suspectIdIssueState = suspectIdIssueState;
	}

	/**
	 * @return the suspectIdIssueCountry
	 */
	public String getSuspectIdIssueCountry() {
		return suspectIdIssueCountry;
	}

	/**
	 * @param suspectIdIssueCountry the suspectIdIssueCountry to set
	 */
	public void setSuspectIdIssueCountry(String suspectIdIssueCountry) {
		this.suspectIdIssueCountry = suspectIdIssueCountry;
	}

	/**
	 * @return the suspectOccupation
	 */
	public String getSuspectOccupation() {
		return suspectOccupation;
	}

	/**
	 * @param suspectOccupation the suspectOccupation to set
	 */
	public void setSuspectOccupation(String suspectOccupation) {
		this.suspectOccupation = suspectOccupation;
	}

	/**
	 * @return the suspectBusinessAreaCode
	 */
	public String getSuspectBusinessAreaCode() {
		return suspectBusinessAreaCode;
	}

	/**
	 * @param suspectBusinessAreaCode the suspectBusinessAreaCode to set
	 */
	public void setSuspectBusinessAreaCode(String suspectBusinessAreaCode) {
		this.suspectBusinessAreaCode = suspectBusinessAreaCode;
	}

	/**
	 * @return the suspectBusinessPhone
	 */
	public String getSuspectBusinessPhone() {
		return suspectBusinessPhone;
	}

	/**
	 * @param suspectBusinessPhone the suspectBusinessPhone to set
	 */
	public void setSuspectBusinessPhone(String suspectBusinessPhone) {
		this.suspectBusinessPhone = suspectBusinessPhone;
	}

	/**
	 * @return the suspectBusinessPhoneExt
	 */
	public String getSuspectBusinessPhoneExt() {
		return suspectBusinessPhoneExt;
	}

	/**
	 * @param suspectBusinessPhoneExt the suspectBusinessPhoneExt to set
	 */
	public void setSuspectBusinessPhoneExt(String suspectBusinessPhoneExt) {
		this.suspectBusinessPhoneExt = suspectBusinessPhoneExt;
	}

	/**
	 * @return the suspectEmployer
	 */
	public String getSuspectEmployer() {
		return suspectEmployer;
	}

	/**
	 * @param suspectEmployer the suspectEmployer to set
	 */
	public void setSuspectEmployer(String suspectEmployer) {
		this.suspectEmployer = suspectEmployer;
	}

	/**
	 * @return the suspectEmployerAreaCode
	 */
	public String getSuspectEmployerAreaCode() {
		return suspectEmployerAreaCode;
	}

	/**
	 * @param suspectEmployerAreaCode the suspectEmployerAreaCode to set
	 */
	public void setSuspectEmployerAreaCode(String suspectEmployerAreaCode) {
		this.suspectEmployerAreaCode = suspectEmployerAreaCode;
	}

	/**
	 * @return the suspectEmployerPhone
	 */
	public String getSuspectEmployerPhone() {
		return suspectEmployerPhone;
	}

	/**
	 * @param suspectEmployerPhone the suspectEmployerPhone to set
	 */
	public void setSuspectEmployerPhone(String suspectEmployerPhone) {
		this.suspectEmployerPhone = suspectEmployerPhone;
	}

	/**
	 * @return the suspectEmployerPhoneExt
	 */
	public String getSuspectEmployerPhoneExt() {
		return suspectEmployerPhoneExt;
	}

	/**
	 * @param suspectEmployerPhoneExt the suspectEmployerPhoneExt to set
	 */
	public void setSuspectEmployerPhoneExt(String suspectEmployerPhoneExt) {
		this.suspectEmployerPhoneExt = suspectEmployerPhoneExt;
	}

	/**
	 * @return the suspectEmployerAddress
	 */
	public String getSuspectEmployerAddress() {
		return suspectEmployerAddress;
	}

	/**
	 * @param suspectEmployerAddress the suspectEmployerAddress to set
	 */
	public void setSuspectEmployerAddress(String suspectEmployerAddress) {
		this.suspectEmployerAddress = suspectEmployerAddress;
	}

	/**
	 * @return the suspectEmployerCity
	 */
	public String getSuspectEmployerCity() {
		return suspectEmployerCity;
	}

	/**
	 * @param suspectEmployerCity the suspectEmployerCity to set
	 */
	public void setSuspectEmployerCity(String suspectEmployerCity) {
		this.suspectEmployerCity = suspectEmployerCity;
	}

	/**
	 * @return the suspectEmployerState
	 */
	public String getSuspectEmployerState() {
		return suspectEmployerState;
	}

	/**
	 * @param suspectEmployerState the suspectEmployerState to set
	 */
	public void setSuspectEmployerState(String suspectEmployerState) {
		this.suspectEmployerState = suspectEmployerState;
	}

	/**
	 * @return the suspectEmployerCountry
	 */
	public String getSuspectEmployerCountry() {
		return suspectEmployerCountry;
	}

	/**
	 * @param suspectEmployerCountry the suspectEmployerCountry to set
	 */
	public void setSuspectEmployerCountry(String suspectEmployerCountry) {
		this.suspectEmployerCountry = suspectEmployerCountry;
	}

	/**
	 * @return the suspectEmployerZipCode
	 */
	public String getSuspectEmployerZipCode() {
		return suspectEmployerZipCode;
	}

	/**
	 * @param suspectEmployerZipCode the suspectEmployerZipCode to set
	 */
	public void setSuspectEmployerZipCode(String suspectEmployerZipCode) {
		this.suspectEmployerZipCode = suspectEmployerZipCode;
	}

	/**
	 * @return the suspectEmployerZipCodeProcessed
	 */
	public String getSuspectEmployerZipCodeProcessed() {
		return suspectEmployerZipCodeProcessed;
	}

	/**
	 * @param suspectEmployerZipCodeProcessed the suspectEmployerZipCodeProcessed to
	 *                                        set
	 */
	public void setSuspectEmployerZipCodeProcessed(String suspectEmployerZipCodeProcessed) {
		this.suspectEmployerZipCodeProcessed = suspectEmployerZipCodeProcessed;
	}

	@Override
	public String toString() {
		return "PartDTransactionConductor [suspectSurname=" + suspectSurname + ", suspectGivenName=" + suspectGivenName
				+ ", suspectInitial=" + suspectInitial + ", suspectClientNo=" + suspectClientNo + ", suspectBirthday="
				+ suspectBirthday + ", suspectAddress=" + suspectAddress + ", suspectCity=" + suspectCity
				+ ", suspectState=" + suspectState + ", suspectCountry=" + suspectCountry + ", suspectZipCode="
				+ suspectZipCode + ", suspectZipCodeProcessed=" + suspectZipCodeProcessed
				+ ", suspectCountryOfResidence=" + suspectCountryOfResidence + ", suspectCountryOfCitizenship="
				+ suspectCountryOfCitizenship + ", suspectHomeAreaCode=" + suspectHomeAreaCode + ", suspectHomePhone="
				+ suspectHomePhone + ", suspectIdentifier=" + suspectIdentifier + ", suspectIdentifierShadow="
				+ suspectIdentifierShadow + ", suspectIdentifierDesc=" + suspectIdentifierDesc + ", suspectIdNo="
				+ suspectIdNo + ", suspectIdIssueState=" + suspectIdIssueState + ", suspectIdIssueCountry="
				+ suspectIdIssueCountry + ", suspectOccupation=" + suspectOccupation + ", suspectBusinessAreaCode="
				+ suspectBusinessAreaCode + ", suspectBusinessPhone=" + suspectBusinessPhone
				+ ", suspectBusinessPhoneExt=" + suspectBusinessPhoneExt + ", suspectEmployer=" + suspectEmployer
				+ ", suspectEmployerAreaCode=" + suspectEmployerAreaCode + ", suspectEmployerPhone="
				+ suspectEmployerPhone + ", suspectEmployerPhoneExt=" + suspectEmployerPhoneExt
				+ ", suspectEmployerAddress=" + suspectEmployerAddress + ", suspectEmployerCity=" + suspectEmployerCity
				+ ", suspectEmployerState=" + suspectEmployerState + ", suspectEmployerCountry="
				+ suspectEmployerCountry + ", suspectEmployerZipCode=" + suspectEmployerZipCode
				+ ", suspectEmployerZipCodeProcessed=" + suspectEmployerZipCodeProcessed + "]";
	}

}
