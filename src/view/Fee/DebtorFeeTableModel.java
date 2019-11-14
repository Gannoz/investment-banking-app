package view.Fee;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Debtor;
import model.DebtorFee;

public class DebtorFeeTableModel extends AbstractTableModel {

	List<DebtorFee> db = new LinkedList<DebtorFee>();

	String[] colNames = { "Request ID", "Last Paid", "Fee %", "Amt Borrowed", "Fee Amount" };

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

		DebtorFee debtorFee = db.get(row);

		switch (col) {
		case 0:
			return debtorFee.getRequestId();
		case 1:
			return debtorFee.getLastPaid();
		case 2:
			return debtorFee.getFeePercent();
		case 3:
			return debtorFee.getAmountRequested();
		case 4:
			return debtorFee.getFeeAmount();
		}
		return null;
	}

}
