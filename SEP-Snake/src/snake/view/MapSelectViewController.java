package snake.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import snake.MainApp;

public class MapSelectViewController {

	/**
	 * The instruction label and the three buttons that load the selected map.
	 */
	@FXML
	private Label label;
	@FXML
	private Button map1;
	@FXML
	private Button map2;
	@FXML
	private Button map3;

	/**
	 * Reference to the main app.
	 */
	private MainApp mainApp;

	/**
	 * The (empty) constructor.
	 */
	public MapSelectViewController()
	{
	}

	/**
	 * The method to initialize the controller class, which is called after the
	 * fxml file has been loaded.
	 */
	@FXML
	private void initialize()
	{
	}

	/**
	 * Is called by the main app to give reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp)
	{
		this.mainApp = mainApp;
	}
}