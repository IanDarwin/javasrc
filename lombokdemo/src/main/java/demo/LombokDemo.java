package demo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@ToString
@Getter
public class LombokDemo {
	
	int id;
	String name;

	public static void main(String[] args) {
		LombokDemo demo = LombokDemo.builder().id(123).name("The Eagles").build();
		System.out.println("Demo is " + demo);
		System.out.printf("Demo %d is %s\n", demo.getId(), demo.getName());
	}

	
}
