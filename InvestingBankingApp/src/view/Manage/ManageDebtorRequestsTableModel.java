package view.Manage;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Debtor;

public class ManageDebtorRequestsTableModel extends AbstractTableModel{
	
	List<Debtor> db = new LinkedList<Debtor>();
	
	String [] colNames = {"Name", "Amount Requested", "Request Date"};
	
	public String getColumnName(int column) {
		return colNames[column];
	}
	
	public int getRowCount() {
		return db.size();
	}

	public int getColumnCount() {
		return 3;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return null;
	}
	
	public void setData(List<Debtor> db) {
		
	}
}
