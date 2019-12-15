package bgu.spl.mics.application.subscribers;

import bgu.spl.mics.*;

/**
 * M handles ReadyEvent - fills a report and sends agents to mission.
 *
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */
public class M extends Subscriber {
	private int tickCounter;

	public M() {
		super("Change_This_Name");
		// TODO Implement this
	}

	@Override
	protected void initialize() {
		tickCounter=0;
		MessageBrokerImpl.getInstance().register(this);
		TickBroadcast tickBroadcast = new TickBroadcast();
		Callback<TickBroadcast> tickBroadcastCallback = (TickBroadcast c) -> tickCounter++;
		this.subscribeBroadcast( tickBroadcast.getClass(),tickBroadcastCallback);
		MissionReceivedEvent MRE = new MissionReceivedEvent();
		Callback<MissionReceivedEvent> MREcallBack = new Callback<MissionReceivedEvent>() {
			@Override
			public void call(MissionReceivedEvent c) {

			}
		};
		this.subscribeEvent(MRE.getClass(),MREcallBack);
//
	}

}
