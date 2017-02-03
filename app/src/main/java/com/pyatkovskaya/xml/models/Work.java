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
}
