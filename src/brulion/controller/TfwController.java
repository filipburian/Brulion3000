package brulion.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import brulion.model.TfwModel;
import brulion.view.TfwView;

public class TfwController {
	private TfwView theView;
	private TfwModel theModel;

	public TfwController(TfwView theView, TfwModel theModel) {
		this.theView = theView;
		this.theModel = theModel;
		this.theView.addAppendListener(new AppendListener());
	}

	class AppendListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			double[] valuesFromFields;

			try {
				valuesFromFields = theView.getValuesFromFields();

				theModel.calculateRasterHalfSize(valuesFromFields);

				theModel.createTfwFiles(valuesFromFields);

			} catch (NumberFormatException ex) {
				theView.displayErrorMessage("Enter valid parameters or check the .txt file.");
			} catch (IOException ex) {
				theView.displayErrorMessage("Invalid file format, add .txt file please.");
			}
		}
	}
}
