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
	private Integer id;

	public Moneypenny(Integer id) {
		super("MoneyPenny"+id.toString());
		// TODO Implement this
		this.id=id;
	}

	@Override
	protected void initialize() {
		tickCounter=0;
		MessageBrokerImpl.getInstance().register(this);
		Callback<TickBroadcast> tickBroadcastCallback = (TickBroadcast tickBroadcast) -> tickCounter++;
		this.subscribeBroadcast(TickBroadcast.class,tickBroadcastCallback);
		Callback<AgentsAvailableEvent> agentsCallBack = (agentsEvent) -> {
			 Boolean allAgentsExist = Squad.getInstance().getAgents(agentsEvent.getAgentsSerialNum());
			if(!allAgentsExist){
				// do something
			}
		};
		this.subscribeEvent(AgentsAvailableEvent.class,agentsCallBack);
	};
	public Integer getId() {
		return id;
	}

}
