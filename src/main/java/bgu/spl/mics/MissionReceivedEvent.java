package bgu.spl.mics;

import bgu.spl.mics.application.passiveObjects.MissionInfo;

public class MissionReceivedEvent<T> implements Event<T> {
    private MissionInfo mission;
    private Future<T> future;

    public MissionReceivedEvent(MissionInfo mission){
        this.mission=mission;
        this.future =  null;

    }


    public MissionInfo getMission(){
        return mission;
    }

    public Future<T> getFuture(){return future;}
}
