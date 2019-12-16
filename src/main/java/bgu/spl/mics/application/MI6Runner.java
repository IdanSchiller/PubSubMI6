package bgu.spl.mics.application;
import bgu.spl.mics.application.passiveObjects.Agent;
import bgu.spl.mics.application.passiveObjects.Inventory;
import bgu.spl.mics.application.passiveObjects.Squad;
import bgu.spl.mics.application.subscribers.Q;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/** This is the Main class of the application. You should parse the input file,
 * create the different instances of the objects, and run the system.
 * In the end, you should output serialized objects.
 */
public class MI6Runner {
    HashMap<String,Agent> agents;

    public static void main(String[] args) {
        // TODO Implement this
        HashMap<String,Agent> agents= new HashMap<String,Agent>();

//        if (args[0] == null) {
//
//        }
        JSONParser parser = new JSONParser();

        Object obj = null;
        try {
            obj = parser.parse(new FileReader("/users/studs/bsc/2020/zivsini/IdeaProjects/SPLass2/src/main/java/bgu/spl/mics/application/input.json"));


            JSONObject jsonObject = (JSONObject) obj;

            String[] inventory = (String[]) jsonObject.get("inventory");

            Inventory inv = new Inventory();
            inv.load(inventory);

            Map squad = (Map) jsonObject.get("squad");

                String name= iter.next().toString();
                String serialNumber= iter.next().toString();



            Agent[] agentsArr = new Agent[squad.size()];

            Squad sq = new Squad();
            sq.load(agentsArr);

            Map services = (Map) jsonObject.get("services");


        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }





    }






}
