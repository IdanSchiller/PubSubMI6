package bgu.spl.mics.application.subscribers;

import bgu.spl.mics.*;
import bgu.spl.mics.application.passiveObjects.Diary;
import bgu.spl.mics.application.passiveObjects.Inventory;
import bgu.spl.mics.application.passiveObjects.Report;
//import com.sun.org.apache.xpath.internal.operations.Bool;
//import com.sun.tools.javac.util.Pair;


import java.util.ArrayList;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Q is the only Subscriber\Publisher that has access to the {@link bgu.spl.mics.application.passiveObjects.Inventory}.
 *
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */
public class Q extends Subscriber {
 	private Inventory inv;
 	private AtomicInteger tickCounter;
 	private long ticksLimit;

//	private static class QHolder{
//		private static Q instance=new Q();
//	}
	public Q(long ticksLimit){
		// innitiate fields
		super("Q");
		inv= Inventory.getInstance();
		tickCounter=new AtomicInteger();
		ticksLimit=ticksLimit;
	}

 //TODO ziv

	@Override
	protected void initialize() throws InterruptedException {
		MessageBrokerImpl.getInstance().register(this);
		Callback<TickBroadcast> tickBroadcastCallback = (TickBroadcast tickBroadcast) -> {
			tickCounter.getAndIncrement();
			if (tickCounter.intValue()== ticksLimit)
			{
				super.terminate();
			}
		};
		this.subscribeBroadcast(TickBroadcast.class,tickBroadcastCallback);
		Callback<GadgetAvailableEvent> GAE = (GadgetAvailableEvent gadgetAvailable) -> CheckGadgetAvailable(gadgetAvailable);
		this.subscribeEvent(GadgetAvailableEvent.class,GAE);

	}

	private void CheckGadgetAvailable(GadgetAvailableEvent gadgetAvailable){
		int tick = tickCounter.get();
		Boolean b=inv.getItem(gadgetAvailable.getGadget());
		if (b){
			this.complete(gadgetAvailable, tick);
		}
		else{
			this.complete(gadgetAvailable, null);
		}

	}
}
