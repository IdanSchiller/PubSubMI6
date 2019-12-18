package bgu.spl.mics;
//import javafx.util.Pair;

import java.util.List;

public class AgentsAvailableEvent<T> implements Event<T> {


    // Fields
private Future<T> future=null;
    private List<String> agentsSerialNum;


    // Constructor
    public AgentsAvailableEvent(List<String> agentsSerialNum){
        this.agentsSerialNum=agentsSerialNum;
    }

    public List<String> getAgentsSerialNum() {
        return agentsSerialNum;
    }

    public Future<T> getFuture(){return future;}
}
