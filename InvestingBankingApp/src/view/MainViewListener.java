package view;

public interface MainViewListener {
	
	public void addInvestorInfo(FormEvent e);
	public void editInvestorInfo(int id, FormEvent e);
	public void removeInvestorInfo(int index);
	public void getInvestorInfo(int id);
	
	public void addDebtorInfo(FormEvent e);
	public void editDebtorInfo(int id, FormEvent e);
	public void removeDebtorInfo(int id);
	public void getDebtorInfo(int id);
	
}
