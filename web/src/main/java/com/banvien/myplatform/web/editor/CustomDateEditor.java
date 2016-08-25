package com.banvien.myplatform.web.editor;


import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.banvien.myplatform.core.Constants;

public class CustomDateEditor extends PropertyEditorSupport {
	private transient final Log log = LogFactory.getLog(CustomDateEditor.class);
	private String dateFormat = Constants.DATE_FORMAT;
	public CustomDateEditor(String dateFormat) {
		this.dateFormat = dateFormat;
	}
	public CustomDateEditor(){}
	public void setAsText(String text) throws IllegalArgumentException {
		if (text != null && text.trim().length() > 1) {
			SimpleDateFormat format = new SimpleDateFormat(dateFormat);
			try {
				Date d = (Date) format.parse(text);
				setValue(new Date(d.getTime()));
			} catch (Exception e) {
				log.error("Invalid date format [" + dateFormat + "]" + e.getMessage());
			}
		}

	}
}
