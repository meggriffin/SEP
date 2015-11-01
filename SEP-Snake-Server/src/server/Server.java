package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

	 private int port;
	    private ServerSocket serverSocket;
	    private Runnable r;

	    public Server(){
	    }


	    public Server(int port){
	        this.port = port;
	    }


	    public void establish(int port) throws IOException {
	        this.port = port;
	        serverSocket = new ServerSocket(port);
	        System.out.println("A new ThreadingServer has been established on port " + port);
	    }


	    public void accept() throws IOException {
	        while (true) {
	            Socket clientSocket = serverSocket.accept();
	            //  Runnable r = new ServerHandler(clientSocket);
	            r = new ServerHandler(clientSocket);
	            Thread t = new Thread(r);
	            t.start();
	        }
	    }

	    public void start(int port) throws IOException{
	        establish(port);
	        accept();
	    }


	    public Runnable getR() {
	        return r;
	    }

	    public void setR(Runnable r) {
	        this.r = r;
	    }



	    @Override
	    public void run() {
	        try{
	            start(port);
	            /*
	            serverSocket = new ServerSocket(port);
	            while(true){
	                Socket clientSocket = serverSocket.accept();
	                Runnable r = new ServerHandler(clientSocket);
	                Thread t = new Thread(r);
	                t.start();

	            }  */

	        } catch(IOException e){
	            System.err.print(e);
	        }
	    }


	    public int getPort() {
	        return port;
	    }

	    public void setPort(int port) {
	        this.port = port;
	    }

	    public ServerSocket getServerSocket() {
	        return serverSocket;
	    }

	    public void setServerSocket(ServerSocket serverSocket) {
	        this.serverSocket = serverSocket;
	    }

}
