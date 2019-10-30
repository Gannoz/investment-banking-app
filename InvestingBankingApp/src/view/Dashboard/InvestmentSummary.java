package view.Dashboard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class InvestmentSummary extends JPanel {

	private JLabel minimumSize;
	private JLabel investmentSummaryLabel;
	private JLabel totalInvestmentLabel;
	private JLabel totalInvestedLabel;
	private JLabel fundsAvailableLabel;
	private JLabel totalInvestmentsContainer;
	private JLabel totalInvestedContainer;
	private JLabel fundsAvailableContainer;
	private long totalInvestments;
	private long totalInvested;
	private long fundsAvailable;

	private JButton test;

	public InvestmentSummary() {

		minimumSize = new JLabel();

		investmentSummaryLabel = new JLabel("Investment Summary (Rp)");
		totalInvestmentLabel = new JLabel("Total Investments");
		totalInvestedLabel = new JLabel("Total Invested");
		fundsAvailableLabel = new JLabel("Funds Available");

		totalInvestmentsContainer = new JLabel();
		totalInvestedContainer = new JLabel();
		fundsAvailableContainer = new JLabel();

		test = new JButton("test");

		setDesign();

		update();

		layoutComponents();

	}

	public void setData(long totalInvestments, long totalInvested, long totalEarnings) {

		this.totalInvestments = totalInvestments;
		this.totalInvested = totalInvested;
		this.fundsAvailable = totalInvestments - totalInvested + totalEarnings;

		update();
	}

	private String numberToCommaString(long number) {
		return NumberFormat.getNumberInstance(Locale.US).format(number);
	}

	public void update() {
		totalInvestmentsContainer.setText(numberToCommaString(totalInvestments));
		totalInvestedContainer.setText(numberToCommaString(totalInvested));
		fundsAvailableContainer.setText(numberToCommaString(fundsAvailable));
	}

	public void setDesign() {
		Color lightBlue = new Color(204, 247, 255);
		int size = 25;

		setBackground(lightBlue);

		minimumSize.setHorizontalAlignment(SwingConstants.CENTER);

		investmentSummaryLabel.setFont(new Font("montserrat", Font.PLAIN, size));
		investmentSummaryLabel.setHorizontalAlignment(SwingConstants.CENTER);
//		investmentSummaryLabel.setForeground(Color.white);

		// Container
		size = 30;

		totalInvestmentsContainer.setFont(new Font("", Font.BOLD, size));
		totalInvestedContainer.setFont(new Font("", Font.BOLD, size));
		fundsAvailableContainer.setFont(new Font("", Font.BOLD, size));
		totalInvestmentsContainer.setHorizontalAlignment(SwingConstants.CENTER);
		totalInvestedContainer.setHorizontalAlignment(SwingConstants.CENTER);
		fundsAvailableContainer.setHorizontalAlignment(SwingConstants.CENTER);

		// Container Label
		size = 19;

		totalInvestmentLabel.setFont(new Font("", Font.PLAIN, size));
		totalInvestedLabel.setFont(new Font("", Font.PLAIN, size));
		fundsAvailableLabel.setFont(new Font("", Font.PLAIN, size));
		totalInvestmentLabel.setHorizontalAlignment(SwingConstants.CENTER);
		totalInvestedLabel.setHorizontalAlignment(SwingConstants.CENTER);
		fundsAvailableLabel.setHorizontalAlignment(SwingConstants.CENTER);

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
		gc.gridy++;

		gc.fill = GridBagConstraints.BOTH;

		gc.weightx = 0;
		gc.weighty = 1;
		gc.gridwidth = 3;

		gc.gridx = 0;
		gc.insets = new Insets(40, 15, 35, 15);
		gc.anchor = GridBagConstraints.CENTER;

		add(investmentSummaryLabel, gc);

		// Second Row
		gc.weightx = 1;
		gc.weighty = 0.5;

		gc.gridy++;
		gc.gridwidth = 1;
		gc.anchor = GridBagConstraints.PAGE_END;
		gc.insets = new Insets(30, 25, 0, 25);

		gc.gridx = 0;
		add(totalInvestmentsContainer, gc);

		gc.gridx = 1;
		add(totalInvestedContainer, gc);

		gc.gridx = 2;
		add(fundsAvailableContainer, gc);

		// Third Row
		gc.gridy++;

		gc.anchor = GridBagConstraints.PAGE_START;
		gc.insets = new Insets(20, 25, 75, 25);

		gc.gridx = 0;
		add(totalInvestmentLabel, gc);

		gc.gridx = 1;
		add(totalInvestedLabel, gc);

		gc.gridx = 2;
		add(fundsAvailableLabel, gc);

	}

}
