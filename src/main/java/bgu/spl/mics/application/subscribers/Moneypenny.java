package bgu.spl.mics.application.subscribers;

import bgu.spl.mics.*;
import bgu.spl.mics.application.passiveObjects.Squad;
import org.javatuples.Pair;

import java.util.List;

/**
 * Only this type of Subscriber can access the squad.
 * Three are several Moneypenny-instances - each of them holds a unique serial number that will later be printed on the report.
 *
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */
public class Moneypenny extends Subscriber {
	private int tickCounter;
	private Pair<List<String>,Integer> result;
	private Integer id;
	private long ticksLimit;

	public Moneypenny(Integer id, long ticksLimit) {
		super("MoneyPenny "+id.toString());
		// TODO Implement this
		this.id=id;
		this.ticksLimit=ticksLimit;
	}

	@Override
	protected void initialize() throws InterruptedException {
		Thread.currentThread().setName(getName());
		tickCounter=0;
		MessageBrokerImpl.getInstance().register(this);
		Callback<TickBroadcast> tickBroadcastCallback = (TickBroadcast tickBroadcast) -> {
			this.tickCounter=tickBroadcast.getTick();
			// System.out.println("MoneyPenny "+id+" received tick number: "+tickBroadcast.getTick());
			if (tickCounter== ticksLimit)
			{
				super.terminate();
			}
		};
		this.subscribeBroadcast(TickBroadcast.class,tickBroadcastCallback);
		Callback<AgentsAvailableEvent> agentsCallBack = (agentsEvent) -> {
			System.out.println("MP"+id+"--GOT:"+agentsEvent.getAgentsSerialNum()+"<<M"+agentsEvent.getmId()+"--AT"+tickCounter);
			Boolean allAgentsExist = Squad.getInstance().getAgents(agentsEvent.getAgentsSerialNum());
			if(!allAgentsExist){
				this.complete(agentsEvent,null);
			}else{
				result = new Pair<>(Squad.getInstance().getAgentsNames(agentsEvent.getAgentsSerialNum()),id);
				this.complete(agentsEvent, result);
				System.out.println("MP"+id+"--FINISHED:"+agentsEvent.getAgentsSerialNum()+" with result: "+agentsEvent.getFuture().get()+ "--AT"+tickCounter);
			}
		};
		if (this.id%2==0) {
			this.subscribeEvent(AgentsAvailableEvent.class, agentsCallBack);
		}

			Callback<SendAgentsEvent> sendAgentsCB = (sendAgentsEvent)->{
				System.out.println("MP"+id+"--GOT SEND:"+sendAgentsEvent.getSerials()+"--AT:"+tickCounter);
				Squad.getInstance().sendAgents(sendAgentsEvent.getSerials(),sendAgentsEvent.getDuration());
			complete(sendAgentsEvent,true);
				System.out.println("MP"+id+"--FINISHED SENT:"+sendAgentsEvent.getSerials()+"--AT:"+tickCounter);

			};
		if (this.id%2!=0) {
			subscribeEvent(SendAgentsEvent.class, sendAgentsCB);
		}
		Callback<ReleaseAgentsEvent> releaseAgentsCB = releaseAgentsEvent -> {
			System.out.println("MP"+id+"--GOT RELEASE: "+releaseAgentsEvent.getSerials()+"--AT: "+tickCounter);
			Squad.getInstance().releaseAgents(releaseAgentsEvent.getSerials());
			complete(releaseAgentsEvent,true);
			System.out.println("MP"+id+"--FINISH RELEASE: "+releaseAgentsEvent.getSerials()+"--AT: "+tickCounter);
		};
		if (this.id%2==0) {
			subscribeEvent(ReleaseAgentsEvent.class, releaseAgentsCB);
		}
	};
	public Integer getId() {
		return id;
	}

}
