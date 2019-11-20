package view.Debtor;

import view.FormEvent;

public interface DebtorListener {

	public void addDebtorInfo(FormEvent e);

	public void editDebtorInfo(int id, FormEvent e);

	public void removeDebtorInfo(int id);

	public void getDebtorInfo(int id);
	
	public void addRequest(int debtorId, long amountRequested);
}
