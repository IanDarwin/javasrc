<?xml version="1.0"?>
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
			<td><xsl:value-of select="name"/></td>
			<td><xsl:value-of select="children"/></td>
		</tr>
	</xsl:for-each>

	</table>

</body></html>
</xsl:template>
</xsl:stylesheet>
