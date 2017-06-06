package brulion.executive;

import brulion.controller.TfwController;
import brulion.controller.ExcelController;
import brulion.model.TfwModel;
import brulion.model.ExcelModel;
import brulion.view.TfwView;
import brulion.view.BrulionView;
import brulion.view.ExcelView;

public class Brulion3000 {
	public static void main(String[] args) {

		TfwView tfwView = new TfwView();
		TfwModel tfwModel = new TfwModel();
		TfwController tfwController = new TfwController(tfwView, tfwModel);
		ExcelView excelView = new ExcelView();
		ExcelModel excelModel = new ExcelModel();
		ExcelController excelController = new ExcelController(excelView, excelModel);

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new BrulionView(tfwView, excelView).setVisible(true);
			}
		});
	}
}
