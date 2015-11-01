package server.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;
import server.model.ClientInfo;
import server.model.ServerModel;


public class ServerView extends BorderPane{
	
	
	 private ServerModel model;

	    //   private ThreadHandler threadHandler;




	    // Controls
	    Label portLabel;
	    TextField portTxtField;
	    TextArea infoTxtArea;
	    Button startButton;
	    Button interruptButton;
	    Button refreshButton;

	    // Table
	    TableView<ClientInfo> clientInfoTable;
	    TableColumn<ClientInfo, String> clientIPColumn ;
	    TableColumn<ClientInfo, Integer> clientPortColumn ;
	    TableColumn<ClientInfo, String> clientNicknameColumn ;






	    public ServerView(ServerModel model){
	        this.model = model;
	        initializeGUI();
	        bindViewComponentsToModel();
	    }

	    public void initializeGUI(){

	        // 1- Create the Controls
	        portLabel = new Label("Port: ");
	        portTxtField = new TextField();
	        infoTxtArea = new TextArea();
	        startButton = new Button("Start");
	        refreshButton = new Button("Refresh Info");
	        interruptButton = new Button("Interruptible");

	        GridPane gridPane = new GridPane();
	        gridPane.setHgap(5);
	        gridPane.setVgap(5);
	        gridPane.add(portLabel, 0, 0);
	        gridPane.add(portTxtField, 1, 0, 2, 1);
	        gridPane.add(startButton, 0, 2);
	        gridPane.add(refreshButton,1, 2);
	        gridPane.add(interruptButton, 2, 2);



	        clientInfoTable = new TableView<ClientInfo>();

	        clientIPColumn = new TableColumn<ClientInfo, String>("Client IP");
	        clientIPColumn.setMinWidth(100);
	        clientIPColumn.setCellValueFactory(new PropertyValueFactory<ClientInfo, String>("clientIP"));


	        clientPortColumn = new TableColumn<ClientInfo, Integer>("Client Port");
	        clientPortColumn.setMinWidth(100);
	        clientPortColumn.setCellValueFactory(new PropertyValueFactory<ClientInfo, Integer>("clientPort"));


	        clientNicknameColumn = new TableColumn<ClientInfo, String>("Client Nickname");
	        clientNicknameColumn.setMinWidth(300);
	        clientNicknameColumn.setCellValueFactory(new PropertyValueFactory<ClientInfo, String>("clientNickname"));
	        // Create the Table:

	        clientInfoTable.getColumns().addAll(clientIPColumn, clientPortColumn, clientNicknameColumn);



	        Label studentTableLabel = new Label("ClientInfo Table");
	        VBox vBox = new VBox(10, studentTableLabel, clientInfoTable);
	        vBox.setPadding(new Insets(10, 10, 10, 10));

	        HBox hBox = new HBox(infoTxtArea);
	        // Add the HBox and GridPane to the BorderPane
	        this.setLeft(gridPane);
	        this.setCenter(vBox);

	    }


	    public TableView<ClientInfo> getClientInfoTable() {
	        return clientInfoTable;
	    }


	    /*
	    public ThreadHandler getThreadHandler() {
	        return threadHandler;
	    }

	    public void setThreadHandler(ThreadHandler threadHandler) {
	        this.threadHandler = threadHandler;
	    }

	    */

	    public void bindViewComponentsToModel() {
	        portTxtField.textProperty().bindBidirectional(model.portProperty(),  new NumberStringConverter());

	    }


}
