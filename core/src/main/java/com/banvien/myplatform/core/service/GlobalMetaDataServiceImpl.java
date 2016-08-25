package com.banvien.myplatform.core.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.banvien.myplatform.core.Constants;
import com.banvien.myplatform.core.dao.CountryDAO;
import com.banvien.myplatform.core.dao.LanguageDAO;
import com.banvien.myplatform.core.dao.ProvinceDAO;
import com.banvien.myplatform.core.domain.Country;
import com.banvien.myplatform.core.domain.Language;
import com.banvien.myplatform.core.domain.Province;
import com.banvien.myplatform.core.exception.ObjectNotFoundException;

public class GlobalMetaDataServiceImpl implements GlobalMetaDataService {
	private transient final Log log = LogFactory.getLog(GlobalMetaDataService.class);
	private CountryDAO countryDAO;
	private ProvinceDAO provinceDAO;
	private LanguageDAO languageDAO;
	
	private static Map<String, String> mapRoles = new HashMap<String, String>(); 
    private static Map<Integer, Country> mapCountries = new HashMap<Integer, Country>();
    private static Map<Integer, Province> mapProvinces = new HashMap<Integer, Province>();
    private static Map<Integer, Language> mapLanguages = new HashMap<Integer, Language>();
	
	public void initializeGlobalMetaData() {
		List<Country> countries = countryDAO.findAll();
		List<Province> provinces = provinceDAO.findAll();
		List<Language> languages = languageDAO.findAll();

		//Put each item into cache
		for (Country country : countries) {
			mapCountries.put(country.getCountryID(), country);
		}
		
		for (Province province : provinces) {
			mapProvinces.put(province.getProvinceID(), province);
		}
		
		for (Language language : languages) {
			mapLanguages.put(language.getLanguageID(), language);
		}
		
		mapRoles.put(Constants.ROLE_ADMIN, Constants.ROLE_ADMIN);
		mapRoles.put(Constants.ROLE_USER, Constants.ROLE_USER);
	}


	@Override
	public Country lookupCountry(Integer countryID)
			throws ObjectNotFoundException {
		Country result = (Country) mapCountries.get(countryID);
		if (result == null) {
			throw new ObjectNotFoundException("Country not found");
		}
		return result;
	}
	
	@Override
	public Province lookupProvince(Integer provinceID)
			throws ObjectNotFoundException {
		Province result = (Province) mapProvinces.get(provinceID);
		if (result == null) {
			throw new ObjectNotFoundException("Province not found");
		}
		return result;
	}

	@Override
	public List<Province> getProvinces() {
		return new ArrayList<Province>(mapProvinces.values());
	}

	@Override
	public List<Country> getCountries() {
		return new ArrayList<Country>(mapCountries.values());
	}

	@Override
	public List<String> getRoles() {
		return new ArrayList<String>(mapRoles.values());
	}
	
	@Override
	public List<Language> getLanguages() {
		return new ArrayList<Language>(mapLanguages.values());
	}
	
	public void setCountryDAO(CountryDAO countryDAO) {
		this.countryDAO = countryDAO;
	}

	public void setProvinceDAO(ProvinceDAO provinceDAO) {
		this.provinceDAO = provinceDAO;
	}

	public void setLanguageDAO(LanguageDAO languageDAO) {
		this.languageDAO = languageDAO;
	}
	
	

}
