package view.Manage;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.InvestorRequest;

public class ManageInvestorOffersTableModel extends AbstractTableModel {

	List<InvestorRequest> db = new LinkedList<InvestorRequest>();

	String[] colNames = { "Id", "Amount Offered", "Offer Date" };

	public String getColumnName(int column) {

		return colNames[column];
	}

	public void setData(List<InvestorRequest> db) {

		this.db = db;
	}

	public List<InvestorRequest> getData() {
		return db;
	}

	public int getRowCount() {

		return db.size();
	}

	public int getColumnCount() {

		return 3;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {

		InvestorRequest investor = db.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return investor.getId();
		case 1:
			return investor.getAmtRequested();
		case 2:
			return investor.getTimeCreated();
		}
		return null;
	}

}
