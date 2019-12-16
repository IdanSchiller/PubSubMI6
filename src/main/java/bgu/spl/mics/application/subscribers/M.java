package bgu.spl.mics.application.subscribers;

import bgu.spl.mics.*;
import bgu.spl.mics.application.passiveObjects.Diary;
import bgu.spl.mics.application.passiveObjects.Report;

import java.util.List;

/**
 * M handles ReadyEvent - fills a report and sends agents to mission.
 *
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */
public class M extends Subscriber {
	private int tickCounter;
	private int thicksLimit;
	private SimplePublisher simPub;

	public M(int ticksLimit) {
		super("Change_This_Name");
		this.thicksLimit=ticksLimit;
		// TODO Implement this
	}

	@Override
	protected void initialize() {
		tickCounter=0;
		simPub = new SimplePublisher();
		MessageBrokerImpl.getInstance().register(this);
		Callback<TickBroadcast> tickBroadcastCallback = (TickBroadcast tickBroadcast) -> tickCounter++;
		this.subscribeBroadcast(TickBroadcast.class,tickBroadcastCallback);
		Callback<MissionReceivedEvent> MREcallBack = missionEvent -> {
			List<String> serialAgentsList = missionEvent.getMission().getSerialAgentsNumbers();
			Event<Boolean> agentsEvent = new AgentsAvailableEvent<>(serialAgentsList);
			Future<Boolean> agentsFuture = simPub.sendEvent(agentsEvent);
			Boolean agentsIsDone = agentsFuture.get();
			if (agentsIsDone) {
				String gadget = missionEvent.getMission().getGadget();
				Event<Boolean> gadgetEvent = new GadgetAvailableEvent(gadget);
				Future<Boolean> gadgFuture = simPub.sendEvent(gadgetEvent);
				Boolean gadgetIsDone = gadgFuture.get();
				if (gadgetIsDone && missionEvent.getMission().getTimeExpired()<tickCounter) {


				}
			}

			missionEvent.getFuture().resolve("resolved");
			Report r = new Report();
			Diary.getInstance().addReport(r);
		};
		this.subscribeEvent(MissionReceivedEvent.class,MREcallBack);

	}

}
