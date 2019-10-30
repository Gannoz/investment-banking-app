package model;

import view.FormEvent;

public class Debtor extends Person {

	private static int count = 1;
	private int amountBorrowed;
	private float feePercentage;
	private long feeAmount;

	public Debtor(String nik, String name, Gender gender, String address, String rtrw, String village, String district,
			String religion, String marriageStatus, String occupation, String nationality, int amountBorrowedId) {
		super(nik, name, gender, address, rtrw, village, district, religion, marriageStatus, occupation, nationality);

		this.amountBorrowed = amountBorrowedId;

		id = count;
		count++;
	}

	public Debtor(Person person, int amount) {
		super(person);
		this.amountBorrowed = amount;

		id = count;
		count++;
	}

	public Debtor(int id, Person person, int amount) {
		super(person);
		this.id = id;
		this.amountBorrowed = amount;

	}

	public Debtor(int id, String nik, String name, Gender gender, String address, String rtrw, String village,
			String district, String religion, String marriageStatus, String occupation, String nationality,
			int amountBorrowedId) {
		super(id, nik, name, gender, address, rtrw, village, district, religion, marriageStatus, occupation,
				nationality);

		this.amountBorrowed = amountBorrowedId;
	}

	public int getAmountBorrowed() {
		return amountBorrowed;
	}

	public void setAmountBorrowed(int amountBorrowedId) {
		this.amountBorrowed = amountBorrowedId;
	}

	public float getFeePercentage() {
		return feePercentage;
	}

	public void setFeePercentage(float feePercentage) {
		this.feePercentage = feePercentage;
	}

	public long getFeeAmount() {
		return feeAmount;
	}

	public void setFeeAmount(long feeAmount) {
		this.feeAmount = feeAmount;
	}

}
