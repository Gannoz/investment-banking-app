package model;

import java.util.List;

import model.Database.Database;
import model.Earnings.Earnings;
import model.Investments.Investments;

public class MainModel {

	private Database db;
	private Investments investments;
	private Earnings earnings;

	public MainModel() {

		db = new Database();
		investments = new Investments();
		earnings = new Earnings();
	}

	// INVESTOR

	public List<Investor> getInvestors() {
		return db.getInvestors();
	}

	public List<Investor> getUnmanagedInvestors() {
		return db.getUnmanagedInvestors();
	}

	public List<Investor> getManagedInvestors() {
		return db.getManagedInvestors();
	}

	public List<Investor> getUnpaidInvestors() {
		return db.getUnpaidInvestors();
	}

	public void manageInvestor(int id) {
		db.manageInvestor(id);
		investments.addTotalInvestment(db.getInvestor(id).getAmountInvested());
	}

	public void unmanageInvestor(int id) {
		db.unmanageInvestor(id);
	}

	public void paidInvestor(int id) {
		db.paidInvestor(id);
	}

	public Investor getInvestor(int id) {
		return db.getInvestor(id);
	}

	public void addInvestor(Investor investor) {
		db.addInvestor(investor);
	}

	public void editInvestor(int id, Investor newInvestor) {
		db.editInvestor(id, newInvestor);
	}

	public void removeInvestor(int id) {
		db.removeInvestor(id);
	}

	// DEBTOR

	public List<Debtor> getDebtors() {
		return db.getDebtors();
	}

	public List<Debtor> getUnmanagedDebtors() {
		return db.getUnmanagedDebtors();
	}

	public List<Debtor> getManagedDebtors() {
		return db.getManagedDebtors();
	}

	public List<Debtor> getUnpaidDebtors() {
		return db.getUnpaidDebtors();
	}

	public void manageDebtor(int id) {
		db.manageDebtor(id);
		investments.addTotalInvested(db.getDebtor(id).getAmountBorrowed());
	}

	public void unmanageDebtor(int id) {
		db.unmanageDebtor(id);
	}

	public void fulfillDebtor(int id) {
		db.unmanageDebtor(id);
	}

	public void paidDebtor(int id) {
		db.paidDebtor(id);
	}

	public Debtor getDebtor(int id) {
		return db.getDebtor(id);
	}

	public void addDebtor(Debtor debtor) {
		db.addDebtor(debtor);
	}

	public void editDebtor(int id, Debtor newDebtor) {
		db.editDebtor(id, newDebtor);
	}

	public void removeDebtor(int id) {
		db.removeDebtor(id);
	}

	public long getTotalInvestments() {
		return investments.getTotalInvestments();
	}

	public long getTotalInvested() {
		return investments.getTotalInvested();
	}

	public long calcDebtorFeeAmount(long amount) {
		return earnings.calcDebtorFeeAmount(amount);
	}

	public float calcDebtorFeeMultiplier(long amount) {
		return earnings.calcDebtorFeeMultiplier(amount);
	}
	
	public long getTotalEarnings() {
		return earnings.getTotalEarnings();
	}
	
	public void addTotalEarnings(long earning) {
		earnings.addEarnings(earning);
	}

}
