package view;

import java.util.EventObject;

public class FormEvent extends EventObject {

	private String nik;
	private String name;
	private String gender;
	private String address;
	private String rtrw;
	private String village;
	private String district;
	private String religion;
	private String marriageStatus;
	private String occupation;
	private String nationality;
	
	private int amount;

	public FormEvent(Object source) {
		super(source);

	}

	public FormEvent(Object source, String nik, String name, String gender, String address, String rtrw, String village,
			String district, String religion, String marriageStatus, String occupation, String nationality) {
		super(source);

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

	public FormEvent(Object source, String nik, String name, String gender, String address, String rtrw, String village,
			String district, String religion, String marriageStatus, String occupation, String nationality,
			int amount) {
		
		this(source, nik, name, gender, address, rtrw, village, district, religion, marriageStatus, occupation, nationality);
		this.amount = amount;
		
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
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
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
