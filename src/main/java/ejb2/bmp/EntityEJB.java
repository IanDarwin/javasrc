import javax.ejb.*;
import java.sql.*;
import javax.sql.*;
import java.util.*;
import javax.naming.*;

/**
 * Replacement for the BMP example on page 576-7-45
 */
public class Bean implements EntityBean {
	private String title;	// pkey
	private int stockCount = 0;
	private double price = 0;

	/** findByPrimaryKey is a required Entity Bean method.
	 * Using String title as a PKEY is a bit lame...
	 */
	public String ejbFindByPrimaryKey(String pKey) throws FinderException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			ps = con.prepareStatement(
				"select title from Recordings where title = ?");
			ps.setString(1, pKey);
			rs = ps.executeQuery();
			if (!rs.next()) {
				throw new ObjectNotFoundException(pKey);
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
	public Collection ejbFindAll() throws FinderException {
		Connection con = null;
        Statement ps = null;
        ResultSet rs = null;
		Collection c = new ArrayList();
        try {
            con = getConnection();
            ps = con.createStatement();
            rs = ps.executeQuery("select title from Recordings");
            while (rs.next()) {
				// Returning the pKey in this Collection will cause the 
				// container to assign and load a bean for this pkey.
				c.add(rs.getString(1));
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

	public String ejbCreate() {
		// dummy for now
		return null;
	}

	public void ejbPostCreate() {
		// dummy for now
	}

	/** Just to compile, does not work. */
	private Connection getConnection() throws SQLException, NamingException {
		return
		((DataSource)new InitialContext().lookup("MyPool")).getConnection();
	}

	/** Just to compile, does not work. */
	private void cleanup(Connection co, Statement st, ResultSet rs) {
		try {
			if (rs != null) rs.close();
			if (st != null) st.close();
			if (co != null) co.close();
		} catch (SQLException ex) {
			// nothing to do. "It's not my fault!!"
		}
	}

	public void ejbLoad() { }
 
	public void ejbStore() { }
 
	public void ejbActivate() { }

	public void ejbPassivate() { }
 
	public void ejbRemove() { }
 
	public void setEntityContext(javax.ejb.EntityContext $1) { }
 
	public void unsetEntityContext() { }
}
