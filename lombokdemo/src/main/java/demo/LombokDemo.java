package demo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

record demo2(){}

@Builder
@Setter@Getter
public class LombokDemo {
	
	int id;
	String name;

	public static void main(String[] args) {
		LombokDemo demo = LombokDemo.builder().id(123).name("The Eagles").build();
		demo.getId();
	}

	
}
