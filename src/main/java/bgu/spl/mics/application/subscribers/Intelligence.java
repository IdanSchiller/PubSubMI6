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
		private Integer tickCounter;
		private Map<Integer,MissionInfo> missionMap;
		private Long ticksLimit;

	public Intelligence(List<MissionInfo> missionList, Integer id, Long ticksLimit) {
		super("Intelligence "+id.toString());
		this.id=id;
		missionMap=new HashMap<>();
		for (MissionInfo mission:missionList){
			missionMap.put(mission.getTimeIssued(),mission);
		}
		this.tickCounter= 0;
		this.ticksLimit=ticksLimit;
	}

	@Override
	protected void initialize() throws InterruptedException {
		Thread.currentThread().setName(getName());
		MessageBrokerImpl.getInstance().register(this);
		Callback<TickBroadcast> tickBroadcastCallback = (TickBroadcast tickBroadcast) -> {
			this.tickCounter=tickBroadcast.getTick();
			if(tickCounter==ticksLimit.intValue())
			{
				super.terminate();
			}
//			/** test */
//			System.out.println("tick received" );
			if (missionMap.containsKey(tickCounter))
			{
				Event<Boolean> missionEvent = new MissionReceivedEvent<>(missionMap.get(tickCounter),this.id);
				Future<Boolean> dontCareFuture = this.getSimplePublisher().sendEvent(missionEvent);
				System.out.println("Intelligence "+id+" sent mission: "+missionMap.get(tickCounter).getMissionName()+" at tick: "+tickCounter);
			}
		};
		this.subscribeBroadcast(TickBroadcast.class,tickBroadcastCallback);
	}

}
