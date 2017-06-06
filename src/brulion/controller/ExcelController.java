package brulion.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import brulion.model.ExcelModel;
import brulion.view.ExcelView;

public class ExcelController {

	private ExcelView excelView;
	private ExcelModel excelModel;

	public ExcelController(ExcelView excelView, ExcelModel excelModel) {
		this.excelView = excelView;
		this.excelModel = excelModel;
		this.excelView.addAppendListener(new AppendListener());
		this.excelView.addSaveListener(new SaveListener());
	}

	class AppendListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String[] valuesFromFields;

			try {
				valuesFromFields = excelView.getValuesFromFields();

				excelModel.fillXlsWithValues(valuesFromFields);

			} catch (NumberFormatException ex) {
				excelView.displayErrorMessage("Enter valid parameters or check the .txt file.");
			}
		}
	}

	class SaveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String[] valuesFromFields;
			try {
				valuesFromFields = excelView.getValuesFromFields();
				excelModel.saveFile(valuesFromFields);

			} catch (NumberFormatException ex) {
				excelView.displayErrorMessage("Enter valid parameters or check the .txt file.");
			}
		}
	}

}
