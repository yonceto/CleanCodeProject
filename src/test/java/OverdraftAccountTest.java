import atm.OverdraftAccount;
import org.junit.Before;
import org.junit.Test;
import еxceptions.InvalidAmountException;

import static org.junit.Assert.*;

public class OverdraftAccountTest {
    private OverdraftAccount current;


    @Before
    public void setUp() {
        current = new OverdraftAccount(50);
    }

    @Test
    public void testWithdrawCertainAmount() {
        current.deposit(50);
        current.withdraw(65);
        assertEquals(-15, current.checkBalance(), 0.00001);
    }


    @Test(expected =  InvalidAmountException.class)
    public void testThrowExceptionWhenBalanceIsBelowOverdraftMaximum() {
        current.deposit(100);
        current.withdraw(160);
    }
}