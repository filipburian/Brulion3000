package brulion.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;

public class TfwModel {
	private String userDefaultPath = System.getProperty("user.home");

	private PrintWriter valuesToWrite;

	File file;

	private BufferedReader reader;

	// private double tlX = 0.00d; Nie wiem dlaczego, ale dane nie s¹ u¿yte przy
	// obliczeniach.

	// private double tlY = 0.00d;

	private double rasterHalfWidth, rasterHalfHeigth;;

	private double rasterCoordinateX = 0.00d;

	private double rasterCoordinateY = 0.00d;

	private double azimuth = 0.00d;

	private int chooserReturnValue;

	String[] textFileSplitedRow;

	String textFileRow;

	public void calculateRasterHalfSize(double[] valuesFromTextField) {
		rasterHalfWidth = (valuesFromTextField[5] / 2.00) * valuesFromTextField[3];
		rasterHalfHeigth = (valuesFromTextField[4] / 2.00) * valuesFromTextField[3];
	}

	public void createTfwFiles(double[] valuesFromTextField) throws IOException {
		JFileChooser fileChooser = new JFileChooser(userDefaultPath);
		chooserReturnValue = fileChooser.showOpenDialog(null);

		if (chooserReturnValue == JFileChooser.APPROVE_OPTION) {
			try {
				reader = new BufferedReader(new FileReader(fileChooser.getSelectedFile()));
				textFileRow = reader.readLine();

				if (textFileRow != null)

					while (textFileRow != null) {
						textFileSplitedRow = textFileRow.split("\\s+");

						azimuth = ((valuesFromTextField[0] + valuesFromTextField[1]
								+ valuesFromTextField[2] * (Double.parseDouble(textFileSplitedRow[6]))) * Math.PI
								/ valuesFromTextField[0]);

						calculateAnglesValues(valuesFromTextField);

						calculateRasterCoords(textFileSplitedRow);

						file = new File(
								fileChooser.getSelectedFile().getParent() + "/" + textFileSplitedRow[0] + ".tfw");

						valuesToWrite = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));

						writeToTfwFile(calculateAnglesValues(valuesFromTextField), rasterCoordinateX,
								rasterCoordinateY);

						valuesToWrite.close();

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

	private String[] calculateAnglesValues(double[] valuesFromTextFields) {
		String[] angles = new String[4];
		angles[0] = String.valueOf(valuesFromTextFields[3] * Math.cos(azimuth));
		angles[1] = String.valueOf(-valuesFromTextFields[3] * Math.sin(azimuth));
		angles[2] = String.valueOf(-valuesFromTextFields[3] * Math.sin(azimuth));
		angles[3] = String.valueOf(-valuesFromTextFields[3] * Math.cos(azimuth));

		return angles;
	}

	private void calculateRasterCoords(String[] valuesFromTextFile) {
		rasterCoordinateX = ((Math.cos(azimuth)) * (-rasterHalfWidth)) + ((-Math.sin(azimuth)) * (-rasterHalfHeigth))
				+ (Double.parseDouble(valuesFromTextFile[2]));

		rasterCoordinateY = ((-Math.cos(azimuth)) * (-rasterHalfHeigth)) + ((-Math.sin(azimuth)) * (-rasterHalfWidth))
				+ (Double.parseDouble(valuesFromTextFile[1]));
	}

	private void writeToTfwFile(String[] angles, double rasterCoordinateX, double rasterCoordinateY)
			throws IOException {
		for (String angle : angles) {
			valuesToWrite.println(angle);
		}

		valuesToWrite.print(String.valueOf(rasterCoordinateX) + "\n");
		valuesToWrite.print(String.valueOf(rasterCoordinateY) + "\n");
	}
}
