package bgu.spl.mics;

import bgu.spl.mics.Broadcast;
import bgu.spl.mics.application.TickBroadcastCallBack;

public class TickBroadcast implements Broadcast {

    // Fields:
    private Callback CB;


    // Constructor
    public TickBroadcast(){
        CB = new TickBroadcastCallBack();
    }

    // Methods:

    public Callback getCallback() {
        return CB;
    }




}
