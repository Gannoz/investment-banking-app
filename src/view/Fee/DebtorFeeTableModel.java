package view.Fee;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Debtor;
import model.DebtorFee;

public class DebtorFeeTableModel extends AbstractTableModel {

	List<DebtorFee> db = new LinkedList<DebtorFee>();

	String[] colNames = { "Debtor ID", "Last Paid", "Fee %", "Amt Borrowed", "Fee Amount" };

	public String getColumnName(int column) {
		return colNames[column];
	}

	public void setData(List<DebtorFee> db) {

		this.db = db;
	}

	public List<DebtorFee> getData() {

		return db;
	}

	public int getColumnCount() {

		return colNames.length;
	}

	public int getRowCount() {

		return db.size();
	}

	public Object getValueAt(int row, int col) {

		DebtorFee debtor = db.get(row);

		switch (col) {
		case 0:
			return debtor.getDebtorId();
		case 1:
			return debtor.getLastPaid();
		case 2:
			return debtor.getFeePercent();
		case 3:
			return debtor.getAmountRequested();
		case 4:
			return debtor.getFeeAmount();
		}
		return null;
	}

}
