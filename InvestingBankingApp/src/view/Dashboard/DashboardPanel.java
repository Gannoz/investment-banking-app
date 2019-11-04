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

	public void setData(long totalInvestments, long totalInvested, long totalEarnings, long monthEarnings) {
		dashboardMain.setData(totalInvestments, totalInvested, totalEarnings, monthEarnings);
	}

	public void setTableData(List<Investor> investors, List<Debtor> debtors) {
		dashboardMain.setTableData(investors, debtors);
	}

	public void refreshTables() {
		dashboardMain.refreshTables();
	}

}
