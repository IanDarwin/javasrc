<title>Where does "jsp:useBean" actually look?</title>
<P>Hello</P>
<P>The code for this JSP simply does:
<ul>
<li>pageContext.setAttribute("blobject", new Thread("She Lives!"));
(note that we just use Thread as a convenient class that has at least
one getXXX() method we can usefully call);
<li>a jsp:useBean for id="blobject" class="User"
<li>a jsp:getProperty name="blobject" property=name
</ul>
<P>All of which just goes to prove that "useBean"-managed beans
really just live as attributes in the pageContext
(or sessionContext, for scope=session, etc).
<HR></HR>
At least is does IF the following should happen to show the 
name I created the Thread object with:
<HR></HR>
<% pageContext.setAttribute("blobject", new Thread("She Lives!")); %>
<jsp:useBean id="blobject" class="java.lang.Thread" scope="page" />
<jsp:getProperty name="blobject" property="name" /> 
<HR></HR>
