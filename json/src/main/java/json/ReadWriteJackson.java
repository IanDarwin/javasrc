package json;

// tag::main[]
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReadWriteJackson {


    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();                // <1>
        
        String jsonInput =                                       // <2>
                "{\"id\":0,\"firstName\":\"Robin\",\"lastName\":\"Wilson\"}";
        Person q = mapper.readValue(jsonInput, Person.class);
        System.out.println("Read and parsed Person from JSON: " + q);
        
        Person p = new Person(0, "Roger", "Rabbit");             // <3>
        System.out.print("Person object " + p +" as JSON = ");
        mapper.writeValue(System.out, p);
    }

    record Person(int id, String firstName, String lastName){    // <4>
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			if (firstName != null)
				sb.append(firstName).append(' ');
			if (lastName != null)
				sb.append(lastName);
			if (sb.length() == 0)
				sb.append("NO NAME");
			return sb.toString();
		}
	}
}
// end::main[]
