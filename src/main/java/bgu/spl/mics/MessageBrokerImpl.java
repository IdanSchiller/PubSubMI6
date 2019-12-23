package bgu.spl.mics;

import bgu.spl.mics.application.subscribers.Moneypenny;

import java.util.LinkedList;
import java.util.List;
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
	public  <T> void subscribeEvent(Class<? extends Event<T>> type, Subscriber m) throws InterruptedException {
		if(!eventsMap.containsKey(type.getName()))
		{
			eventsMap.put(type.getName(),new LinkedBlockingQueue<Subscriber>());
		}
		eventsMap.get(type.getName()).put(m);
	}

	@Override
	public synchronized void subscribeBroadcast(Class<? extends Broadcast> type, Subscriber m) {
		if(!broadcastMap.containsKey(type.getName()))
		{
			broadcastMap.put(type.getName(),new LinkedList<Subscriber>());
		}
		broadcastMap.get(type.getName()).add(m);
	}

	@Override
	public  <T> void complete(Event<T> e, T result) throws InterruptedException {
		String type = e.getClass().getName();
		switch (type){
			case MR:
				((MissionReceivedEvent)e).getFuture().resolve(result);
				break;
			case AA:
				Future<T> currFut = ((AgentsAvailableEvent)e).getFuture();
				currFut.resolve(result);
				break;
			case GA:
				((GadgetAvailableEvent)e).getFuture().resolve(result);
				break;
			case SA:
				((SendAgentsEvent)e).getFuture().resolve(result);
				break;
			case RA:
				((ReleaseAgentsEvent)e).getFuture().resolve(result);
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + type);
		}

	}

	@Override
	public synchronized void sendBroadcast(Broadcast b) {
		if(!broadcastMap.isEmpty()) {
			if(!broadcastMap.get(b.getClass().getName()).isEmpty()) {
				List<Subscriber> list = broadcastMap.get(b.getClass().getName());
				for (int i=0; i<list.size();i++) {
					subsMap.get(list.get(i)).add(b);
				}
			}
		}
	}

	@Override
	public  <T> Future<T> sendEvent(Event<T> e) throws InterruptedException {
		Subscriber currSub;
		Future<T> currFuture = new Future<>();
		String eventClass = e.getClass().getName();
		switch (eventClass){
			case MR:
				currSub = eventsMap.get(eventClass).take();
				subsMap.get(currSub).put(e);
				eventsMap.get(eventClass).put(currSub);
				((MissionReceivedEvent) e).setFuture(currFuture);
				return currFuture;
			case AA:
				currSub = eventsMap.get(eventClass).take();
				subsMap.get(currSub).put(e);
				eventsMap.get(eventClass).put(currSub);
				((AgentsAvailableEvent)e).setFuture(currFuture);
				return currFuture;
			case GA:
				currSub = eventsMap.get(eventClass).take();
				subsMap.get(currSub).put(e);
				eventsMap.get(eventClass).put(currSub);
				((GadgetAvailableEvent)e).setFuture(currFuture);
				return currFuture;
			case SA:
				currSub = eventsMap.get(eventClass).take();
				subsMap.get(currSub).put(e);
				eventsMap.get(eventClass).put(currSub);
				((SendAgentsEvent)e).setFuture(currFuture);
				return currFuture;
			case RA:
				currSub = eventsMap.get(eventClass).take();
				subsMap.get(currSub).put(e);
				eventsMap.get(eventClass).put(currSub);
				((ReleaseAgentsEvent)e).setFuture(currFuture);
				return currFuture;
		}
		return currFuture;
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
