package bgu.spl.mics.application.passiveObjects;

import java.util.List;

/**
 * Passive data-object representing a delivery vehicle of the store.
 * You must not alter any of the given public methods of this class.
 * <p>
 * You may add ONLY private fields and methods to this class.
 */
public class Report {

	private String missionName;
	private int mId;
	private int mpId;
	private List<String> agentsSerialNumbers;
	private List<String> agentsNames;
	private String gadgetName;
	private int qTime;
	private int timeIssued;
	private int timeCreated;
	/**
     * Retrieves the mission name.
     */
	public Report(MissionInfo mission,List<String> agentsNames,int mId,int mpId,int qTime,int timeCreated){
			missionName=mission.getMissionName();
			this.mId=mId;
			this.mpId=mpId;
			this.agentsSerialNumbers=mission.getSerialAgentsNumbers();
			this.agentsNames=agentsNames;
			this.gadgetName=mission.getGadget();
			this.qTime=qTime;
			this.timeIssued=mission.getTimeIssued();
			this.timeCreated=timeCreated;
	}

	public String getMissionName() {
		// TODO Implement this
		return missionName;
	}

	/**
	 * Sets the mission name.
	 */
	public void setMissionName(String missionName) {
		// TODO Implement this
		this.missionName=missionName;
	}

	/**
	 * Retrieves the M's id.
	 */
	public int getMId() {
		// TODO Implement this
		return mId;
	}

	/**
	 * Sets the M's id.
	 */
	public void setM(int mId) {
		// TODO Implement this
		this.mId=mId;
	}

	/**
	 * Retrieves the Moneypenny's id.
	 */
	public int getMoneypennyId() {
		// TODO Implement this
		return mpId;
	}

	/**
	 * Sets the Moneypenny's id.
	 */
	public void setMoneypennyId(int moneyPennyId) {
		// TODO Implement this
		mpId=moneyPennyId;
	}

	/**
	 * Retrieves the serial numbers of the agents.
	 * <p>
	 * @return The serial numbers of the agents.
	 */
	public List<String> getAgentsSerialNumbersNumber() {
		// TODO Implement this
		return agentsSerialNumbers;
	}

	/**
	 * Sets the serial numbers of the agents.
	 */
	public void setAgentsSerialNumbers(List<String> agentsSerialNumbers) {
		// TODO Implement this
		this.agentsSerialNumbers=agentsSerialNumbers;
	}

	/**
	 * Retrieves the agents names.
	 * <p>
	 * @return The agents names.
	 */
	public List<String> getAgentsNames() {
		// TODO Implement this
		return agentsNames;
	}

	/**
	 * Sets the agents names.
	 */
	public void setAgentsNames(List<String> agentsNames) {
		// TODO Implement this
		agentsNames=agentsNames;
	}

	/**
	 * Retrieves the name of the gadget.
	 * <p>
	 * @return the name of the gadget.
	 */
	public String getGadgetName() {
		// TODO Implement this
		return gadgetName;
	}

	/**
	 * Sets the name of the gadget.
	 */
	public void setGadgetName(String gadgetName) {
		// TODO Implement this
		gadgetName=gadgetName;
	}

	/**
	 * Retrieves the time-tick in which Q Received the GadgetAvailableEvent for that mission.
	 */
	public int getQTime() {
		// TODO Implement this
		return qTime;
	}

	/**
	 * Sets the time-tick in which Q Received the GadgetAvailableEvent for that mission.
	 */
	public void setQTime(int qTime) {
		// TODO Implement this
		this.qTime=qTime;
	}

	/**
	 * Retrieves the time when the mission was sent by an Intelligence Publisher.
	 */
	public int getTimeIssued() {
		// TODO Implement this
		return timeIssued;
	}

	/**
	 * Sets the time when the mission was sent by an Intelligence Publisher.
	 */
	public void setTimeIssued(int timeIssued) {
		// TODO Implement this
		this.timeIssued=timeIssued;
	}

	/**
	 * Retrieves the time-tick when the report has been created.
	 */
	public int getTimeCreated() {
		// TODO Implement this
		return timeCreated;
	}

	/**
	 * Sets the time-tick when the report has been created.
	 */
	public void setTimeCreated(int timeCreated) {
		// TODO Implement this
		this.timeCreated=timeCreated;
	}
}
