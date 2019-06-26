import atm.AtmImpl;
import atm.BankManagement;
import atm.Account;
import atm.RegularUser;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import еxceptions.InvalidNewPinCodeException;
import еxceptions.InvalidPinCodeException;
import еxceptions.NoSuchUserException;


public class ATMTest {
    private static final String MOCKED_USER_ID = "Test123";
    private static final String MOCKED_USER_PIN = "123";
    private static final String MOCKED_USER_NEW_PIN = "1234";
    private static final int MOCKED_ACCOUNT_NUMBER = 1;
    private static final String WRONG_MOCK_USED_ID = "WrongId";
    private static final String WRONG_MOCK_USER_PIN = "WrongPin";

    private AtmImpl myATMImpl;
    @Mock private BankManagement mockManagement;
    @Mock private RegularUser mockUser;
    @Mock private Account mockAccount;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        myATMImpl = new AtmImpl(mockManagement);
        Mockito.when(mockUser.getAccount(MOCKED_ACCOUNT_NUMBER)).thenReturn(mockAccount);
        Mockito.when(mockUser.validatePin(MOCKED_USER_PIN)).thenReturn(true);
        Mockito.when(mockUser.changePin(MOCKED_USER_PIN)).thenThrow(InvalidNewPinCodeException.class);
        Mockito.when(mockManagement.getUser(WRONG_MOCK_USED_ID, MOCKED_USER_PIN)).thenThrow(NoSuchUserException.class);
        Mockito.when(mockManagement.getUser(MOCKED_USER_ID,WRONG_MOCK_USER_PIN)).thenThrow(InvalidPinCodeException.class);
        Mockito.doReturn(mockUser).when(mockManagement).getUser(MOCKED_USER_ID);
        Mockito.doReturn(mockUser).when(mockManagement).getUser(MOCKED_USER_ID,MOCKED_USER_PIN);
    }


    @Test
    public void testDepositWhenUserIsFound() {
        myATMImpl.depositToAccount(MOCKED_USER_ID, MOCKED_USER_PIN, MOCKED_ACCOUNT_NUMBER, 50);
        Mockito.verify(mockAccount, Mockito.times(1)).deposit(50);
    }

    @Test(expected = NoSuchUserException.class)
    public void testDepositWhenUserIsNotFound() {
        myATMImpl.depositToAccount(WRONG_MOCK_USED_ID, MOCKED_USER_PIN, MOCKED_ACCOUNT_NUMBER, 50);
    }

    @Test(expected = InvalidPinCodeException.class)
    public void testDepositWhenPinIsInvalid() {
        myATMImpl.depositToAccount(MOCKED_USER_ID, WRONG_MOCK_USER_PIN, MOCKED_ACCOUNT_NUMBER, 50);
    }

    @Test
    public void testWithdrawWhenUserIsFound() {
        myATMImpl.withdrawFromAccount(MOCKED_USER_ID, MOCKED_USER_PIN, MOCKED_ACCOUNT_NUMBER, 20);
        Mockito.verify(mockAccount, Mockito.times(1)).withdraw(20);
    }

    @Test(expected = NoSuchUserException.class)
    public void testWithdrawWhenUserIsNotFound() {
        myATMImpl.withdrawFromAccount(WRONG_MOCK_USED_ID, MOCKED_USER_PIN, MOCKED_ACCOUNT_NUMBER, 10);
    }

    @Test(expected = InvalidPinCodeException.class)
    public void testWithdrawWhenPinIsInvalid() {
        myATMImpl.withdrawFromAccount(MOCKED_USER_ID, WRONG_MOCK_USER_PIN, MOCKED_ACCOUNT_NUMBER, 10);
    }

    @Test
    public void testCheckBalance() {
       myATMImpl.checkBalanceOfAccount(MOCKED_USER_ID, MOCKED_USER_PIN, MOCKED_ACCOUNT_NUMBER);
       Mockito.verify(mockAccount, Mockito.times(1)).checkBalance();
    }


    @Test
    public void testChangePinCode() {
        myATMImpl.changePinCode(MOCKED_USER_ID, MOCKED_USER_PIN, MOCKED_USER_NEW_PIN);
        Mockito.verify(mockUser,Mockito.times(1)).changePin(MOCKED_USER_NEW_PIN);
    }

    @Test(expected = InvalidNewPinCodeException.class)
    public void testThrowExceptionWhenNewPinIsSameAsOldPin() {
        myATMImpl.changePinCode(MOCKED_USER_ID, MOCKED_USER_PIN, MOCKED_USER_PIN);
    }
}