package bgu.spl.mics;

public class GadgetAvailableEvent<T> implements Event<T> {

    private String gadget;
    private Future<T> fut;

    public GadgetAvailableEvent(String gadget){
        this.gadget=gadget;
    }

    public String getGadget() {
        return gadget;
    }

}
