package snake.view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.util.Pair;
import snake.MainApp;
import snake.utility.SecureHashAlgorithm;

public class LoginScreenViewController {

	/**
	 * The welcome label and the three buttons that lead to the startscreen
	 * (after an login pop-up dialog).
	 */
	@FXML
	private Label welcomeLabel;
	@FXML
	private Button signIn;
	@FXML
	private Button signUp;
	@FXML
	private Button skip;
	@FXML
	private Button playOnline;

	/**
	 * Reference to the main app.
	 */
	private MainApp mainApp;

	/**
	 * The (empty) constructor.
	 */
	public LoginScreenViewController()
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
	 * Open a dialog popup, where you can enter the login data. Login data will
	 * be encrypted and compared with stored encrypted data. In case of
	 * equality, the login was successful.
	 */
	@FXML
	private void handleSignInButton()
	{
		// Set up the dialog window.
		Dialog<Pair<String, String>> signInDialog = new Dialog<>();
		signInDialog.setTitle("Sign In");
		signInDialog.setHeaderText("Please enter username and password and press ok.");
		signInDialog.initStyle(StageStyle.UTILITY);
		signInDialog.initModality(Modality.NONE);

		// Set up the two buttons.
		ButtonType loginButton = new ButtonType("Login", ButtonData.OK_DONE);
		signInDialog.getDialogPane().getButtonTypes().addAll(loginButton, ButtonType.CLOSE);

		// Set up the grid pane (for an organized structure).
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		// Set up the username and password text fields.
		TextField username = new TextField();
		username.setPromptText("Username");
		PasswordField password = new PasswordField();
		password.setPromptText("Password");

		// Add the elements (labels and text fields for username and password)
		// ton the 2x2 grid.
		grid.add(new Label("Username:"), 0, 0);
		grid.add(username, 1, 0);
		grid.add(new Label("Password:"), 0, 1);
		grid.add(password, 1, 1);

		// "Load" the ButtonType object as a Node object in order to use the
		// disable method for the login button (which is re-enabled when the
		// listener notices an entry in the username field).
		Node loginButtonNode = signInDialog.getDialogPane().lookupButton(loginButton);
		loginButtonNode.setDisable(true);
		username.textProperty().addListener((observable, oldValue, newValue) -> {
			loginButtonNode.setDisable(newValue.trim().isEmpty()); // lambda
																	// declaration
		});
		password.textProperty().addListener((observable, oldValue, newValue) -> {
			loginButtonNode.setDisable(newValue.trim().isEmpty()); // lambda
			// declaration
		});

		// Add the grid to the dialog window.
		signInDialog.getDialogPane().setContent(grid);

		// Give the focus to the username field?
		Platform.runLater(() -> username.requestFocus());

		// Converts the input to a format corresponding to the returning result
		// of the dialog.
		signInDialog.setResultConverter(dialogButton -> {
			if (dialogButton == loginButton)
			{
				return new Pair<>(username.getText(), password.getText());
			}
			return null;
		});

		// Store the result in the variable.
		Optional<Pair<String, String>> result = signInDialog.showAndWait();

		if (result.isPresent())
		{
			try
			{
				boolean loginFound = false;
				Scanner scanner = new Scanner(new File("./test.txt"));
				while (scanner.hasNextLine() && !loginFound)
				{
					String salt = scanner.nextLine();
					String encrytedPassword = scanner.nextLine();
					if (SecureHashAlgorithm.getSHA256Password(result.toString(), salt).compareTo(encrytedPassword) == 0)
					{
						loginFound = true;
						Alert alert = new Alert(AlertType.CONFIRMATION);
						alert.setTitle("Login successful.");
						alert.setHeaderText("You have successfully logged in.");
						alert.setContentText("You will get to the main menue now.");
						alert.showAndWait();
						// TODO login
					}
				}
				scanner.close();
				if (!loginFound)
				{
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Login failed.");
					alert.setHeaderText("Your username and password dont fit to existing login data.");
					alert.setContentText("Please check your input and try again or sign up.");
					alert.showAndWait();
				}
			} catch (IOException e)
			{
				e.printStackTrace();
			}

		} else
		{
			// TODO Close window?
		}
	}

	/**
	 * Open a dialog pop, where you can create an user. User data will be
	 * encrypted with SHA-512 and stored in the set file.
	 */
	@FXML
	private void handleSignUpButton()
	{
		// Set up the dialog window.
		Dialog<Pair<String, String>> signInDialog = new Dialog<>();
		signInDialog.setTitle("Sign Up");
		signInDialog.setHeaderText("Please choose a username and a password and press ok.");
		signInDialog.initStyle(StageStyle.UTILITY);
		signInDialog.initModality(Modality.APPLICATION_MODAL);

		// Set up the two buttons.
		ButtonType loginButton = new ButtonType("Sign Up", ButtonData.OK_DONE);
		signInDialog.getDialogPane().getButtonTypes().addAll(loginButton, ButtonType.CLOSE);

		// Set up the grid pane (for an organized structure).
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		// Set up the username and password text fields.
		TextField username = new TextField();
		username.setPromptText("Username");
		PasswordField password = new PasswordField();
		password.setPromptText("Password");
		PasswordField passwordConfirmation = new PasswordField();
		passwordConfirmation.setPromptText("Confirm Password");

		// Add the elements (labels and text fields for username and passwords)
		// ton the 2x3 grid.
		grid.add(new Label("Username:"), 0, 0);
		grid.add(username, 1, 0);
		grid.add(new Label("Password:"), 0, 1);
		grid.add(password, 1, 1);
		grid.add(new Label("Password:"), 0, 2);
		grid.add(passwordConfirmation, 1, 2);

		// "Load" the ButtonType object as a Node object in order to use the
		// disable method for the login button (which is re-enabled when the
		// listener notices an entry in the username field).
		Node loginButtonNode = signInDialog.getDialogPane().lookupButton(loginButton);
		loginButtonNode.setDisable(true);
		username.textProperty().addListener((observable, oldValue, newValue) -> {
			loginButtonNode.setDisable(newValue.trim().isEmpty()); // lambda
																	// declaration
		});

		// Add the grid to the dialog window.
		signInDialog.getDialogPane().setContent(grid);

		// Give the focus to the username field?
		Platform.runLater(() -> username.requestFocus());

		// Converts the input to a format corresponding to the returning result
		// of the dialog.

		signInDialog.setResultConverter(dialogButton -> {
			if (dialogButton == loginButton)
			{
				if (password.getText().equals(passwordConfirmation.getText()))
				{
					return new Pair<>(username.getText(), password.getText());
				} else
				{
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Login failed.");
					alert.setHeaderText("Password does not correspond to confirm password.");
					alert.setContentText("Please check your input and try again.");
					alert.showAndWait();
					return new Pair<>("fail", "liaf");
				}
			}
			return null;
		});

		// Store the result in the variable.
		Optional<Pair<String, String>> result = signInDialog.showAndWait();

		// Little trick to restart the window.
		if (result.toString().compareTo("Optional[fail=liaf]") == 0)
		{
			handleSignUpButton();
		} else if (result.isPresent())
		{
			try
			{
				FileWriter writer = new FileWriter("./test.txt", true);
				String salt = SecureHashAlgorithm.generateSalt();
				writer.write(salt);
				writer.write(System.lineSeparator());
				writer.write(SecureHashAlgorithm.getSHA256Password(result.toString(), salt));
				writer.write(System.lineSeparator());
				writer.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * Forward directly to the startscreen.
	 */
	@FXML
	private void handleSkipButton()
	{
		mainApp.loadStartScreenView();
	}
	
	/**
	 * Forward directly to the startscreen.
	 */
	@FXML
	private void handlePlayOnlineButton()
	{
		if(mainApp.connectToServer())
		{
			mainApp.loadStartScreenView();
		}else{
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Connection failed.");
			alert.setHeaderText("Couldn't connect to server.");
			alert.setContentText("Please try again with other credentials....");
			alert.showAndWait();
		}
	}
}