package view.Debtor;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DebtorHeader extends JPanel{
	
	private JLabel debtorLabel;
	private JLabel oneMonthProfitLossLabel;
	private JLabel oneYearProfitLossLabel;
	
	private JLabel oneMonthProfitLossDisplayLabel;
	private JLabel oneYearProfitLossDisplayLabel;
	
	private int oneMonthPL = 1;
	private int oneYearPL = 3;
	
	public DebtorHeader() {
		setBackground(Color.black);
		
		debtorLabel = new JLabel("Debtor");
		oneMonthProfitLossLabel = new JLabel("Current Month P/L");
		oneYearProfitLossLabel = new JLabel("Current Year P/L");
		
		debtorLabel.setForeground(Color.white);
		oneMonthProfitLossLabel.setForeground(Color.white);
		oneYearProfitLossLabel.setForeground(Color.white);
		
		debtorLabel.setFont(new Font("",Font.BOLD, 20));
		
		oneMonthProfitLossDisplayLabel = new JLabel();
		oneYearProfitLossDisplayLabel = new JLabel();
		
		layoutComponents();
		updateProfitLoss();
		
	}
	
	public void updateProfitLoss() {
		
		if (oneMonthPL > 0) {
			oneMonthProfitLossDisplayLabel.setForeground(Color.green);
		}else {
			oneMonthProfitLossDisplayLabel.setForeground(Color.red);
		}
		
		if (oneYearPL > 0) {
			oneYearProfitLossDisplayLabel.setForeground(Color.green);
		}else {
			oneYearProfitLossDisplayLabel.setForeground(Color.red);
		}
		
		oneMonthProfitLossDisplayLabel.setText(oneMonthPL + " %");
		oneYearProfitLossDisplayLabel.setText(oneYearPL + " %");
	}
	
	private void layoutComponents() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		// Overview
		gc.gridx = 0;
		gc.gridy = 0;
		
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(10,10,10,0);
		add(debtorLabel, gc);
		
		// Last 1M P/L
		gc.gridx++;
		
		gc.weightx = 0.1;
		gc.anchor = GridBagConstraints.LINE_END;
		add(oneMonthProfitLossLabel, gc);
		
		gc.gridx++;
		gc.anchor = GridBagConstraints.LINE_START;
		add(oneMonthProfitLossDisplayLabel, gc);
		
		// Last 1Y P/L
		gc.gridx++;
		
		gc.weightx = 0.1;
		gc.anchor = GridBagConstraints.LINE_END;
		add(oneYearProfitLossLabel, gc);
		
		gc.gridx++;
		gc.anchor = GridBagConstraints.LINE_START;
		add(oneYearProfitLossDisplayLabel, gc);
		
	}
	
	
}
