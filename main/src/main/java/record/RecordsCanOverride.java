package record;

/**
 * Simply demonstrate that records CAN override equals/hashcode & other methods.
 * Not usually a good idea, but where records have a primary identity, may be OK.
 */
public class RecordsCanOverride {

	record BankAccount(int accountNumber, AcctType type, double interestRate, double Balance) {

		@Override
		public boolean equals(Object o) {
			if (o instanceof BankAccount acct) {
				return this.accountNumber == acct.accountNumber;
			}
			return false;
		}

		@Override
		public int hashCode() {
			return accountNumber * 31;
		}
	}

	public static void main(String[] args) {
		BankAccount ac1 = new BankAccount(314159, AcctType.SAVINGS, 5.5, 1_000_000d),
		ac2 = new BankAccount(314159, AcctType.CHECKING, 2.1, 1.00);

		System.out.println(ac1 == ac2);
		System.out.println(ac1.equals(ac2));
	}
}

	enum AcctType {CHECKING, SAVINGS};
