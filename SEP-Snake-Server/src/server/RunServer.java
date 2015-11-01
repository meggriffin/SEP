package server;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import server.model.ServerModel;
import server.view.ServerPresenter;
import server.view.ServerView;

public class RunServer extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception
	{
        ServerModel serverModel = new ServerModel();
        ServerView serverView = new ServerView(serverModel);
        ServerPresenter serverPresenter = new ServerPresenter(serverModel, serverView);


        Scene scene = new Scene(serverView, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Server");
        primaryStage.show();
	}
	
    public static void main(final String[] arguments)
    {
       Application.launch(arguments);
    }

}
