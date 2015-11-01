package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import org.json.JSONException;
import org.json.JSONObject;

public class ServerHandler implements Runnable {
	 private Socket clientSocket;
	    private static int counter;

	    private JSONObject clientInfo;



	    public ServerHandler(Socket clientSocket){
	        this.clientSocket = clientSocket;
	        counter++;
	        printConnectionInfo();
	    }


	    private void printConnectionInfo(){
	        String clientNr = getClientNr() + " client has been connected from: ";
	        String clientIP = clientSocket.getInetAddress().getHostAddress();
	        System.out.println(clientNr + clientIP);
	    }

	    private String getClientNr(){
	        if(counter == 1)
	            return "First";
	        else if(counter == 2)
	            return "Second";
	        else if (counter == 3)
	            return "Third";
	        else
	            return counter + "-th";
	    }


	    public JSONObject receiveJSON() throws IOException, JSONException {
	        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
	        String line = in.readLine();
	        JSONObject jsonObject = new JSONObject(line);
	        System.out.println("Got from client on port " + clientSocket.getPort() + " " + jsonObject.toString(2));
	        return jsonObject;
	    }


	    public void sendJSON(JSONObject jsonObject) throws IOException, JSONException {
	        OutputStreamWriter out = new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8");
	        out.write(jsonObject.toString() + "\n");
	        out.flush();
	        System.out.println("Sent to client on port " + clientSocket.getPort() + " " + jsonObject.toString(2));
	    }


	    public org.json.JSONObject getClientInfo() {
	        return clientInfo;
	    }

	    public void setClientInfo(org.json.JSONObject clientInfo) {
	        this.clientInfo = clientInfo;
	    }

	    @Override
	    public void run() {
	        try{
	            // try{

	            clientInfo = receiveJSON();

	            //   }

	           /*
	            finally {
	                clientSocket.close();
	            }
	*/
	        } catch(IOException e){
	            System.err.print(e);
	        } catch (JSONException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
}
