package bgu.spl.mics;

import javafx.util.Pair;

public class GadgetAvailableEvent<T> implements Event<T> {

    Future<Pair<Boolean,Integer>> future; // returns the Boolean resault if the gadget is found and the Integer QTime  - the time the event was received
    private String gadget;
    private Future<T> fut;

    public GadgetAvailableEvent(String gadget){
        this.gadget=gadget;
    }

    public String getGadget() {
        return gadget;
    }
    public Future<Pair<Boolean,Integer>> getFuture(){
        return future;
    }

}
