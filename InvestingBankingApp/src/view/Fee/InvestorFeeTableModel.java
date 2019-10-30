package view.Fee;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Investor;

public class InvestorFeeTableModel extends AbstractTableModel {

	List<Investor> db = new LinkedList<Investor>();

	String[] colNames = { "ID", "Name", "Fee %", "Fee Amount" };

	public String getColumnName(int column) {
		return colNames[column];
	}

	public void setData(List<Investor> db) {

		this.db = db;
	}

	public List<Investor> getData() {

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

		Investor investor = db.get(row);

		switch (col) {
		case 0:
			return investor.getId();
		case 1:
			return investor.getName();
		case 2:
			return investor.getAmountInvested();
		case 3:
			return investor.getAmountInvested();
		}
		return null;
	}
}
