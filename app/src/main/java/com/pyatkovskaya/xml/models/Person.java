package com.pyatkovskaya.xml.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Person
{
	private String firstname,
		   		  lastname,
		   		  surname,
		   		  pasport,
		   		  country;
	private Date birthday;
	private Educate school;
	private List<Educate> highSchool = new ArrayList<>();
	private boolean gender,
				   maried;
	private List<Work>  works = new ArrayList<>();
	private List<Child> childs = new ArrayList<>();

	public Person() {
	}

	public Person(String firstname, String lastname, String surname, String pasport,
				  String country, String birthday, Educate school, boolean gender, boolean maried ) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.surname = surname;
		this.pasport = pasport;
		this.country = country;
		try {
			this.birthday = new SimpleDateFormat("dd.MM.yyyy").parse(birthday);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.school = school;

		this.gender = gender;
		this.maried = maried;
	}
	
	public static boolean isValidPasport(String code)
	{
		if (code.length() != 8) return false;
		char[] ch = new char[8];
		code.getChars(0, 8, ch, 0);
		for (int i = 0; i < 8; i++)
		{
			//System.out.println(Character.isLetter(ch[i]));
			if (!(i <= 1 && Character.isLetter(ch[i])) &&
			    !(i >  1 && Character.isDigit (ch[i]))) return false;
		}
		return true;
	}

	public void setHighSchool(List<Educate> highSchool) {
		this.highSchool = highSchool;
	}

	public void setWorks(List<Work> works) {
		this.works = works;
	}

	public void setChilds(List<Child> childs) {
		this.childs = childs;
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

	public String getPasport() {
		return pasport;
	}

	public String getCountry() {
		return country;
	}

	public Date getBirthday() {
		return birthday;
	}
    public String getBirthdayString() {

        return new SimpleDateFormat("dd.MM.yyyy").format(birthday);
    }
	public Educate getSchool() {
		return school;
	}

	public List<Educate> getHighSchool() {
		return highSchool;
	}

	public boolean isGender() {
		return gender;
	}

	public boolean isMaried() {
		return maried;
	}

	public List<Work> getWorks() {
		return works;
	}

	public List<Child> getChilds() {
		return childs;
	}
    public static Person generateMe(){
        String MFNames[] = {"Алексей",
                "Александр",
                "Андрей",
                "Иван",
                "Василий",
                "Павел",
                "Виталий",
                "Сергей",
                "Николай",
                "Илья",
                "Видим",
                "Ашот",
                "Равшан",
                "Джамшут",
                "Дмитрий",
                "Валерий"
        };

        String FFNames[] = {"Наталия",
                "Виктория",
                "Александра",
                "Нина",
                "Алевтина",
                "Елена",
                "Екатерина",
                "Татьяна",
                "Анна",
                "Ирина",
                "Инна"
        };

        String MLNames[] = {"Алексевич",
                "Алексанрович",
                "Андреевич",
                "Иванович",
                "Васильевич",
                "Павлович",
                "Витальевич",
                "Сергеевич",
                "Вадимович",
                "Ашотович",
                "Равшанович",
                "Джамшутович",
                "Дмитриевич",
                "Валериевич"
        };

        String FLNames[] = {"Алексеевна",
                "Александровна",
                "Андреевна",
                "Ивановна",
                "Васильевна",
                "Павловна",
                "Витальевна",
                "Сергеевна",
                "Вадимовна",
                "Ашотовна",
                "Равшановна",
                "Джамшутовна",
                "Дмитриевна",
                "Валериевна"
        };

        String SNames[] = {"Татарчук",
                "Грасимчук",
                "Лифчук",
                "Тимошенко",
                "Кириенко",
                "Алазян",
                "Виниченко",
                "Коленко",
                "Говриленко",
                "Остапчук",
                "Голубь",
                "Воробушек"
        };


        String hEdN[] = {"Политех",
                "ИИТ",
                "МАУП",
                "АДУ",
                "МедИн",
                "МТИ",
                "МТУ"
        };

        String profSp[] = {"Программист",
                "Электирик",
                "Строитель",
                "Дресеровщик",
                "Токарь",
                "Учитель",
                "Маркетолог",
                "Юрист",
                "Флоритст",
                "Футболист",
                "Дворнник",
                "Киллер",
                "Актер",
                "Слесарь"
        };

        String pred[] = {"СтальКанат",
                "ЮниСофт",
                "Google",
                "Бона",
                "Арион",
                "Продукты 24",
                "Таврия В",
                "Школа",
                "МакДональдс",
                "Сильпо",
        };

        String Countrys[] = {"Украина", "Россия", "США", "Молдавия", "Босния и Герцоговиня","Сербия", "Хорватия", "Северная Корея", "Гондурас"};

        String pass[] = {"АГ", "РО", "ГТ", "ФР", "ПА", "НО", "УК"};

            boolean Sex = Math.random() > 0.5;
            String FName = (Sex) ? MFNames[(int) Math.floor((Math.random() * (MFNames.length)))] :
                    FFNames[(int) Math.floor((Math.random() * (FFNames.length)))];
            String LName = (Sex) ? MLNames[(int) Math.floor((Math.random() * (MLNames.length)))] :
                    FLNames[(int) Math.floor((Math.random() * (FLNames.length)))];
            String SName = SNames[(int) Math.floor((Math.random() * (SNames.length)))];

            String Passport = pass[(int) Math.floor((Math.random() * (pass.length)))];
            for (int j = 0; j < 6; j++) {
                int tempI = (int) (Math.random() * 10);
                if (tempI == 10) tempI = 1;
                Passport += tempI;
            }

            String Country = Countrys[(int) Math.floor((Math.random() * (Countrys.length)))];

            int bday = (int) (Math.random() * 28 + 1);
            int bmos = (int) (Math.random() * 11 + 1);
            int byar = 1950 + ((int) (Math.random() * 30));

            String sEducate_Name = "№" + (int) (Math.random() * 100);
            int sEducate_Byeat = byar + 7;
            int sEducate_Eyeat = byar + 7 + 9;

            boolean Maried = Math.random() > 0.5;

            Person result = new Person(FName, LName, SName, Passport, Country, "" + bday + "." + bmos + "." + byar, new Educate(sEducate_Byeat, sEducate_Eyeat, sEducate_Name, ""), Sex, Maried);


            int countChilds = (int) (Math.random() * 6);
            for (int j = 1; j < countChilds; j++) {
                boolean Ch_Sex = Math.random() > 0.5;
                String ChFName = (Ch_Sex) ? MFNames[(int) Math.floor((Math.random() * (MFNames.length)))] :
                        FFNames[(int) Math.floor((Math.random() * (FFNames.length)))];
                String ChLName = (Ch_Sex) ? MLNames[(int) Math.floor((Math.random() * (MLNames.length)))] :
                        FLNames[(int) Math.floor((Math.random() * (FLNames.length)))];
                int ChBday = byar + 16 + (int) (Math.random() * 30);

                result.getChilds().add(new Child(ChBday, ChLName, SName, ChFName));
            }

            int hEducatesCount = (int) (Math.random() * 3);
            for (int j = 1; j < hEducatesCount; j++) {
                String sEName = hEdN[(int) Math.floor((Math.random() * (hEdN.length)))];
                String prof = profSp[(int) Math.floor((Math.random() * (profSp.length)))];
                int byear = byar + 15 + (int) (Math.random() * 20);
                int eyear = byear + (int) (Math.random() * 6);
                result.getHighSchool().add(new Educate(byear, eyear, sEName, prof));
            }

            int WorksCount = (int) (Math.random() * 6);
            for (int j = 1; j < WorksCount; j++) {
                String WName = pred[(int) Math.floor((Math.random() * (pred.length)))];
                String spec = profSp[(int) Math.floor((Math.random() * (profSp.length)))];

                int Bbday = (int) (Math.random() * 28 + 1);
                int Bbmos = (int) (Math.random() * 11 + 1);
                int Bbyear = byar + 15 + (int) (Math.random() * 30);
                int Ebday = (int) (Math.random() * 28 + 1);
                int Ebmos = (int) (Math.random() * 11 + 1);
                int Ebyear = Bbyear + (int) (Math.random() * 6);

                result.getWorks().add(new Work(WName, spec, "" + Bbday + "." + Bbmos + "." + Bbyear, "" + Ebday + "." + Ebmos + "." + Ebyear));
            }
        return result;
    }

}
