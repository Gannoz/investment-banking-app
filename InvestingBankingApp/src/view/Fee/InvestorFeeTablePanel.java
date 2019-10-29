package view.Fee;

import java.awt.Color;
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
import view.InvestorTableListener;

public class InvestorFeeTablePanel extends JPanel{

	private JLabel titleLabel;
	private InvestorFeeTableModel tableModel;
	private JTable table;
	private JScrollPane tableSP;
	
	private InvestorTableListener listener;
	
	public InvestorFeeTablePanel() {
		
		titleLabel = new JLabel("Investor Fees");
		tableModel = new InvestorFeeTableModel();
		table = new JTable(tableModel);
		
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				try {
					JTable clickedTable = (JTable) e.getSource();
					int row = table.rowAtPoint(e.getPoint());
					
					if(e.getClickCount() == 2 && clickedTable.getSelectedRow() != -1) {
						listener.sendInvestorId(tableModel.getData().get(row).getId());
					}
				}catch(IndexOutOfBoundsException ex) {
					ex.printStackTrace();
				}
				
			}
		});
		
		table.setPreferredScrollableViewportSize(table.getPreferredScrollableViewportSize());
		table.setFillsViewportHeight(true);
		
		tableSP = new JScrollPane(table);
		
		setDesign();
		layoutComponents();
	}
	
	public void setTableListener(InvestorTableListener listener) {
		this.listener = listener;
	}
	
	public void setData(List<Investor> investors) {
		tableModel.setData(investors);
	}
	
	public void refreshTable() {
		tableModel.fireTableDataChanged();
	}
	
	private void setDesign() {
		

		Color lightBlue = new Color(204, 247, 255);
		setBackground(lightBlue);
		
		int size = 25;
		
		titleLabel.setFont(new Font("sanserif", Font.PLAIN, size));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

	}
	
	private void layoutComponents() {
		
//		setLayout(new BorderLayout());
//		
//		add(titleLabel, BorderLayout.NORTH);
//		add(tableSP, BorderLayout.CENTER);
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx =1;
		c.weighty = 0.3;
		c.gridx = 0;
		c.gridy = 0;
		add(titleLabel, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 0.7;
		c.gridy++;
		c.insets = new Insets(0,25,25,25);
		add(tableSP, c);

	}
}
