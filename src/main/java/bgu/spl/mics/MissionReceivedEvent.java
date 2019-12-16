package bgu.spl.mics;

import bgu.spl.mics.application.passiveObjects.MissionInfo;

public class MissionReceivedEvent implements Event {
    private MissionInfo mission;
    private Future<Boolean> future;

    public MissionReceivedEvent(MissionInfo mission){
        this.mission=mission;
        this.future =  new Future<>();

    }


    public MissionInfo getMission(){
        return mission;
    }

    public Future getFuture(){return future;}
}
