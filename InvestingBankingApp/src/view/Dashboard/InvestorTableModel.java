package view.Dashboard;

import javax.swing.table.AbstractTableModel;

public class InvestorTableModel extends AbstractTableModel{
	
//	private List<Investor> db;
	private String[] columnNames = {"Name", "Amount Invested", "Return"};
	
	public String getColumnName(int column) {
		return columnNames[column];
	}
	
	public int getRowCount() {
		return 0;
	}

	public int getColumnCount() {
		return 3;
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		return null;
	}

}
