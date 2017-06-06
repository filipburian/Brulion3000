package brulion.model;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ExcelModel {

	private Workbook workbook;
	private Sheet linearElements;
	private Sheet angularElements;
	private Sheet excentricGPS;

	private String[] linearHeaders;
	private String[] angularHeaders;

	private void fillLinearHeadersArray() {
		linearHeaders = new String[19];
		linearHeaders[0] = new String("SZEREG");
		linearHeaders[1] = new String("ZDJECIE");
		linearHeaders[2] = new String("X92 (M)");
		linearHeaders[3] = new String("Y92 (M)");
		linearHeaders[4] = new String("B");
		linearHeaders[5] = new String("L");
		linearHeaders[6] = new String("H_ELIPS_M");
		linearHeaders[7] = new String("H_NORM (M)");
		linearHeaders[8] = new String("H_ŒR_TERENU (M)");
		linearHeaders[9] = new String("CZAS_GPS");
		linearHeaders[10] = new String("DATA");
		linearHeaders[11] = new String("KARTA_PRACY ");
		linearHeaders[12] = new String("SKALA_GSD");
		linearHeaders[13] = new String("ROLKA_FOLDER");
		linearHeaders[14] = new String("Wykonawca");
		linearHeaders[15] = new String("WYKONAWCA_zdjêæ");
		linearHeaders[16] = new String("KEZL");
		linearHeaders[17] = new String("PROJEKT");
		linearHeaders[18] = new String("OBIEKT");
	}

	private void fillAngularHeadersArray() {
		angularHeaders = new String[6];
		angularHeaders[0] = new String("Nr szeregu");
		angularHeaders[1] = new String("Nr zdjêcia");
		angularHeaders[2] = new String("OMEGA");
		angularHeaders[3] = new String("PHI");
		angularHeaders[4] = new String("KAPPA");
		angularHeaders[5] = new String("Nr karty pracy");
	}

	private void createWorkbookWithSheets() {
		workbook = new HSSFWorkbook();
		linearElements = workbook.createSheet("Elementy liniowe");
		angularElements = workbook.createSheet("Elementy k¹towe");
		excentricGPS = workbook.createSheet("Ekscentr anteny GPS");
	}

	private void setLinearHeaders() {
		Row row0 = linearElements.createRow(0);
		ArrayList<Cell> cells = new ArrayList<>();
		for (int i = 0; i < linearHeaders.length; i++) {
			cells.add(row0.createCell(i));
			cells.get(i).setCellValue(linearHeaders[i]);

		}
	}

	private void setAngularHeaders() {
		Row row0 = angularElements.createRow(0);
		ArrayList<Cell> cells = new ArrayList<>();
		for (int i = 0; i < angularHeaders.length; i++) {
			cells.add(row0.createCell(i));
			cells.get(i).setCellValue(angularHeaders[i]);

		}
	}

	private void setExcentricValues() {
		Row row23 = excentricGPS.createRow(23);
		row23.createCell(4).setCellValue(0.159);

		Row row24 = excentricGPS.createRow(24);
		row24.createCell(4).setCellValue(0.025);

		Row row25 = excentricGPS.createRow(25);
		row25.createCell(4).setCellValue(1.326);
	}

	public void fillXlsWithValues(String[] constantValues) {

		fillLinearHeadersArray();
		fillAngularHeadersArray();
		createWorkbookWithSheets();
		setLinearHeaders();
		setAngularHeaders();
		setExcentricValues();

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Open file");
		String[] valuesFromTextFields = constantValues;

		int chooserReturnValue = fileChooser.showOpenDialog(null);

		if (chooserReturnValue == JFileChooser.APPROVE_OPTION) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(fileChooser.getSelectedFile()));
				String textFileRow = reader.readLine();

				int iterator = 1;
				if (textFileRow != null)
					while (textFileRow != null) {

						String[] textFileSplitedRow = textFileRow.split("\\s+");
						Row row0 = linearElements.createRow(iterator);
						ArrayList<Cell> linearCells = new ArrayList<>();
						Row row1 = angularElements.createRow(iterator + 1);
						ArrayList<Cell> angularCells = new ArrayList<>();

						for (int i = 0; i < 19; i++) {
							linearCells.add(row0.createCell(i));

						}
						linearCells.get(0).setCellValue(textFileSplitedRow[0]);
						linearCells.get(1).setCellValue(textFileSplitedRow[1]);
						linearCells.get(2).setCellValue(textFileSplitedRow[2]);
						linearCells.get(3).setCellValue(textFileSplitedRow[3]);
						linearCells.get(4).setCellValue(textFileSplitedRow[4]);
						linearCells.get(5).setCellValue(textFileSplitedRow[5]);
						linearCells.get(6).setCellValue(textFileSplitedRow[6]);
						linearCells.get(7).setCellValue(textFileSplitedRow[7]);
						linearCells.get(8).setCellValue(constantValues[0]);
						linearCells.get(9).setCellValue(textFileSplitedRow[8]);
						linearCells.get(10).setCellValue(constantValues[1]);
						linearCells.get(11).setCellValue(constantValues[2]);
						linearCells.get(12).setCellValue(constantValues[3]);
						linearCells.get(13).setCellValue(constantValues[2]);
						linearCells.get(14).setCellValue(constantValues[4]);
						linearCells.get(15).setCellValue(constantValues[4]);
						linearCells.get(16).setCellValue(constantValues[5]);
						linearCells.get(17).setCellValue(constantValues[6]);
						linearCells.get(18).setCellValue(constantValues[7]);

						for (int i = 0; i < 7; i++) {
							angularCells.add(row1.createCell(i));
						}
						angularCells.get(0).setCellValue(textFileSplitedRow[0]);
						angularCells.get(1).setCellValue(textFileSplitedRow[1]);
						angularCells.get(2).setCellValue(textFileSplitedRow[9]);
						angularCells.get(3).setCellValue(textFileSplitedRow[10]);
						angularCells.get(4).setCellValue(textFileSplitedRow[11]);
						angularCells.get(5).setCellValue(constantValues[2]);

						iterator++;
						textFileRow = reader.readLine();
					}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.exit(0);
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}

	}

	public void saveFile(String[] constantValues) {
		String[] valuesFromTextField = constantValues;
		String fileDirectoryName = constantValues[2] + ".xls";

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Save file"); // 
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Files", ".xls");
														
		fileChooser.addChoosableFileFilter(filter);
		fileChooser.setSelectedFile(new File(fileDirectoryName));
		fileChooser.setVisible(true);
		int result = fileChooser.showSaveDialog(fileChooser);

		if (result == JFileChooser.APPROVE_OPTION) {
			fileDirectoryName = fileChooser.getSelectedFile().getAbsolutePath();
		} else {
			return;
		}

		File file = new File(fileDirectoryName);
		if (file.exists() == false) {
			try (FileOutputStream output = new FileOutputStream(file)) {
				workbook.write(output);
				output.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("File already exist");
		}
	}

}
