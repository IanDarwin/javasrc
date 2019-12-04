package domain;

// tag::main[]
public class Address {

    private int id;

    private String streetAddress;
    private String city;
    private String country;

    public int getId() {
        return id;
    }
    // Other accessors and methods omitted for brevity
    // end::main[]
    
    public void setId(int id) {
    		this.id = id;
    }

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
