package bgu.spl.mics.application.subscribers;

import bgu.spl.mics.*;
import bgu.spl.mics.application.passiveObjects.MissionInfo;

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
		private List<MissionInfo> missionList;
		private Long tickCounter;
		private Map<Long,MissionInfo> missionMap;
		private Long ticksLimit;

	public Intelligence(List<MissionInfo> missionList, Integer id, Long ticksLimit) {
		super("Intelligence"+id.toString());
		this.id=id;
		this.missionList=missionList;
		this.tickCounter=null;
		this.ticksLimit=ticksLimit;
	}

	@Override
	protected void initialize() {
		MessageBrokerImpl.getInstance().register(this);
		Callback<TickBroadcast> tickBroadcastCallback = (TickBroadcast tickBroadcast) -> tickCounter++;
		this.subscribeBroadcast(TickBroadcast.class,tickBroadcastCallback);
		while (tickCounter<ticksLimit){
			if (missionMap.containsKey(tickCounter)){
				Event<Boolean> missionEvent = new MissionReceivedEvent<>(missionMap.get(tickCounter));
				Future<Boolean> dontCareFuture = this.getSimplePublisher().sendEvent(missionEvent);
			}
		}
	}

}
