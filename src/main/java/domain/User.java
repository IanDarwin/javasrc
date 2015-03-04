package domain;

// package jabadot;

import java.io.Serializable;
import java.util.Date;

/** Represents one logged in user 
 */
public class User extends Person implements Serializable {

	private static final long serialVersionUID = 5394392565088707959L;
	
	// #name:password:name:email:City:Prov:Country:privs
	/** The login name */
	protected String name;
	protected String password;
	protected String email;			// 5
	protected String address;
	protected String address2;
	protected String company;
	protected String city;
	protected String prov;			// 10
	protected String country;
	protected Date creationDate;
	protected Date lastLoginDate;
	protected String jobDescr;
	protected String os;			// 15
	protected String unixGUI;
	protected String proglang;
	/** user preference */
	protected String skin;

	//								// privs is 19

	protected boolean editPrivs = false;
	protected boolean adminPrivs = false;

	public static final int P_ADMIN = 01000;
	public static final int P_EDIT = 0100;

	/** Construct a user with no data -- must be a no-argument
	 * constructor for use in jsp:useBean.
	 */
	public User() {
		creationDate = new Date();
	}

	/** Construct a user with just the name */
	public User(String n) {
		this();			// set creationDate
		name = n;
	}

	/** Construct a user with all text fields. */
	public User(String nick, String pw, String fname, String lName,
		String emaddr,
		String comp,
		String addr1, String addr2,
		String cty, String pr, String cntry,
		String jd, String os, String gui, String lang,
		String skin) {
		this();			// set creationDate
		name = nick;
		password = pw;
		setFirstName(fname);
		setLastName(lName);
		email = emaddr;
		address = addr1;
		address2 = addr2;
		company = comp;
		city = cty;
		prov = pr;
		country = cntry;
		this.skin = skin;
		jobDescr = jd;
		this.os = os;
		unixGUI = gui;
		proglang = lang;
	}

	/** Construct a user with common text fields.
	 * Obviously should be a builder here. 
	 */
	public User(String nick, String pw, String fname, String lName,
		String emaddr, String city, String prov, String cntry,
		Date credt, Date lastlog, String skin, boolean e, boolean a) {
		this();			// set credt
		name = nick;
		password = pw;
		setFirstName(fname);
		setLastName(lName);
		email = emaddr;
		this.prov = prov;
		this.city = city;
		this.country = cntry;
		this.skin = skin;
		creationDate = (Date) credt.clone();
		lastLoginDate = (Date) lastlog.clone();
		adminPrivs = a;
		editPrivs = e;
	}

	/**
	 * @param nick
	 * @param pass
	 * @param full
	 * @param email2
	 * @param city2
	 * @param prov2
	 * @param ctry
	 */
	public User(String nick, String pass, String full, String email2, String city2, String prov2, String ctry) {
		
		// TODO Auto-generated constructor stub
	}

	/** Return the nickname. */
	public String getName() {
		return name;
	}

	/** The name should not be changeable, but we
	 * want to be able to say <jsp:setProperty property="*"/>
	 * and get it all...
	 */
	public void setName(String nick) {
		name = nick;
	}

	public String getPassword() {
		return password;
	}

	/** Validate a given password against the user's. */
	public boolean checkPassword(String userInput) {
		return password.equals(userInput);
	}

	/** Set password */
	public void setPassword(String password) {
		this.password = password;
	}

	/** Get email */
	public String getEmail() {
		return email;
	}

	/** Set email */
	public void setEmail(String email) {
		this.email = email;
	}

	/** Get city */
	public String getCity() {
		return city;
	}

	/** Set city */
	public void setCity(String city) {
		this.city = city;
	}

	/** Get prov */
	public String getProvince() {
		return prov;
	}

	/** Set prov */
	public void setProv(String prov) {
		this.prov = prov;
	}

	/** Get country */
	public String getCountry() {
		return country;
	}

	/** Set country */
	public void setCountry(String country) {
		this.country = country;
	}

	/** Get adminPrivs */
	public boolean isAdminPrivileged() {
		return adminPrivs;
	}

	/** Set adminPrivs */
	public void setAdminPrivileged(boolean adminPrivs) {
		this.adminPrivs = adminPrivs;
	}

	/** Get EditPrivs */
	public boolean isEditPrivileged() {
		return editPrivs;
	}

	/** Set EditPrivs */
	public void setEditPrivileged(boolean editPrivs) {
		this.editPrivs = editPrivs;
	}

	/** Get all privs, as an int, for use in the database */
	public int getPrivs() {
		int i = 0;
		if (adminPrivs)
			i |= P_ADMIN;
		if (editPrivs)
			i |= P_EDIT;
		return i;
	}

	/** Get the Creation Date (read only field) */
	public Date getCreationDate() {
		return (Date) creationDate.clone();
	}

	/** Get the LastLog Date (read only field) */
	public Date getLastLoginDate() {
		return (Date) lastLoginDate.clone();
	}

	/** Get the LastLog Date (read only field) */
	public void setLastLoginDate(Date d) {
		lastLoginDate = (Date) d.clone();
	}

	/** Return a String representation. */
	public String toString() {
		return new StringBuffer("User[").append(name).append(',').
			append(getFullName()).
			append(']').toString();
	}

	/** Check if all required fields have been set */
	public boolean isComplete() {
		if (name == null || name.length()==0 ||
		    firstName == null || firstName.length()==0 ||
		    lastName == null || lastName.length()==0 ||
		    email == null || email.length()==0)
			return false;
		return true;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getJobDescr() {
		return jobDescr;
	}

	public void setJobDescr(String jobDescr) {
		this.jobDescr = jobDescr;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getUnixGUI() {
		return unixGUI;
	}

	public void setUnixGUI(String unixGUI) {
		this.unixGUI = unixGUI;
	}

	public String getProglang() {
		return proglang;
	}

	public void setProglang(String proglang) {
		this.proglang = proglang;
	}

	public String getSkin() {
		return skin;
	}

	public void setSkin(String skin) {
		this.skin = skin;
	}

	public boolean isEditPrivs() {
		return editPrivs;
	}

	public void setEditPrivs(boolean editPrivs) {
		this.editPrivs = editPrivs;
	}

	public boolean isAdminPrivs() {
		return adminPrivs;
	}

	public void setAdminPrivs(boolean adminPrivs) {
		this.adminPrivs = adminPrivs;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getProv() {
		return prov;
	}

	public static int getpAdmin() {
		return P_ADMIN;
	}

	public static int getpEdit() {
		return P_EDIT;
	}
}
