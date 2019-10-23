package view.Dashboard;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class EarningsTrend extends JPanel {
	
	private JLabel minimumSize;
	private JLabel earningsTrendLabel;
	
	public EarningsTrend() {
		minimumSize = new JLabel();
		
		earningsTrendLabel = new JLabel("Earnings Trend");
		
		setDesign();
		layoutComponents();
		
	}
	
	private void setDesign() {
		Color lightBlue = new Color(204,247,255);
		
		int size = 25;
		
		setBackground(lightBlue);
		
		minimumSize.setHorizontalAlignment(SwingConstants.CENTER);
		
		earningsTrendLabel.setFont(new Font("montserrat", Font.PLAIN, size));
		earningsTrendLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
	}
	
	private void layoutComponents() {
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.gridy = 0;
		
		gc.fill = GridBagConstraints.BOTH;
		
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx = 0;
		gc.insets = new Insets(0,350,0,350);
		gc.anchor = GridBagConstraints.CENTER;
		
		add(minimumSize, gc);
		
		// First Row
		gc.gridy = 0;
		
		gc.fill = GridBagConstraints.BOTH;
		
		gc.gridx = 0;
		gc.insets = new Insets(40,15,220,15);
		gc.anchor = GridBagConstraints.CENTER;
		
		add(earningsTrendLabel, gc);
		
	}
	
}
