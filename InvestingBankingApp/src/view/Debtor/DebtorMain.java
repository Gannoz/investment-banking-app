package view.Debtor;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JPanel;

import model.Debtor;
import view.DebtorTableListener;
import view.FormEvent;
import view.FormListener;

public class DebtorMain extends JPanel {
	
	private DebtorForm debtorForm;
	private DebtorTablePanel debtorTablePanel;
	
	private DebtorListener debtorListener;
	
	private int debtorId;
	
	public DebtorMain() {
		debtorForm = new DebtorForm();
		debtorTablePanel = new DebtorTablePanel();
		
		debtorTablePanel.setDebtorTableListener(new DebtorTableListener() {
			public void sendDebtorId(int id) {
				debtorListener.getDebtorInfo(id);
				debtorId = id;
			}
		});
		
		debtorForm.setFormListener(new FormListener() {
			public void formAddRequest(FormEvent e) {
				debtorListener.addDebtorInfo(e);
				debtorTablePanel.refreshTable();
				debtorForm.resetForm();
			}
			
			public void formEditRequest(FormEvent e) {
				try {
					debtorListener.editDebtorInfo(debtorId, e);
					debtorTablePanel.refreshTable();
					debtorForm.resetForm();
				}catch (NullPointerException error) {
					System.out.println(error);
				}
			}
			
			public void formRemoveRequest() {
				try {
					debtorListener.removeDebtorInfo(debtorId);
					debtorTablePanel.refreshTable();
					debtorForm.resetForm();
				}catch (NullPointerException e) {
					System.out.println(e);
				}
			}
			
			
		});
		
		setDesign();
		layoutComponents();
	}
	
	public void setFormData(FormEvent e) {
		debtorForm.setFormData(e);
	}
	
	public void setTableData(List<Debtor> db) {
		debtorTablePanel.setData(db);
	}
	
	public void setDebtorListener(DebtorListener listener) {
		debtorListener = listener;
	}
	
	private void setDesign() {
		setBackground(Color.white);
	}
	
	private void layoutComponents() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		
		c.weightx = 0.1;
		c.weighty = 0.3;
		
		
		// Investor Form
		
		c.gridy = 0;
		c.gridx = 0;
		
		c.insets = new Insets(25,25,25,25);
		add(debtorForm, c);
		
		// Investor Table List
		
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.PAGE_START;
		
		c.weightx = 0.9;
		c.weighty = 0.7;
		
		c.gridy = 0;
		c.gridx++;
		
		c.insets = new Insets(25,0,25,25);
		add(debtorTablePanel, c);
		
		
	}
}
