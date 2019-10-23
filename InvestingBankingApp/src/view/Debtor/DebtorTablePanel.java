package view.Debtor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import model.Debtor;

public class DebtorTablePanel extends JPanel {
	
	private JLabel minimumSize;
	private JLabel listOfDebtorsLabel;
	private JTable table;
	private DebtorTableModel debtorTableModel;
	private JScrollPane tableSP;
	
	private DebtorTableListener debtorTableListener;

	public DebtorTablePanel() {

		minimumSize = new JLabel();
		listOfDebtorsLabel = new JLabel("List Of Debtors");
		debtorTableModel = new DebtorTableModel();
		table = new JTable(debtorTableModel);
		
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int row = table.rowAtPoint(e.getPoint());
				int id = Integer.parseInt(table.getModel().getValueAt(row, 0).toString());
				
				debtorTableListener.sendDebtorId(id);
			}
		});
		
		table.setPreferredScrollableViewportSize(table.getPreferredScrollableViewportSize());
		table.setFillsViewportHeight(true);
		
		tableSP = new JScrollPane(table);
		tableSP.setPreferredSize(new Dimension(940,490));
		
		

		setDesign();
		layoutComponents();
		
		System.out.println(getPreferredSize());

	}
	
	public void setDebtorTableListener(DebtorTableListener listener) {
		debtorTableListener = listener;
	}
	
	public void refreshTable() {
		debtorTableModel.fireTableDataChanged();
	}
	
	public void setData(List<Debtor> db) {
		debtorTableModel.setData(db);
	}

	private void setDesign() {

		Color lightBlue = new Color(204, 247, 255);
		setBackground(lightBlue);
		
		int size = 25;
		
		minimumSize.setHorizontalAlignment(SwingConstants.CENTER);
		
		listOfDebtorsLabel.setFont(new Font("sanserif", Font.PLAIN, size));
		listOfDebtorsLabel.setHorizontalAlignment(SwingConstants.CENTER);

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
		add(listOfDebtorsLabel, c);
		
		c.weightx = 1;
		c.weighty = 0.9;
		
		c.gridy++;
		c.insets = new Insets(0,25,25,25);
		add(tableSP, c);
		
	}
}
