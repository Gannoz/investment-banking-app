package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ScreenChanger extends JPanel implements ActionListener {

	private JButton dashboardBtn;
	private JButton manageBtn;
	private JButton investorBtn;
	private JButton debtorBtn;
	private JButton feeBtn;
	private Dimension buttonSize;

	private ScreenChangerListener screenChangerListener;

	public ScreenChanger() {

		dashboardBtn = new JButton("Dashboard");
		manageBtn = new JButton("Manage");
		investorBtn = new JButton("Investor");
		debtorBtn = new JButton("Debtor");
		feeBtn = new JButton("Fee");

		buttonSize = new Dimension(0, 30);

		dashboardBtn.setPreferredSize(buttonSize);
		manageBtn.setPreferredSize(buttonSize);
		investorBtn.setPreferredSize(buttonSize);
		debtorBtn.setPreferredSize(buttonSize);
		feeBtn.setPreferredSize(buttonSize);

		dashboardBtn.addActionListener(this);
		manageBtn.addActionListener(this);
		investorBtn.addActionListener(this);
		debtorBtn.addActionListener(this);
		feeBtn.addActionListener(this);

		setDesign();
		layoutComponents();
	}

	private void setDesign() {
		Color lightBlue = new Color(204, 247, 255);

		dashboardBtn.setBackground(lightBlue);
		manageBtn.setBackground(lightBlue);
		investorBtn.setBackground(lightBlue);
		debtorBtn.setBackground(lightBlue);
		feeBtn.setBackground(lightBlue);

//		dashboardBtn.setForeground(Color.white);
//		investorBtn.setForeground(Color.white);
//		debtorBtn.setForeground(Color.white);

	}

	private void layoutComponents() {
		setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();

		// First column
		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.LINE_START;

		gc.gridx = 0;
		add(dashboardBtn, gc);

		gc.gridx++;
		add(manageBtn, gc);

		gc.gridx++;
		add(investorBtn, gc);

		gc.gridx++;
		add(debtorBtn, gc);

		gc.gridx++;
		add(feeBtn, gc);

	}

	public void setScreenChangerListener(ScreenChangerListener listener) {
		this.screenChangerListener = listener;
	}

	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton) e.getSource();

		if (clicked == dashboardBtn) {
			screenChangerListener.overviewPressed();
		} else if (clicked == manageBtn) {
			screenChangerListener.managePressed();
		} else if (clicked == investorBtn) {
			screenChangerListener.investorPressed();
		} else if (clicked == debtorBtn) {
			screenChangerListener.debtorPressed();
		} else if (clicked == feeBtn) {
			screenChangerListener.feePressed();
		}
	}

}
