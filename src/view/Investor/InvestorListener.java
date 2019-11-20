package view.Investor;

import view.FormEvent;

public interface InvestorListener {

	public void addInvestorInfo(FormEvent e);

	public void editInvestorInfo(int id, FormEvent e);

	public void removeInvestorInfo(int id);

	public void getInvestorInfo(int id);
	
	public void addRequest(int investorId, long amountRequested);

}
