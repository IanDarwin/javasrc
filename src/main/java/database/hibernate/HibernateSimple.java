package database.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import domain.Person;

// BEGIN main
public class HibernateSimple {
	@SuppressWarnings({ "unchecked", "deprecation" })
	public static void main(String[] args) {

		System.out.println("HibernateSimple.main()");

		Configuration cf = new Configuration();
		cf.configure();
		SessionFactory sf = null;
		Session session = null;
		try {
			sf = cf.buildSessionFactory();
			session = sf.openSession();

			Transaction tx = session.beginTransaction();

			// Create an entity in the database.
			Person np = new Person("Tom", "Boots");
			System.out.println(np);
			session.save(np);
			tx.commit();

			int id = np.getId();
			System.out.println("Created Person with Id " + id);

			tx = session.beginTransaction();

			Query query = session.createQuery(
				"select p from Person p order by p.lastName");

			List<Person> list = query.list();
			System.out.println("There are " + list.size() + " persons:");
			list.forEach(p ->
				System.out.println(
						p.getFirstName() + ' ' + p.getLastName())
			);
			System.out.println();
		} finally {
			if (session != null) {
				session.close();				
			}
		}
	}
}
// END main
