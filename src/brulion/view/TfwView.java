package brulion.view;

import java.awt.event.ActionListener;

import javax.swing.*;

public class TfwView extends JPanel {
	private JTextField angleCurrency = new JTextField("180", 10);
	private JTextField additionConstant = new JTextField("0.00", 10);
	private JTextField multiplicationConstant = new JTextField("-1.00", 10);
	private JTextField pixel = new JTextField("0.10", 10);
	private JTextField matrixSizeX = new JTextField("11310", 10);
	private JTextField matrixSizeY = new JTextField("17310", 10);

	private JLabel angleCurrencyLabel = new JLabel("K¹t obrotu ");
	private JLabel additionConstantLabel = new JLabel("Sta³a dodawania ");
	private JLabel multiplicationConstantLabel = new JLabel("Sta³a mno¿enia ");
	private JLabel pixelLabel = new JLabel("Piksel [m] ");
	private JLabel matrixSizeXLabel = new JLabel("Wymiar matrycy X [px] ");
	private JLabel matrixSizeYLabel = new JLabel("Wymiar matrycy Y [px] ");

	private JButton appendButton = new JButton("Add file");

	public TfwView() {

		this.add(angleCurrencyLabel);
		this.add(angleCurrency);
		this.add(additionConstantLabel);
		this.add(additionConstant);
		this.add(multiplicationConstantLabel);
		this.add(multiplicationConstant);
		this.add(pixelLabel);
		this.add(pixel);
		this.add(matrixSizeXLabel);
		this.add(matrixSizeX);
		this.add(matrixSizeYLabel);
		this.add(matrixSizeY);
		this.add(appendButton);
		angleCurrency.requestFocus(true);

	}

	public double[] getValuesFromFields() {
		double[] values = new double[6];
		values[0] = Double.parseDouble(angleCurrency.getText());
		values[1] = Double.parseDouble(additionConstant.getText());
		values[2] = Double.parseDouble(multiplicationConstant.getText());
		values[3] = Double.parseDouble(pixel.getText());
		values[4] = Double.parseDouble(matrixSizeX.getText());
		values[5] = Double.parseDouble(matrixSizeY.getText());
		return values;
	}

	public double getAngleCurrency() {
		return Double.parseDouble(angleCurrency.getText());
	}

	public double getAdditionConstant() {
		return Double.parseDouble(additionConstant.getText());
	}

	public double getMultiplicationConstant() {
		return Double.parseDouble(multiplicationConstant.getText());
	}

	public double getPixel() {
		return Double.parseDouble(pixel.getText());
	}

	public double getMatrixSizeX() {
		return Double.parseDouble(matrixSizeX.getText());
	}

	public double getMatrixSizeY() {
		return Double.parseDouble(matrixSizeY.getText());
	}

	public void addAppendListener(ActionListener listenForAppendButton) {
		appendButton.addActionListener(listenForAppendButton);
	}

	public void displayErrorMessage(String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage);
	}

	public void displaySuccessMessage() {
		JOptionPane.showMessageDialog(this, "Tfw files have been created.");
	}
}
