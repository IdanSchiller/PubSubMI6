package bgu.spl.mics;


import java.util.List;

public class SendAgentsEvent<T> implements Event<T> {


    private List<String> serials;
    private int time;

    public SendAgentsEvent(List<String> serials, int time)
    {
        serials=serials;
        time=time;
    }

    public List<String> getSerials() {
        return serials;
    }

    public int getTime() {
        return time;
    }

}
