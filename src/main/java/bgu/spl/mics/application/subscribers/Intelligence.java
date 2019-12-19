package bgu.spl.mics.application.subscribers;

import bgu.spl.mics.*;
import bgu.spl.mics.application.passiveObjects.MissionInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * A Publisher only.
 * Holds a list of Info objects and sends them
 *
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */
public class Intelligence  extends Subscriber {

		private Integer id;
		private Long tickCounter;
		private Map<Integer,MissionInfo> missionMap;
		private Long ticksLimit;

	public Intelligence(List<MissionInfo> missionList, Integer id, Long ticksLimit) {
		super("Intelligence"+id.toString());
		this.id=id;
		missionMap=new HashMap<>();
		for (MissionInfo mission:missionList){
			missionMap.put(mission.getTimeIssued(),mission);
		}
		this.tickCounter=null;
		this.ticksLimit=ticksLimit;
	}

	@Override
	protected void initialize() throws InterruptedException {
		MessageBrokerImpl.getInstance().register(this);
		MessageBrokerImpl.getInstance().subscribeBroadcast(TickBroadcast.class,this);
		Callback<TickBroadcast> tickBroadcastCallback = (TickBroadcast tickBroadcast) -> {
			if (missionMap.containsKey(tickCounter)) {
				if(tickCounter==ticksLimit)
				{
					super.terminate();
				}
				Event<Boolean> missionEvent = new MissionReceivedEvent<>(missionMap.get(tickCounter));
				Future<Boolean> dontCareFuture = this.getSimplePublisher().sendEvent(missionEvent);
			}
		};
		this.subscribeBroadcast(TickBroadcast.class,tickBroadcastCallback);
	}

}
