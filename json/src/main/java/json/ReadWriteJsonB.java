package json;

import java.io.IOException;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import domain.Person;

// tag::main[]
public class ReadWriteJsonB {

	 public static void main(String[] args) throws IOException {

		Jsonb jsonb = JsonbBuilder.create();			// <1>

		// Read
		String jsonInput =								// <2>
			"{\"id\":0,\"firstName\":\"Robin\",\"lastName\":\"Williams\"}";
		Person rw = jsonb.fromJson(jsonInput, Person.class);
		System.out.println(rw);

		String result = jsonb.toJson(rw);				// <3>
		System.out.println(result);
	 }
}
// end::main[]
