package brulion.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

public class BrulionView extends JFrame {

	private JTabbedPane bookmarks = new JTabbedPane();
	private JPanel tfwPanel = new TfwView();
	private JPanel excelPanel = new ExcelView();

	public BrulionView(JPanel tfwView, JPanel excelView) {
		try { 
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		    e.printStackTrace();
		}

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(500, 500);
		this.setLocationByPlatform(true);
		this.setTitle("Brulion3000 v.2");

		tfwPanel = tfwView;
		excelPanel = excelView;

		this.add(tfwPanel);
		this.add(excelPanel);
		this.setVisible(true);

		bookmarks.add(tfwPanel, "Tfw creator");
		bookmarks.add(excelPanel, "Xls creator");
		this.add(bookmarks);
	}
}
