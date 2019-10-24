package view.Debtor;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Debtor;

public class DebtorTableModel extends AbstractTableModel{

	private List<Debtor> db = new LinkedList<Debtor>();
	
	String[] columnNames = {"ID", "NIK", "Name", "Gender", "Address", "Religion", "Marriage Status", "Occupation",
	"Nationality", "Amount Borrowed"};
	
	public String getColumnName(int column) {
		return columnNames[column];
	}
	
	public void setData(List<Debtor> db) {
		this.db = db;
	}
	
	public int getRowCount() {
		return db.size();
	}

	public int getColumnCount() {
		return 10;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Debtor debtor = db.get(rowIndex);
		
		switch (columnIndex) {
			case 0:
				return debtor.getId();
			case 1:
				return debtor.getNik();
			case 2:
				return debtor.getName();
			case 3:
				return debtor.getGender();
			case 4:
				return debtor.getAddress();
			case 5:
				return debtor.getReligion();
			case 6:
				return debtor.getMarriageStatus();
			case 7:
				return debtor.getOccupation();
			case 8:
				return debtor.getNationality();
			case 9:
				return debtor.getAmountBorrowed();
		}
		return null;
	}

}
