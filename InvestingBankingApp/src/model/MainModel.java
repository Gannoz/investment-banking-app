package model;

import java.util.List;

import model.Database.Database;
import model.Database.DatabaseListener;

public class MainModel{

	private Database db;
	
	public MainModel() {
		
		db = new Database();
		
		db.setDatabaseListener(new DatabaseListener() {
			
		});
		
	}
	
	// DATABASE ======================================================
	
	public List<Investor> getInvestors(){
		return db.getInvestors();
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
	
	public List<Debtor> getDebtors() {
		return db.getDebtors();
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
