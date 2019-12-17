package bgu.spl.mics.application.subscribers;

import bgu.spl.mics.*;
import bgu.spl.mics.application.passiveObjects.Agent;
import bgu.spl.mics.application.passiveObjects.Diary;
import bgu.spl.mics.application.passiveObjects.Report;
import javafx.util.Pair;

import java.util.List;
import java.util.Map;

/**
 * M handles ReadyEvent - fills a report and sends agents to mission.
 *
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */
public class M extends Subscriber {
	private int tickCounter;
	private int thicksLimit;
	private Integer id;
	private Pair<List<Agent>,Integer> MAEFT; //=Mission Available Event Future Type

	public M(int ticksLimit) {
		super("Change_This_Name");
		this.thicksLimit=ticksLimit;
		// TODO Implement this
	}

	@Override
	protected void initialize() {
		tickCounter=0;
		MessageBrokerImpl.getInstance().register(this);
		Callback<TickBroadcast> tickBroadcastCallback = (TickBroadcast tickBroadcast) -> tickCounter++;
		this.subscribeBroadcast(TickBroadcast.class,tickBroadcastCallback);
		Callback<MissionReceivedEvent> MREcallBack = missionEvent -> {
			Future<Pair<Boolean,Integer>> gadgFuture = null; // so it will be out of "if"'s scope and can be used in the Report constructor.
			List<String> serialAgentsList = missionEvent.getMission().getSerialAgentsNumbers();
			Event<Pair<List<Agent>,Integer>> agentsEvent = new AgentsAvailableEvent<>(serialAgentsList);
			Future<Pair<List<Agent>,Integer>> agentsFuture = this.getSimplePublisher().sendEvent(agentsEvent);
			Pair<List<Agent>,Integer> agentsFutureResault = agentsFuture.get();
			Boolean agentsIsDone = agentsFuture.isDone();
			if (agentsIsDone) {
				String gadget = missionEvent.getMission().getGadget();
				Event<Pair<Boolean,Integer>> gadgetEvent = new GadgetAvailableEvent(gadget);
				gadgFuture = this.getSimplePublisher().sendEvent(gadgetEvent);
				Pair<Boolean,Integer> gadgFutureResault = gadgFuture.get();
				Boolean gadgetIsDone = gadgFuture.isDone();
				if (gadgetIsDone && missionEvent.getMission().getTimeExpired()<tickCounter) {
					Event<Boolean> sendAgents= new SendAgentsEvent<>(serialAgentsList,missionEvent.getMission().getDuration());
					Future<Boolean> sendAgentsFut = this.getSimplePublisher().sendEvent(sendAgents);
				}
				if (gadgetIsDone && tickCounter<=missionEvent.getMission().getTimeExpired()){
					Event<Boolean> releaseAgents = new ReleaseAgentsEvent<>(serialAgentsList);
					Future<Boolean> releaseAgentsFut = this.getSimplePublisher().sendEvent(releaseAgents);
				}
			}
			missionEvent.getFuture().resolve("resolved");
			Report r = new Report(missionEvent.getMission(),agentsFutureResault.getKey(),id,agentsFutureResault.getValue(),gadgFuture.get().getValue(),this.tickCounter);
			Diary.getInstance().addReport(r);
		};
		this.subscribeEvent(MissionReceivedEvent.class,MREcallBack);

	}

	public Integer getId(){ return id; }
	public int getTickCounter(){return tickCounter;}


}
