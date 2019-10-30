package view.Manage;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;

import model.Debtor;
import model.Investor;

public class ManagePanel extends JPanel {

	private ManageHeader manageHeader;
	private ManageMain manageMain;

	private ManageListener listener;

	public ManagePanel() {

		manageHeader = new ManageHeader();
		manageMain = new ManageMain();

		manageMain.setManageListener(new ManageListener() {

			@Override
			public void manageDebtorRequest(int id) {

				listener.manageDebtorRequest(id);
			}

			@Override
			public void unmanageDebtorRequest(int id) {

				listener.unmanageDebtorRequest(id);
			}

			@Override
			public void fullfillDebtorRequest(int id) {

				listener.fullfillDebtorRequest(id);
			}

			@Override
			public void manageInvestorRequest(int id) {
				// TODO Auto-generated method stub

				listener.manageInvestorRequest(id);
			}

			@Override
			public void unmanageInvestorRequest(int id) {
				// TODO Auto-generated method stub

				listener.unmanageInvestorRequest(id);
			}

			@Override
			public void fullfillInvestorRequest(int id) {
				// TODO Auto-generated method stub

			}
		});

		setLayout(new BorderLayout());
		add(manageHeader, BorderLayout.NORTH);
		add(manageMain, BorderLayout.CENTER);
	}

	public void setManageListener(ManageListener listener) {
		this.listener = listener;
	}

	public void setData(long investorEquity, long earnings) {
		manageMain.setData(investorEquity, earnings);
	}

	public void setTableData(List<Investor> unmanagedInvestors, List<Debtor> unmanagedDebtors,
			List<Investor> managedInvestors, List<Debtor> managedDebtors) {

		manageMain.setTableData(unmanagedInvestors, unmanagedDebtors, managedInvestors, managedDebtors);
	}

	public void refreshTables() {
		manageMain.refreshTables();
	}

}
