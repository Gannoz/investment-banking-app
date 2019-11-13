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

import model.DebtorFee;
import view.DebtorTableListener;

public class DebtorFeeTablePanel extends JPanel {

	private JLabel titleLabel;
	private DebtorFeeTableModel tableModel;
	private JTable table;
	private JScrollPane tableSP;

	private DebtorTableListener listener;

	public DebtorFeeTablePanel() {

		titleLabel = new JLabel("Debtor Fees");
		tableModel = new DebtorFeeTableModel();
		table = new JTable(tableModel);

		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				try {
					JTable clickedTable = (JTable) e.getSource();
					int row = table.rowAtPoint(e.getPoint());

					if (e.getClickCount() == 2 && clickedTable.getSelectedRow() != -1) {
						listener.sendDebtorId(tableModel.getData().get(row).getDebtorId());
					}
				} catch (IndexOutOfBoundsException ex) {
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

	public void setTableListener(DebtorTableListener listener) {
		this.listener = listener;
	}

	public void setData(List<DebtorFee> debtors) {
		tableModel.setData(debtors);
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

		setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		c.weightx = 0.3;
		c.weighty = 0.3;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.NONE;
		c.gridx = 0;
		c.gridy = 0;
		add(titleLabel, c);

		c.weightx = 0.7;
		c.weighty = 0.7;
		c.fill = GridBagConstraints.BOTH;
		c.gridy++;
		c.insets = new Insets(0, 25, 25, 25);
		add(tableSP, c);

	}
}
