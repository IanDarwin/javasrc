<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="parent_committee">
	<html><head><title>Your Committee</title></head>
	<body>
	<ol>
	<xsl:for-each select="parent">
		<li><xsl:value-of select="name"/>
			(<xsl:value-of select="children"/> children)</li>
	</xsl:for-each>
	</ol>
	</body>
	</html>
</xsl:template>
</xsl:stylesheet>
