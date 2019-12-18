package bgu.spl.mics;

import bgu.spl.mics.application.subscribers.Moneypenny;

import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * The {@link MessageBrokerImpl class is the implementation of the MessageBroker interface.
 * Write your implementation here!
 * Only private fields and methods can be added to this class.
 */
public class MessageBrokerImpl implements MessageBroker {
	private Map<Subscriber, LinkedBlockingQueue<Message>> subsMap;
	private Map<String, LinkedBlockingQueue<Subscriber>> eventsMap;
	private Map<String, LinkedList<Subscriber>> broadcastMap;
	private final String MR = "bgu.spl.mics.MissionReceivedEvent";
	private final String AA = "bgu.spl.mics.AgentsAvailableEvent";
	private final String GA = "bgu.spl.mics.GadgetAvailableEvent";
	private final String SA = "bgu.spl.mics.SendAgentsEvent";
	private final String RA = "bgu.spl.mics.ReleaseAgentsEvent";
	private final String TB = "bgu.spl.mics.TickBroadcast";



	private static class MessageBrokerImplHolder{
		private static MessageBrokerImpl instance = new MessageBrokerImpl();
	}

	private MessageBrokerImpl(){
		// innitiate fields
		subsMap = new ConcurrentHashMap<>();
		eventsMap = new ConcurrentHashMap<>();
		broadcastMap = new ConcurrentHashMap<>();
	}
	/**
	 * Retrieves the single instance of this class.
	 */
	public static MessageBroker getInstance() {
		//TODO: Implement this
		return MessageBrokerImplHolder.instance;
	}

	@Override
	public <T> void subscribeEvent(Class<? extends Event<T>> type, Subscriber m) throws InterruptedException {
		eventsMap.get(type.getName()).put(m);
	}

	@Override
	public void subscribeBroadcast(Class<? extends Broadcast> type, Subscriber m) {
		broadcastMap.get(type.getName()).add(m);
	}

	@Override
	public <T> void complete(Event<T> e, T result) {
		String type = e.getClass().getName();
		switch (type){
			case MR:
				((MissionReceivedEvent)e).getFuture().resolve(result);
			case AA:
				((AgentsAvailableEvent)e).getFuture().resolve(result);
			case GA:
				((GadgetAvailableEvent)e).getFuture().resolve(result);
			case SA:
				((SendAgentsEvent)e).getFuture().resolve(result);
			case RA:
				((ReleaseAgentsEvent)e).getFuture().resolve(result);
		}

	}

	@Override
	public void sendBroadcast(Broadcast b) {
		for (Subscriber s: broadcastMap.get(b.getClass().getName()))
		{
			subsMap.get(s).add(b);
		}
	}

	@Override
	public <T> Future<T> sendEvent(Event<T> e) throws InterruptedException {
		Subscriber currSub;
		Future<T> currFutue = new Future<>();
		String eventClass = e.getClass().getName();
		switch (eventClass){
			case MR:
				currFutue =  ((MissionReceivedEvent) e).getFuture();
				currSub = eventsMap.get(eventClass).take();
				subsMap.get(currSub).put(e);
				eventsMap.get(eventClass).put(currSub);
			case AA:
				currFutue = ((AgentsAvailableEvent)e).getFuture();
				currSub = eventsMap.get(eventClass).take();
				subsMap.get(currSub).put(e);
				eventsMap.get(eventClass).put(currSub);
			case GA:
				currFutue = ((GadgetAvailableEvent)e).getFuture();
				currSub = eventsMap.get(eventClass).take();
				subsMap.get(currSub).put(e);
				eventsMap.get(eventClass).put(currSub);
			case SA:
				currFutue = ((SendAgentsEvent)e).getFuture();
				currSub = eventsMap.get(eventClass).take();
				subsMap.get(currSub).put(e);
				eventsMap.get(eventClass).put(currSub);
			case RA:
				currFutue = ((ReleaseAgentsEvent)e).getFuture();
				currSub = eventsMap.get(eventClass).take();
				subsMap.get(currSub).put(e);
				eventsMap.get(eventClass).put(currSub);
		}
		return currFutue;
	}

	@Override
	public void register(Subscriber m) {
		subsMap.put(m,new LinkedBlockingQueue<>());
	}

	@Override
	public void unregister(Subscriber m) {
		subsMap.remove(m);
		for(String s: eventsMap.keySet()){
			eventsMap.get(s).remove(m);
		}
		for(String s: broadcastMap.keySet()){
			broadcastMap.get(s).remove(m);
		}
	}

	@Override
	public Message awaitMessage(Subscriber m) throws InterruptedException {
		return this.subsMap.get(m).take();
	}

}
