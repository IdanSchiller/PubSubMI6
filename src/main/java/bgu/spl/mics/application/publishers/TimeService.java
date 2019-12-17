package bgu.spl.mics.application.publishers;

import bgu.spl.mics.*;

import java.util.concurrent.TimeUnit;

/**
 * TimeService is the global system timer There is only one instance of this Publisher.
 * It keeps track of the amount of ticks passed since initialization and notifies
 * all other subscribers about the current time tick using {@link Tick Broadcast}.
 * This class may not hold references for objects which it is not responsible for.
 *
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */
public class TimeService extends Publisher {
	// fields
	private SimplePublisher simpleSub;

	private int ticksLimit;
	private int timeCounter;
	private int currtick;
	private TimeUnit unit;


	public TimeService(int ticksLimits) {
		super("TimeService");
		this.ticksLimit = ticksLimits;
	}


	@Override
	protected void initialize() {
		currtick = 0;

		// TODO Implement this

	}
// TODO ziv
	@Override
	public void run() {
		initialize();
		while (currtick < ticksLimit) {
			// for (every 100 miliseconds){
			Broadcast tick = new TickBroadcast();
			simpleSub.sendBroadcast(tick);
			try {
				Thread.sleep(unit.toMillis(100));
			}
			catch (Exception e){}
			// TODO Implement this
		}


	}
}
