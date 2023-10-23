/**
 * 
 */
package com.ifs.str.parts;

import com.ifs.forms.xml.SetType;
import com.ifs.fstream.annotations.FormData;
import com.ifs.fstream.annotations.FormField;
import com.ifs.fstream.annotations.FormData.Type;

/**
 * <b>STR - Part E</b>
 * <p>
 * Information about the entity on whose behalf the conducting transaction was
 * conducted
 * </p>
 * 
 * @author AWAK001
 * 
 */
@FormData(type = Type.ChildFieldSet, setType = SetType.dispositions, parentSetType = SetType.transactions)
public class PartEOnBehalfOfEntity implements PartInterface {

	/**
	 * 
	 */
	public PartEOnBehalfOfEntity() {
	}

	@FormField(name = "str_e_entity_name_")
	private String entityName;

	@FormField(name = "str_e_business_type_")
	private String businessType;

	@FormField(name = "str_e_entity_address_")
	private String entityAddress;

	@FormField(name = "str_e_entity_city_")
	private String entityCity;

	@Override
	public String toString() {
		return "PartEOnBehalfOfEntity [entityName=" + entityName + ", businessType=" + businessType + ", entityAddress="
				+ entityAddress + ", entityCity=" + entityCity + ", entityState=" + entityState + ", entityCountry="
				+ entityCountry + ", entityZipCode=" + entityZipCode + ", entityZipCodeProcessed="
				+ entityZipCodeProcessed + ", entityAreaCode=" + entityAreaCode + ", entityPhone=" + entityPhone
				+ ", entityPhoneExt=" + entityPhoneExt + ", incorporationNo=" + incorporationNo + ", issueState="
				+ issueState + ", issueCountry=" + issueCountry + ", individualNameA=" + individualNameA
				+ ", individualNameB=" + individualNameB + ", individualNameC=" + individualNameC + "]";
	}

	@FormField(name = "str_e_entity_state_")
	private String entityState;

	@FormField(name = "str_e_entity_country_")
	private String entityCountry;

	@FormField(name = "str_e_entity_zip_code_")
	private String entityZipCode;

	@FormField(name = "str_e_entity_zip_code_processed_")
	private String entityZipCodeProcessed;

	@FormField(name = "str_e_entity_area_code_")
	private String entityAreaCode;

	@FormField(name = "str_e_entity_phone_")
	private String entityPhone;

	@FormField(name = "str_e_entity_phone_ext_")
	private String entityPhoneExt;

	@FormField(name = "str_e_incorporation_no_")
	private String incorporationNo;

	@FormField(name = "str_e_issue_state_")
	private String issueState;

	@FormField(name = "str_e_issue_country_")
	private String issueCountry;

	@FormField(name = "str_e_individual_name_a_")
	private String individualNameA;

	@FormField(name = "str_e_individual_name_b_")
	private String individualNameB;

	@FormField(name = "str_e_individual_name_c_")
	private String individualNameC;

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getEntityAddress() {
		return entityAddress;
	}

	public void setEntityAddress(String entityAddress) {
		this.entityAddress = entityAddress;
	}

	public String getEntityCity() {
		return entityCity;
	}

	public void setEntityCity(String entityCity) {
		this.entityCity = entityCity;
	}

	public String getEntityState() {
		return entityState;
	}

	public void setEntityState(String entityState) {
		this.entityState = entityState;
	}

	public String getEntityCountry() {
		return entityCountry;
	}

	public void setEntityCountry(String entityCountry) {
		this.entityCountry = entityCountry;
	}

	public String getEntityZipCode() {
		return entityZipCode;
	}

	public void setEntityZipCode(String entityZipCode) {
		this.entityZipCode = entityZipCode;
	}

	public String getEntityZipCodeProcessed() {
		return entityZipCodeProcessed;
	}

	public void setEntityZipCodeProcessed(String entityZipCodeProcessed) {
		this.entityZipCodeProcessed = entityZipCodeProcessed;
	}

	public String getEntityAreaCode() {
		return entityAreaCode;
	}

	public void setEntityAreaCode(String entityAreaCode) {
		this.entityAreaCode = entityAreaCode;
	}

	public String getEntityPhone() {
		return entityPhone;
	}

	public void setEntityPhone(String entityPhone) {
		this.entityPhone = entityPhone;
	}

	public String getEntityPhoneExt() {
		return entityPhoneExt;
	}

	public void setEntityPhoneExt(String entityPhoneExt) {
		this.entityPhoneExt = entityPhoneExt;
	}

	public String getIncorporationNo() {
		return incorporationNo;
	}

	public void setIncorporationNo(String incorporationNo) {
		this.incorporationNo = incorporationNo;
	}

	public String getIssueState() {
		return issueState;
	}

	public void setIssueState(String issueState) {
		this.issueState = issueState;
	}

	public String getIssueCountry() {
		return issueCountry;
	}

	public void setIssueCountry(String issueCountry) {
		this.issueCountry = issueCountry;
	}

	public String getIndividualNameA() {
		return individualNameA;
	}

	public void setIndividualNameA(String individualNameA) {
		this.individualNameA = individualNameA;
	}

	public String getIndividualNameB() {
		return individualNameB;
	}

	public void setIndividualNameB(String individualNameB) {
		this.individualNameB = individualNameB;
	}

	public String getIndividualNameC() {
		return individualNameC;
	}

	public void setIndividualNameC(String individualNameC) {
		this.individualNameC = individualNameC;
	}


}
