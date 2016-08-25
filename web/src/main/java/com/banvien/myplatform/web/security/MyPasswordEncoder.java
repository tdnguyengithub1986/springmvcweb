package com.banvien.myplatform.web.security;

/**
 * 
 */

import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.encoding.PasswordEncoder;

import com.banvien.myplatform.core.security.DesEncrypterUtils;

/**
 *
 *
 */
public class MyPasswordEncoder implements PasswordEncoder {

	public String encodePassword(String password, Object salt)
			throws DataAccessException {
		return DesEncrypterUtils.getInstance().encrypt(password);
	}

	public boolean isPasswordValid(String encPass, String rawPass, Object salt)
			throws DataAccessException {
		String encPass2 = DesEncrypterUtils.getInstance().encrypt(rawPass);
		return encPass.equals(encPass2);
	}

}
