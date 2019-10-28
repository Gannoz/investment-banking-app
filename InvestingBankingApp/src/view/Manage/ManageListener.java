package view.Manage;

public interface ManageListener {
	
	public void manageInvestorRequest(int id);
	public void unmanageInvestorRequest(int id);
	public void fullfillInvestorRequest(int id);

	public void manageDebtorRequest(int id);
	public void unmanageDebtorRequest(int id);
	public void fullfillDebtorRequest(int id);
	
}
