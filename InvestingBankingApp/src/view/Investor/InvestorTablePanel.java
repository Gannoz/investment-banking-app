package view.Investor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import model.Investor;

public class InvestorTablePanel extends JPanel {
	
	private JLabel listOfInvestorsLabel;
	private InvestorTableModel investorTableModel;
	private JTable table;
	private JScrollPane tableSP;
	
	private InvestorTableListener investorTableListener;

	public InvestorTablePanel() {
		listOfInvestorsLabel = new JLabel("List Of Investors");
		
		investorTableModel = new InvestorTableModel();
		table = new JTable(investorTableModel);
		
		table.setPreferredScrollableViewportSize(table.getPreferredScrollableViewportSize());
		table.setFillsViewportHeight(true);
		
		tableSP = new JScrollPane(table);
		tableSP.setPreferredSize(new Dimension(940,490));
		
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int row = table.rowAtPoint(e.getPoint());
				table.getSelectionModel().setSelectionInterval(row, row);
				
				investorTableListener.sendInvestorId(Integer.parseInt(table.getValueAt(row, 0).toString()));
			}
		});

		setDesign();
		layoutComponents();
		
		System.out.println(getPreferredSize());

	}
	
	public void setInvestorTableListener(InvestorTableListener listener) {
		investorTableListener = listener;
	}
	
	public void setData(List<Investor> data) {
		investorTableModel.setData(data);
	}
	
	public void refreshTable() {
		investorTableModel.fireTableDataChanged();
	}

	private void setDesign() {

		Color lightBlue = new Color(204, 247, 255);
		setBackground(lightBlue);
		
		int size = 25;
		
		listOfInvestorsLabel.setFont(new Font("sanserif", Font.PLAIN, size));
		listOfInvestorsLabel.setHorizontalAlignment(SwingConstants.CENTER);

	}

	private void layoutComponents() {
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
		// Label Column
		
		c.weightx = 1;
		c.weighty = 0.1;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.PAGE_START;
		
		c.gridx = 0;
		c.gridy = 0;
//		c.insets = new Insets(0, 235, 0, 235);
//		add(minimumSize,c);
		
//		c.gridy++;
		c.insets = new Insets(25,0,25,0);
		add(listOfInvestorsLabel, c);
		
		c.weightx = 1;
		c.weighty = 0.9;
		
		c.gridy++;
		c.insets = new Insets(0,25,25,25);
		add(tableSP, c);
		
	}
}
