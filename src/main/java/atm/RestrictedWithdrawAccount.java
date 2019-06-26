package atm;

import еxceptions.InvalidAmountException;
import еxceptions.WithdrawException;

public class RestrictedWithdrawAccount extends BasicAccount {
    private double withdrawRestriction;

    public RestrictedWithdrawAccount(double withdrawRestriction) {
        super();
        this.withdrawRestriction = withdrawRestriction;
    }

    @Override
    public void withdraw(double amount) {
        if(amount > this.withdrawRestriction) {
            throw new WithdrawException();
        }
        if(amount > this.balance) {
            throw new InvalidAmountException();
        }
        this.balance -= amount;
    }
}
