package view.Investor;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Investor;

public class InvestorTableModel extends AbstractTableModel{
	
	private List<Investor> db = new LinkedList<Investor>();
	
	String[] columnNames = {"ID", "NIK", "Name", "Gender", "Address", "Religion", "Marriage Status", "Occupation",
	"Nationality", "Amount Invested"};
	
	
	public String getColumnName(int column) {
		return columnNames[column];
	}
	
	public void setData(List<Investor> db) {
		this.db = db;
	}
	
	public int getRowCount() {
		return db.size();
	}

	public int getColumnCount() {
		return 10;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Investor investor = db.get(rowIndex);
		
		switch (columnIndex) {
		case 0:
			return investor.getId();
		case 1:
			return investor.getNik();
		case 2:
			return investor.getName();
		case 3:
			return investor.getGender();
		case 4:
			return investor.getAddress();
		case 5:
			return investor.getReligion();
		case 6:
			return investor.getMarriageStatus();
		case 7:
			return investor.getOccupation();
		case 8:
			return investor.getNationality();
		case 9:
			return investor.getAmountInvested();
		}
		return null;
	}

}
