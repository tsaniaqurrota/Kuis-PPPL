import org.example.Wallet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class WalletTest {
    private Wallet mainAcc;
    private Wallet recipientAcc;

    @BeforeEach
    void initMethod() {
        mainAcc = new Wallet(500000, "IDR");
        recipientAcc = new Wallet(0, "IDR");
    }

    @AfterEach
    void cleanMethod() {
        mainAcc = null;
        recipientAcc = null;
    }

    @Test
    public void testGetBalance() {
        assertAll(
                () -> assertEquals(500000, mainAcc.getBalance()),
                () -> assertNotEquals(0, mainAcc.getBalance()),
                () -> assertEquals(0, recipientAcc.getBalance())
        );
    }

    @Test
    public void testGetCurrency() {
        assertAll(
                () -> assertEquals("IDR", mainAcc.getCurrency()),
                () -> assertNotNull(mainAcc.getCurrency()),
                () -> assertEquals("IDR", recipientAcc.getCurrency()),
                () -> assertNotNull(recipientAcc.getCurrency())
        );
    }

    @Test
    public void testDepositAmount() {
        mainAcc.depositAmount(50000);
        assertAll(
                () -> assertEquals(550000, mainAcc.getBalance()),
                () -> assertNotEquals(500000, mainAcc.getBalance())
        );
    }

    @Test
    public void testDepositNegativeAmount() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> mainAcc.depositAmount(-50000)),
                () -> assertDoesNotThrow(() -> mainAcc.depositAmount(50000))
        );
    }

    @Test
    public void testWithdrawAmount() {
        mainAcc.withdrawAmount(100000);
        assertAll(
                () -> assertEquals(400000, mainAcc.getBalance()),
                () -> assertNotEquals(500000, mainAcc.getBalance())
        );
    }

    @Test
    public void testWithdrawNegativeAmount() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> mainAcc.withdrawAmount(-10000)),
                () -> assertDoesNotThrow(() -> mainAcc.depositAmount(100000))
        );
    }

    @Test
    public void testWithdrawInsuficientAmount() {
        assertAll(
                () -> assertFalse(mainAcc.withdrawAmount(600000)),
                () -> assertTrue(mainAcc.withdrawAmount(500000))
        );
    }

    @Test
    public void testTransferFunds() {
        mainAcc.transferFunds(recipientAcc, 100000);

        assertAll(
                () -> assertEquals(100000, recipientAcc.getBalance()),
                () -> assertEquals(400000, mainAcc.getBalance())
        );
    }

    @Test
    public void testTransferInsuficientFunds() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> mainAcc.transferFunds(recipientAcc, 600000)),
                () -> assertDoesNotThrow(() -> mainAcc.transferFunds(recipientAcc, 500000))
        );
    }

    @Test
    public void testTransferDifferentCurrency() {
        assertAll(
                () -> assertEquals(recipientAcc.getCurrency(), mainAcc.getCurrency())
        );
    }
}

