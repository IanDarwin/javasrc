<%@page import="java.sql.*" errorPage="oops.jsp"%>
<html><body>
<p>As of <%= new java.util.Date() %>, you have
<% 
	int i;
	try {
		ResultSet rs = getResults();
		i = rs.getInt("total");
	} catch (SQLException ex) {
		throw new ServletException(ex);
	}
	out.println(i);
 %>
orders in your account.
<%! 
	Connection conn;
	public ResultSet getResults() throws SQLException {
		PreparedStatement ps = conn.prepareStatement("select count(order) orders where order.customerid = ?");
		ps.setInt(1, 1234);			// customer number
		return ps.executeQuery(); 
	}
 %>
