package view.Manage;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ManageInfoPanel extends JPanel{
	
	private JLabel investorEquityLabel;
	private JLabel investorEquityContainer;
	
	private JLabel earningsLabel;
	private JLabel earningsContainer;
	
	private JLabel fundsAvailableLabel;
	private JLabel fundsAvailableContainer;
	
	private JButton investButton;
	private JButton withdrawButton;
	
	public ManageInfoPanel() {
		investorEquityLabel = new JLabel("Investor Equity");
		investorEquityContainer = new JLabel();
		
		earningsLabel = new JLabel("Earnings");
		earningsContainer = new JLabel();
		
		fundsAvailableLabel = new JLabel("Funds Available");
		fundsAvailableContainer = new JLabel();
		
		setDesign();
		update();
		layoutComponents();
		
	}
	
	private String numberToCommaString(int number) {
		return NumberFormat.getNumberInstance(Locale.US).format(number);
	}
	
	private void update() {
		investorEquityContainer.setText(numberToCommaString(6000000));
		earningsContainer.setText(numberToCommaString(3000000));
		fundsAvailableContainer.setText(numberToCommaString(9000000));
	}
	
	private void setDesign() {
		Color lightBlue = new Color(204,247,255);
		
		setBackground(lightBlue);
		
		int size = 25;
		investorEquityLabel.setFont(new Font("montserrat", Font.PLAIN, size));
		earningsLabel.setFont(new Font("montserrat", Font.PLAIN, size));
		fundsAvailableLabel.setFont(new Font("montserrat", Font.PLAIN, size));
		
		size = 30;
		investorEquityContainer.setFont(new Font("montserrat", Font.BOLD, size));
		earningsContainer.setFont(new Font("montserrat", Font.BOLD, size));
		fundsAvailableContainer.setFont(new Font("montserrat", Font.BOLD, size));
		
		
		investorEquityLabel.setHorizontalAlignment(SwingConstants.CENTER);
		investorEquityContainer.setHorizontalAlignment(SwingConstants.CENTER);
		earningsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		earningsContainer.setHorizontalAlignment(SwingConstants.CENTER);
		fundsAvailableLabel.setHorizontalAlignment(SwingConstants.CENTER);
		fundsAvailableContainer.setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	private void layoutComponents(){
		setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.BOTH;
		
		c.weightx = 1;
		c.weighty = 1;
		c.gridy = 0;
		c.gridx = 0;
		c.anchor = GridBagConstraints.CENTER;
		add(investorEquityLabel, c);
		
		c.gridx++;
		add(investorEquityContainer, c);
		
		c.gridx = 0;
		c.gridy++;
		add(earningsLabel, c);
		
		c.gridx++;
		add(earningsContainer, c);
		
		c.gridx = 0;
		c.gridy++;
		add(fundsAvailableLabel, c);
		
		c.gridx++;
		add(fundsAvailableContainer, c);
		
	}
}
