package model;

import view.FormEvent;

public class Debtor extends Person {

	private static int count = 1;
	private int amountBorrowedId;

	public Debtor(String nik, String name, Gender gender, String address, String rtrw, String village, String district,
			String religion, String marriageStatus, String occupation, String nationality, int amountBorrowedId) {
		super(nik, name, gender, address, rtrw, village, district, religion, marriageStatus, occupation, nationality);
		
		this.amountBorrowedId = amountBorrowedId;
		
		id = count;
		count++;
	}
	
	public Debtor(Person person, int amount) {
		super(person);
		this.amountBorrowedId = amount;
		
		id = count;
		count++;
	}
	
	public Debtor(int id, Person person, int amount) {
		super(person);
		this.id = id;
		this.amountBorrowedId = amount;
		
	}
	
	public Debtor(int id, String nik, String name, Gender gender, String address, String rtrw, String village,
			String district, String religion, String marriageStatus, String occupation, String nationality,
			int amountBorrowedId) {
		super(id, nik, name, gender, address, rtrw, village, district, religion, marriageStatus, occupation,
				nationality);
		
		this.amountBorrowedId = amountBorrowedId;
	}
	
	public FormEvent getFormInfo() {
		String nik = getNik();
		String name = getName();
		String gender = getGender().toString();
		String address = getAddress();
		String rtrw = getRtrw();
		String village = getVillage();
		String district = getDistrict();
		String religion = getReligion();
		String marriageStatus = getMarriageStatus();
		String occupation = getOccupation();
		String nationality = getNationality();
		int amount = getAmountBorrowedId();
		
		FormEvent ev = new FormEvent(this, nik, name, gender, address, rtrw, village, district, religion,
				marriageStatus, occupation, nationality, amount);
		
		return ev;
	}
	
	
	public int getAmountBorrowedId() {
		return amountBorrowedId;
	}

	public void setAmountBorrowed(int amountBorrowedId) {
		this.amountBorrowedId = amountBorrowedId;
	}

}
