import atm.RestrictedWithdrawAccount;
import org.junit.Before;
import org.junit.Test;
import еxceptions.InvalidAmountException;
import еxceptions.WithdrawException;

import static org.junit.Assert.*;

public class RestrictedWithdrawAccountTest {
    private RestrictedWithdrawAccount current;

    @Before
    public void setUp() {
        current = new RestrictedWithdrawAccount(100);
    }

    @Test(expected = WithdrawException.class)
    public void testThrowExceptionWhenAmountIsBiggerThanWithdrawMaximum() {
        current.deposit(200);
        current.withdraw(110);
    }

    @Test(expected = InvalidAmountException.class)
    public void testThrowExceptionWhenAmountIsBiggerThanCurrentBalance() {
        current.deposit(50);
        current.withdraw(60);
    }


    @Test
    public void testWithdrawWhenAmountIsBelowRestriction() {
        current.deposit(200);
        current.withdraw(90);
        assertEquals(110, current.checkBalance(), 0.00001);
    }
}