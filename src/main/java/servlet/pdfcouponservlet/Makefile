WEBAPPDIR = /usr/local/tomcat/webapps

all:	pdfcouponservlet.war
pdfcouponservlet.war:	WEB-INF/classes/PDFCouponServlet.class WEB-INF/lib/spdf.jar
	jar cvf pdfcouponservlet.war .
WEB-INF/classes/PDFCouponServlet.class: PDFCouponServlet.java
	jikes -d WEB-INF/classes PDFCouponServlet.java
WEB-INF/lib/spdf.jar: ../../spdf/spdf.jar
	cp ../../spdf/spdf.jar WEB-INF/lib

install:	all
	cp pdfcouponservlet.war $(WEBAPPDIR)
