package bgu.spl.mics;


public class GadgetAvailableEvent<T> implements Event<T> {

//    Future<Pair<Boolean,Integer>> future; // returns the Boolean resault if the gadget is found and the Integer QTime  - the time the event was received
    private String gadget;
    private Future<T> future=null;

    public GadgetAvailableEvent(String gadget){
        this.gadget=gadget;
    }

    public String getGadget() {
        return gadget;
    }

    public Future<T> getFuture(){
        return future;
    }

}
