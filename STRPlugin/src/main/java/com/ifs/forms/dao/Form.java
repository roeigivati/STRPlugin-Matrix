package com.ifs.forms.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "acm_forms")
public class Form {

	public Form() {
	}

	@Id
	@Column(name = "form_internal_id")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "hi_forms")
	@GenericGenerator(strategy = "com.ifs.dao.id.ACMNewHiLoGenerator", name = "hi_forms", parameters = {
			@Parameter(name = "sequence", value = "hi_forms") })
	private long id;

	@Column(name = "form_identifier")
	private String formIdentifier;

//	@Column(name="case_internal_id")
//	private int caseInternalId;

	@Column(name = "alert_internal_id")
	private int alertInternalId;

//	@Column(name="parent_form_internal_id")
//	private int parentFormInternalId;

	@Column(name = "form_xml")
	private String xml;

	@Column(name = "create_date")
	private Date createDate;

	@Column(name = "create_user")
	private int createUser;

	@Column(name = "last_update_user")
	private int lastUpdateUser;

	@Column(name = "form_name")
	private String name;

	@Column(name = "e_status")
	private String status;

	@Column(name = "form_type_internal_id")
	private int typeInternalId;
	
	@Transient
	private int eFileCount;
	

	public int geteFileCount() {
		return eFileCount;
	}

	public void seteFileCount(int eFileCount) {
		this.eFileCount = eFileCount;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the formIdentifier
	 */
	public String getFormIdentifier() {
		return formIdentifier;
	}

	/**
	 * @param formIdentifier the formIdentifier to set
	 */
	public void setFormIdentifier(String formIdentifier) {
		this.formIdentifier = formIdentifier;
	}

	/**
	 * @return the alertInternalId
	 */
	public int getAlertInternalId() {
		return alertInternalId;
	}

	/**
	 * @param alertInternalId the alertInternalId to set
	 */
	public void setAlertInternalId(int alertInternalId) {
		this.alertInternalId = alertInternalId;
	}

	/**
	 * @return the xml
	 */
	public String getXml() {
		return xml;
	}

	/**
	 * @param xml the xml to set
	 */
	public void setXml(String xml) {
		this.xml = xml;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the createUser
	 */
	public int getCreateUser() {
		return createUser;
	}

	/**
	 * @param createUser the createUser to set
	 */
	public void setCreateUser(int createUser) {
		this.createUser = createUser;
	}

	/**
	 * @return the lastUpdateUser
	 */
	public int getLastUpdateUser() {
		return lastUpdateUser;
	}

	/**
	 * @param lastUpdateUser the lastUpdateUser to set
	 */
	public void setLastUpdateUser(int lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the typeInternalId
	 */
	public int getTypeInternalId() {
		return typeInternalId;
	}

	/**
	 * @param typeInternalId the typeInternalId to set
	 */
	public void setFormTypeInternalId(int typeInternalId) {
		this.typeInternalId = typeInternalId;
	}

//	form_type_internal_id numeric(9,0) NOT NULL,
//	form_xml nvarchar(MAX) COLLATE SQL_Latin1_General_CP1_CS_AS NULL,
//	create_date datetime NOT NULL,
//	last_update_date datetime NULL,
//	last_update_user numeric(9,0) NULL,
//	create_user numeric(9,0) NOT NULL,
//	form_identifier nvarchar(50) COLLATE SQL_Latin1_General_CP1_CS_AS NOT NULL,
//	form_name nvarchar(250) COLLATE SQL_Latin1_General_CP1_CS_AS NOT NULL,
//	reference nvarchar(500) COLLATE SQL_Latin1_General_CP1_CS_AS NULL,
//	e_status nvarchar(50) COLLATE SQL_Latin1_General_CP1_CS_AS NOT NULL,
//	form_pdf image NULL,
//	reference_errors nvarchar(MAX) COLLATE SQL_Latin1_General_CP1_CS_AS NULL,
//	fl_has_attachments numeric(1,0) NULL,
//	alert_internal_id numeric(9,0) NULL,
//	form_internal_id numeric(15,0) NOT NULL,
//	case_internal_id numeric(15,0) NULL,
//	parent_form_internal_id numeric(15,0) NULL,
//	date_filing datetime NULL,

}
