<html><body>
<p>As of <%= new Date() %>, you have
<% 
	try {
		ResultSet rs = getResults(); int i = rs.getInt("total");
	} catch (SQLException ex) {
		throw new JspException(ex);
	}
	out.println(i)
 %>
orders in your account.
<%! 
	public ResultSet getResults() throws SQLException {
		PreparedStatement ps = conn.prepareStatement("select total from orders where customerid = ?");
		ps.setInt(1, 1234);			// customer number
		return ps.executeQuery(); 
	}
 %>
