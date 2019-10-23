package model;

import view.FormEvent;

public interface MainModelListener {
	
	// Investor functions
	public void addInvestor(Investor investor);
	public void editInvestor(int id, Investor investor);
	public void removeInvestor(int index);
	public void getInvestor(int id);
	
	// Debtor functions
	public void addDebtor(FormEvent e);
	public void editDebtor(int id, FormEvent e);
	public void removeDebtor(int id);
	public void getDebtor(int id);
}
