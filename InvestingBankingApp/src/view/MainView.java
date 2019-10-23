package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JFrame;

import model.Debtor;
import model.Investor;
import view.Dashboard.DashboardPanel;
import view.Debtor.DebtorListener;
import view.Debtor.DebtorPanel;
import view.Investor.InvestorListener;
import view.Investor.InvestorPanel;
import view.Manage.ManagePanel;

public class MainView extends JFrame{
	
	private ScreenChanger screenChanger;
	private PanelContainer panelContainer;
	
	private DashboardPanel dashboardPanel;
	private ManagePanel managePanel;
	private InvestorPanel investorPanel;
	private DebtorPanel debtorPanel;
	
	private MainViewListener viewListener;
	
	private int frameWidth = 1500;
	private int frameHeight = 770;
	
	public MainView() {
		super("Investment Banking App");
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(frameWidth,frameHeight);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println((int)dim.getWidth()- frameWidth);
		System.out.println((int)dim.getHeight() - frameHeight);
		setLocation((int)dim.getWidth()- frameWidth, (int)dim.getHeight() - frameHeight);
		
		setResizable(false);
		
		screenChanger = new ScreenChanger();
		dashboardPanel = new DashboardPanel();
		managePanel = new ManagePanel();
		investorPanel = new InvestorPanel();
		debtorPanel = new DebtorPanel();
		panelContainer = new PanelContainer(dashboardPanel, managePanel, investorPanel, debtorPanel);
		
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
		});
		
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
		});
		
		setLayout(new BorderLayout());
		
		add(screenChanger, BorderLayout.NORTH);
		add(panelContainer, BorderLayout.CENTER);
		
		screenChanger.setScreenChangerListener(new ScreenChangerListener() {

			public void overviewPressed() {
				frameWidth = 1500;
				frameHeight = 770;
				panelContainer.displayDashboardPanel();
				dashboardPanel.refreshTables();
				setSize(frameWidth,frameHeight);
				
			}
			
			public void managePressed() {
				panelContainer.displayManagePanel();
				setSize(frameWidth,frameHeight);
			}

			public void investorPressed() {
				panelContainer.displayInvestorPanel();
				setSize(frameWidth,frameHeight);
			}

			public void debtorPressed() {
				panelContainer.displayDebtorPanel();
				setSize(frameWidth,frameHeight);
			}
			
		});
		
	}
	
	public void setViewListener(MainViewListener listener) {
		viewListener = listener;
	}
	
	public void setTableData(List<Investor> investors, List<Debtor> debtors) {
		dashboardPanel.setInvestorTableData(investors);
		dashboardPanel.setDebtorTableData(debtors);
		investorPanel.setTableData(investors);
		debtorPanel.setTableData(debtors);
		
	}
	
	public void setInvestorFormData(FormEvent e) {
		investorPanel.setFormData(e);
		
	}
	
	public void setDebtorFormData(FormEvent e) {
		debtorPanel.setFormData(e);
	}
	
}
