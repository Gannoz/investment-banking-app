package model;

import java.util.List;

import model.Database.Database;

public class MainModel{

	private Database db;
	
	public MainModel() {
		
		db = new Database();
		
	}
	
	// DATABASE ======================================================
	
	// INVESTOR
	
	public List<Investor> getInvestors(){
		return db.getInvestors();
	}
	
	public List<Investor> getUnmanagedInvestors(){
		return db.getUnmanagedInvestors();
	}
	
	public List<Investor> getManagedInvestors(){
		return db.getManagedInvestors();
	}
	
	public void manageInvestor(int id) {
		db.manageInvestor(id);
	}
	
	public void unmanageInvestor(int id) {
		db.unmanageInvestor(id);
	}
	
	public Investor getInvestor(int id) {
		return db.getInvestor(id);
	}
	
	public void addInvestor(Investor investor) {
		db.addInvestor(investor);
	}
	
	public void editInvestor(int id , Investor newInvestor) {
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
	
	public void manageDebtor(int id) {
		db.manageDebtor(id);
	}
	
	public void unmanageDebtor(int id) {
		db.unmanageDebtor(id);
	}
	
	public void fulfillDebtor(int id) {
		db.unmanageDebtor(id);
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
	
}
