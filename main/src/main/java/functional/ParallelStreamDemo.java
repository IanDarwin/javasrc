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
		List<Employee> employees = getEmployees();

		long max = employees
				.parallelStream()
				.mapToLong(Employee::getPersonnelNumber)
				.max()
				.getAsLong();

		System.out.println("Highest personell number is " + max);
	}

	static List<Employee> getEmployees() {
		List<Employee> employees = new ArrayList<>();

		// Do some work here

		return employees;
	}
}
