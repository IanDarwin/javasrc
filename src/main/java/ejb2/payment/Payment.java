package com.darwinsys.orders;

import javax.ejb.EJBObject;

public interface Payment extends EJBObject, PaymentMethods {
	public static final int CASH = 1;
	public static final int CHEQUE = 2;
	public static final int CREDIT = 3;
}
