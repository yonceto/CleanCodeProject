import atm.BasicAccount;
import org.junit.Before;
import org.junit.Test;
import еxceptions.InvalidAmountException;

import static org.junit.Assert.*;

public class AccountTest {
    private BasicAccount current;

    @Before
    public void setUp() {
        current = new BasicAccount();
        current.deposit(200);
    }

    @Test
    public void testGetAccountNumber() {
        assertEquals( 4, current.getAccountNumber());
    }

    @Test
    public void testWithdrawCertainAmount() {
        current.withdraw(12.5);
        assertEquals(187.5, current.checkBalance(), 0.000001);
    }

    @Test(expected = InvalidAmountException.class)
    public void testThrowExceptionWhenAmountIsBiggerThanBalance() {
        current.withdraw(210);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowExceptionWhenWithdrawAmountIsNegative() {
        current.withdraw(-50);
    }

    @Test
    public void testDepositAmount() {
        current.deposit(27.5);
        assertEquals(227.5, current.checkBalance(), 0.000001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowExceptionWhenDepositAmountIsNegative() {
        current.deposit(-20);
    }

    @Test
    public void testCheckBalance() {
        assertEquals(200, current.checkBalance(), 0.000001);
    }
}