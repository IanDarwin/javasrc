void main() {
	Class outer = lang.NestMates.class;
	Class inner = lang.NestMates.Nested.class;
	System.out.println("Is outer a nestmate of itself? " + outer.isNestmateOf(outer));
	System.out.println("Is outer a nestmate of inner? " + outer.isNestmateOf(inner));
	System.out.println("What is inner's nest host? " + inner.getNestHost());
	System.out.println("What are all of inner's nestmates? " + 
		java.util.Arrays.toString(inner.getNestMembers()));
}
