package view;

import java.awt.CardLayout;

import javax.swing.JPanel;

public class PanelContainer extends JPanel{
	
	CardLayout cl = new CardLayout();
	
	public PanelContainer(JPanel dashboardPanel, JPanel managePanel, JPanel investorPanel, JPanel debtorPanel) {
		setLayout(cl);
		
		add(dashboardPanel, "1");
		add(managePanel, "2");
		add(investorPanel, "3");
		add(debtorPanel, "4");
		
	}
	
	public void displayDashboardPanel() {
		cl.show(this, "1");
	}
	
	public void displayManagePanel() {
		cl.show(this, "2");
	}
	
	public void displayInvestorPanel() {
		cl.show(this, "3");
	}
	
	public void displayDebtorPanel() {
		cl.show(this, "4");
	}
	
}
