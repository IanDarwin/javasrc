package functional;

import structure.Employee;

import java.io.*;
import java.nio.file.*;
import java.util.*;

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
