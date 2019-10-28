package view;

public interface MainViewListener {
	
	// INVESTOR
	public void addInvestorInfo(FormEvent e);
	public void editInvestorInfo(int id, FormEvent e);
	public void removeInvestorInfo(int index);
	public void getInvestorInfo(int id);
	
	// DEBTOR
	public void addDebtorInfo(FormEvent e);
	public void editDebtorInfo(int id, FormEvent e);
	public void removeDebtorInfo(int id);
	public void getDebtorInfo(int id);
	
	// MANAGE
	public void manageInvestorRequest(int id);
	public void unmanageInvestorRequest(int id);
	public void fulfillInvestorRequest(int id);
	
	public void manageDebtorRequest(int id);
	public void unmanageDebtorRequest(int id);
	public void fulfillDebtorRequest(int id);
	
	
}
