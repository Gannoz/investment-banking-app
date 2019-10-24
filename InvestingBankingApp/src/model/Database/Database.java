package model.Database;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import model.Debtor;
import model.Investor;

public class Database {
	
	private List<Investor> investors;
	private List<Investor> unmanagedInvestors;
	private List<Investor> managedInvestors;
	private List<Debtor> debtors;
	private List<Debtor> unmanagedDebtors;
	private List<Debtor> managedDebtors;
	
	public Database() {
		
		investors = new LinkedList<Investor>();
		unmanagedInvestors = new LinkedList<Investor>();
		managedInvestors = new LinkedList<Investor>();
		debtors = new LinkedList<Debtor>();
		unmanagedDebtors = new LinkedList<Debtor>();
		managedDebtors = new LinkedList<Debtor>();
		
	}
	
	// INVESTOR
	
	public List<Investor> getInvestors(){
		
		return Collections.unmodifiableList(investors);
	}
	
	public List<Investor> getUnmanagedInvestors(){
		
		return Collections.unmodifiableList(unmanagedInvestors);
	}

	public List<Investor> getManagedInvestors(){
	
	return Collections.unmodifiableList(managedInvestors);
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
	
	
	
	public void addInvestor(Investor investor) {
		
		investors.add(investor);
		unmanagedInvestors.add(investor);
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
		
		try {
			index = unmanagedInvestors.indexOf(investorEdited);
			unmanagedInvestors.remove(investorEdited);
			unmanagedInvestors.add(index, newInvestor);
		}catch(Exception e) {
			
		}
		
		try {
			index = managedInvestors.indexOf(investorEdited);
			managedInvestors.remove(investorEdited);
			managedInvestors.add(index, newInvestor);
		}catch(Exception e) {
			
		}
	}
	
	public void manageInvestor(int id) {
		Investor investorManaged = null;
		
		for(Investor investor: investors) {
			if (investor.getId() == id) {
				investorManaged = investor;
			}
		}
		
		unmanagedInvestors.remove(investorManaged);
		managedInvestors.add(investorManaged);
	}
	
	public void removeInvestor(int id) {
		
		Investor investorRemoved = null;
		for(Investor investor: investors) {
			if (investor.getId() == id) {
				investorRemoved = investor;
			}
		}
		investors.remove(investorRemoved);
		
		try {
			unmanagedInvestors.remove(investorRemoved);
		}catch(Exception e) {
			
		}
		
		try {
			managedInvestors.remove(investorRemoved);
		}catch(Exception e) {
			
		}
	}
	
	// DEBTOR
	
	public List<Debtor> getDebtors(){
		
		return Collections.unmodifiableList(debtors);
	}
	
	public List<Debtor> getUnmanagedDebtors(){
		
		return Collections.unmodifiableList(unmanagedDebtors);
	}

	public List<Debtor> getManagedDebtors(){
		
	return Collections.unmodifiableList(managedDebtors);
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
	
	public void addDebtor(Debtor debtor) {
		
		debtors.add(debtor);
		unmanagedDebtors.add(debtor);
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
		
		try {
			index = unmanagedDebtors.indexOf(debtorEdited);
			unmanagedDebtors.remove(debtorEdited);
			unmanagedDebtors.add(index, newDebtor);
		}catch(Exception e) {
			
		}
		
		try {
			index = managedDebtors.indexOf(debtorEdited);
			managedDebtors.remove(debtorEdited);
			managedDebtors.add(index, newDebtor);
		}catch(Exception e) {
			
		}
	}
	
	public void manageDebtor(int id) {
		
		Debtor debtorManaged = null;
		for(Debtor debtor: debtors) {
			if (debtor.getId() == id) {
				debtorManaged = debtor;
			}
		}
		unmanagedDebtors.remove(debtorManaged);
		managedDebtors.add(debtorManaged);
	}
	
	public void removeDebtor(int id) {
		
		Debtor debtorRemoved = null;
		for(Debtor debtor: debtors) {
			if (debtor.getId() == id) {
				debtorRemoved = debtor;
			}
		}
		debtors.remove(debtorRemoved);
		
		try {
			unmanagedDebtors.remove(debtorRemoved);
		}catch(Exception e) {
			
		}
		
		try {
			managedDebtors.remove(debtorRemoved);
		}catch(Exception e) {
			
		}
	}
}
