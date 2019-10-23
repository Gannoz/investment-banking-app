package view.Debtor;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;

import controller.Controller;
import model.Debtor;
import view.FormEvent;

public class DebtorPanel extends JPanel{
	
	private DebtorHeader debtorHeader;
	private DebtorMain debtorMain;
	
	private DebtorListener debtorListener;
	
	public DebtorPanel() {
		debtorHeader = new DebtorHeader();
		debtorMain = new DebtorMain();
		
		debtorMain.setDebtorListener(new DebtorListener() {

			public void addDebtorInfo(FormEvent e) {
				debtorListener.addDebtorInfo(e);
			}
			
			public void editDebtorInfo(int id, FormEvent e) {
				debtorListener.editDebtorInfo(id, e);
			}
			
			public void removeDebtorInfo(int id) {
				debtorListener.removeDebtorInfo(id);
			}
			
			public void getDebtorInfo(int id) {
				debtorListener.getDebtorInfo(id);
			}
			
		});
		
		setLayout(new BorderLayout());
		
		add(debtorHeader, BorderLayout.NORTH);
		add(debtorMain, BorderLayout.CENTER);
		
	}
	
	public void setFormData(FormEvent e) {
		debtorMain.setFormData(e);
	}
	
	public void setDebtorListener(DebtorListener listener) {
		debtorListener = listener;
	}
	
	public void setTableData(List<Debtor> db) {
		debtorMain.setTableData(db);
	}

}
