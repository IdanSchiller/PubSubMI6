package bgu.spl.mics.application.passiveObjects;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import netscape.javascript.JSObject;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Passive object representing the diary where all reports are stored.
 * <p>
 * This class must be implemented safely as a thread-safe singleton.
 * You must not alter any of the given public methods of this class.
 * <p>
 * You can add ONLY private fields and methods to this class as you see fit.
 */
public class Diary {
	private List<Report> reports;
	private AtomicInteger total;

	private static class dairyHolder{
		private static Diary instance=new Diary();
	}
	private Diary(){
		reports = new ArrayList<Report>();
		total= new AtomicInteger();
	}
	/**
	 * Retrieves the single instance of this class.
	 */
	public static Diary getInstance() {
		return dairyHolder.instance;
	}

	public List<Report> getReports() {
		return reports;
	}

	/**
	 * adds a report to the diary
	 * @param reportToAdd - the report to add
	 */
	public void addReport(Report reportToAdd){
		//TODO: Implement this
		reports.add(reportToAdd);
		incrementTotal();
	}

	/**
	 *
	 * <p>
	 * Prints to a file name @filename a serialized object List<Report> which is a
	 * List of all the reports in the diary.
	 * This method is called by the main method in order to generate the output.
	 */
	public void printToFile(String filename) {

		JsonObject obj = new JsonObject();
		obj.add("Diary.total", total.get());
		JsonArray arr = new JsonArray();
		obj.add("Reports",arr);

		for (Report r : reports) {
			JsonObject j = new JsonObject();
			j.add("MissionName", r.getMissionName());
			j.add("M", r.getMId());
			j.add("MoneyPenny", r.getMoneypennyId());
			j.add("agentsSerialNumbers", r.getAgentsSerialNumbersNumber());
			j.add("agentsName", r.getAgentsNames());
			j.add("gadgetName", r.getGadgetName());
			j.add("timeIssued", r.getTimeIssued());
			j.add("QTime", r.getQTime());
			j.add("timeCreated", r.getTimeCreated());
			obj.getAsJsonObject("Reports").add(j);
		}
		FileWriter file = new FileWriter(filename);
		try{
			file.write(obj.toJSONString());
		}
		catch (IOException e){
			e.printStackTrace();
		}
		finally {
			file.flush();
			file.close();
		}
	}

	/**
	 * Gets the total number of received missions (executed / aborted) be all the M-instances.
	 * @return the total number of received missions (executed / aborted) be all the M-instances.
	 */
	public int getTotal(){
		//TODO: Implement this
		return total.get();
	}

	public void incrementTotal(){
		total.getAndIncrement();
	}


}
