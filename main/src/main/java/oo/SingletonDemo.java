package oo;

public class SingletonDemo {
    public static void main(String[] args) {

        // tag::enumBased[]
        // Demonstrate the enum method:
        EnumSingleton.INSTANCE.demoMethod();
        // end::enumBased[]

        // tag::codeBased[]
        // Demonstrate the codeBased method:
        Singleton.getInstance().demoMethod();
        // end::codeBased[]
    }
}
