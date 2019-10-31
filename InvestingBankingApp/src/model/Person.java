package model;

import java.time.LocalDateTime;

import view.FormEvent;

public class Person {

	protected int id;
	private String nik;
	private String name;
	private Gender gender;
	private String address;
	private String rtrw;
	private String village;
	private String district;
	private String religion;
	private String marriageStatus;
	private String occupation;
	private String nationality;
	private boolean managed = false;
	private boolean paid = false;
	private String timeCreated;
	private String timeManaged;

	public Person(String nik, String name, Gender gender, String address, String rtrw, String village, String district,
			String religion, String marriageStatus, String occupation, String nationality) {

		this.nik = nik;
		this.name = name;
		this.gender = gender;
		this.address = address;
		this.rtrw = rtrw;
		this.village = village;
		this.district = district;
		this.religion = religion;
		this.marriageStatus = marriageStatus;
		this.occupation = occupation;
		this.nationality = nationality;

	}

	public Person(int id, String nik, String name, Gender gender, String address, String rtrw, String village,
			String district, String religion, String marriageStatus, String occupation, String nationality) {

		this(nik, name, gender, address, rtrw, village, district, religion, marriageStatus, occupation, nationality);

		this.id = id;

	}

	public Person(FormEvent e) {

		this.nik = e.getNik();
		this.name = e.getName();
		String genderCat = e.getGender();
		this.address = e.getAddress();
		this.rtrw = e.getRtrw();
		this.village = e.getVillage();
		this.district = e.getDistrict();
		this.religion = e.getReligion();
		this.marriageStatus = e.getMarriageStatus();
		this.occupation = e.getOccupation();
		this.nationality = e.getNationality();

		Gender gender = null;

		if (genderCat.equalsIgnoreCase("male")) {
			this.gender = Gender.male;
		} else if (genderCat.equalsIgnoreCase("female")) {
			this.gender = Gender.female;
		}
	}

	public Person(Person p) {
		this.nik = p.getNik();
		this.name = p.getName();
		this.gender = p.getGender();
		this.address = p.getAddress();
		this.rtrw = p.getRtrw();
		this.village = p.getVillage();
		this.district = p.getDistrict();
		this.religion = p.getReligion();
		this.marriageStatus = p.getMarriageStatus();
		this.occupation = p.getOccupation();
		this.nationality = p.getNationality();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNik() {
		return nik;
	}

	public void setNik(String nik) {
		this.nik = nik;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRtrw() {
		return rtrw;
	}

	public void setRtrw(String rtrw) {
		this.rtrw = rtrw;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getMarriageStatus() {
		return marriageStatus;
	}

	public void setMarriageStatus(String marriageStatus) {
		this.marriageStatus = marriageStatus;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public boolean isManaged() {
		return managed;
	}

	public void setManaged(boolean managed) {
		this.managed = managed;
	}

	public String getTimeCreated() {
		return timeCreated;
	}

	public void setTimeCreated(String timeCreated) {
		this.timeCreated = timeCreated;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public String getTimeManaged() {
		return timeManaged;
	}

	public void setTimeManaged(String timeManaged) {
		this.timeManaged = timeManaged;
	}
	
}
