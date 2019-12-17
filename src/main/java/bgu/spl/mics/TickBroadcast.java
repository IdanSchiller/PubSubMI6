package bgu.spl.mics;

public class TickBroadcast implements Broadcast {

    private int tick;

    public TickBroadcast(int tick){
        tick=tick;
    }

    public int getTick() {
        return tick;
    }

}