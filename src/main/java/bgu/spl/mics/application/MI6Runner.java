package bgu.spl.mics.application;
import bgu.spl.mics.AgentsAvailableEvent;
import bgu.spl.mics.MissionReceivedEvent;
import bgu.spl.mics.application.passiveObjects.Agent;
import bgu.spl.mics.application.passiveObjects.Inventory;
import bgu.spl.mics.application.passiveObjects.MissionInfo;
import bgu.spl.mics.application.passiveObjects.Squad;
import bgu.spl.mics.application.subscribers.Q;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/** This is the Main class of the application. You should parse the input file,
 * create the different instances of the objects, and run the system.
 * In the end, you should output serialized objects.
 */
public class MI6Runner {


    public static void main(String[] args) throws FileNotFoundException {
        // TODO Implement this


//        if (args[0] == null) {
//
//        }
        Gson gson = new Gson();
        FileReader FR = new FileReader("/users/studs/bsc/2020/zivsini/IdeaProjects/SPLass2/src/main/java/bgu/spl/mics/application/input.json");
        List<Thread> threads = new LinkedList<>();


        try {
            JsonReader read = new JsonReader(FR);
            JsonElement element = JsonParser.parseReader(read);

            /** inventory*/
            Inventory inventory = Inventory.getInstance();
            JsonArray inv = element.getAsJsonObject().get("inventory").getAsJsonArray();
            String[] inventoryToLoad = new String[inv.size()];
            for(int i=0;i<inventoryToLoad.length;i++)
            {
                inventoryToLoad[i]= inv.get(i).toString();
            }

            /** services */
            JsonObject ser = element.getAsJsonObject().get("services").getAsJsonObject();
            Integer m = ser.getAsJsonObject().get("M").getAsInt();
            Integer mp = ser.getAsJsonObject().get("Moneypenny").getAsInt();






            /** squad */
            Squad squad = Squad.getInstance();
            JsonArray sqd = element.getAsJsonObject().get("squad").getAsJsonArray();
            Agent[] agents = new Agent[sqd.size()];
            int i=0;
            for (JsonElement agentDetails: sqd){
                Agent a = new Agent();
                a.setName(agentDetails.getAsJsonObject().get("name").getAsString());
                a.setSerialNumber(agentDetails.getAsJsonObject().get("serialNumber").getAsString());
                agents[i]=a;
                i++;
            }
            Squad.getInstance().load(agents);







        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }


}
