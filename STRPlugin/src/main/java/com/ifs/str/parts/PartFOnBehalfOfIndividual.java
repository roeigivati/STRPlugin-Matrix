package com.ifs.str.parts;

import java.util.Date;

import com.ifs.forms.xml.FieldType;
import com.ifs.forms.xml.SetType;
import com.ifs.fstream.annotations.FormData;
import com.ifs.fstream.annotations.FormField;
import com.ifs.fstream.annotations.FormData.Type;

/**
 * <b>STR - Part F</b>
 * <p>
 * Information about the individual on whose behalf the conducting transaction
 * was conducted
 * </p>
 * 
 * @author AWAK001
 * 
 */
@FormData(type = Type.ChildFieldSet, setType = SetType.dispositions, parentSetType = SetType.transactions)
public class PartFOnBehalfOfIndividual implements PartInterface {

	public PartFOnBehalfOfIndividual() {
	}

	@FormField(name = "str_f_surname_")
	private String surname;

	@FormField(name = "str_f_given_name_")
	private String givenName;

	@FormField(name = "str_f_initial_")
	private String initial;

	@FormField(name = "str_f_individual_birthday_", type = FieldType.Date, format = "yyyy-MM-dd")
	private String individualBirthday;

	@FormField(name = "str_f_address_")
	private String address;

	@FormField(name = "str_f_city_")
	private String city;

	@FormField(name = "str_f_state_")
	private String state;

	@FormField(name = "str_f_country_")
	private String country;

	@FormField(name = "str_f_zip_code_")
	private String zipCode;

	@FormField(name = "str_f_zip_code_processed_")
	private String zipCodeProcessed;

	@FormField(name = "str_f_home_area_code_")
	private String homeAreaCode;

	@FormField(name = "str_f_home_phone_")
	private String homePhone;

	@FormField(name = "str_f_business_area_code_")
	private String businessAreaCode;

	@FormField(name = "str_f_business_phone_")
	private String businessPhone;

	@FormField(name = "str_f_business_phone_ext_")
	private String businessPhoneExt;

	@FormField(name = "str_f_individual_identifier_")
	private String individualIdentifier;

	@FormField(name = "str_f_individual_identifier_desc_")
	private String individualIdentifierDesc;

	@FormField(name = "str_f_individual_id_no_")
	private String individualIdNo;

	@FormField(name = "str_f_issue_state_")
	private String issueState;

	@FormField(name = "str_f_issue_country_")
	private String issueCountry;

	@FormField(name = "str_f_country_of_residence_")
	private String countryOfResidence;

	@FormField(name = "str_f_country_of_citizenship_")
	private String countryOfCitizenship;

	@FormField(name = "str_f_individual_occupation_")
	private String individualOccupation;

	@FormField(name = "str_f_individual_employer_")
	private String individualEmployer;

	@FormField(name = "str_f_employer_address_")
	private String employerAddress;

	@FormField(name = "str_f_employer_city_")
	private String employerCity;

	@FormField(name = "str_f_employer_state_")
	private String employerState;

	@FormField(name = "str_f_employer_country_")
	private String employerCountry;

	@FormField(name = "str_f_employer_zip_code_")
	private String employerZipCode;

	@FormField(name = "str_f_employer_zip_code_processed_")
	private String employerZipCodeProcessed;

	@FormField(name = "str_f_employer_business_area_code_")
	private String employerBusinessAreaCode;

	@FormField(name = "str_f_employer_business_phone_")
	private String employerBusinessPhone;

	@FormField(name = "str_f_employer_business_phone_ext_")
	private String employerBusinessPhoneExt;

	@FormField(name = "str_f_relationship_to_suspect_")
	private String relationshipToSuspect;

	@FormField(name = "str_f_relationship_to_suspect_desc_")
	private String relationshipToSuspectDesc;

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the givenName
	 */
	public String getGivenName() {
		return givenName;
	}

	/**
	 * @param givenName the givenName to set
	 */
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	/**
	 * @return the initial
	 */
	public String getInitial() {
		return initial;
	}

	/**
	 * @param initial the initial to set
	 */
	public void setInitial(String initial) {
		this.initial = initial;
	}

	/**
	 * @return the individualBirthday
	 */
	public String getIndividualBirthday() {
		return individualBirthday;
	}

	/**
	 * @param individualBirthday the individualBirthday to set
	 */
	public void setIndividualBirthday(String individualBirthday) {
		this.individualBirthday = individualBirthday;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * @return the zipCodeProcessed
	 */
	public String getZipCodeProcessed() {
		return zipCodeProcessed;
	}

	/**
	 * @param zipCodeProcessed the zipCodeProcessed to set
	 */
	public void setZipCodeProcessed(String zipCodeProcessed) {
		this.zipCodeProcessed = zipCodeProcessed;
	}

	/**
	 * @return the homeAreaCode
	 */
	public String getHomeAreaCode() {
		return homeAreaCode;
	}

	/**
	 * @param homeAreaCode the homeAreaCode to set
	 */
	public void setHomeAreaCode(String homeAreaCode) {
		this.homeAreaCode = homeAreaCode;
	}

	/**
	 * @return the homePhone
	 */
	public String getHomePhone() {
		return homePhone;
	}

	/**
	 * @param homePhone the homePhone to set
	 */
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	/**
	 * @return the businessAreaCode
	 */
	public String getBusinessAreaCode() {
		return businessAreaCode;
	}

	/**
	 * @param businessAreaCode the businessAreaCode to set
	 */
	public void setBusinessAreaCode(String businessAreaCode) {
		this.businessAreaCode = businessAreaCode;
	}

	/**
	 * @return the businessPhone
	 */
	public String getBusinessPhone() {
		return businessPhone;
	}

	/**
	 * @param businessPhone the businessPhone to set
	 */
	public void setBusinessPhone(String businessPhone) {
		this.businessPhone = businessPhone;
	}

	/**
	 * @return the businessPhoneExt
	 */
	public String getBusinessPhoneExt() {
		return businessPhoneExt;
	}

	/**
	 * @param businessPhoneExt the businessPhoneExt to set
	 */
	public void setBusinessPhoneExt(String businessPhoneExt) {
		this.businessPhoneExt = businessPhoneExt;
	}

	/**
	 * @return the individualIdentifier
	 */
	public String getIndividualIdentifier() {
		return individualIdentifier;
	}

	/**
	 * @param individualIdentifier the individualIdentifier to set
	 */
	public void setIndividualIdentifier(String individualIdentifier) {
		this.individualIdentifier = individualIdentifier;
	}

	/**
	 * @return the individualIdentifierDesc
	 */
	public String getIndividualIdentifierDesc() {
		return individualIdentifierDesc;
	}

	/**
	 * @param individualIdentifierDesc the individualIdentifierDesc to set
	 */
	public void setIndividualIdentifierDesc(String individualIdentifierDesc) {
		this.individualIdentifierDesc = individualIdentifierDesc;
	}

	/**
	 * @return the individualIdNo
	 */
	public String getIndividualIdNo() {
		return individualIdNo;
	}

	/**
	 * @param individualIdNo the individualIdNo to set
	 */
	public void setIndividualIdNo(String individualIdNo) {
		this.individualIdNo = individualIdNo;
	}

	/**
	 * @return the issueState
	 */
	public String getIssueState() {
		return issueState;
	}

	/**
	 * @param issueState the issueState to set
	 */
	public void setIssueState(String issueState) {
		this.issueState = issueState;
	}

	/**
	 * @return the issueCountry
	 */
	public String getIssueCountry() {
		return issueCountry;
	}

	/**
	 * @param issueCountry the issueCountry to set
	 */
	public void setIssueCountry(String issueCountry) {
		this.issueCountry = issueCountry;
	}

	/**
	 * @return the countryOfResidence
	 */
	public String getCountryOfResidence() {
		return countryOfResidence;
	}

	/**
	 * @param countryOfResidence the countryOfResidence to set
	 */
	public void setCountryOfResidence(String countryOfResidence) {
		this.countryOfResidence = countryOfResidence;
	}

	/**
	 * @return the countryOfCitizenship
	 */
	public String getCountryOfCitizenship() {
		return countryOfCitizenship;
	}

	/**
	 * @param countryOfCitizenship the countryOfCitizenship to set
	 */
	public void setCountryOfCitizenship(String countryOfCitizenship) {
		this.countryOfCitizenship = countryOfCitizenship;
	}

	/**
	 * @return the individualOccupation
	 */
	public String getIndividualOccupation() {
		return individualOccupation;
	}

	/**
	 * @param individualOccupation the individualOccupation to set
	 */
	public void setIndividualOccupation(String individualOccupation) {
		this.individualOccupation = individualOccupation;
	}

	/**
	 * @return the individualEmployer
	 */
	public String getIndividualEmployer() {
		return individualEmployer;
	}

	/**
	 * @param individualEmployer the individualEmployer to set
	 */
	public void setIndividualEmployer(String individualEmployer) {
		this.individualEmployer = individualEmployer;
	}

	/**
	 * @return the employerAddress
	 */
	public String getEmployerAddress() {
		return employerAddress;
	}

	/**
	 * @param employerAddress the employerAddress to set
	 */
	public void setEmployerAddress(String employerAddress) {
		this.employerAddress = employerAddress;
	}

	/**
	 * @return the employerCity
	 */
	public String getEmployerCity() {
		return employerCity;
	}

	/**
	 * @param employerCity the employerCity to set
	 */
	public void setEmployerCity(String employerCity) {
		this.employerCity = employerCity;
	}

	/**
	 * @return the employerState
	 */
	public String getEmployerState() {
		return employerState;
	}

	/**
	 * @param employerState the employerState to set
	 */
	public void setEmployerState(String employerState) {
		this.employerState = employerState;
	}

	/**
	 * @return the employerCountry
	 */
	public String getEmployerCountry() {
		return employerCountry;
	}

	/**
	 * @param employerCountry the employerCountry to set
	 */
	public void setEmployerCountry(String employerCountry) {
		this.employerCountry = employerCountry;
	}

	/**
	 * @return the employerZipCode
	 */
	public String getEmployerZipCode() {
		return employerZipCode;
	}

	/**
	 * @param employerZipCode the employerZipCode to set
	 */
	public void setEmployerZipCode(String employerZipCode) {
		this.employerZipCode = employerZipCode;
	}

	/**
	 * @return the employerZipCodeProcessed
	 */
	public String getEmployerZipCodeProcessed() {
		return employerZipCodeProcessed;
	}

	/**
	 * @param employerZipCodeProcessed the employerZipCodeProcessed to set
	 */
	public void setEmployerZipCodeProcessed(String employerZipCodeProcessed) {
		this.employerZipCodeProcessed = employerZipCodeProcessed;
	}

	/**
	 * @return the employerBusinessAreaCode
	 */
	public String getEmployerBusinessAreaCode() {
		return employerBusinessAreaCode;
	}

	/**
	 * @param employerBusinessAreaCode the employerBusinessAreaCode to set
	 */
	public void setEmployerBusinessAreaCode(String employerBusinessAreaCode) {
		this.employerBusinessAreaCode = employerBusinessAreaCode;
	}

	/**
	 * @return the employerBusinessPhone
	 */
	public String getEmployerBusinessPhone() {
		return employerBusinessPhone;
	}

	/**
	 * @param employerBusinessPhone the employerBusinessPhone to set
	 */
	public void setEmployerBusinessPhone(String employerBusinessPhone) {
		this.employerBusinessPhone = employerBusinessPhone;
	}

	/**
	 * @return the employerBusinessPhoneExt
	 */
	public String getEmployerBusinessPhoneExt() {
		return employerBusinessPhoneExt;
	}

	/**
	 * @param employerBusinessPhoneExt the employerBusinessPhoneExt to set
	 */
	public void setEmployerBusinessPhoneExt(String employerBusinessPhoneExt) {
		this.employerBusinessPhoneExt = employerBusinessPhoneExt;
	}

	/**
	 * @return the relationshipToSuspect
	 */
	public String getRelationshipToSuspect() {
		return relationshipToSuspect;
	}

	/**
	 * @param relationshipToSuspect the relationshipToSuspect to set
	 */
	public void setRelationshipToSuspect(String relationshipToSuspect) {
		this.relationshipToSuspect = relationshipToSuspect;
	}

	/**
	 * @return the relationshipToSuspectDesc
	 */
	public String getRelationshipToSuspectDesc() {
		return relationshipToSuspectDesc;
	}

	/**
	 * @param relationshipToSuspectDesc the relationshipToSuspectDesc to set
	 */
	public void setRelationshipToSuspectDesc(String relationshipToSuspectDesc) {
		this.relationshipToSuspectDesc = relationshipToSuspectDesc;
	}

	@Override
	public String toString() {
		return "PartFOnBehalfOfIndividual [surname=" + surname + ", givenName=" + givenName + ", initial=" + initial
				+ ", individualBirthday=" + individualBirthday + ", address=" + address + ", city=" + city + ", state="
				+ state + ", country=" + country + ", zipCode=" + zipCode + ", zipCodeProcessed=" + zipCodeProcessed
				+ ", homeAreaCode=" + homeAreaCode + ", homePhone=" + homePhone + ", businessAreaCode="
				+ businessAreaCode + ", businessPhone=" + businessPhone + ", businessPhoneExt=" + businessPhoneExt
				+ ", individualIdentifier=" + individualIdentifier + ", individualIdentifierDesc="
				+ individualIdentifierDesc + ", individualIdNo=" + individualIdNo + ", issueState=" + issueState
				+ ", issueCountry=" + issueCountry + ", countryOfResidence=" + countryOfResidence
				+ ", countryOfCitizenship=" + countryOfCitizenship + ", individualOccupation=" + individualOccupation
				+ ", individualEmployer=" + individualEmployer + ", employerAddress=" + employerAddress
				+ ", employerCity=" + employerCity + ", employerState=" + employerState + ", employerCountry="
				+ employerCountry + ", employerZipCode=" + employerZipCode + ", employerZipCodeProcessed="
				+ employerZipCodeProcessed + ", employerBusinessAreaCode=" + employerBusinessAreaCode
				+ ", employerBusinessPhone=" + employerBusinessPhone + ", employerBusinessPhoneExt="
				+ employerBusinessPhoneExt + ", relationshipToSuspect=" + relationshipToSuspect
				+ ", relationshipToSuspectDesc=" + relationshipToSuspectDesc + "]";
	}

}
