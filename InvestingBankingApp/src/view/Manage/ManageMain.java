package view.Manage;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ManageMain extends JPanel{
	
	private JLabel label;
	private ManageDebtorRequestsTablePanel debtorRequestsTablePanel;
	private ManagePendingPaymentsTablePanel paymentsReceivableTablePanel;
	private ManageInvestorOffersTablePanel investorOffersTablePanel;
	private ManagePaymentsPayableTablePanel paymentsPayableTablePanel;
	private ManageInfoPanel manageInfoPanel;
	
	public ManageMain() {
		
		debtorRequestsTablePanel = new ManageDebtorRequestsTablePanel();
		paymentsReceivableTablePanel = new ManagePendingPaymentsTablePanel();
		investorOffersTablePanel = new ManageInvestorOffersTablePanel();
		paymentsPayableTablePanel = new ManagePaymentsPayableTablePanel();
		
		manageInfoPanel = new ManageInfoPanel();
		
		setDesign();
		layoutComponents();
	}
	
	private void setDesign() {
		setBackground(Color.white);
		
	}
	
	private void layoutComponents() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.weightx = 0.2;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight=1;
		
		c.insets = new Insets(25,25,25,25);
		
		add(debtorRequestsTablePanel, c);
		
		c.gridy++;
		c.insets = new Insets(0,25,25,25);
		add(paymentsReceivableTablePanel, c);
		
		c.gridheight=1;
		c.weightx = 0.6;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		c.gridx++;
		c.gridy = 0;
		c.insets = new Insets(25,0,25,25);
		add(manageInfoPanel, c);
		
		c.weightx = 0.2;
		c.weighty = 1;
		c.gridx++;
		c.gridy = 0;
		c.insets = new Insets(25,0,25,25);
		add(investorOffersTablePanel, c);
		
		c.gridy++;
		c.insets = new Insets(0,0,25,25);
		add(paymentsPayableTablePanel, c);
		
	}
}
