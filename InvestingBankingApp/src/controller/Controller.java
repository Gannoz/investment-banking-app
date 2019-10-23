package controller;

import java.util.List;

import model.Debtor;
import model.Gender;
import model.Investor;
import model.MainModel;
import model.Person;
import model.Database.Database;
import view.FormEvent;
import view.MainView;
import view.MainViewListener;

public class Controller {
	
	private MainModel mainModel;
	
	private MainView mainView;
	
	public Controller () {
		
		mainView = new MainView();
		mainModel = new MainModel();
		
		// Initialize model-> view connection ======================================
		
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
	
	// Initialize view -> model connection ======================================
	
	// INVESTOR ======================================
	
	public List<Investor> getInvestors(){
		
		return mainModel.getInvestors();
	}
	
	public FormEvent getInvestor(int id) {
		
		Investor investor = mainModel.getInvestor(id);
		
		return personToForm(investor, investor.getAmountInvested());
	}
	
	public void addInvestor(FormEvent e) {
		
		Investor investor = new Investor(formToPerson(e), e.getAmount());
		
		mainModel.addInvestor(investor);
		
	}
	
	public void editInvestor(int id, FormEvent e) {
		
		Investor newInvestor = new Investor(id, formToPerson(e), e.getAmount());
		
		mainModel.editInvestor(id, newInvestor);
	}
	
	public void removeInvestor(int id) {
		
		mainModel.removeInvestor(id);
	}
	
	// DEBTOR ======================================
	
	public List<Debtor> getDebtors(){
		
		return mainModel.getDebtors();
	}
	
	public FormEvent getDebtor(int id) {
		
		Debtor debtor = mainModel.getDebtor(id);
		
		return personToForm(debtor, debtor.getAmountBorrowed());
	}
	
	public void addDebtor(FormEvent e) {
		
		Debtor debtor = new Debtor(formToPerson(e), e.getAmount());
		
		mainModel.addDebtor(debtor);
		
	}
	
	public void editDebtor(int id, FormEvent e) {
		
		Debtor newDebtor = new Debtor(id, formToPerson(e), e.getAmount());
		
		mainModel.editDebtor(id, newDebtor);
	}
	
	public void removeDebtor(int id) {
		
		mainModel.removeDebtor(id);
	}
}
