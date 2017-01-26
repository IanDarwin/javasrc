<!-- 
 Login bean usage.
 $Id$
 -->
<jsp:useBean id="pwck" class="jsp.darwin.PasswordChecker"/>
<jsp:setProperty name="pwck" property="*"/>
<% if (pwck.isValidPassword()) out.println("You are logged in");
 else out.println("Login name and/or password incorrect.");
 %>
<br>
