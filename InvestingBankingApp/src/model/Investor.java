package model;

import view.FormEvent;

public class Investor extends Person {

	private static int count = 1;
	private int amountInvested;

	public Investor(String nik, String name,Gender gender, String address, String rtrw, String village,
			String district, String religion, String marriageStatus, String occupation, String nationality,
			int amountInvested) {
		super(nik, name, gender, address, rtrw, village, district, religion, marriageStatus, occupation, nationality);
		
		this.amountInvested = amountInvested;
		
		id = count;

		count++;
	}
	
	public Investor(Person person, int amount) {
		super(person);
		this.amountInvested = amount;
		
		id = count;
		count++;
	}
	
	public Investor(int id, Person person, int amount) {
		super(person);
		this.id = id;
		this.amountInvested = amount;
		
	}
	

	public Investor(int id, String nik, String name, Gender gender, String address, String rtrw, String village,
			String district, String religion, String marriageStatus, String occupation, String nationality,
			int amountInvested) {
		super(id, nik, name, gender, address, rtrw, village, district, religion, marriageStatus, occupation,
				nationality);
		this.amountInvested = amountInvested;
		
	}
	
	public int getAmountInvested() {
		return amountInvested;
	}

	public void setAmountInvested(int amountInvested) {
		this.amountInvested = amountInvested;
	}

}
