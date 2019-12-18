package bgu.spl.mics;


import java.util.List;

public class SendAgentsEvent<T> implements Event<T> {

    private Future<T> future;
    private List<String> serials;
    private int duration;


    public SendAgentsEvent(List<String> serials, int duration)
    {
        this.serials=serials;
        this.duration = duration;
    }

    public List<String> getSerials() {
        return serials;
    }

    public int getDuration() {
        return duration;
    }
    public Future<T> getFuture() {
        return future;
    }


}
