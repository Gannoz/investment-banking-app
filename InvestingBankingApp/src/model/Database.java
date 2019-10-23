package model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Database {
	
	private List<Investor> investors;
	private List<Debtor> debtors;
	
	public Database() {
		investors = new LinkedList<Investor>();
		debtors = new LinkedList<Debtor>();
		
	}
	
	public List<Investor> getInvestors(){
		return Collections.unmodifiableList(investors);
	}
	
	public Investor getInvestor(int id) {
		Investor selectedInvestor = null;
		for(Investor investor: investors) {
			if (investor.getId() == id) {
				selectedInvestor = investor;
			}
		}
		
		return selectedInvestor;
	}
	
	public List<Debtor> getDebtors(){
		return Collections.unmodifiableList(debtors);
	}
	
	public Debtor getDebtor(int id) {
		Debtor selectedDebtor = null;
		for(Debtor debtor: debtors) {
			if (debtor.getId() == id) {
				selectedDebtor = debtor;
			}
		}
		return selectedDebtor;
	}
	
	public void addInvestor(Investor investor) {
		investors.add(investor);
	}
	
	public void editInvestor(int id, Investor newInvestor) {
		Investor investorEdited = null;
		for (Investor investor : investors) {
			if (investor.getId() == id) {
				investorEdited = investor;
			}
		}
		int index = investors.indexOf(investorEdited);
		investors.remove(investorEdited);
		investors.add(index, newInvestor);
	}
	
	public void removeInvestor(int id) {
		Investor investorRemoved = null;
		for(Investor investor: investors) {
			if (investor.getId() == id) {
				investorRemoved = investor;
			}
		}
		investors.remove(investorRemoved);
	}
	
	public void addDebtor(Debtor debtor) {
		System.out.println(debtor.getAmountBorrowed());
		
		debtors.add(debtor);
	}
	
	public void editDebtor(int id, Debtor newDebtor) {
		Debtor debtorEdited = null;
		for (Debtor debtor : debtors) {
			if (debtor.getId() == id) {
				debtorEdited = debtor;
			}
		}
		int index = debtors.indexOf(debtorEdited);
		debtors.remove(debtorEdited);
		debtors.add(index, newDebtor);
	}
	
	public void removeDebtor(int id) {
		Debtor debtorRemoved = null;
		for(Debtor debtor: debtors) {
			if (debtor.getId() == id) {
				debtorRemoved = debtor;
			}
		}
		System.out.println(debtorRemoved);
		debtors.remove(debtorRemoved);
	}
}
