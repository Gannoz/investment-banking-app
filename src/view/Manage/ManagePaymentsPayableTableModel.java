package view.Manage;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Investor;

public class ManagePaymentsPayableTableModel extends AbstractTableModel {

	List<Investor> db = new LinkedList<Investor>();

	String[] colNames = { "Id", "Amount Borrowed", "Time Managed" };

	public String getColumnName(int column) {

		return colNames[column];
	}

	public void setData(List<Investor> db) {

		this.db = db;
	}

	public List<Investor> getData() {
		return db;
	}

	public int getRowCount() {

		return db.size();
	}

	public int getColumnCount() {

		return 3;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {

		Investor investor = db.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return investor.getId();
		case 1:
			return investor.getAmountInvested();
		case 2:
			return investor.getTimeManaged();
		}
		return null;
	}

}