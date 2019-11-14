package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MainModel {

	private Database db;
	private Investments investments;
	private Earnings earnings;
	private LocalDateTime time;

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
		investments.subtractTotalInvestment(db.getInvestor(id).getAmountInvested());
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

	public List<DebtorRequest> getUnmanagedDebtors() {
		return db.getUnmanagedDebtors();
	}

	public List<DebtorRequest> getManagedDebtors() {
		return db.getManagedDebtors();
	}

	public List<DebtorFee> getUnpaidDebtors() {
		return db.getUnpaidDebtors();
	}

	public void manageDebtor(int id) {
		db.manageDebtor(id);
		investments.addTotalInvested(db.getDebtor(id).getAmountBorrowed());
	}

	public void unmanageDebtor(int id) {
		investments.subtractTotalInvested(db.getDebtor(id).getAmountBorrowed());
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
	
	public void addDebtorRequest(int debtorId, int amountRequested) {
		db.addDebtorRequest(debtorId, amountRequested);
	}
	
	// FEES
	
	public DebtorFee getDebtorFee(int requestId) {
		return db.getDebtorFee(requestId);
	}
	
	// INVESTMENTS

	public long getTotalInvestments() {
		return investments.getTotalInvestments();
	}

	public long getTotalInvested() {
		return investments.getTotalInvested();
	}
	
	// EARNINGS

	public long calcDebtorFeeAmount(long amount) {
		return db.calcDebtorFeeAmount(amount);
	}

	public float calcDebtorFeeMultiplier(long amount) {
		return db.calcDebtorFeeMultiplier(amount);
	}
	
	public long getTotalEarnings() {
		return db.getTotalEarnings();
	}
	
	public long getMonthEarnings() {
		return db.getMonthEarnings();
	}
	
	public long getYearEarnings() {
		return db.getYearEarnings();
	}
	
	public void addEarnings(int requestId, long earning) {
		db.addEarnings(requestId, earning);
	}

}
