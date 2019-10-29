package view.Fee;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class FeePanel extends JPanel{

	private FeeHeader feeHeader;
	private FeeMain feeMain;
	
	public FeePanel() {
		
		feeHeader = new FeeHeader();
		feeMain = new FeeMain();
		
		setLayout(new BorderLayout());
		
		add(feeHeader, BorderLayout.NORTH);
		add(feeMain, BorderLayout.CENTER);
	}
}
