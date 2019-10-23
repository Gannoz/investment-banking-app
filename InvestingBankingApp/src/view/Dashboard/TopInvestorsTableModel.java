package view.Dashboard;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Investor;

public class TopInvestorsTableModel extends AbstractTableModel{

	private List<Investor> db = new LinkedList<Investor>();
	
	String [] colNames = {"Name", "Amount Invested", "Share %"};
	
	public String getColumnName(int column) {
		return colNames[column];
	}
	
	public void setData(List<Investor> db) {
		this.db = db;
	}
	
	public int getRowCount() {
		return db.size();
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Investor investor = db.get(rowIndex);
		
		switch (columnIndex) {
			case 0:
				return investor.getName();
			case 1:
				return investor.getAmountInvested();
			case 2:
				return investor.getNik();
		}
		return null;
		
	}
	
	private List<Investor> sortData(List<Investor> list){
		int max;
		
		List<Investor> topInvestorsList = new LinkedList<Investor>();
		
		for(int i = 0; i < list.size(); i++) {
			Investor topInvestor = null;
			max = 0;
			for(Investor investor : list) {
				if (investor.getAmountInvested() > max) {
					max = investor.getAmountInvested();
					topInvestor = investor;
				}
			}
			topInvestorsList.add(topInvestor);
		}
		
		return topInvestorsList;
		
	}

}
