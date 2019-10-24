package view.Manage;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Debtor;

public class ManagePendingPaymentsTableModel extends AbstractTableModel{
	
	List<Debtor> db = new LinkedList<Debtor>();
	
	String [] colNames = {"Name", "Amount Lent", "Payment Receivable"};

	public String getColumnName(int column) {
		
		return colNames[column];
	}
	
	public void setData(List<Debtor> db) {
		
		this.db = db;
	}
	
	public int getRowCount() {
		
		return db.size();
	}

	public int getColumnCount() {
		
		return 3;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Debtor debtor = db.get(rowIndex);
		
		switch(columnIndex) {
		case 0:
			return debtor.getName();
		case 1:
			return debtor.getAmountBorrowed();
		case 2:
			return debtor.getNik();
		}
		return null;
	}

}
