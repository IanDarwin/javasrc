package ejb.bmp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.FinderException;
import javax.ejb.ObjectNotFoundException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Replacement for the BMP example on page 576-7-15.
 * STATUS: INCOMPLETE!!!
 * It will compile but will NOT run yet!
 */
public class EntityEJB implements EntityBean {

	private static final long serialVersionUID = 3832898866826457656L;
	private EntityContext ctx;
	private Integer id;	// pkey
	private Recording fields;
	private int stockCount = 0;
	private double price = 0;

	/** findByPrimaryKey is a required Entity Bean method.
	 * This method runs with no PrimaryKey in the Context yet;
	 * it does not actually allocate the bean instance fields.
	 */
	public Integer ejbFindByPrimaryKey(Integer pKey) throws FinderException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			ps = con.prepareStatement(
				"select title from Recordings where id = ?");
			ps.setInt(1, pKey.intValue());
			rs = ps.executeQuery();
			if (!rs.next()) {
				throw new ObjectNotFoundException(pKey.toString());
			}
			if (rs.next()) {
				throw new FinderException("Duplicate pkey!? " + pKey);
			}

			// Returning pKey will cause the container to
			// create and load a bean for this pkey.
			return pKey;
		} catch (SQLException ex) {
			throw new FinderException(ex.toString());
		} catch (NamingException ex) {
			throw new FinderException(ex.toString());
		} finally {
			cleanup(con, ps, rs);
		}
	}

	/** Return a Collection of all Stock Items */
	public Collection<Integer> ejbFindAll() throws FinderException {
		Connection con = null;
        Statement ps = null;
        ResultSet rs = null;
		Collection<Integer> c = new ArrayList<Integer>();
        try {
            con = getConnection();
            ps = con.createStatement();
            rs = ps.executeQuery("select id from Recordings");
            while (rs.next()) {
				// Returning the pKey in this Collection will cause the 
				// container to assign and load a bean for this pkey.
				c.add(rs.getInt(1));
			}
        } catch (SQLException ex) {
            throw new FinderException(ex.toString());
        } catch (NamingException ex) {
            throw new FinderException(ex.toString());
        } finally {
            cleanup(con, ps, rs);
        }
		return c;
	}

	public String ejbCreate(Recording rec) {
		fields = rec;
		return null;
	}

	public void ejbPostCreate(Recording rec) {
		// dummy for now
	}

	public Recording getRecording() {
		return fields;
	}

	/** Just to compile, does not work. */
	private Connection getConnection() throws SQLException, NamingException {
		return
		((DataSource)new InitialContext().lookup("MyPool")).getConnection();
	}

	/** Clean up JDBC objects in the correct order */
	private void cleanup(Connection co, Statement st, ResultSet rs) {
		try {
			if (rs != null) rs.close();
			if (st != null) st.close();
			if (co != null) co.close();
		} catch (SQLException ex) {
			// nothing to do. "It's not my fault!!"
		}
	}

	public void ejbLoad() { 
		Integer pkey = (Integer)ctx.getPrimaryKey();
		// This must be implemented!!!
		throw new IllegalStateException(
		"You used my bean before I finished writing it!");
	}
 
	public void ejbStore() { 
		// This must be implemented!!!
		throw new IllegalStateException(
		"You used my bean before I finished writing it!");
	}
 
	public void ejbActivate() { }

	public void ejbPassivate() { }
 
	public void ejbRemove() {
		// This must be implemented!!!
		throw new IllegalStateException(
		"You used my bean before I finished writing it!");
	}
 
	public void setEntityContext(EntityContext ctx) { 
		this.ctx = ctx;
	}
 
	public void unsetEntityContext() {
		ctx = null;
	}
}
