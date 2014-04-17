package json;

import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

// BEGIN main
public class SoftwareParseOrgJson {
    final static String FILE_NAME = "/json/softwareinfo.json";

    public static void main(String[] args) throws Exception {
        
        InputStream jsonInput =
            SoftwareParseOrgJson.class.getResourceAsStream(FILE_NAME);
        if (jsonInput == null) {
            throw new NullPointerException("can't find" + FILE_NAME);
        }
        JSONObject obj = new JSONObject(new JSONTokener(jsonInput));      // <1>
        System.out.println("Software Name: " + obj.getString("name"));    // <2>
        System.out.println("Version: " + obj.getString("version"));
        System.out.println("Description: " + obj.getString("description"));
        System.out.println("Class: " + obj.getString("className"));
        JSONArray contribs = obj.getJSONArray("contributors");            // <3>
        for (int i = 0; i < contribs.length(); i++) {                     // <4>
            System.out.println("Contributor Name: " + contribs.get(i));
        }
    }

}
// END main
