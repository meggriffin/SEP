package snake.network.client;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author katrinschauer
 *
 */

public class Client {
	
	private String host;
    private int port;
    private Socket socket;
    private final String DEFAULT_HOST = "localhost";


    public void connect(String host, int port) throws IOException {
        this.setHost(host);
        this.port = port;
        socket = new Socket(host, port);
        System.out.println("ClientInfo has been connected..");
    }


    public JSONObject receiveJSON() throws IOException, JSONException {
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
        String line = in.readLine();
        JSONObject jsonObject = new JSONObject(line);
        System.out.println("Got from server: " + " " + jsonObject.toString(2));
        return jsonObject;
    }


    public void sendJSON(JSONObject jsonObject) throws IOException, JSONException {
        OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
        out.write(jsonObject.toString() + "\n");
        out.flush();
        System.out.println("Sent to server: " + " " + jsonObject.toString(2));
    }


    public String getHost() 
    {
        return host;
    }

    public int getPort() 
    {
        return port;
    }

    public Socket getSocket() 
    {
        return socket;
    }


	public void setHost(String host)
	{
		this.host = host;
	}


	public String getDEFAULT_HOST()
	{
		return DEFAULT_HOST;
	}

    
}
	

