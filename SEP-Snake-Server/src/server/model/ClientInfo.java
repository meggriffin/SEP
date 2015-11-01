package server.model;

public class ClientInfo {
	
	private String clientIP;
    private int clientPort;
    private String clientNickname;



    public ClientInfo(){

    }


    public ClientInfo(String clientIP, int clientPort){
        this.clientIP = clientIP;
        this.clientPort = clientPort;
    }

    public String getClientIP() {
        return clientIP;
    }

    public void setClientIP(String clientIP) {
        this.clientIP = clientIP;
    }

    public int getClientPort() {
        return clientPort;
    }

    public void setClientPort(int clientPort) {
        this.clientPort = clientPort;
    }

    public String getClientNickname() {
        return clientNickname;
    }

    public void setClientNickname(String clientNickname) {
        this.clientNickname = clientNickname;
    }

}
