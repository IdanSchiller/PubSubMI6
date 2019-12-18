package bgu.spl.mics;

import java.util.LinkedList;
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
	public void subscribeBroadcast(Class<? extends Broadcast> type, Subscriber m) throws InterruptedException {
		// TODO check if type.tostring
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
	public <T> Future<T> sendEvent(Event<T> e) {
		// TODO Auto-generated method stub
		// add Event e to subscriber queue
		String eventClass = e.getClass().toString();
		switch (eventClass){
			case "MissionReceivedEvent":
				Future<T> future =  ((MissionReceivedEvent) e).getFuture();
				currM = mQueue.pop();
				currM.getQueue().push(e);
				mQueue.push(currM);
				return future;
			case "AgentsAvailableEvent":
				currMP = mpQueue.pop();
				currMP.getQueue().push(e);
				mpQueue.push(currMP);

		}
		return null;
	}

	@Override
	public void register(Subscriber m) {
		// TODO Auto-generated method stub
		subsMap.put(m,new LinkedBlockingQueue<>());
	}

	@Override
	public void unregister(Subscriber m) {
		// TODO Auto-generated method stub
		subsMap.remove(m);
		eventsMap.forEachEntry();
	}

	@Override
	public Message awaitMessage(Subscriber m) throws InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	private Message sendMessage(Subscriber m)
	{




		return null;
	}

}
