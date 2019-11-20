package controller;

import java.util.List;

import model.Debtor;
import model.DebtorFee;
import model.DebtorRequest;
import model.Gender;
import model.Investor;
import model.MainModel;
import model.Person;
import view.FormEvent;
import view.MainView;
import view.MainViewListener;

public class Controller {

	private MainModel mainModel;

	private MainView mainView;

	public Controller() {

		mainView = new MainView();
		mainModel = new MainModel();

		// Set MainView Listener ======================================
		
		setData();
		
		mainView.setTableData(getInvestors(), getDebtors(), getUnmanagedInvestors(), getUnmanagedDebtors(),
				getManagedInvestors(), getManagedDebtors(), getUnpaidInvestors(), getUnpaidDebtors());

		mainView.setViewListener(new MainViewListener() {
			
			// INVESTOR
			
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

			@Override
			public void manageInvestorRequest(int id) {
				manageInvestor(id);

				// Set the data on the view
				setData();
			}

			@Override
			public void unmanageInvestorRequest(int id) {

				unmanageInvestor(id);
				setData();
			}

			@Override
			public void fulfillInvestorRequest(int id) {
				// TODO Auto-generated method stub

			}
			
			@Override
			public void paidInvestorRequest(int id) {
				paidInvestor(id);

			}
			
			// DEBTOR

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

			public void manageDebtorRequest(int id) {
				manageDebtor(id);

				// Change the fee percentage and fee amount of the debtor
//				Debtor debtor = mainModel.getDebtor(id);
//				long amountBorrowed = debtor.getAmountBorrowed();
//				float feeMultiplier = mainModel.calcDebtorFeeMultiplier(amountBorrowed);
//				debtor.setFeePercentage(feeMultiplier * 100);
//				debtor.setFeeAmount((long) (amountBorrowed * feeMultiplier));

				// Set data to view
				setData();
			}

			public void unmanageDebtorRequest(int id) {

				unmanageDebtor(id);
				setData();
			}

			public void fulfillDebtorRequest(int id) {

			}

			@Override
			public void paidDebtorRequest(int requestId) {
				DebtorFee debtorFee = mainModel.getDebtorFee(requestId);
				mainModel.addEarnings(requestId, debtorFee.getFeeAmount());
				paidDebtor(requestId);
				
				setData();
			}

			@Override
			public void addDebtorRequest(int debtorId, long amountRequested) {
				// TODO Auto-generated method stub
				mainModel.addDebtorRequest(debtorId, amountRequested);
			}

			@Override
			public void addInvestorRequest(int investorId, long amountRequested) {
				mainModel.addInvestorRequest(investorId, amountRequested);
				
			}

		});

	}
	
	public void setData() {
		mainView.setData(getTotalInvestments(), getTotalInvested(), getTotalEarnings(), getMonthEarnings(), getYearEarnings());
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
			gender = Gender.M;
		} else if (genderCategory.equalsIgnoreCase("female")) {
			gender = Gender.F;
		}

		Person person = new Person(nik, name, gender, address, rtrw, village, district, religion, marriageStatus,
				occupation, nationality);

		return person;
	}

	// Initialize view - model connection ======================================

	// DASHBOARD ======================================

	public long getTotalInvestments() {
		return mainModel.getTotalInvestments();
	}

	public long getTotalInvested() {
		return mainModel.getTotalInvested();
	}

	public long getTotalEarnings() {
		return mainModel.getTotalEarnings();
	}
	
	public long getMonthEarnings() {
		return mainModel.getMonthEarnings();
	}
	
	public long getYearEarnings() {
		return mainModel.getYearEarnings();
	}

	// INVESTOR ======================================

	public List<Investor> getInvestors() {

		return mainModel.getInvestors();
	}

	public List<Investor> getUnmanagedInvestors() {

		return mainModel.getUnmanagedInvestors();
	}

	public List<Investor> getManagedInvestors() {

		return mainModel.getManagedInvestors();
	}

	public List<Investor> getUnpaidInvestors() {

		return mainModel.getUnpaidInvestors();
	}

	public void unmanageInvestor(int id) {

		mainModel.unmanageInvestor(id);
	}

	public void manageInvestor(int id) {

		mainModel.manageInvestor(id);
	}

	public void paidInvestor(int id) {
		mainModel.paidInvestor(id);
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

	public List<Debtor> getDebtors() {

		return mainModel.getDebtors();
	}

	public List<DebtorRequest> getUnmanagedDebtors() {

		return mainModel.getUnmanagedDebtors();
	}

	public List<DebtorRequest> getManagedDebtors() {

		return mainModel.getManagedDebtors();
	}

	public List<DebtorFee> getUnpaidDebtors() {
		return mainModel.getUnpaidDebtors();
	}

	public void manageDebtor(int id) {

		mainModel.manageDebtor(id);
	}

	public void unmanageDebtor(int id) {
		mainModel.unmanageDebtor(id);
	}

	public void paidDebtor(int id) {
		mainModel.paidDebtor(id);
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
