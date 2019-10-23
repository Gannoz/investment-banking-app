package view.Debtor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import view.FormEvent;
import view.FormListener;

public class DebtorForm extends JPanel {
	
	private JLabel debtorInfoLabel;
	private JLabel NIKLabel;
	private JLabel nameLabel;
	private JLabel genderLabel;
	private JLabel addressLabel;
	private JLabel rtrwLabel;
	private JLabel villageLabel;
	private JLabel districtLabel;
	private JLabel religionLabel;
	private JLabel marriageStatusLabel;
	private JLabel occupationLabel;
	private JLabel nationalityLabel;
	private JLabel amountBorrowedLabel;
	
	private JRadioButton maleRadio;
	private JRadioButton femaleRadio;
	private ButtonGroup genderGroup;
	
	private JButton addBtn;
	private JButton editBtn;
	private JButton removeBtn;
	
	private JLabel colon1;
	private JLabel colon2;
	private JLabel colon3;
	private JLabel colon4;
	private JLabel colon5;
	private JLabel colon6;
	private JLabel colon7;
	private JLabel colon8;
	private JLabel colon9;
	private JLabel colon10;
	private JLabel colon11;
	private JLabel colon12;
	
	private JTextField NIKField;
	private JTextField nameField;
	private JTextField genderField;
	private JTextField addressField;
	private JTextField rtrwField;
	private JTextField villageField;
	private JTextField districtField;
	private JTextField religionField;
	private JTextField marriageStatusField;
	private JTextField occupationField;
	private JTextField nationalityField;
	private JComboBox amountBorrowedComboBox;
	
	private FormListener formListener;
	
	public DebtorForm() {
		
		debtorInfoLabel = new JLabel("Debtor Info");
		NIKLabel = new JLabel("NIK");
		nameLabel = new JLabel("Name");
		genderLabel = new JLabel("Gender");
		addressLabel = new JLabel("Address");
		rtrwLabel = new JLabel("RT/RW");
		villageLabel = new JLabel("Village");
		districtLabel = new JLabel("District");
		religionLabel = new JLabel("Religion");
		marriageStatusLabel = new JLabel("Marriage Status");
		occupationLabel = new JLabel("Occupation");
		nationalityLabel = new JLabel("Nationality");
		amountBorrowedLabel = new JLabel("Amount Borrowed");
		
		// Buttons
		addBtn = new JButton("Add");
		editBtn = new JButton("Edit");
		removeBtn = new JButton("Remove");
		
		colon1 = new JLabel(":");
		colon2 = new JLabel(":");
		colon3 = new JLabel(":");
		colon4 = new JLabel(":");
		colon5 = new JLabel(":");
		colon6 = new JLabel(":");
		colon7 = new JLabel(":");
		colon8 = new JLabel(":");
		colon9 = new JLabel(":");
		colon10 = new JLabel(":");
		colon11 = new JLabel(":");
		colon12 = new JLabel(":");
		
		int width = 17;
		
		NIKField = new JTextField(width);
		nameField = new JTextField(width);
//		genderField = new JTextField(width);
		addressField = new JTextField(width);
		rtrwField = new JTextField(width);
		villageField = new JTextField(width);
		districtField = new JTextField(width);
		religionField = new JTextField(width);
		marriageStatusField = new JTextField(width);
		occupationField = new JTextField(width);
		nationalityField = new JTextField(width);
		amountBorrowedComboBox = new JComboBox();
		
		// Gender Radio Button
		maleRadio = new JRadioButton("Male");
		femaleRadio = new JRadioButton("Female");
		
		maleRadio.setActionCommand("Male");
		femaleRadio.setActionCommand("Female");
		
		genderGroup = new ButtonGroup();	
		maleRadio.setSelected(true);
		genderGroup.add(maleRadio);
		genderGroup.add(femaleRadio);
		
		DefaultComboBoxModel amtBorrowedModel = new DefaultComboBoxModel();
		amtBorrowedModel.addElement("Rp.500,000");
		amtBorrowedModel.addElement("Rp.1,000,000");
		amtBorrowedModel.addElement("Rp.2,500,000");
		amtBorrowedModel.addElement("Rp.5,000,000");
		amtBorrowedModel.addElement("Rp.10,000,000");
		
		amountBorrowedComboBox.setModel(amtBorrowedModel);
		amountBorrowedComboBox.setSelectedIndex(0);
		amountBorrowedComboBox.setEditable(false);
		
		addBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				String nik = NIKField.getText();
				String name = nameField.getText();
				String gender = genderGroup.getSelection().getActionCommand();
				String address = addressField.getText();
				String rtrw = rtrwField.getText();
				String village = villageField.getText();
				String district = districtField.getText();
				String religion = religionField.getText();
				String marriageStatus = marriageStatusField.getText();
				String occupation = occupationField.getText();
				String nationality = nationalityField.getText();
				int amountBorrowed = amountBorrowedComboBox.getSelectedIndex();
				
				switch(amountBorrowed) {
				case 0:
					amountBorrowed = 500000;
					break;
				case 1:
					amountBorrowed = 1000000;
					break;
				case 2:
					amountBorrowed = 2500000;
					break;
				case 3:
					amountBorrowed = 5000000;
					break;
				case 4:
					amountBorrowed = 10000000;
					break;
				}
				
				FormEvent ev = new FormEvent(this, nik, name, gender, address, rtrw, village, district, religion,
						marriageStatus, occupation, nationality, amountBorrowed);
				
				formListener.formAddRequest(ev);
				
			}
		});
		
		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nik = NIKField.getText();
				String name = nameField.getText();
				String gender = genderGroup.getSelection().getActionCommand();
				String address = addressField.getText();
				String rtrw = rtrwField.getText();
				String village = villageField.getText();
				String district = districtField.getText();
				String religion = religionField.getText();
				String marriageStatus = marriageStatusField.getText();
				String occupation = occupationField.getText();
				String nationality = nationalityField.getText();
				int amountBorrowed = amountBorrowedComboBox.getSelectedIndex();
				
				switch(amountBorrowed) {
				case 0:
					amountBorrowed = 500000;
					break;
				case 1:
					amountBorrowed = 1000000;
					break;
				case 2:
					amountBorrowed = 2500000;
					break;
				case 3:
					amountBorrowed = 5000000;
					break;
				case 4:
					amountBorrowed = 10000000;
					break;
				}

				FormEvent ev = new FormEvent(this, nik, name, gender, address, rtrw, village, district, religion,
						marriageStatus, occupation, nationality, amountBorrowed);
				
				formListener.formEditRequest(ev);
			}
		});
		
		removeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formListener.formRemoveRequest();
			}
		});
		
		setDesign();
		layoutComponents();
		
	}
	
	public void resetForm() {
		NIKField.setText("");
		nameField.setText("");
		
		maleRadio.setSelected(true);
		femaleRadio.setSelected(false);
		
		addressField.setText("");
		rtrwField.setText("");
		villageField.setText("");
		districtField.setText("");
		religionField.setText("");
		marriageStatusField.setText("");
		occupationField.setText("");
		nationalityField.setText("");
		amountBorrowedComboBox.setSelectedIndex(0);;
	}
	
	public void setFormData(FormEvent e) {
		System.out.println(e.getAmount());
		NIKField.setText(e.getNik());
		nameField.setText(e.getName());
		
		String gender = e.getGender();
		if (gender.equalsIgnoreCase("male")) {
			maleRadio.setSelected(true);
			femaleRadio.setSelected(false);
		}else if (gender.equalsIgnoreCase("female")) {
			maleRadio.setSelected(false);
			femaleRadio.setSelected(true);
		}
		
		addressField.setText(e.getAddress());
		rtrwField.setText(e.getRtrw());
		villageField.setText(e.getVillage());
		districtField.setText(e.getDistrict());
		religionField.setText(e.getReligion());
		marriageStatusField.setText(e.getMarriageStatus());
		occupationField.setText(e.getOccupation());
		nationalityField.setText(e.getNationality());
		
		switch(e.getAmount()) {
		case 500000:
			e.setAmount(0);
			break;
		case 1000000:
			e.setAmount(1);
			break;
		case 2500000:
			e.setAmount(2);
			break;
		case 5000000:
			e.setAmount(3);
			break;
		case 10000000:
			e.setAmount(4);
			break;
		}
		
		amountBorrowedComboBox.setSelectedIndex(e.getAmount());
	}
	
	public void setFormListener(FormListener listener) {
		formListener = listener;
	}
	
	private void setDesign() {
		Color lightBlue = new Color(204,247,255);
		
		setBackground(lightBlue);
		maleRadio.setBackground(lightBlue);
		femaleRadio.setBackground(lightBlue);
		
		int size = 25;
		
		debtorInfoLabel.setFont(new Font("sanserif", Font.PLAIN, size));
		debtorInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		
	}
	
	private void layoutComponents() {
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
		
		c.weightx = 1;
		c.weighty = 0.1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.PAGE_START;
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		c.insets = new Insets(25,0,25,0);
		add(debtorInfoLabel, c);
		
		c.weighty = 0.9;
		
		double labelWeightX = 0;
		double colonWeightX = 0;
		double fieldWeightX = 0;
		
		// Label Column
		
		c.weightx = labelWeightX;
		c.anchor = GridBagConstraints.LINE_START;
		c.gridwidth = 1;
		c.insets = new Insets(0,35,10,0);
		
		c.gridy++;
		add(NIKLabel, c);
		
		c.gridy++;
		add(nameLabel, c);
		
		c.gridy++;
		add(genderLabel, c);
		
		c.gridy++;
		c.gridy++;
		add(addressLabel, c);
		
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.LINE_END;
		c.gridy++;
		add(rtrwLabel, c);
		
		c.gridy++;
		add(villageLabel, c);
		
		c.gridy++;
		add(districtLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		
		c.anchor = GridBagConstraints.LINE_START;
		c.gridy++;
		add(religionLabel, c);
		
		c.gridy++;
		add(marriageStatusLabel, c);
		
		c.gridy++;
		add(occupationLabel, c);
		
//		c.insets = new Insets(0,0,35,0);
		c.gridy++;
		add(nationalityLabel, c);
		
		c.gridy++;
		add(amountBorrowedLabel, c);
		
		// Colon Column
		
		c.weightx = colonWeightX;
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(0,10,10,10);
		add(colon1, c);
		
		c.gridy++;
		add(colon2, c);
		
		c.gridy++;
		add(colon3, c);
		
		c.gridy++;
		c.gridy++;
		add(colon4, c);
		
		c.gridy++;
		add(colon5, c);
		
		c.gridy++;
		add(colon6, c);
		
		c.gridy++;
		add(colon7, c);
		
		c.gridy++;
		add(colon8, c);
		
		c.gridy++;
		add(colon9, c);
		
		c.gridy++;
		add(colon10, c);
		
		c.gridy++;
		add(colon11, c);
		
//		c.insets = new Insets(0,0,35,0);
		c.gridy++;
		add(colon12, c);
		
		// Field Column
		
		c.weightx = fieldWeightX;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(0,0,10,35);
		
		c.gridx = 2;
		c.gridy = 1;
		add(NIKField, c);
		
		c.gridy++;
		add(nameField, c);
		
		c.gridy++;
//		add(genderField, c);
		add(maleRadio, c);
		
		c.gridy++;
//		add(genderField, c);
		add(femaleRadio, c);
		
		c.gridy++;
		add(addressField, c);
		
		c.gridy++;
		add(rtrwField, c);
		
		c.gridy++;
		add(villageField, c);
		
		c.gridy++;
		add(districtField, c);
		
		c.gridy++;
		add(religionField, c);
		
		c.gridy++;
		add(marriageStatusField, c);
		
		c.gridy++;
		add(occupationField, c);
		
//		c.insets = new Insets(0,0,35,0);
		c.gridy++;
		add(nationalityField, c);
		
		c.gridy++;
		add(amountBorrowedComboBox, c);
		
		// Button
		
		c.weightx = 1;

		c.gridx = 0;
		c.gridy++;

		c.gridwidth = 3;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,35,10,35);
		
		ButtonPanel bp = new ButtonPanel();
		add(bp, c);
		
	}
	
	private class ButtonPanel extends JPanel{
		public ButtonPanel() {
			setLayout(new GridBagLayout());
			Color lightBlue = new Color(204,247,255);
			setBackground(lightBlue);
			
			Dimension size = new Dimension(80,28);
			
			addBtn.setPreferredSize(size);
			editBtn.setPreferredSize(size);
			removeBtn.setPreferredSize(size);
			
			GridBagConstraints c = new GridBagConstraints();
			
			c.weightx = 1;
			c.weighty = 1;
			c.anchor = GridBagConstraints.CENTER;
			c.fill = GridBagConstraints.NONE;
			
			c.gridx = 0;
			c.gridy = 0;
			add(addBtn, c);
			
			c.gridx++;
			add(editBtn, c);
			
			c.gridx++;
			add(removeBtn, c);
		}
		
	}
	
}
