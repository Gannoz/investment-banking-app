package view.Manage;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class ManagePanel extends JPanel{
	
	private ManageHeader manageHeader;
	private ManageMain manageMain;
	
	public ManagePanel() {
		
		manageHeader = new ManageHeader();
		manageMain = new ManageMain();
		
		setLayout(new BorderLayout());
		add(manageHeader, BorderLayout.NORTH);
		add(manageMain, BorderLayout.CENTER);
	}
}
