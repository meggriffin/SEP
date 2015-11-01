package server.view;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import server.Server;
import server.ServerHandler;
import server.model.ClientInfo;
import server.model.ServerModel;

public class ServerPresenter {
	private ServerModel model;
    private ServerView view;


    private Server server;
    private Thread connectThread;


    public ServerPresenter(ServerModel model, ServerView view) throws JSONException{
        this.model = model;
        this.view = view;

        activateEvents();
    }


    public void activateEvents() throws JSONException {
        view.startButton.setOnAction(e-> startServer());
        view.refreshButton.setOnAction(e-> refreshTable());
        view.interruptButton.setOnAction(e-> interruptServer());
    }

    private void interruptServer() {

        view.interruptButton.setDisable(true);
        connectThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    connectBlocking();
                } catch(IOException e){

                }
            }
        });
    }


    public void refreshTable() throws JSONException{

        ServerHandler serverHandler = (ServerHandler) server.getR();
        JSONObject jsonClientInfo = serverHandler.getClientInfo();
        ClientInfo clientInfo = new ClientInfo();
        clientInfo.setClientIP(jsonClientInfo.getString("clientIP"));
        clientInfo.setClientPort(jsonClientInfo.getInt("clientPort"));
        clientInfo.setClientNickname(jsonClientInfo.getString("clientNickname"));

        view.clientInfoTable.getItems().add(clientInfo);
    }

    private void connectBlocking() throws IOException {

    }

    public void startServer(){
        server = new Server(model.getPort());
        Thread serverThread = new Thread(server);
        serverThread.start();
    }


    public void stopServer(){
        try {
            if(server.getServerSocket()!= null)
                server.getServerSocket().close();

            else
                System.out.println("YOur server.getServerSocket() is null!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
