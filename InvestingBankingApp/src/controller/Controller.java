package controller;

import java.util.List;

import model.Database;
import model.Debtor;
import model.Gender;
import model.Investor;
import model.Person;
import view.FormEvent;
import view.MainView;
import view.MainViewListener;

public class Controller {
	
	private Database db;
	
	private MainView mainView;
	
	public Controller () {
		
		db = new Database();
		
		// Initialize view requirements ======================================
		
		mainView = new MainView();
		
		mainView.setTableData(getInvestors(), getDebtors());
		
		mainView.setViewListener(new MainViewListener() {
			
			public void addInvestorInfo(FormEvent e) {
				addInvestor(e);
			}
			
			public void editInvestorInfo(int id, FormEvent e) {
				editInvestor(id, e);
			}

			public void removeInvestorInfo(int index) {
				removeInvestor(index);
			}

			public void getInvestorInfo(int id) {
				mainView.setInvestorFormData(getInvestor(id));
			}
			
			public void addDebtorInfo(FormEvent e) {
				addDebtor(e);
			}
			
			public void editDebtorInfo(int id, FormEvent e) {
				editDebtor(id, e);
			}
			
			public void removeDebtorInfo(int id) {
				removeDebtor(id);
			}
			
			public void getDebtorInfo(int id) {
				mainView.setDebtorFormData(getDebtor(id));
			}
			
		});
		
	}
	
	// Model-View Methods ======================================
	
	public FormEvent personToForm(Person person, int amount) {
		
		String nik = person.getNik();
		String name = person.getName();
		String gender = person.getGender().toString();
		String address = person.getAddress();
		String rtrw = person.getRtrw();
		String village = person.getVillage();
		String district = person.getDistrict();
		String religion = person.getReligion();
		String marriageStatus = person.getMarriageStatus();
		String occupation = person.getOccupation();
		String nationality = person.getNationality();
		
		FormEvent ev = new FormEvent(this, nik, name, gender, address, rtrw, village, district, religion,
				marriageStatus, occupation, nationality, amount);
		
		return ev;
	}
	
	public Person formToPerson(FormEvent e) {
		
		String nik = e.getNik();
		String name = e.getName();
		String genderCategory = e.getGender();
		String address = e.getAddress();
		String rtrw = e.getRtrw();
		String village = e.getVillage();
		String district = e.getDistrict();
		String religion = e.getReligion();
		String marriageStatus = e.getMarriageStatus();
		String occupation = e.getOccupation();
		String nationality = e.getNationality();
		
		Gender gender = null;
		
		if (genderCategory.equalsIgnoreCase("male")) {
			gender = Gender.male;
		}else if (genderCategory.equalsIgnoreCase("female")) {
			gender = Gender.female;
		}
		
		Person person = new Person(nik, name, gender, address, rtrw, village, district, religion,
				marriageStatus, occupation, nationality);
		
		return person;
	}
	
	
	// INVESTOR ======================================
	
	public List<Investor> getInvestors(){
		
		return db.getInvestors();
	}
	
	public FormEvent getInvestor(int id) {
		
		Investor investor = db.getInvestor(id);
		
		return personToForm(investor, investor.getAmountInvested());
	}
	
	public void addInvestor(FormEvent e) {
		
		Investor investor = new Investor(formToPerson(e), e.getAmount());
		
		db.addInvestor(investor);
		
	}
	
	public void editInvestor(int id, FormEvent e) {
		
		Investor newInvestor = new Investor(id, formToPerson(e), e.getAmount());
		
		db.editInvestor(id, newInvestor);
	}
	
	public void removeInvestor(int id) {
		
		db.removeInvestor(id);
	}
	
	// DEBTOR ======================================
	
	public List<Debtor> getDebtors(){
		
		return db.getDebtors();
	}
	
	public FormEvent getDebtor(int id) {
		
		Debtor debtor = db.getDebtor(id);
		
		return personToForm(debtor, debtor.getAmountBorrowedId());
	}
	
	public void addDebtor(FormEvent e) {
		
		Debtor debtor = new Debtor(formToPerson(e), e.getAmount());
		
		db.addDebtor(debtor);
		
	}
	
	public void editDebtor(int id, FormEvent e) {
		
		Debtor newDebtor = new Debtor(id, formToPerson(e), e.getAmount());
		
		db.editDebtor(id, newDebtor);
	}
	
	public void removeDebtor(int id) {
		
		db.removeDebtor(id);
	}
}
