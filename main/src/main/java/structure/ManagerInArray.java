package structure;

/**
 * This is a toy program that shows creating a Manager object.
 */
public class ManagerInArray {
	public static void main(String[] args) {
		Employee aa;
		Employee staff[] = {
			new Employee("Jon Jonsson", 45678),
			aa=new Employee("Robin Smith", 12345),
			new Manager("Finance", aa)
		};
		for (int i=0; i<staff.length; i++) {
			System.out.println("Employee's name is " + staff[i].getName());
			if (staff[i] instanceof Manager) {
				Manager mgr = (Manager)staff[i];
				System.out.println("\tIS A MANAGER; department is " +
					mgr.getManagedDepartment());
			}
		}
	}
}
