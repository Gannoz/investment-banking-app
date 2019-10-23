package view.Dashboard;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;

import controller.Controller;
import model.Debtor;
import model.Investor;

public class DashboardPanel extends JPanel {

	DashboardHeader dashboardHeader;
	DashboardMain dashboardMain;

	public DashboardPanel() {
		dashboardHeader = new DashboardHeader();
		dashboardMain = new DashboardMain();

		setLayout(new BorderLayout());

		add(dashboardHeader, BorderLayout.NORTH);
		add(dashboardMain, BorderLayout.CENTER);
		
		
	}
	
	public void setInvestorTableData(List<Investor> db) {
		dashboardMain.setInvestorTableData(db);
	}
	
	public void setDebtorTableData(List<Debtor> db) {
		dashboardMain.setDebtorTableData(db);
	}
	
	public void refreshTables() {
		dashboardMain.refreshTables();
	}

}
