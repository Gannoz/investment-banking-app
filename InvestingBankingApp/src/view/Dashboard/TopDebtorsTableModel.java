package view.Dashboard;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Debtor;
import model.Debtor;

public class TopDebtorsTableModel extends AbstractTableModel{
	
	private List<Debtor> db = new LinkedList<Debtor>();
	
	String[] colNames = {"Name", "Amount Borrowed", "Monthly Expense"};
	
	public String getColumnName(int column) {
		
		return colNames[column];
	}
	
	public void setData(List<Debtor> db) {
		
		this.db = db;
	}
	
	public int getRowCount() {
		
		return db.size();
	}
	
	public int getColumnCount() {
		
		return 3;
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Debtor debtor = db.get(rowIndex);
		
		switch(columnIndex) {
		case 0:
			return debtor.getName();
		case 1:
			return debtor.getAmountBorrowed();
		case 2:
			return debtor.getNik();
		}
		return null;
	}
	
	private List<Debtor> sortData(List<Debtor> list){
		
		int max;
		
		List<Debtor> topDebtorsList = new LinkedList<Debtor>();
		
		for(int i = 0; i < list.size(); i++) {
			Debtor topDebtor = null;
			max = 0;
			for(Debtor debtor : list) {
				if (debtor.getAmountBorrowed() > max) {
					max = debtor.getAmountBorrowed();
					topDebtor = debtor;
				}
			}
			topDebtorsList.add(topDebtor);
		}
		
		return topDebtorsList;
		
	}

}
