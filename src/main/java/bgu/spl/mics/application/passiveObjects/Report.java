package bgu.spl.mics.application.passiveObjects;

import java.util.List;

/**
 * Passive data-object representing a delivery vehicle of the store.
 * You must not alter any of the given public methods of this class.
 * <p>
 * You may add ONLY private fields and methods to this class.
 */
public class Report {

	private String MissionName;
	private int M;
	private int MP;
	private List<String> AgentsSerialNumbersNumber;
	private List<String> AgentsNames;
	private String GadgetName;
	private int Qtime;
	private int TimeIssued;
	private int TimeCreated;
	/**
     * Retrieves the mission name.
     */
	public String getMissionName() {
		// TODO Implement this
		return MissionName;
	}

	/**
	 * Sets the mission name.
	 */
	public void setMissionName(String missionName) {
		// TODO Implement this
		MissionName=missionName;
	}

	/**
	 * Retrieves the M's id.
	 */
	public int getM() {
		// TODO Implement this
		return M;
	}

	/**
	 * Sets the M's id.
	 */
	public void setM(int m) {
		// TODO Implement this
		M=m;
	}

	/**
	 * Retrieves the Moneypenny's id.
	 */
	public int getMoneypenny() {
		// TODO Implement this
		return MP;
	}

	/**
	 * Sets the Moneypenny's id.
	 */
	public void setMoneypenny(int moneypenny) {
		// TODO Implement this
		MP=moneypenny;
	}

	/**
	 * Retrieves the serial numbers of the agents.
	 * <p>
	 * @return The serial numbers of the agents.
	 */
	public List<String> getAgentsSerialNumbersNumber() {
		// TODO Implement this
		return AgentsSerialNumbersNumber;
	}

	/**
	 * Sets the serial numbers of the agents.
	 */
	public void setAgentsSerialNumbersNumber(List<String> agentsSerialNumbersNumber) {
		// TODO Implement this
		AgentsSerialNumbersNumber=agentsSerialNumbersNumber;
	}

	/**
	 * Retrieves the agents names.
	 * <p>
	 * @return The agents names.
	 */
	public List<String> getAgentsNames() {
		// TODO Implement this
		return AgentsNames;
	}

	/**
	 * Sets the agents names.
	 */
	public void setAgentsNames(List<String> agentsNames) {
		// TODO Implement this
		AgentsNames=agentsNames;
	}

	/**
	 * Retrieves the name of the gadget.
	 * <p>
	 * @return the name of the gadget.
	 */
	public String getGadgetName() {
		// TODO Implement this
		return GadgetName;
	}

	/**
	 * Sets the name of the gadget.
	 */
	public void setGadgetName(String gadgetName) {
		// TODO Implement this
		GadgetName=gadgetName;
	}

	/**
	 * Retrieves the time-tick in which Q Received the GadgetAvailableEvent for that mission.
	 */
	public int getQTime() {
		// TODO Implement this
		return Qtime;
	}

	/**
	 * Sets the time-tick in which Q Received the GadgetAvailableEvent for that mission.
	 */
	public void setQTime(int qTime) {
		// TODO Implement this
		Qtime=qTime;
	}

	/**
	 * Retrieves the time when the mission was sent by an Intelligence Publisher.
	 */
	public int getTimeIssued() {
		// TODO Implement this
		return TimeIssued;
	}

	/**
	 * Sets the time when the mission was sent by an Intelligence Publisher.
	 */
	public void setTimeIssued(int timeIssued) {
		// TODO Implement this
		TimeIssued=timeIssued;
	}

	/**
	 * Retrieves the time-tick when the report has been created.
	 */
	public int getTimeCreated() {
		// TODO Implement this
		return TimeCreated;
	}

	/**
	 * Sets the time-tick when the report has been created.
	 */
	public void setTimeCreated(int timeCreated) {
		// TODO Implement this
		TimeCreated=timeCreated;
	}
}
