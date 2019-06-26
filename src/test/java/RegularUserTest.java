import atm.BasicAccount;
import atm.Account;
import atm.RegularUser;
import atm.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import sun.awt.windows.WEmbeddedFrame;
import еxceptions.InvalidNewPinCodeException;
import еxceptions.InvalidPinCodeException;
import еxceptions.NoSuchAccountException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class RegularUserTest {
    private static final String SPY_USER_ID = "SpyUser";
    private static final String SPY_USER_PIN = "SpyPin";
    private static final String WRONG_USER_PIN = "WrongPin";
    private static final String SPY_USER_CHANGED_PIN = "SpyNewPin";
    private static final int MOCK_ACCOUNT_NUMBER = 1;
    private static final int WRONG_MOCK_ACCOUNT_NUMBER = 2;


    @Spy private RegularUser user;
    @Mock private Account mockAccount;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(mockAccount.getAccountNumber()).thenReturn(MOCK_ACCOUNT_NUMBER);
        Mockito.when(user.getUserID()).thenReturn(SPY_USER_ID);
        Mockito.when(user.getPinCode()).thenReturn(SPY_USER_PIN);
        Mockito.when(user.changePin(SPY_USER_CHANGED_PIN)).thenReturn(true);
        Mockito.doReturn(true).when(user).validatePin(SPY_USER_PIN);
        Mockito.doReturn(mockAccount).when(user).getAccount(MOCK_ACCOUNT_NUMBER);
    }

    @Test
    public void testGetUserID() {
        assertEquals(SPY_USER_ID, user.getUserID());
    }

    @Test
    public void testGetUserPin() {
        assertEquals(SPY_USER_PIN, user.getPinCode());
    }

    @Test
    public void testChangePinWhenNewPinIsDifferent() {
        assertTrue(user.changePin(SPY_USER_CHANGED_PIN));
    }

    @Test
    public void testGetAccount() {
        user.getAccount(MOCK_ACCOUNT_NUMBER);
        assertEquals(mockAccount, user.getAccount(MOCK_ACCOUNT_NUMBER));

    }

    @Test(expected = NoSuchAccountException.class)
    public void testThrowExceptionWhenAccountNotFound() {
        user.getAccount(WRONG_MOCK_ACCOUNT_NUMBER);
    }

    @Test
    public void testValidatePin() {
        assertTrue(user.validatePin(SPY_USER_PIN));
    }

    @Test(expected = InvalidPinCodeException.class)
    public void testThrowExceptionIfPinIsInvalid() {
        user.validatePin(WRONG_USER_PIN);
    }

    @Test
    public void testAddAccountToUser() {
        user.addAccount(mockAccount);
        assertEquals(mockAccount, user.getAccount(MOCK_ACCOUNT_NUMBER));

    }
}