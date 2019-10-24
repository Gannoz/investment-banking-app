package view.Dashboard;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JPanel;

import model.Debtor;
import model.Investor;

public class DashboardMain extends JPanel{
	
	private InvestmentSummary investmentSummary;
	private TopInvestors topInvestors;
	private TopDebtors topDebtors;
	private EarningsSummary earningsSummary;
	private EarningsTrend earningsTrend;
	
	public DashboardMain() {
		
		investmentSummary = new InvestmentSummary();
		topInvestors = new TopInvestors();
		topDebtors = new TopDebtors();
		earningsSummary = new EarningsSummary();
		earningsTrend = new EarningsTrend();
		
		setDesign();
		layoutComponents();
		
	}
	
	public void refreshTables() {
		topInvestors.refreshTable();
		topDebtors.refreshTable();
	}
	
	public void setTableData(List<Investor> investors, List<Debtor> debtors) {
		topInvestors.setTableData(investors);
		topDebtors.setTableData(debtors);
	}
	
	public void setDebtorTableData() {
		
	}
	
	private void setDesign() {
		setBackground(Color.white);
	}
	
	private void layoutComponents() {
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		// First Row
		gc.gridy = 0;
		
		gc.weightx = 1;
		gc.weighty = 1;
		gc.anchor = GridBagConstraints.CENTER;
		gc.fill = GridBagConstraints.HORIZONTAL;
		
		gc.insets = new Insets(25,25,25,25);
		gc.gridx = 0;
		add(investmentSummary, gc);
		
		gc.fill = GridBagConstraints.BOTH;
		
		gc.gridx = 1;
		gc.insets = new Insets(25,0,25,25);
		add(topInvestors, gc);
		
		gc.gridx = 2;
		gc.insets = new Insets(25,0,25,25);
		add(topDebtors, gc);
		
		// Second Row
		gc.gridy++;
		
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		gc.gridx = 0;
		gc.insets = new Insets(0,25,25,25);
		add(earningsSummary, gc);
		
		gc.gridx = 1;
		gc.gridwidth = 2;
		gc.insets = new Insets(0,0,25,25);
		add(earningsTrend, gc);
		
		
	}
	
}
