package view.Dashboard;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class EarningsSummary extends JPanel {

	private JLabel minimumSize;

	private JLabel earningsSummaryLabel;
	private JLabel paidDebtorsLabel;
	private JLabel currentMonthLabel;
	private JLabel currentYearLabel;
	private JLabel paidDebtorsContainer;
	private JLabel currentMonthContainer;
	private JLabel currentYearContainer;
	private long paidDebtors;
	private long currentMonthEarnings;
	private long currentYearEarnings;

	private JButton test;

	public EarningsSummary() {

		minimumSize = new JLabel();

		earningsSummaryLabel = new JLabel("Earnings Summary (Rp)");
		paidDebtorsLabel = new JLabel("Paid Debtors");
		currentMonthLabel = new JLabel("Current Month");
		currentYearLabel = new JLabel("Current Year");

		paidDebtorsContainer = new JLabel();
		currentMonthContainer = new JLabel();
		currentYearContainer = new JLabel();

		test = new JButton("test");

		setDesign();

		update();

		layoutComponents();

	}
	
	public void setData(long monthEarnings, long yearEarnings) {
		currentMonthEarnings = monthEarnings;
		currentYearEarnings = yearEarnings;
		
		update();
	}

	private String numberToCommaString(long number) {
		return NumberFormat.getNumberInstance(Locale.US).format(number);
	}

	public void update() {
		paidDebtorsContainer.setText(numberToCommaString(paidDebtors) + "%");
		currentMonthContainer.setText(numberToCommaString(currentMonthEarnings));
		currentYearContainer.setText(numberToCommaString(currentYearEarnings));
	}

	public void setDesign() {
		Color lightBlue = new Color(204, 247, 255);
		int size = 25;

		setBackground(lightBlue);

		minimumSize.setHorizontalAlignment(SwingConstants.CENTER);

		earningsSummaryLabel.setFont(new Font("montserrat", Font.PLAIN, size));
		earningsSummaryLabel.setHorizontalAlignment(SwingConstants.CENTER);
//		investmentSummaryLabel.setForeground(Color.white);

		size = 30;

		paidDebtorsContainer.setFont(new Font("", Font.BOLD, size));
		currentMonthContainer.setFont(new Font("", Font.BOLD, size));
		currentYearContainer.setFont(new Font("", Font.BOLD, size));
		paidDebtorsContainer.setHorizontalAlignment(SwingConstants.CENTER);
		currentMonthContainer.setHorizontalAlignment(SwingConstants.CENTER);
		currentYearContainer.setHorizontalAlignment(SwingConstants.CENTER);

		size = 19;

		paidDebtorsLabel.setFont(new Font("", Font.PLAIN, size));
		currentMonthLabel.setFont(new Font("", Font.PLAIN, size));
		currentYearLabel.setFont(new Font("", Font.PLAIN, size));
		paidDebtorsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		currentMonthLabel.setHorizontalAlignment(SwingConstants.CENTER);
		currentYearLabel.setHorizontalAlignment(SwingConstants.CENTER);

	}

	public void layoutComponents() {
		setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();

		gc.gridy = 0;

		gc.fill = GridBagConstraints.BOTH;

		gc.weightx = 1;
		gc.weighty = 0;
		gc.gridwidth = 3;
		gc.gridx = 0;
		gc.insets = new Insets(0, 350, 0, 350);
		gc.anchor = GridBagConstraints.CENTER;

		add(minimumSize, gc);

		// First Row
		gc.gridy = 0;

		gc.fill = GridBagConstraints.BOTH;

		gc.weightx = 0;
		gc.weighty = 1;
		gc.gridwidth = 3;

		gc.gridx = 0;
		gc.insets = new Insets(40, 15, 35, 15);
//		add(test, gc);
		gc.anchor = GridBagConstraints.CENTER;

		add(earningsSummaryLabel, gc);

		// Second Row
		gc.weightx = 1;
		gc.weighty = 0.5;

		gc.gridy++;
		gc.gridwidth = 1;
		gc.anchor = GridBagConstraints.PAGE_END;
		gc.insets = new Insets(30, 25, 0, 25);

		gc.gridx = 0;
		add(paidDebtorsContainer, gc);

		gc.gridx = 1;
		add(currentMonthContainer, gc);

		gc.gridx = 2;
		add(currentYearContainer, gc);

		// Third Row
		gc.gridy++;

		gc.anchor = GridBagConstraints.PAGE_START;
		gc.insets = new Insets(20, 25, 75, 25);

		gc.gridx = 0;
		add(paidDebtorsLabel, gc);

		gc.gridx = 1;
		add(currentMonthLabel, gc);

		gc.gridx = 2;
		add(currentYearLabel, gc);

	}

}
