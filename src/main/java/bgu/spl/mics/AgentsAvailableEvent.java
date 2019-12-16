package bgu.spl.mics;


import java.util.List;

public class AgentsAvailableEvent<T> implements Event<T> {


    // Fields
    private List<String> agents;


    // Constructor
    public AgentsAvailableEvent(List<String> agents){
        this.agents=agents;
    }

    public List<String> getAgents() {
        return agents;
    }

}
