package view.Manage;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;

import model.Debtor;
import model.Investor;

public class ManagePanel extends JPanel{
	
	private ManageHeader manageHeader;
	private ManageMain manageMain;
	
	public ManagePanel() {
		
		manageHeader = new ManageHeader();
		manageMain = new ManageMain();
		
		setLayout(new BorderLayout());
		add(manageHeader, BorderLayout.NORTH);
		add(manageMain, BorderLayout.CENTER);
	}
	
	public void setTableData(List<Investor> unmanagedInvestors, List<Debtor> unmanagedDebtors, List<Investor> managedInvestors, List<Debtor> managedDebtors) {
		
		manageMain.setTableData(unmanagedInvestors, unmanagedDebtors, managedInvestors, managedDebtors);
	}
	
	public void refreshTables() {
		manageMain.refreshTables();
	}
	
}
