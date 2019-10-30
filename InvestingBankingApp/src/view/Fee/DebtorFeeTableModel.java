package view.Fee;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Debtor;

public class DebtorFeeTableModel extends AbstractTableModel {

	List<Debtor> db = new LinkedList<Debtor>();

	String[] colNames = { "ID", "Name", "Fee %", "Fee Amount" };

	public String getColumnName(int column) {
		return colNames[column];
	}

	public void setData(List<Debtor> db) {

		this.db = db;
	}

	public List<Debtor> getData() {

		return db;
	}

	@Override
	public int getColumnCount() {

		return colNames.length;
	}

	@Override
	public int getRowCount() {

		return db.size();
	}

	@Override
	public Object getValueAt(int row, int col) {

		Debtor debtor = db.get(row);

		switch (col) {
		case 0:
			return debtor.getId();
		case 1:
			return debtor.getName();
		case 2:
			return debtor.getFeePercentage();
		case 3:
			return debtor.getFeeAmount();
		}
		return null;
	}

}
