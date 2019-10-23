package view.Investor;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;

import controller.Controller;
import model.Investor;
import view.FormEvent;

public class InvestorPanel extends JPanel{
	
	private InvestorHeader investorHeader;
	private InvestorMain investorMain;
	
	private InvestorListener investorListener;
	
	public InvestorPanel() {
		investorHeader = new InvestorHeader();
		investorMain = new InvestorMain();
		
		
		investorMain.setInvestorListener(new InvestorListener() {

			public void addInvestorInfo(FormEvent e) {
				investorListener.addInvestorInfo(e);
			}
			
			public void editInvestorInfo(int id, FormEvent e) {
				investorListener.editInvestorInfo(id, e);
			}
			
			public void removeInvestorInfo(int id) {
				investorListener.removeInvestorInfo(id);
			}
			
			public void getInvestorInfo(int id) {
				investorListener.getInvestorInfo(id);
			}
			
		});
		
		setLayout(new BorderLayout());
		
		add(investorHeader, BorderLayout.NORTH);
		add(investorMain, BorderLayout.CENTER);
		
	}
	
	
	public void setInvestorListener(InvestorListener listener) {
		investorListener = listener;
	}
	
	public void setFormData(FormEvent e) {
		investorMain.setFormData(e);
	}
	
	public void setTableData(List<Investor> db) {
		investorMain.setTableData(db);
	}

}
