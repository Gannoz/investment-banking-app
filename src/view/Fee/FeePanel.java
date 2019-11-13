package view.Fee;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;

import model.DebtorFee;
import model.Investor;

public class FeePanel extends JPanel {

	private FeeHeader feeHeader;
	private FeeMain feeMain;

	private FeeListener listener;

	public FeePanel() {

		feeHeader = new FeeHeader();
		feeMain = new FeeMain();

		feeMain.setFeeListener(new FeeListener() {

			@Override
			public void sendInvestorId(int id) {

				listener.sendInvestorId(id);

			}

			@Override
			public void sendDebtorId(int id) {

				listener.sendDebtorId(id);
			}

		});

		layoutComponents();
	}

	public void setFeeLisener(FeeListener listener) {
		this.listener = listener;
	}

	public void setTableData(List<Investor> investors, List<DebtorFee> debtors) {
		feeMain.setTableData(investors, debtors);
	}

	public void refreshTables() {
		feeMain.refreshTables();
	}

	private void layoutComponents() {

		setLayout(new BorderLayout());

		add(feeHeader, BorderLayout.NORTH);
		add(feeMain, BorderLayout.CENTER);
	}
}
