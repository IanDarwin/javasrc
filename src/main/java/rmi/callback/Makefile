JAVAC?=		jikes
#JAVAC?=	javac

all:	test

test:	stubs
	java ServerMain &
	java com.darwinsys.client.ClientProgram &

artreg:
	zap -y rmi
	cd /tmp; rmiregistry &

stubs:	compile
	rmic  -d . com.darwinsys.callback.TickerServerImpl
	rmic  -d . com.darwinsys.client.ClientProgram

# Build the class files
compile:
	@mkdir -p com/darwinsys/client com/darwinsys/callback
	$(JAVAC) -d . Client.java  
	$(JAVAC) -d . TickerServer.java 
	$(JAVAC) -d . TickerServerImpl.java
	$(JAVAC) -d . ClientProgram.java
	$(JAVAC) -d . ServerMain.java
