package com.pyatkovskaya.xml.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Work
{
	private String name,
			specialty;
	private Date beginDate,
				endDate;
	
	public Work(String name, String specialty, String tbd, String ted)
	{
		this.name = name; this.specialty = specialty;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
			beginDate = dateFormat.parse(tbd);
			endDate = dateFormat.parse(ted);
		} catch (ParseException e)
		{ }

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
