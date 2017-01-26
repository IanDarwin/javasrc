<%@page errorPage="oops.jsp"%>
<%@taglib uri="/jabatags" prefix="jabadot"%>
<title>JabaDot: List of users</title>
<P>Hello! The following users have accounts on the JabaDot prototype site:</P>
<HR></HR>
<jabadot:forAllUsers element="user">
	<!-- jsp:useBean id="user" class="User" scope="page" / -->
	<li><jsp:getProperty name="user" property="name" />
</jabadot:forAllUsers>
<HR></HR>
