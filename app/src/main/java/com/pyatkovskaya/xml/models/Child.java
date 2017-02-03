package com.pyatkovskaya.xml.models;

public class Child
{
	private int birthYear;
	private String firstname, lastname, surname;


	public Child(int birthYear, String firstname, String lastname, String surname) {
		this.birthYear = birthYear;
		this.firstname = firstname;
		this.lastname = lastname;
		this.surname = surname;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getSurname() {
		return surname;
	}
}
