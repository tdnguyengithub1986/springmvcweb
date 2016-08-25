package com.banvien.myplatform.core.service;

import com.banvien.myplatform.core.bean.LanguageBean;
import com.banvien.myplatform.core.domain.Language;
/**
 * <p>Business Service for Languages</p>
 * <p>Generated at Sat Sep 29 11:27:04 ICT 2012</p>
 *
 * @author Portal Generatior v1.1 / Generate a complete Spring/Hibernate and Spring MVC webapp
 */
 public interface LanguageService extends GenericService<Language, Integer>{		
	public Object[] search(LanguageBean searchBean);		
}