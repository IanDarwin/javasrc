package hibernate;

public class DemoDAO extends BaseDAOHibernateImpl<String, Integer>{

	public DemoDAO() {
		super(String.class);
	}

}
