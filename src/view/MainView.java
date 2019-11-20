package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.time.LocalDateTime;
import java.util.List;

import javax.swing.JFrame;

import model.Debtor;
import model.DebtorFee;
import model.DebtorRequest;
import model.Investor;
import view.Dashboard.DashboardPanel;
import view.Debtor.DebtorListener;
import view.Debtor.DebtorPanel;
import view.Fee.FeeListener;
import view.Fee.FeePanel;
import view.Investor.InvestorListener;
import view.Investor.InvestorPanel;
import view.Manage.ManageListener;
import view.Manage.ManagePanel;

public class MainView extends JFrame {

	private ScreenChanger screenChanger;
	private PanelContainer panelContainer;

	private DashboardPanel dashboardPanel;
	private ManagePanel managePanel;
	private InvestorPanel investorPanel;
	private DebtorPanel debtorPanel;
	private FeePanel feePanel;

	private MainViewListener viewListener;

	private int frameWidth = 1500;
	private int frameHeight = 770;

	public MainView() {

		super("Investment Banking App");

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(frameWidth, frameHeight);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println((int) dim.getWidth() - frameWidth);
		System.out.println((int) dim.getHeight() - frameHeight);
		setLocation((int) dim.getWidth() - frameWidth, (int) dim.getHeight() - frameHeight);

		setResizable(false);
		setLocationRelativeTo(null);

		screenChanger = new ScreenChanger();
		dashboardPanel = new DashboardPanel();
		managePanel = new ManagePanel();
		investorPanel = new InvestorPanel();
		debtorPanel = new DebtorPanel();
		feePanel = new FeePanel();
		panelContainer = new PanelContainer(dashboardPanel, managePanel, investorPanel, debtorPanel, feePanel);

		// INVESTOR LISTENER
		// Send investor form information to MainView

		investorPanel.setInvestorListener(new InvestorListener() {

			public void addInvestorInfo(FormEvent e) {
				viewListener.addInvestorInfo(e);
			}

			public void editInvestorInfo(int id, FormEvent e) {
				viewListener.editInvestorInfo(id, e);
			}

			public void removeInvestorInfo(int index) {
				viewListener.removeInvestorInfo(index);
			}

			public void getInvestorInfo(int id) {
				viewListener.getInvestorInfo(id);
			}

			@Override
			public void addRequest(int investorId, long amountRequested) {
				viewListener.addInvestorRequest(investorId, amountRequested);
				
			}
		});

		// DEBTOR LISTENER
		// Send debtor form information to MainView

		debtorPanel.setDebtorListener(new DebtorListener() {

			public void addDebtorInfo(FormEvent e) {
				viewListener.addDebtorInfo(e);
			}

			public void editDebtorInfo(int id, FormEvent e) {
				viewListener.editDebtorInfo(id, e);
			}

			public void removeDebtorInfo(int id) {
				viewListener.removeDebtorInfo(id);
			}

			public void getDebtorInfo(int id) {
				viewListener.getDebtorInfo(id);
			}

			@Override
			public void addRequest(int debtorId, long amountRequested) {
				viewListener.addDebtorRequest(debtorId, amountRequested);
				
			}

		});

		// MANAGE LISTENER
		// Listen to manage panel

		managePanel.setManageListener(new ManageListener() {

			@Override
			public void manageDebtorRequest(int id) {

				viewListener.manageDebtorRequest(id);

			}

			@Override
			public void unmanageDebtorRequest(int id) {

				viewListener.unmanageDebtorRequest(id);
			}

			@Override
			public void fullfillDebtorRequest(int id) {

				viewListener.fulfillDebtorRequest(id);
			}

			@Override
			public void manageInvestorRequest(int id) {
				// TODO Auto-generated method stub
				viewListener.manageInvestorRequest(id);
			}

			@Override
			public void unmanageInvestorRequest(int id) {
				// TODO Auto-generated method stub
				viewListener.unmanageInvestorRequest(id);
			}

			@Override
			public void fullfillInvestorRequest(int id) {
				// TODO Auto-generated method stub

			}
		});

		// FEE LISTENERS

		feePanel.setFeeLisener(new FeeListener() {

			@Override
			public void sendInvestorId(int id) {
				viewListener.paidInvestorRequest(id);

			}

			@Override
			public void sendDebtorId(int id) {
				viewListener.paidDebtorRequest(id);

			}

		});

		setLayout(new BorderLayout());

		add(screenChanger, BorderLayout.NORTH);
		add(panelContainer, BorderLayout.CENTER);

		// Screen Changer

		screenChanger.setScreenChangerListener(new ScreenChangerListener() {

			public void overviewPressed() {
				
				System.out.println(LocalDateTime.now());
				frameWidth = 1500;
				frameHeight = 770;
				panelContainer.displayDashboardPanel();
				dashboardPanel.refreshTables();
				setSize(frameWidth, frameHeight);
			}

			public void managePressed() {

				panelContainer.displayManagePanel();
				managePanel.refreshTables();
				setSize(frameWidth, frameHeight);
			}

			public void investorPressed() {

				panelContainer.displayInvestorPanel();
				setSize(frameWidth, frameHeight);
			}

			public void debtorPressed() {

				panelContainer.displayDebtorPanel();
				setSize(frameWidth, frameHeight);
			}

			public void feePressed() {
				panelContainer.displayFeePanel();
				feePanel.refreshTables();
				setSize(frameWidth, frameHeight);
			}

		});

	}

	public void setViewListener(MainViewListener listener) {

		viewListener = listener;
	}

	public void setData(long totalInvestments, long totalInvested, long totalEarnings, long monthEarnings, long yearEarnings) {

		dashboardPanel.setData(totalInvestments, totalInvested, totalEarnings, monthEarnings, yearEarnings);
		managePanel.setData(totalInvestments - totalInvested, totalEarnings);
	}

	public void setTableData(List<Investor> investors, List<Debtor> debtors, List<Investor> unmanagedInvestors,
			List<DebtorRequest> unmanagedDebtors, List<Investor> managedInvestors, List<DebtorRequest> managedDebtors,
			List<Investor> unpaidInvestors, List<DebtorFee> unpaidDebtors) {

		dashboardPanel.setTableData(investors, debtors);
		investorPanel.setTableData(investors);
		debtorPanel.setTableData(debtors);
		managePanel.setTableData(unmanagedInvestors, unmanagedDebtors, managedInvestors, managedDebtors);
		feePanel.setTableData(unpaidInvestors, unpaidDebtors);
	}

	public void setInvestorFormData(FormEvent e) {

		investorPanel.setFormData(e);
	}

	public void setDebtorFormData(FormEvent e) {

		debtorPanel.setFormData(e);
	}

}
