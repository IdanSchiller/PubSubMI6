package bgu.spl.mics.application.subscribers;

import bgu.spl.mics.*;
import bgu.spl.mics.application.passiveObjects.Diary;
import bgu.spl.mics.application.passiveObjects.Inventory;
import bgu.spl.mics.application.passiveObjects.Report;

import java.util.ArrayList;
import java.util.List;

/**
 * Q is the only Subscriber\Publisher that has access to the {@link bgu.spl.mics.application.passiveObjects.Inventory}.
 *
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */
public class Q extends Subscriber {
 	private Inventory inv;
 	private int tickCounter;

	private static class QHolder{
		private static Q instance=new Q();

	}
	private Q(){
		// innitiate fields
		super("Q");
		inv= Inventory.getInstance();
	}

 //TODO ziv

	@Override
	protected void initialize() {
		// TODO Implement this
		MessageBrokerImpl.getInstance().register(this);
		Callback<TickBroadcast> tickBroadcastCallback = (TickBroadcast tickBroadcast) -> tickCounter++;
		this.subscribeBroadcast(TickBroadcast.class,tickBroadcastCallback);
		Callback<GadgetAvailableEvent> GAE = (GadgetAvailableEvent gadgetAvailable) ->
		this.subscribeEvent(GadgetAvailableEvent.class,GAE);

//		MessageBrokerImpl.getInstance().register(this);
//		GadgetAvailableEvent GAE = new GadgetAvailableEvent("");
//		this.subscribeEvent(GadgetAvailableEvent.class,GadgetAvailableEvent.getCallback());
		
	}

	private void CheckGadgetAvailble()
}
