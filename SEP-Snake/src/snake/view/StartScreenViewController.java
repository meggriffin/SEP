package snake.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.util.Pair;
import snake.MainApp;

public class StartScreenViewController {

	/**
	 * Attributes to control the buttons of the view.
	 */
	@FXML
	private Label userWelcomeLabel; // If signed in, use username, otherwise
									// unpersonal welcome.
	@FXML
	private Button playButton;
	@FXML
	private Button optionsButton;
	@FXML
	private Button creditsButton;
	@FXML
	private Button helpButton;
	@FXML
	private Button quitButton;

	/**
	 * Reference to the main app.
	 */
	private MainApp mainApp;

	/**
	 * The (empty) constructor.
	 */
	public StartScreenViewController()
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

	/**
	 * Delete the payer from the login file. Authentication is required. TODO
	 * delete playerData.
	 */
	public void deletePlayer()
	{
		// Set up the dialog window.
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Delete Player");
		dialog.setHeaderText("WARNING: Your data will be deleted!\nPlease enter username and password.");
		dialog.initStyle(StageStyle.UTILITY);
		dialog.initModality(Modality.APPLICATION_MODAL);

		// Add buttons

		// Add gridPane

		// Add textFields

		// Add labels and textFields to gridPane

		// Set disable

		// Add gridPane to dialog

		// Give focus to username

		// Set resultConverter
	}

	/**
	 * Switch the scene to the game view.
	 */
	@FXML
	private void handlePlayButton()
	{
		mainApp.initRootLayout();
	}

	/**
	 * Switch to the options Layout.
	 */
	@FXML
	private void handleOptionsButton()
	{
		// TODO
	}

	/**
	 * Switch to credits layout.
	 */
	@FXML
	private void handleCreditsButton()
	{
		// TODO
	}

	/**
	 * Switch to help layout.
	 */
	@FXML
	private void handleHelpButton()
	{
		// TODO
	}

	/**
	 * Terminate the application/virtual machine.
	 */
	@FXML
	private void handleQuitButton()
	{
		System.exit(0);
	}
}