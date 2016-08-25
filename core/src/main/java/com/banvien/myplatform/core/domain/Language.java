package com.banvien.myplatform.core.domain;

import java.io.Serializable;


/**
 * <p>Pojo mapping TABLE language</p>
 * <p></p>
 *
 * <p>Generated at date</p>
 * @author Portal Generatior v1.1 / Hibernate pojos and xml mapping files.
 * 
 */
public class Language implements Serializable {

	/**
	 * Attribute languageID.
	 */
	private Integer languageID;
	
	/**
	 * Attribute code.
	 */
	private String code;
	
	/**
	 * Attribute name.
	 */
	private String name;
	
	/**
	 * Attribute languageKey.
	 */
	private String languageKey;
	
	/**
	 * <p> 
	 * </p>
	 * @return languageID
	 */
	public Integer getLanguageID() {
		return languageID;
	}

	/**
	 * @param languageID new value for languageID 
	 */
	public void setLanguageID(Integer languageID) {
		this.languageID = languageID;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code new value for code 
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	
	/**
	 * <p> 
	 * </p>
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name new value for name 
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return languageKey
	 */
	public String getLanguageKey() {
		return languageKey;
	}

	/**
	 * @param languageKey new value for languageKey 
	 */
	public void setLanguageKey(String languageKey) {
		this.languageKey = languageKey;
	}
	

public Language(){}	
		
	public Language(Integer languageID){this.languageID = languageID;}	
	}