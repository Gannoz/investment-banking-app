package view.Manage;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ManageControlPanel extends JPanel {

	private JButton manageButton;
	private JButton unmanageButton;
	private JButton fullfillButton;

	private ManageControlListener listener;

	public ManageControlPanel() {

		manageButton = new JButton("Manage");
		unmanageButton = new JButton("Unmanage");
		fullfillButton = new JButton("Fulfill");

		// Add functionality to buttons

		manageButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ev) {

				listener.managePerson();

			}
		});

		unmanageButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ev) {

				listener.unmanagePerson();
			}
		});

		fullfillButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				listener.fulfillPerson();
			}
		});

		setDesign();
		layoutComponents();
	}

	public void setManageControlListener(ManageControlListener listener) {
		this.listener = listener;
	}

	private void setDesign() {

		Color lightBlue = new Color(204, 247, 255);

		int size = 25;

		setBackground(lightBlue);

	}

	private void layoutComponents() {

		setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		c.gridy = 0;

		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.PAGE_START;

		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.anchor = GridBagConstraints.CENTER;

		c.gridy = 0;

		c.gridx = 0;
		c.weighty = 0.1;
		c.insets = new Insets(30, 15, 30, 15);
		c.anchor = GridBagConstraints.CENTER;

		add(manageButton, c);

		c.gridx++;
		add(unmanageButton, c);

		c.gridx++;
		add(fullfillButton, c);

	}
}
