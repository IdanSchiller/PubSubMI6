package bgu.spl.mics.application.subscribers;

import bgu.spl.mics.*;
import bgu.spl.mics.application.passiveObjects.Squad;

/**
 * Only this type of Subscriber can access the squad.
 * Three are several Moneypenny-instances - each of them holds a unique serial number that will later be printed on the report.
 *
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */
public class Moneypenny extends Subscriber {
	private int tickCounter;
	private SimplePublisher simPub;

	public Moneypenny() {
		super("Change_This_Name");
		// TODO Implement this
	}

	@Override
	protected void initialize() {
		tickCounter=0;
		simPub = new SimplePublisher();
		MessageBrokerImpl.getInstance().register(this);
		Callback<TickBroadcast> tickBroadcastCallback = (TickBroadcast tickBroadcast) -> tickCounter++;
		this.subscribeBroadcast(TickBroadcast.class,tickBroadcastCallback);
		Callback<AgentsAvailableEvent> agentsCallBack = (agentsEvent) -> {
			 Boolean agentsAreAvailable = Squad.getInstance().getAgents(agentsEvent.getAgents());
		};
	}

}
