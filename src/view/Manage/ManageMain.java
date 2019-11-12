package view.Manage;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Debtor;
import model.DebtorRequest;
import model.Investor;
import view.DebtorTableListener;
import view.InvestorTableListener;

public class ManageMain extends JPanel {

	private JLabel label;
	private ManageDebtorRequestsTablePanel debtorRequestsTablePanel;
	private ManagePaymentsReceivableTablePanel paymentsReceivableTablePanel;
	private ManageInvestorOffersTablePanel investorOffersTablePanel;
	private ManagePaymentsPayableTablePanel paymentsPayableTablePanel;
	private ManageInfoPanel manageInfoPanel;
	private ManageControlPanel manageControlPanel;

	private ManageListener listener;

	private int manageInvestorId = 0;
	private int unmanageInvestorId = 0;
	private int manageDebtorId = 0;
	private int unmanageDebtorId = 0;

	public ManageMain() {

		debtorRequestsTablePanel = new ManageDebtorRequestsTablePanel();
		paymentsReceivableTablePanel = new ManagePaymentsReceivableTablePanel();
		investorOffersTablePanel = new ManageInvestorOffersTablePanel();
		paymentsPayableTablePanel = new ManagePaymentsPayableTablePanel();

		manageInfoPanel = new ManageInfoPanel();
		manageControlPanel = new ManageControlPanel();

		// TABLE LISTENERS

		investorOffersTablePanel.setTableListener(new InvestorTableListener() {

			@Override
			public void sendInvestorId(int id) {

				try {
					manageInvestorId = id;
					manageDebtorId = 0;
				} catch (IndexOutOfBoundsException e) {
					System.out.println();
				}

				paymentsPayableTablePanel.clearSelection();
				debtorRequestsTablePanel.clearSelection();
				paymentsReceivableTablePanel.clearSelection();
			}
		});

		paymentsPayableTablePanel.setTableListener(new InvestorTableListener() {

			@Override
			public void sendInvestorId(int id) {

				try {
					unmanageInvestorId = id;
					unmanageDebtorId = 0;
				} catch (IndexOutOfBoundsException e) {
					System.out.println();
				}

				investorOffersTablePanel.clearSelection();
				paymentsReceivableTablePanel.clearSelection();
				debtorRequestsTablePanel.clearSelection();
			}
		});

		debtorRequestsTablePanel.setTableListener(new DebtorTableListener() {

			@Override
			public void sendDebtorId(int id) {

				try {
					manageDebtorId = id;
					manageInvestorId = 0;
				} catch (IndexOutOfBoundsException e) {
					System.out.println();
				}

				investorOffersTablePanel.clearSelection();
				paymentsReceivableTablePanel.clearSelection();
				paymentsPayableTablePanel.clearSelection();

			}
		});

		paymentsReceivableTablePanel.setTableListener(new DebtorTableListener() {

			@Override
			public void sendDebtorId(int id) {

				try {
					unmanageDebtorId = id;
					unmanageInvestorId = 0;
				} catch (IndexOutOfBoundsException e) {
					System.out.println();
				}

				investorOffersTablePanel.clearSelection();
				paymentsPayableTablePanel.clearSelection();
				debtorRequestsTablePanel.clearSelection();
			}

		});

		// CONTROL LISTENERS

		manageControlPanel.setManageControlListener(new ManageControlListener() {

			@Override
			public void unmanagePerson() {

				try {
					if (unmanageDebtorId == 0 && unmanageInvestorId != 0) {
						listener.unmanageInvestorRequest(unmanageInvestorId);
					} else if (unmanageInvestorId == 0 && unmanageDebtorId != 0) {
						listener.unmanageDebtorRequest(unmanageDebtorId);
					}
				} catch (NullPointerException e) {
					System.out.println();
				}

				unmanageInvestorId = 0;
				unmanageDebtorId = 0;

				refreshTables();
			}

			@Override
			public void managePerson() {

				try {
					if (manageDebtorId == 0 && manageInvestorId != 0) {
						listener.manageInvestorRequest(manageInvestorId);
					} else if (manageInvestorId == 0 && manageDebtorId != 0) {
						listener.manageDebtorRequest(manageDebtorId);
					}
				} catch (NullPointerException e) {
					System.out.println();
				}

				manageInvestorId = 0;
				manageDebtorId = 0;

				refreshTables();
			}

			@Override
			public void fulfillPerson() {

				refreshTables();

			}
		});

		setDesign();
		layoutComponents();
	}

	public void setManageListener(ManageListener listener) {
		this.listener = listener;
	}

	public void setData(long investorEquity, long totalEarnings) {
		manageInfoPanel.setData(investorEquity, totalEarnings);
	}

	private void setDesign() {
		setBackground(Color.white);

	}

	private void layoutComponents() {
		setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		// Column 0
		c.weightx = 0.2;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;

		c.insets = new Insets(25, 25, 25, 25);
		add(investorOffersTablePanel, c);

		c.gridy++;
		c.insets = new Insets(0, 25, 25, 25);
		add(paymentsPayableTablePanel, c);

		// Column 1
		c.gridheight = 1;
		c.weightx = 0.6;
		c.weighty = 0.5;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		c.gridx++;
		c.gridy = 0;
		c.insets = new Insets(25, 0, 25, 25);
		add(manageInfoPanel, c);

		c.weighty = 0.5;
		c.gridy++;
		c.insets = new Insets(0, 0, 25, 25);
		add(manageControlPanel, c);

		// Column 2
		c.weightx = 0.2;
		c.weighty = 1;
		c.gridx++;
		c.gridy = 0;
		c.insets = new Insets(25, 0, 25, 25);
		add(debtorRequestsTablePanel, c);

		c.gridy++;
		c.insets = new Insets(0, 0, 25, 25);
		add(paymentsReceivableTablePanel, c);

	}

	public void setTableData(List<Investor> unmanagedInvestors, List<DebtorRequest> unmanagedDebtors,
			List<Investor> managedInvestors, List<DebtorRequest> managedDebtors) {

		investorOffersTablePanel.setTableData(unmanagedInvestors);
		debtorRequestsTablePanel.setTableData(unmanagedDebtors);
		paymentsPayableTablePanel.setTableData(managedInvestors);
		paymentsReceivableTablePanel.setTableData(managedDebtors);

	}

	public void refreshTables() {
		investorOffersTablePanel.refreshTable();
		debtorRequestsTablePanel.refreshTable();
		paymentsPayableTablePanel.refreshTable();
		paymentsReceivableTablePanel.refreshTable();
	}
}
