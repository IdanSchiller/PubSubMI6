package bgu.spl.mics;

public class GadgetAvailableEvent implements Event {

    private Callback CB;

    public GadgetAvailableEvent(){
        CB= new GadgetAvailableEventCallBack();
    }

    public Callback getCallback() {
        return CB;
    }
}
