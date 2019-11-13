package view.Manage;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Debtor;
import model.DebtorRequest;

public class ManagePaymentsReceivableTableModel extends AbstractTableModel {

	List<DebtorRequest> db = new LinkedList<DebtorRequest>();

	String[] colNames = { "Id", "Debtor ID", "Amount Lent", "Time Managed" };

	public String getColumnName(int column) {

		return colNames[column];
	}

	public void setData(List<DebtorRequest> db) {

		this.db = db;
	}

	public List<DebtorRequest> getData() {
		return db;
	}

	public int getRowCount() {

		return db.size();
	}

	public int getColumnCount() {

		return colNames.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {

		DebtorRequest debtor = db.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return debtor.getId();
		case 1:
			return debtor.getDebtorId();
		case 2:
			return debtor.getAmtRequested();
		case 3:
			return debtor.getTimeManaged();
		}
		return null;
	}

}
