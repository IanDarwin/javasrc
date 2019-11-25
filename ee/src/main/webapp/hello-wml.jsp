<%@page contentType="text/vnd.wap.wml" import="java.util.*" %>
<?xml "version=1.0"?>
<!DOCTYPE wml PUBLIC>
<wml>
	<card id="Example" title="Example JSP/WML">
		<p align="center">Data current as of <%= new Date() %></p>
		<p>
			<a title="Data1"> href="#data1>More Details</a>
		</p>
	</card>

	<card id="data1" title="DataOne">
	<p>This is the card called DataOne</p>
	</card>
</wml>
