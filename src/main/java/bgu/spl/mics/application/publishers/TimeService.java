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
//				Broadcast tick = new TickBroadcast(currTime.get());
				TimeService.super.getSimplePublisher().sendBroadcast(new TickBroadcast(currTime.get()));
			}
		};
		timer= new Timer("Timer");

	}


	@Override
	protected void initialize() throws InterruptedException {
		currTime = new AtomicInteger();
		Thread.currentThread().setName(getName());
	}
	// TODO ziv
	@Override
	public void run() {
		try {
			initialize();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while (currTime.get()<ticksLimit)
		{
			currTime.getAndIncrement();
			try {
			//	System.out.println("sending current time: " + currTime.toString());
				TimerTask newTask = NewTask(currTime);
				newTask.run();
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println("did not send "+currTime.get());
			}
		}
		timer.cancel();

	}

//		boolean terminate=false;
//		while (!terminate){
//			timer.schedule(NewTask(),100);
//			TimerTask newTask = NewTask();
//			if (currTime.get()<ticksLimit)
//				try {
//					currTime.getAndIncrement();
//					timer.scheduleAtFixedRate(NewTask(currTime), 0, 100);
//					System.out.println("sending current time: " +currTime.toString());
//				} catch (Exception e) {
//					final int CUURTIME = currTime.get();
//					System.out.println("timer task problem " + CUURTIME + " " + e);
//					currTime.getAndIncrement();
//				}
//			if (currTime.get()==ticksLimit) {
//				terminate = true;
//			}
//		}
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

	private TimerTask NewTask(AtomicInteger currentTime) {
		return new TimerTask() {
			@Override
			public void run() {
				//	currTime.getAndIncrement();
				if (currentTime.get()!=ticksLimit+1){
					TimeService.super.getSimplePublisher().sendBroadcast(new TickBroadcast(currentTime.get()));
					 System.out.println("sent "+currentTime.get());
				}else {
					System.out.println("out of bound");
				}
			}
		};

	}
}
