<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">

<html>
<head><title>Our People</title></head>
<body>

	<table border="1">
	<tr>
		<th>Name</th>
		<th>EMail</th>
	</tr>

	<xsl:for-each select="people/person">
		<tr>
			<td><xsl:value-of select="name"/></td>
			<td><xsl:value-of select="email"/></td>
		</tr>
	</xsl:for-each>

	</table>

</body></html>
</xsl:template>
</xsl:stylesheet>
