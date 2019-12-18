package bgu.spl.mics;

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
	private ConcurrentHashMap<Subscriber, LinkedBlockingQueue<Message>> subsMap;
	private ConcurrentHashMap<String, LinkedBlockingQueue<Subscriber>> eventsMap;
	private ConcurrentHashMap<String, LinkedList<Subscriber>> broadcastList;
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
		subsMap = new ConcurrentHashMap<Subscriber, LinkedBlockingQueue<Message>>();
		eventsMap = new ConcurrentHashMap<String, LinkedBlockingQueue<Subscriber>>();
		broadcastList = new ConcurrentHashMap<String, LinkedList<Subscriber>>();
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
		// TODO Auto-generated method stub
		eventsMap.get(type.getName()).put(m);
	}

	@Override
	public void subscribeBroadcast(Class<? extends Broadcast> type, Subscriber m) {
		// TODO Auto-generated method stub
		broadcastList.get(type.getName()).add(m);
	}

	@Override
	public <T> void complete(Event<T> e, T result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendBroadcast(Broadcast b) {
		// TODO Auto-generated method stub
		for (Subscriber s: broadcastList.get(b.getClass().getName()))
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
		// TODO Auto-generated method stub
		subsMap.remove(m);
		for(String s: eventsMap.keySet()){
			eventsMap.get(s).remove(m);
		}
		for(String s: broadcastList.keySet()){
			broadcastList.get(s).remove(m);
		}
	}

	@Override
	public Message awaitMessage(Subscriber m) throws InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}

}
