<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/TR/WD-xsl">
<xsl:template match="/">

<html><body>

	<table border="1">
	<tr>
		<th>Name:</th>
		<th>EMail:</th>
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
