package atm;

import еxceptions.InvalidAmountException;

public class OverdraftAccount extends BasicAccount {
    private double maxAllowedOverdraft;

    public OverdraftAccount(double maxAllowedOverdraft) {
        super();
        this.maxAllowedOverdraft = maxAllowedOverdraft;
    }

    @Override
    public void withdraw(double amount) {
        if(Math.abs(this.balance - amount) > this.maxAllowedOverdraft) {
            throw new InvalidAmountException();
        }
        this.balance -= amount;
    }
}
