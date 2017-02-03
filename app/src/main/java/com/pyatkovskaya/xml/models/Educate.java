package com.pyatkovskaya.xml.models;

public class Educate
{
	private int beginYear, endYear;
	
	private String name, profession;

	public Educate(int beginYear, int endYear, String name, String profession) {
		this.beginYear = beginYear;
		this.endYear = endYear;
		this.name = name;
		this.profession = profession;
	}

	public int getBeginYear() {
		return beginYear;
	}

	public int getEndYear() {
		return endYear;
	}

	public String getName() {
		return name;
	}

	public String getProfession() {
		return profession;
	}
}
