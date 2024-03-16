static final ScopedValue<Client> CLIENT = ScopedValue.newInstance();

private final Client currentClient = new Client(1234);

void main() {
	ScopedValue.runWhere(CLIENT, currentClient, () -> doSomething());
}

void doSomething() {
	doSomethingElse();
}
void doSomethingElse() {
	doYetMore();
}
void doYetMore() {
	Client client = CLIENT.get();
	System.out.println(STR."In doYetMore, client = " + client);
}

record Client(int id) { }
