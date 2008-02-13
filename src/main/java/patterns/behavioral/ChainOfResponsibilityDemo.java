package patterns.behavioral;

/** A simple demo of ChainOfResponsibility,
 * a GoF Design Pattern.
 */
enum Service { PURCHASE, DISCOUNT, REFUND }

abstract class Employee {
	Employee next; // Next employee up the chain
	public void setNext(Employee next) {
		this.next = next;
	}
	public abstract void processCustomer(Service s);
}

class Cashier extends Employee {
	@Override
	public void processCustomer(Service s) {
		if (s == Service.PURCHASE) {
			System.out.println("Cashier handled " + s);
			return;
		}
		next.processCustomer(s);
	}	
}

class Supervisor extends Employee {

	@Override
	public void processCustomer(Service s) {
		if (s == Service.PURCHASE || s == Service.DISCOUNT) {
			System.out.println("Supervisor handled " + s);
			return;
		}
		next.processCustomer(s);
	}		
}

class StoreManager extends Employee {
	@Override
	public void processCustomer(Service s) {
		System.out.println("Manager handled " + s);
	}

}

public class ChainOfResponsibilityDemo {
	public static void main(String[] args) {
		Employee s3 = new StoreManager();
		Employee s2 = new Supervisor();
		s2.setNext(s3);
		Employee s1 = new Cashier();
		s1.setNext(s2);

		s1.processCustomer(Service.PURCHASE);
		s1.processCustomer(Service.DISCOUNT);
		s1.processCustomer(Service.REFUND);
	}
}
