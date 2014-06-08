package json;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import domain.Person;

// BEGIN main
public class ReadWriteJackson {

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();                // <1>
        
        String jsonInput =                                       // <2>
                "{\"id\":0,\"firstName\":\"Robin\",\"lastName\":\"Wilson\"}";
        Person q = mapper.readValue(jsonInput, Person.class);
        System.out.println("Read and parsed Person from JSON: " + q);
        
        Person p = new Person("Roger", "Rabbit");                // <3>
        System.out.print("Person object " + p +" as JSON = ");
        mapper.writeValue(System.out, p);
    }
}
// END main
