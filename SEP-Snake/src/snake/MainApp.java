package snake;

import java.io.IOException;
import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.UnknownHostException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import snake.model.Map;
import snake.model.SnakeInstance;
import snake.network.client.Client;
import snake.view.GameGraphicsViewController;
import snake.view.LoginScreenViewController;
import snake.view.StartScreenViewController;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	private Client client;

	@Override
	public void start(Stage primaryStage)
	{
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Snake");
		
		initClient();

		// Set the application icon
		// this.primaryStage.getIcons().add(new
		// Image("file:resources/images/placeholder"));

		// Initializes the rootlayout
		// initRootLayout();

		// Loads the gamegraphicsview
		// loadGameGraphicsView();

		//Loads the login screen view
		loadLoginScreenView();

	}

	public static void main(String[] args)
	{
		// (new Thread(new SnakeThread(new Map("testmap")))).start();
		launch(args);
	}

	/**
	 * Initializes the rootlayout
	 */
	public void initRootLayout()
	{
		try
		{
			primaryStage.close();

			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayoutView.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);

			// Give the controller acces to the main app.
			// RootLayoutController controller = loader.getController();
			// controller.setMainApp(this);

			// Shows the primaryStage
			primaryStage.show();

			loadGameGraphicsView();

		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void loadGameGraphicsView()
	{
		try
		{
			// initRootLayout();

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/GameGraphicsView.fxml"));
			AnchorPane gameGraphicsView = loader.load();

			rootLayout.setCenter(gameGraphicsView);
			//
			// Scene scene = new Scene(gameGraphicsView);
			// primaryStage.setScene(scene);
			//
			// primaryStage.show();

			GameGraphicsViewController controller = loader.getController();
			controller.setMainApp(this);

			Map mytextmap = new Map("testmap");
			GameInstance gameInstance = new GameInstance(controller, mytextmap);
			gameInstance.newSnake(new SnakeInstance("testsnake", mytextmap));
			gameInstance.newGameMechanic();
			gameInstance.pushMapChangesToStack();
			controller.drawCanvas(gameInstance);
			
			
			
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void loadLoginScreenView()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/LoginScreenView.fxml"));
			AnchorPane loginScreenView = loader.load();

			Scene scene = new Scene(loginScreenView);
			primaryStage.setScene(scene);

			primaryStage.show();

			LoginScreenViewController controller = loader.getController();
			controller.setMainApp(this);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void loadStartScreenView()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/StartscreenView.fxml"));
			AnchorPane startScreenView = loader.load();

			Scene scene = new Scene(startScreenView);
			primaryStage.setScene(scene);

			primaryStage.show();

			StartScreenViewController controller = loader.getController();
			controller.setMainApp(this);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void initClient()
	{
		client = new Client();
	}
	
	public Boolean connectToServer()
	{
		//#TODO
		try
		{
			client.connect("localhost", 7777);
		} 
		catch (ConnectException e) {
            System.err.println(client.getHost() + " connect refused");
            return false;
        }

        catch(UnknownHostException e){
            System.err.println(client.getHost() + " Unknown host");
            client.setHost(client.getDEFAULT_HOST());
            return false;
        }

        catch (NoRouteToHostException e) {
            System.err.println(client.getHost() + " Unreachable");
            return false;

        }

        catch (IllegalArgumentException e){
            System.err.println(client.getHost() + " wrong port");
            return false;
        }

        catch(IOException e){
            System.err.println(client.getHost() + ' ' + e.getMessage());
            System.err.println(e);
        }
        finally {
            try {
                client.getSocket().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		return true;
	}
	public MainApp()
	{
		// TODO Auto-generated constructor stub
	}

}
