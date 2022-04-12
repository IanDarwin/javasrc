package functional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import structure.Employee;

/**
 * Parallelized stream example: compute average word length in a file
 */
public class ParallelStreamDemo {
	public static void main(String[] args) throws IOException {
		// Use parallel stream to parse employees and 
		// find highest emp# assigned so far
		long max = Files.lines(Path.of("employee-data.txt"))
			.parallel()
			.map(s -> Employee.toEmployee(s))
			.mapToLong(Employee::getPersonnelNumber)
			.max()
			.getAsLong();
		System.out.println("Highest personnel number is " + max);
	}
}
