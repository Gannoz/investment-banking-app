package view.Investor;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JPanel;

import model.Investor;
import view.FormEvent;
import view.FormListener;
import view.InvestorTableListener;

public class InvestorMain extends JPanel {

	private InvestorForm investorForm;
	private InvestorTablePanel investorTablePanel;

	private int investorId;

	private InvestorListener investorListener;

	public InvestorMain() {
		investorForm = new InvestorForm();
		investorTablePanel = new InvestorTablePanel();

		investorTablePanel.setInvestorTableListener(new InvestorTableListener() {

			public void sendInvestorId(int id) {
				investorListener.getInvestorInfo(id);
				investorId = id;
			}
		});

		investorForm.setFormListener(new FormListener() {
			public void formAddRequest(FormEvent e) {
				investorListener.addInvestorInfo(e);
				investorTablePanel.refreshTable();
				investorForm.resetForm();
			}

			public void formEditRequest(FormEvent e) {
				try {
					investorListener.editInvestorInfo(investorId, e);
					investorTablePanel.refreshTable();
					investorForm.resetForm();
				} catch (NullPointerException error) {
					System.out.println(error);
				}
			}

			public void formRemoveRequest() {
				try {
					investorListener.removeInvestorInfo(investorId);
					investorTablePanel.refreshTable();
					investorForm.resetForm();
				} catch (NullPointerException error) {
					System.out.println(error);
				}
			}

			@Override
			public void formRequest(int amountRequested) {
				// TODO Auto-generated method stub
				
			}

		});

		setDesign();
		layoutComponents();
	}

	public void setFormData(FormEvent e) {
		investorForm.setFormData(e);
	}

	public void setInvestorListener(InvestorListener listener) {
		investorListener = listener;
	}

	public void setTableData(List<Investor> data) {
		investorTablePanel.setData(data);
	}

	private void setDesign() {
		setBackground(Color.white);
	}

	private void layoutComponents() {
		setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;

		c.weightx = 0.2;
		c.weighty = 1;

		// Investor Form

		c.gridy = 0;
		c.gridx = 0;

		c.insets = new Insets(25, 25, 25, 25);
		add(investorForm, c);

		// Investor Table List

		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.PAGE_START;

		c.weightx = 0.8;
		c.weighty = 1;

		c.gridy = 0;
		c.gridx++;

		c.insets = new Insets(25, 0, 25, 25);
		add(investorTablePanel, c);

	}
}
