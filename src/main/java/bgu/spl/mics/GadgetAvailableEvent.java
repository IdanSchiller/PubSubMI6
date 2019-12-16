package bgu.spl.mics;

public class GadgetAvailableEvent<T> implements Event<T> {

    private String gadget;
    private Future<T> future;

    public GadgetAvailableEvent(String gadget){
        this.gadget=gadget;
        this.future = new Future<>();
    }


    public Future<T> getFuture(){
        return future;
    }

}
