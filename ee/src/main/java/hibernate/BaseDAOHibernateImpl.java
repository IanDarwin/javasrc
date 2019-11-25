package hibernate;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.darwinsys.database.GenericDAO;

/**
 * Hibernate implementation of GenericDAO.
 * @author Original by Per Mellqvist, see http://www.ibm.com/developerworks/java/library/j-genericdao.html
 */
public class BaseDAOHibernateImpl<T, PK extends Serializable>
    implements GenericDAO<T, PK> {

	private Class<T> type;

	private SessionFactory sessionfactory;

	public BaseDAOHibernateImpl(Class<T> type) {
        this.type = type;
    }

    public PK create(T o) {
        return (PK)getSession().save(o);
    }

	public T read(PK id) {
        return (T)getSession().get(type, id);
    }

    public void update(T o) {
        getSession().update(o);
    }

    public void delete(T o) {
        getSession().delete(o);
    }

    private Session getSession() {
		return sessionfactory.getCurrentSession();
	}

    public SessionFactory getSessionfactory() {
		return sessionfactory;
	}

    /** Allow for injection of Hibernate SessionFactory */
	public void setSessionfactory(SessionFactory sessionfactory) {
		this.sessionfactory = sessionfactory;
	}

}
