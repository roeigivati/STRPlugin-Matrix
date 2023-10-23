package com.ifs.str.parts;

import com.ifs.fstream.annotations.FormData;
import com.ifs.fstream.annotations.FormData.Type;
import com.ifs.fstream.annotations.FormField;

/**
 * <b>STR - Part A</b>
 * <p>
 * Information about where the transaction took place
 * </p>
 * 
 * @author AWAK001
 */
@FormData(type = Type.Field)
public class PartABranch implements PartInterface {

	@FormField(name = "str_a_branch_key")
	private String branchKey;

	@FormField(name = "str_a_branch_location_no")
	private String branchLocationNo;

	@FormField(name = "str_a_branch_name")
	private String branchName;

	@FormField(name = "str_a_branch_ad")
	private String branchAd;

	@FormField(name = "str_a_branch_city")
	private String branchCity;

	@FormField(name = "str_a_branch_state")
	private String branchState;

	@FormField(name = "str_a_branch_zipcode")
	private String branchZipcode;

	@FormField(name = "str_a_branch_zipcode_processed")
	private String branchZipcodeProcessed;

	@FormField(name = "str_a_contact_last_name")
	private String contactLastName;

	@FormField(name = "str_a_contact_first_name")
	private String contactFirstName;

	@FormField(name = "str_a_contact_middle_name")
	private String contactMiddleName;

	@FormField(name = "str_a_contact_area_code")
	private String contactAreaCode;

	@FormField(name = "str_a_contact_phone_number")
	private String contactPhoneNumber;

	@FormField(name = "str_a_contact_phone_ext")
	private String contactPhoneExt;

	/**
	 * @return the branchKey
	 */
	public String getBranchKey() {
		return branchKey;
	}

	/**
	 * @param branchKey the branchKey to set
	 */
	public void setBranchKey(String branchKey) {
		this.branchKey = branchKey;
	}

	/**
	 * @return the branchLocationNo
	 */
	public String getBranchLocationNo() {
		return branchLocationNo;
	}

	/**
	 * @param branchLocationNo the branchLocationNo to set
	 */
	public void setBranchLocationNo(String branchLocationNo) {
		this.branchLocationNo = branchLocationNo;
	}

	/**
	 * @return the branchName
	 */
	public String getBranchName() {
		return branchName;
	}

	/**
	 * @param branchName the branchName to set
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	/**
	 * @return the branchAd
	 */
	public String getBranchAd() {
		return branchAd;
	}

	/**
	 * @param branchAd the branchAd to set
	 */
	public void setBranchAd(String branchAd) {
		this.branchAd = branchAd;
	}

	/**
	 * @return the branchCity
	 */
	public String getBranchCity() {
		return branchCity;
	}

	/**
	 * @param branchCity the branchCity to set
	 */
	public void setBranchCity(String branchCity) {
		this.branchCity = branchCity;
	}

	/**
	 * @return the branchState
	 */
	public String getBranchState() {
		return branchState;
	}

	/**
	 * @param branchState the branchState to set
	 */
	public void setBranchState(String branchState) {
		this.branchState = branchState;
	}

	/**
	 * @return the branchZipcode
	 */
	public String getBranchZipcode() {
		return branchZipcode;
	}

	/**
	 * @param branchZipcode the branchZipcode to set
	 */
	public void setBranchZipcode(String branchZipcode) {
		this.branchZipcode = branchZipcode;
	}

	/**
	 * @return the branchZipcodeProcessed
	 */
	public String getBranchZipcodeProcessed() {
		return branchZipcodeProcessed;
	}

	/**
	 * @param branchZipcodeProcessed the branchZipcodeProcessed to set
	 */
	public void setBranchZipcodeProcessed(String branchZipcodeProcessed) {
		this.branchZipcodeProcessed = branchZipcodeProcessed;
	}

	/**
	 * @return the contactLastName
	 */
	public String getContactLastName() {
		return contactLastName;
	}

	/**
	 * @param contactLastName the contactLastName to set
	 */
	public void setContactLastName(String contactLastName) {
		this.contactLastName = contactLastName;
	}

	/**
	 * @return the contactFirstName
	 */
	public String getContactFirstName() {
		return contactFirstName;
	}

	/**
	 * @param contactFirstName the contactFirstName to set
	 */
	public void setContactFirstName(String contactFirstName) {
		this.contactFirstName = contactFirstName;
	}

	/**
	 * @return the contactMiddleName
	 */
	public String getContactMiddleName() {
		return contactMiddleName;
	}

	/**
	 * @param contactMiddleName the contactMiddleName to set
	 */
	public void setContactMiddleName(String contactMiddleName) {
		this.contactMiddleName = contactMiddleName;
	}

	/**
	 * @return the contactAreaCode
	 */
	public String getContactAreaCode() {
		return contactAreaCode;
	}

	/**
	 * @param contactAreaCode the contactAreaCode to set
	 */
	public void setContactAreaCode(String contactAreaCode) {
		this.contactAreaCode = contactAreaCode;
	}

	/**
	 * @return the contactPhoneNumber
	 */
	public String getContactPhoneNumber() {
		return contactPhoneNumber;
	}

	/**
	 * @param contactPhoneNumber the contactPhoneNumber to set
	 */
	public void setContactPhoneNumber(String contactPhoneNumber) {
		this.contactPhoneNumber = contactPhoneNumber;
	}

	/**
	 * @return the contactPhoneExt
	 */
	public String getContactPhoneExt() {
		return contactPhoneExt;
	}

	/**
	 * @param contactPhoneExt the contactPhoneExt to set
	 */
	public void setContactPhoneExt(String contactPhoneExt) {
		this.contactPhoneExt = contactPhoneExt;
	}

	@Override
	public String toString() {
		return "PartABranch [branchKey=" + branchKey + ", branchLocationNo=" + branchLocationNo + ", branchName="
				+ branchName + ", branchAd=" + branchAd + ", branchCity=" + branchCity + ", branchState=" + branchState
				+ ", branchZipcode=" + branchZipcode + ", branchZipcodeProcessed=" + branchZipcodeProcessed
				+ ", contactLastName=" + contactLastName + ", contactFirstName=" + contactFirstName
				+ ", contactMiddleName=" + contactMiddleName + ", contactAreaCode=" + contactAreaCode
				+ ", contactPhoneNumber=" + contactPhoneNumber + ", contactPhoneExt=" + contactPhoneExt + "]";
	}

}
