BDK_HOME	= C:/bdk
#BDK_HOME	= /usr/local/java/bdk
PKG			= darwinsys
BEAN_CLASSES = ${PKG}/ChartData.class ${PKG}/Chart.class \
			${PKG}/ChartBeanInfo.class

#JAVAC		= javac -g
JAVAC		= jikes
# The "-d ." is needed to get classes into their correct directory structure
OUTDIR		= -d .

all:	${PKG}/ChartTest.class ${BEAN_CLASSES}
${PKG}/ChartTest.class: ChartTest.java ${BEAN_CLASSES}
		$(JAVAC) $(OUTDIR) ChartTest.java

${PKG}/Chart.class: Chart.java
		$(JAVAC) $(OUTDIR) Chart.java
${PKG}/ChartData.class: ChartData.java
		$(JAVAC) $(OUTDIR) ChartData.java
${PKG}/ChartBeanInfo.class: ChartBeanInfo.java
		$(JAVAC) $(OUTDIR) ChartBeanInfo.java

jar:	DarwinSysChart.jar
DarwinSysChart.jar:	all
		jar cvfm DarwinSysChart.jar manifest.stub darwinsys
install:	DarwinSysChart.jar
		cp DarwinSysChart.jar $(BDK_HOME)/jars/DarwinSysChart.jar
