package com.darwinsys.rain;

import java.io.*;
import java.util.*;
import javax.ejb.*;

/**
 * PublisherBean - Implementation class for an Entity Bean representing 
 * one Publisher in the RainForest application.
 * This version uses CMP2.0.
 */
public abstract class PublisherBean implements EntityBean {

	/** No-argument constructor (required by law :-)) */
	public PublisherBean() {
	};

	// Abstract, "virtual", container managed fields

	public abstract Integer getId();
	public abstract void setId(Integer id);

	public abstract String getName();
	public abstract void setName(String val);

	public abstract String getCity();
	public abstract void setCity(String val);

	public abstract String getPhone();
	public abstract void setPhone(String val);

	// create/postCreate methods.

	/** Create the Publisher Recording. */
	public Integer ejbCreate(int pkey, String name, String city, String phone) throws CreateException {
		setId(new Integer(pkey));
		setName(name);
		setCity(city);
		setPhone(phone);
		return null;
	}

	public void ejbPostCreate(int pkey, String name, String title, String phone)
		 throws CreateException {
	}

	// Callback methods.

	/** The obvious Entity Context object */
	private EntityContext ctx;

	/** Sets the EntityContext for the EJB. */
	public void setEntityContext(EntityContext ctx) {
		this.ctx = ctx;
	}

	/** Unset the EntityContext for the EJB. */
	public void unsetEntityContext() {
		this.ctx = null;
	}

	public void ejbActivate() {
	}

	public void ejbPassivate() {
	}

	public void ejbLoad() {
	}

	public void ejbStore() {
	}

	public void ejbRemove() throws RemoveException {
	}

}
