package bgu.spl.mics.application.publishers;

import bgu.spl.mics.*;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TimeService is the global system timer There is only one instance of this Publisher.
 * It keeps track of the amount of ticks passed since initialization and notifies
 * all other subscribers about the current time tick using {@link //Tick Broadcast}.
 * This class may not hold references for objects which it is not responsible for.
 *
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */
public class TimeService extends Publisher {
	// fields
	private SimplePublisher simpleSub;
	private TimerTask task;
	private Timer timer;
	private Long ticksLimit;
	private AtomicInteger currTime;
	private TimeUnit unit;


	public TimeService(Long ticksLimits) {
		super("TimeService");
		this.ticksLimit = ticksLimits;
		task= new TimerTask() {
			@Override
			public void run() {
				currTime.getAndIncrement();
				Broadcast tick = new TickBroadcast(currTime.get());
				simpleSub.sendBroadcast(tick);
			}
		};
		timer= new Timer("Timer");

	}


	@Override
	protected void initialize() {
		currTime = new AtomicInteger();
	}
	// TODO ziv
	@Override
	public void run() {
		initialize();
		while (currTime.get() < ticksLimit) {
			timer.schedule(task,100);
		}
		timer.cancel();

	}
}
