package brulion.view;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ExcelView extends JPanel {

	private JTextField meanGroundLevel = new JTextField("0", 15);
	private JTextField data = new JTextField("2017-05-01", 15);
	private JTextField worksheet = new JTextField("17_06_01_1_001_2017", 15);
	private JTextField GSD = new JTextField("25 cm", 15);
	private JTextField doer = new JTextField("Gispro", 15);
	private JTextField dftNumber = new JTextField("DFT.7201.001.2017", 15);
	private JTextField project = new JTextField("LPIS", 15);
	private JTextField object = new JTextField("A", 15);

	private JLabel meanGroundLevelLabel = new JLabel("H_œr_terenu ");
	private JLabel dataLabel = new JLabel("Data ");
	private JLabel worksheetLabel = new JLabel("Karta pracy ");
	private JLabel GSDLabel = new JLabel("GSD ");
	private JLabel doerLabel = new JLabel("Wykonawca ");
	private JLabel dftNumberLabel = new JLabel("DFT ");
	private JLabel projectLabel = new JLabel("Projekt ");
	private JLabel objectLabel = new JLabel("Obiekt ");

	private JButton appendButton = new JButton("Add txt file");
	private JButton saveButton = new JButton("Save .xls file");

	public ExcelView() {
		this.setLayout(new GridLayout(5, 4));
		this.add(meanGroundLevelLabel);
		this.add(meanGroundLevel);
		this.add(dataLabel);
		this.add(data);
		this.add(worksheetLabel);
		this.add(worksheet);
		this.add(GSDLabel);
		this.add(GSD);
		this.add(doerLabel);
		this.add(doer);
		this.add(dftNumberLabel);
		this.add(dftNumber);
		this.add(projectLabel);
		this.add(project);
		this.add(objectLabel);
		this.add(object);

		this.add(appendButton);
		this.add(saveButton);
		meanGroundLevel.requestFocus(true);

	}

	public String[] getValuesFromFields() {
		String[] values = new String[8];
		values[0] = meanGroundLevel.getText().trim();
		values[1] = data.getText().trim();
		values[2] = worksheet.getText().trim();
		values[3] = GSD.getText().trim();
		values[4] = doer.getText().trim();
		values[5] = dftNumber.getText().trim();
		values[6] = project.getText().trim();
		values[7] = object.getText().trim();
		return values;
	}

	public void addAppendListener(ActionListener listenForAppendButton) {
		appendButton.addActionListener(listenForAppendButton);
	}

	public void addSaveListener(ActionListener listenForSaveButton) {
		saveButton.addActionListener(listenForSaveButton);
	}

	public void displayErrorMessage(String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage);
	}

}
