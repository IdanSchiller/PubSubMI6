package bgu.spl.mics;
import bgu.spl.mics.application.passiveObjects.Agent;
import javafx.util.Pair;

import java.util.List;
import java.util.Map;

public class AgentsAvailableEvent<T> implements Event<T> {


    // Fields
    private Future<Pair<List<Agent>,Integer>> fut;
    private List<String> agentsSerialNum;


    // Constructor
    public AgentsAvailableEvent(List<String> agentsSerialNum){
        this.agentsSerialNum=agentsSerialNum;
    }

    public List<String> getAgentsSerialNum() {
        return agentsSerialNum;
    }

    public Future<Pair<List<Agent>, Integer>> getFuture(){return fut;}
}
