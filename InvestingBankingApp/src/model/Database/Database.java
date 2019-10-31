package model.Database;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import model.Debtor;
import model.Investor;

public class Database {

	private List<Investor> investors;
	private List<Investor> unmanagedInvestors;
	private List<Investor> managedInvestors;
	private List<Investor> unpaidInvestors;

	private List<Debtor> debtors;
	private List<Debtor> unmanagedDebtors;
	private List<Debtor> managedDebtors;
	private List<Debtor> unpaidDebtors;

	public Database() {

		investors = new LinkedList<Investor>();
		unmanagedInvestors = new LinkedList<Investor>();
		managedInvestors = new LinkedList<Investor>();
		unpaidInvestors = new LinkedList<Investor>();
		debtors = new LinkedList<Debtor>();
		unmanagedDebtors = new LinkedList<Debtor>();
		managedDebtors = new LinkedList<Debtor>();
		unpaidDebtors = new LinkedList<Debtor>();

	}

	// INVESTOR

	public List<Investor> getInvestors() {

		return Collections.unmodifiableList(investors);
	}

	public List<Investor> getUnmanagedInvestors() {

		return Collections.unmodifiableList(unmanagedInvestors);
	}

	public List<Investor> getManagedInvestors() {

		return Collections.unmodifiableList(managedInvestors);
	}

	public List<Investor> getUnpaidInvestors() {
		return Collections.unmodifiableList(unpaidInvestors);
	}

	public Investor getInvestor(int id) {

		Investor selectedInvestor = null;
		for (Investor investor : investors) {
			if (investor.getId() == id) {
				selectedInvestor = investor;
			}
		}

		return selectedInvestor;
	}

	public void addInvestor(Investor investor) {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		investor.setTimeCreated(LocalDateTime.now().format(dtf));

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
		} catch (Exception e) {

		}

		try {
			index = managedInvestors.indexOf(investorEdited);
			managedInvestors.remove(investorEdited);
			managedInvestors.add(index, newInvestor);
		} catch (Exception e) {

		}
	}

	public void manageInvestor(int id) {

		Investor investorManaged = null;

		for (Investor investor : investors) {
			if (investor.getId() == id) {
				investor.setManaged(true);
				investorManaged = investor;
			}
		}

		unmanagedInvestors.remove(investorManaged);
		managedInvestors.add(investorManaged);
		unpaidInvestors.add(investorManaged);
	}

	public void unmanageInvestor(int id) {

		Investor investorUnmanaged = null;

		for (Investor investor : investors) {
			if (investor.getId() == id) {
				investor.setManaged(false);
				investorUnmanaged = investor;
			}
		}

		unmanagedInvestors.add(investorUnmanaged);
		managedInvestors.remove(investorUnmanaged);
		unpaidInvestors.remove(investorUnmanaged);
	}

	public void paidInvestor(int id) {

		Investor unpaidInvestor = null;

		for (Investor investor : investors) {
			if (investor.getId() == id) {
				investor.setPaid(true);
				unpaidInvestor = investor;
			}
		}

		unpaidInvestors.remove(unpaidInvestor);
	}

	public void removeInvestor(int id) {

		Investor investorRemoved = null;
		for (Investor investor : investors) {
			if (investor.getId() == id) {
				investorRemoved = investor;
			}
		}
		investors.remove(investorRemoved);

		try {
			unmanagedInvestors.remove(investorRemoved);
		} catch (Exception e) {

		}

		try {
			managedInvestors.remove(investorRemoved);
		} catch (Exception e) {

		}
	}

	// DEBTOR

	public List<Debtor> getDebtors() {

		return Collections.unmodifiableList(debtors);
	}

	public List<Debtor> getUnmanagedDebtors() {

		return Collections.unmodifiableList(unmanagedDebtors);
	}

	public List<Debtor> getManagedDebtors() {

		return Collections.unmodifiableList(managedDebtors);
	}

	public List<Debtor> getUnpaidDebtors() {
		return Collections.unmodifiableList(unpaidDebtors);
	}

	public Debtor getDebtor(int id) {

		Debtor selectedDebtor = null;
		for (Debtor debtor : debtors) {
			if (debtor.getId() == id) {
				selectedDebtor = debtor;
			}
		}
		return selectedDebtor;
	}

	public void addDebtor(Debtor debtor) {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		debtor.setTimeCreated(LocalDateTime.now().format(dtf));

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
		} catch (Exception e) {

		}

		try {
			index = managedDebtors.indexOf(debtorEdited);
			managedDebtors.remove(debtorEdited);
			managedDebtors.add(index, newDebtor);
		} catch (Exception e) {

		}
	}

	public void manageDebtor(int id) {

		Debtor debtorManaged = null;
		for (Debtor debtor : debtors) {
			if (debtor.getId() == id) {
				debtorManaged = debtor;
			}
		}
		unmanagedDebtors.remove(debtorManaged);
		managedDebtors.add(debtorManaged);
		unpaidDebtors.add(debtorManaged);
	}

	public void unmanageDebtor(int id) {

		Debtor debtorUnmanaged = null;
		for (Debtor debtor : debtors) {
			if (debtor.getId() == id) {
				debtorUnmanaged = debtor;
			}
		}
		unmanagedDebtors.add(debtorUnmanaged);
		managedDebtors.remove(debtorUnmanaged);
		unpaidDebtors.remove(debtorUnmanaged);
	}

	public void paidDebtor(int id) {

		Debtor unpaidDebtor = null;
		for (Debtor debtor : debtors) {
			if (debtor.getId() == id) {
				debtor.setPaid(true);
				unpaidDebtor = debtor;
			}
		}

		unpaidDebtors.remove(unpaidDebtor);
	}

	public void removeDebtor(int id) {

		Debtor debtorRemoved = null;
		for (Debtor debtor : debtors) {
			if (debtor.getId() == id) {
				debtorRemoved = debtor;
			}
		}

		debtors.remove(debtorRemoved);

		try {
			unmanagedDebtors.remove(debtorRemoved);
		} catch (Exception e) {

		}

		try {
			managedDebtors.remove(debtorRemoved);
		} catch (Exception e) {

		}
	}
}
