package bgu.spl.mics.application.subscribers;

import bgu.spl.mics.*;
import bgu.spl.mics.application.passiveObjects.Diary;
import bgu.spl.mics.application.passiveObjects.Report;
import org.javatuples.Pair;

import java.util.List;

/**
 * M handles ReadyEvent - fills a report and sends agents to mission.
 *
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */
public class M extends Subscriber {
	private int tickCounter;
	private int ticksLimit;
	private Integer id;

	public M(int ticksLimit,Integer id) {
		super("M "+id.toString());
		this.ticksLimit =ticksLimit;
		id=id;
		// TODO Implement this
	}

	@Override
	protected void initialize() throws InterruptedException {
		Thread.currentThread().setName(getName());
		tickCounter = 0;
		MessageBrokerImpl.getInstance().register(this);
		Callback<TickBroadcast> tickBroadcastCallback = (TickBroadcast tickBroadcast) ->{
			this.tickCounter=tickBroadcast.getTick();
			if (tickCounter== ticksLimit)
			{
				Diary.getInstance().printToFile("/users/studs/bsc/2020/zivsini/IdeaProjects/SPLass2/src/main/java/bgu/spl/mics/application/dairy.json");
				super.terminate();
			}
		};
		this.subscribeBroadcast(TickBroadcast.class, tickBroadcastCallback);
		Callback<MissionReceivedEvent> MREcallBack = missionEvent -> {
			Future<Integer> gadgFuture = null; // so it will be out of "if"'s scope and can be used in the Report constructor.
			List<String> serialAgentsList = missionEvent.getMission().getSerialAgentsNumbers();
			Event<Pair<List<String>, Integer>> agentsEvent = new AgentsAvailableEvent<>(serialAgentsList);
			Future<Pair<List<String>, Integer>> agentsFuture = this.getSimplePublisher().sendEvent(agentsEvent);
			Pair<List<String>, Integer> agentsFutureResult = agentsFuture.get();
			System.out.println("null or not");
			if (agentsFutureResult.getValue1() == null) { // not all agents exist in the squad
				Diary.getInstance().incrementTotal();
			} else { // all agents exist in the squad
				String gadget = missionEvent.getMission().getGadget();
				Event<Integer> gadgetEvent = new GadgetAvailableEvent(gadget);
				gadgFuture = this.getSimplePublisher().sendEvent(gadgetEvent);
				Integer gadgFutureResault = gadgFuture.get();
				if (gadgFuture == null) { // gadget doesn't exist in the inventory
					Diary.getInstance().incrementTotal();
				} else { // gadget exists in the inventory
					if (missionEvent.getMission().getTimeExpired() >= tickCounter) { // mission's time expired
						Event<Boolean> releaseAgents = new ReleaseAgentsEvent<>(serialAgentsList);
						Future<Boolean> releaseAgentsFut = this.getSimplePublisher().sendEvent(releaseAgents);
						Diary.getInstance().incrementTotal();
					} else { // all conditions to execute the mission are met and the agents are sent
						Event<Boolean> sendAgents = new SendAgentsEvent<>(serialAgentsList, missionEvent.getMission().getDuration());
						Future<Boolean> sendAgentsFut = this.getSimplePublisher().sendEvent(sendAgents);
						Report r = new Report(missionEvent.getMission(), agentsFutureResult.getValue0(), id, agentsFutureResult.getValue1(), gadgFuture.get(), this.tickCounter);
						Diary.getInstance().addReport(r);
					}
				}
			}
			this.complete(missionEvent,true);

		};
		this.subscribeEvent(MissionReceivedEvent.class, MREcallBack);
	}

	public Integer getId(){ return id; }
	public int getTickCounter(){return tickCounter;}


}
