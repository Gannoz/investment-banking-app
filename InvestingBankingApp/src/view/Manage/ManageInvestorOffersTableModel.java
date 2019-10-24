package view.Manage;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Debtor;
import model.Investor;

public class ManageInvestorOffersTableModel extends AbstractTableModel{
	
	List<Investor> db = new LinkedList<Investor>();
	
	String [] colNames = {"Name", "Amount Offered", "Offer Date"};
	
	public String getColumnName(int column) {
		
		return colNames[column];
	}
	
	public void setData(List<Investor> db) {
		
		this.db = db;
	}
	
	public int getRowCount() {
		
		return db.size();
	}

	public int getColumnCount() {
		
		return 3;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Investor investor = db.get(rowIndex);
		
		switch(columnIndex) {
		case 0:
			return investor.getName();
		case 1:
			return investor.getAmountInvested();
		case 2:
			return investor.getNik();
		}
		return null;
	}
	
	
}
