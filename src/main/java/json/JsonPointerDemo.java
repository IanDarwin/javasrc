package json;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonNumber;
import javax.json.JsonPointer;
import javax.json.JsonReader;
import javax.json.JsonString;
import javax.json.JsonStructure;

public class JsonPointerDemo {

	public static void main(String[] args) {
		String jsonPerson =
			"{\"firstName\":\"Robin\",\"lsstName\":\"Williams\"," + 
					"\"age\": 63," + 
					"\"id\":0," +
					"\"roles\":[\"Mork\", \"Mrs. Doubtfire\", \"Patch Adams\"]}\n";

		JsonReader rdr =
				Json.createReader(new StringReader(jsonPerson));	 // <1>
		JsonStructure jsonStr = rdr.read();
		rdr.close();

		JsonPointer jsonPointer = Json.createPointer("/firstName");	// <2>
		JsonString jsonString = (JsonString)jsonPointer.getValue(jsonStr);
		String firstName = jsonString.getString();
		System.out.println("Name = " + firstName);
		
		JsonNumber num =											// <3>
				(JsonNumber) Json.createPointer("/age").getValue(jsonStr);
		System.out.println("Age = " + num + "; a " + num.getClass().getName());
		
		jsonPointer = Json.createPointer("/roles/1");				// <4>
		jsonString = (JsonString)jsonPointer.getValue(jsonStr);
		System.out.println("Role = " + jsonString);
	}

}
