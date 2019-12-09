package bgu.spl.mics.application;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/** This is the Main class of the application. You should parse the input file,
 * create the different instances of the objects, and run the system.
 * In the end, you should output serialized objects.
 */
public class MI6Runner {
    public static void main(String[] args) {
        // TODO Implement this
        if(args[0]==null)
        {

        }
        Object obj = new JSONParser().parse(new FileReader(args[0]));
        JSONObject jo = (JSONObject) obj;

    }
}
