package bgu.spl.mics;


import java.util.List;

public class SendAgentsEvent<T> implements Event<T> {

    private Future<Boolean> future;
    private List<String> serials;
    private int duration;


    public SendAgentsEvent(List<String> serials, int duration)
    {
        serials=serials;
        duration = duration;
    }

    public List<String> getSerials() {
        return serials;
    }

    public int getDuration() {
        return duration;
    }
    public Future<Boolean> getFuture() {
        return future;
    }


}
