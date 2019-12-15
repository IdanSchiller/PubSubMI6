package bgu.spl.mics.application.subscribers;

import bgu.spl.mics.MessageBrokerImpl;
import bgu.spl.mics.Subscriber;
import bgu.spl.mics.TickBroadcast;

/**
 * M handles ReadyEvent - fills a report and sends agents to mission.
 *
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */
public class M extends Subscriber {

	public M() {
		super("Change_This_Name");
		// TODO Implement this
	}

	@Override
	protected void initialize() {
		MessageBrokerImpl.getInstance().register(this);
		TickBroadcast tickBroadcast = new TickBroadcast();
		this.subscribeBroadcast(tickBroadcast.getClass(),tickBroadcast.getCallback());
		
	}

}
