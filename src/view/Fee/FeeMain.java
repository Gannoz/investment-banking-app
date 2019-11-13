package view.Fee;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JPanel;

import model.DebtorFee;
import model.Investor;
import view.DebtorTableListener;
import view.InvestorTableListener;

public class FeeMain extends JPanel {

	private InvestorFeeTablePanel investorFeeTablePanel;
	private DebtorFeeTablePanel debtorFeeTablePanel;

	private FeeListener listener;

	public FeeMain() {

		investorFeeTablePanel = new InvestorFeeTablePanel();
		debtorFeeTablePanel = new DebtorFeeTablePanel();

		investorFeeTablePanel.setTableListener(new InvestorTableListener() {

			public void sendInvestorId(int id) {

				listener.sendInvestorId(id);
				refreshTables();
			}
		});

		debtorFeeTablePanel.setTableListener(new DebtorTableListener() {

			public void sendDebtorId(int id) {

				listener.sendDebtorId(id);
				refreshTables();
			}
		});

		setDesign();
		layoutComponents();
	}

	public void setFeeListener(FeeListener listener) {
		
		this.listener = listener;
	}

	public void setTableData(List<Investor> investors, List<DebtorFee> debtors) {
		
		investorFeeTablePanel.setData(investors);
		debtorFeeTablePanel.setData(debtors);
	}

	public void refreshTables() {

		investorFeeTablePanel.refreshTable();
		debtorFeeTablePanel.refreshTable();
	}

	private void setDesign() {
		setBackground(Color.white);
	}

	private void layoutComponents() {

		setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		c.anchor = GridBagConstraints.PAGE_START;
		c.fill = GridBagConstraints.BOTH;

		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(25, 25, 25, 25);
		add(investorFeeTablePanel, c);

		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx++;
		c.insets = new Insets(25, 0, 25, 25);
		add(debtorFeeTablePanel, c);

	}
}
