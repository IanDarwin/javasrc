package json;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonNumber;
import jakarta.json.JsonPointer;
import jakarta.json.JsonReader;
import jakarta.json.JsonString;
import jakarta.json.JsonStructure;

/**
 * Show some parts of the JSON Pointer API, part of Java's JSON Processing.
 * @author Ian Darwin
 */
//tag::main[]
public class JsonPointerDemo {

	public static void main(String[] args) {
		String jsonPerson = """
			{"firstName":"Robin","lastName":"Williams",
				"age": 63,
				"id":0,
				"roles":["Mork", "Mrs. Doubtfire", "Patch Adams"]
			}
		""";

		System.out.println("Input: " + jsonPerson);
		
		JsonReader rdr =
				Json.createReader(new StringReader(jsonPerson));	 // <1>
		JsonStructure jsonStr = rdr.read();
		rdr.close();

		JsonPointer jsonPointer = Json.createPointer("/firstName");	 // <2>
		JsonString jsonString = (JsonString)jsonPointer.getValue(jsonStr);
		String firstName = jsonString.getString();
		System.out.println("/firstName => " + firstName);
		
		JsonNumber num =											// <3>
				(JsonNumber) Json.createPointer("/age").getValue(jsonStr);
		System.out.println("/age => " + num + "; a " + num.getClass().getName());
		
		jsonPointer = Json.createPointer("/roles");					// <4>
		JsonArray roles = (JsonArray) jsonPointer.getValue(jsonStr);		
		System.out.println("/roles => " + roles);
		System.out.println("JsonArray roles.get(1) => " + roles.get(1));
		
		jsonPointer = Json.createPointer("/roles/1");				// <5>
		jsonString = (JsonString)jsonPointer.getValue(jsonStr);
		System.out.println("/roles/1 => " + jsonString);
	}
}
//end::main[]
