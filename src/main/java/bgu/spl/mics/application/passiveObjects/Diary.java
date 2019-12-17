package bgu.spl.mics.application.passiveObjects;

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
	private List<Report> Reports;
	private AtomicInteger total;

	private static class dairyHolder{
		private static Diary instance=new Diary();

	}
	private Diary(){
		Reports = new ArrayList<Report>();
		total= new AtomicInteger();
	}
	/**
	 * Retrieves the single instance of this class.
	 */
	public static Diary getInstance() {
		//TODO: Implement this
		return null;
	}

	public List<Report> getReports() {
		return Reports;
	}

	/**
	 * adds a report to the diary
	 * @param reportToAdd - the report to add
	 */
	public void addReport(Report reportToAdd){
		//TODO: Implement this
		Reports.add(reportToAdd);
		incrementTotal();
	}

	/**
	 *
	 * <p>
	 * Prints to a file name @filename a serialized object List<Report> which is a
	 * List of all the reports in the diary.
	 * This method is called by the main method in order to generate the output.
	 */
	public void printToFile(String filename){
		//TODO: Implement this
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
