package server.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ServerModel {

    private IntegerProperty port = new SimpleIntegerProperty(this, "port", 9999);



    public final int getPort(){
        return port.get();
    }


    public final void setPort(int port){
        this.port.set(port);
    }


    public final IntegerProperty portProperty(){
        return port;
    }
	
}
