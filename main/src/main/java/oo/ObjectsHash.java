package oo;

import java.util.Objects;

/**
 * Objects.hash() is a short way to write hashCode().
 * We also do it the traditional way to see if it gives the same result.
 * Does it? Run it and see.
 */
public class ObjectsHash {
	String name;
	String address;
	String country;

	public ObjectsHash(String name, String address, String country) {
		this.name = name;
		this.address = address;
		this.country = country;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ObjectsHash that = (ObjectsHash) o;
		return Objects.equals(name, that.name) && Objects.equals(address, that.address) && Objects.equals(country, that.country);
	}

	public int hashCode() {
		return Objects.hash(name, address, country);
	}

	public int oldHashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		return result;
	}

	public static void main(String[] args) {
		ObjectsHash person1 = new ObjectsHash("Tommie", "23 Main St", "Zanzibar");
		System.out.println(person1.hashCode());
		System.out.println(person1.oldHashCode());
		ObjectsHash person2 = new ObjectsHash("Ян Дарвін", "Kyiv", "Ukraine");
		System.out.println(person2.hashCode());
		System.out.println(person2.oldHashCode());
	}
}
