<?xml version="1.0"?>
<!-- Try to get non-well-formed-XML to be output by XSL-T -->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

<xsl:template match="parent_committee">

<html>
<head><title>Your Committee</title></head>
<body>
	<table border="1">
	<tr>
		<th>Name</th>
		<th>Children</th>
	</tr>

	<xsl:for-each select="parent">
		<tr>
			&lt;td&gt;<xsl:value-of select="name"/>
			<td><xsl:value-of select="children"/></td>
			%lt;mifTag &lt;foo
		</tr>
	</xsl:for-each>

	</table>

</body></html>
</xsl:template>
</xsl:stylesheet>
