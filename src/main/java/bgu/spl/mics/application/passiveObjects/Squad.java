package bgu.spl.mics.application.passiveObjects;
import java.util.*;

/**
 * Passive data-object representing a information about an agent in MI6.
 * You must not alter any of the given public methods of this class. 
 * <p>
 * You may add ONLY private fields and methods to this class.
 */
public class Squad {

	private HashMap<String, Agent> AgentsMap;

	private static class SquadHolder{
		private static Squad instance = new Squad();
	}
	private Squad(){
		AgentsMap= new HashMap<>();
	}
	/**
	 * Retrieves the single instance of this class.
	 */
	public static Squad getInstance() {
		return SquadHolder.instance;
	}

	/**
	 * Initializes the squad. This method adds all the agents to the squad.
	 * <p>
	 * @param agents 	Data structure containing all data necessary for initialization
	 * 						of the squad.
	 */
	public void load (Agent[] agents) {
		for(Agent a:agents) {
			this.AgentsMap.put(a.getSerialNumber(), a);
		}
	}

	/**
	 * Releases agents.
	 */
	public void releaseAgents(List<String> serials){
		// TODO Implement this
		Iterator<String> iter = serials.iterator();
		while(iter.hasNext()){
			AgentsMap.get(iter).release();
			iter.next();
		}
	}

	/**
	 * simulates executing a mission by calling sleep.
	 * @param time   milliseconds to sleep
	 */
	public void sendAgents(List<String> serials, int time){
		// TODO Implement this
	}

	/**
	 * acquires an agent, i.e. holds the agent until the caller is done with it
	 * @param serials   the serial numbers of the agents
	 * @return ‘false’ if an agent of serialNumber ‘serial’ is missing, and ‘true’ otherwise
	 */
	public boolean getAgents(List<String> serials) throws InterruptedException {
		Collections.sort(serials);
		for (String agent: serials) {
			if (!this.AgentsMap.containsKey(agent)) return false;
			else if (this.AgentsMap.get(agent).isAvailable()) {
				this.AgentsMap.get(agent).acquire();
			} else {wait(); } // waits till the agent is release (notified by Agent.release() method) and available again
		}
		return true;
	}

	/**
	 * gets the agents names
	 * @param serials the serial numbers of the agents
	 * @return a list of the names of the agents with the specified serials.
	 */
	public List<String> getAgentsNames(List<String> serials){
		List<String> agentsNameList = new LinkedList<>();
		for (String agentSerialNum: serials){
			if (this.AgentsMap.containsKey(agentSerialNum)){
				agentsNameList.add(this.AgentsMap.get(agentSerialNum).getName());
			}
		}
		return  agentsNameList;
	}

}
