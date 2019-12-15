package bgu.spl.mics;

public class MissionReceivedEvent<T> implements Event<T> {
   private T t;


    public MissionReceivedEvent(T resault){
        t = resault;
    }
}
