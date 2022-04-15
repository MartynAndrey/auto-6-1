package ru.netology.transfer.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import ru.netology.transfer.data.DataHelper;
import ru.netology.transfer.page.DashboardPage;
import ru.netology.transfer.page.LoginPage;
import ru.netology.transfer.page.TransferPage;
import ru.netology.transfer.page.VerificationPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MoneyTransferTest {
    private DashboardPage dashboardPage;
    private int firstCardOriginalBalance;
    private int secondCardOriginalBalance;

    @BeforeAll
    public void setUp() {
        open("http://localhost:9999/");
        LoginPage loginPage = new LoginPage();
        VerificationPage verificationPage = loginPage.validLogin(DataHelper.AuthInfo.getValidAuthInfo());
        this.dashboardPage = verificationPage.validVerify(DataHelper.VerificationCode.getValidVerificationCode());
        this.firstCardOriginalBalance = this.dashboardPage.getFirstCardBalance();
        this.secondCardOriginalBalance = this.dashboardPage.getSecondCardBalance();
    }

    @AfterEach
    public void alignBalance() {
        int firstCardCurrentBalance = this.dashboardPage.getFirstCardBalance();
        int secondCardCurrentBalance = this.dashboardPage.getSecondCardBalance();
        int different = this.firstCardOriginalBalance - firstCardCurrentBalance;

        if (different > 0) {
            TransferPage transferPage = this.dashboardPage.makeDepositFirstCard();
            this.dashboardPage = transferPage.makeDeposit(different, DataHelper.CardsInfo.getValidCardNumbers().getSecondCardNumber());
        } else if (different < 0) {
            TransferPage transferPage = this.dashboardPage.makeDepositSecondCard();
            this.dashboardPage = transferPage.makeDeposit(different * (-1), DataHelper.CardsInfo.getValidCardNumbers().getFirstCardNumber());
        }
    }

//-1->-1----------------------------------------------------------------------------

    @Test
    public void shouldTransferZeroToFirstFromFirst() {
        int firstCardStartBalance = this.dashboardPage.getFirstCardBalance();
        int secondCardStartBalance = this.dashboardPage.getSecondCardBalance();
        int amount = 0;

        int[] expected = {firstCardStartBalance, secondCardStartBalance};

        TransferPage transferPage = this.dashboardPage.makeDepositFirstCard();
        this.dashboardPage = transferPage.makeDeposit(amount, DataHelper.CardsInfo.getValidCardNumbers().getFirstCardNumber());

        int firstCardEndBalance = this.dashboardPage.getFirstCardBalance();
        int secondCardEndBalance = this.dashboardPage.getSecondCardBalance();

        int[] actual = {firstCardEndBalance, secondCardEndBalance};

        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldTransferHalfToFirstFromFirst() {
        int firstCardStartBalance = this.dashboardPage.getFirstCardBalance();
        int secondCardStartBalance = this.dashboardPage.getSecondCardBalance();
        int amount = firstCardStartBalance / 2;

        int[] expected = {firstCardStartBalance, secondCardStartBalance};

        TransferPage transferPage = this.dashboardPage.makeDepositFirstCard();
        this.dashboardPage = transferPage.makeDeposit(amount, DataHelper.CardsInfo.getValidCardNumbers().getFirstCardNumber());

        int firstCardEndBalance = this.dashboardPage.getFirstCardBalance();
        int secondCardEndBalance = this.dashboardPage.getSecondCardBalance();

        int[] actual = {firstCardEndBalance, secondCardEndBalance};

        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldTransferAllToFirstFromFirst() {
        int firstCardStartBalance = this.dashboardPage.getFirstCardBalance();
        int secondCardStartBalance = this.dashboardPage.getSecondCardBalance();
        int amount = firstCardStartBalance;

        int[] expected = {firstCardStartBalance, secondCardStartBalance};

        TransferPage transferPage = this.dashboardPage.makeDepositFirstCard();
        this.dashboardPage = transferPage.makeDeposit(amount, DataHelper.CardsInfo.getValidCardNumbers().getFirstCardNumber());

        int firstCardEndBalance = this.dashboardPage.getFirstCardBalance();
        int secondCardEndBalance = this.dashboardPage.getSecondCardBalance();

        int[] actual = {firstCardEndBalance, secondCardEndBalance};

        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldTransferMoreToFirstFromFirst() {
        int firstCardStartBalance = this.dashboardPage.getFirstCardBalance();
        int secondCardStartBalance = this.dashboardPage.getSecondCardBalance();
        int amount = firstCardStartBalance * 2;

        int[] expected = {firstCardStartBalance, secondCardStartBalance};

        TransferPage transferPage = this.dashboardPage.makeDepositFirstCard();
        this.dashboardPage = transferPage.makeDeposit(amount, DataHelper.CardsInfo.getValidCardNumbers().getFirstCardNumber());

        int firstCardEndBalance = this.dashboardPage.getFirstCardBalance();
        int secondCardEndBalance = this.dashboardPage.getSecondCardBalance();

        int[] actual = {firstCardEndBalance, secondCardEndBalance};

        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldTransferLessToFirstFromFirst() {
        int firstCardStartBalance = this.dashboardPage.getFirstCardBalance();
        int secondCardStartBalance = this.dashboardPage.getSecondCardBalance();
        int amount = firstCardStartBalance * (-1);

        int[] expected = {firstCardStartBalance, secondCardStartBalance};

        TransferPage transferPage = this.dashboardPage.makeDepositFirstCard();
        this.dashboardPage = transferPage.makeDeposit(amount, DataHelper.CardsInfo.getValidCardNumbers().getFirstCardNumber());

        int firstCardEndBalance = this.dashboardPage.getFirstCardBalance();
        int secondCardEndBalance = this.dashboardPage.getSecondCardBalance();

        int[] actual = {firstCardEndBalance, secondCardEndBalance};

        assertArrayEquals(actual, expected);
    }

//-2->-2----------------------------------------------------------------------------

    @Test
    public void shouldTransferZeroToSecondFromSecond() {
        int firstCardStartBalance = this.dashboardPage.getFirstCardBalance();
        int secondCardStartBalance = this.dashboardPage.getSecondCardBalance();
        int amount = 0;

        int[] expected = {firstCardStartBalance, secondCardStartBalance};

        TransferPage transferPage = this.dashboardPage.makeDepositSecondCard();
        this.dashboardPage = transferPage.makeDeposit(amount, DataHelper.CardsInfo.getValidCardNumbers().getSecondCardNumber());

        int firstCardEndBalance = this.dashboardPage.getFirstCardBalance();
        int secondCardEndBalance = this.dashboardPage.getSecondCardBalance();

        int[] actual = {firstCardEndBalance, secondCardEndBalance};

        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldTransferHalfToSecondFromSecond() {
        int firstCardStartBalance = this.dashboardPage.getFirstCardBalance();
        int secondCardStartBalance = this.dashboardPage.getSecondCardBalance();
        int amount = secondCardStartBalance / 2;

        int[] expected = {firstCardStartBalance, secondCardStartBalance};

        TransferPage transferPage = this.dashboardPage.makeDepositSecondCard();
        this.dashboardPage = transferPage.makeDeposit(amount, DataHelper.CardsInfo.getValidCardNumbers().getSecondCardNumber());

        int firstCardEndBalance = this.dashboardPage.getFirstCardBalance();
        int secondCardEndBalance = this.dashboardPage.getSecondCardBalance();

        int[] actual = {firstCardEndBalance, secondCardEndBalance};

        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldTransferAllToSecondFromSecond() {
        int firstCardStartBalance = this.dashboardPage.getFirstCardBalance();
        int secondCardStartBalance = this.dashboardPage.getSecondCardBalance();
        int amount = secondCardStartBalance;

        int[] expected = {firstCardStartBalance, secondCardStartBalance};

        TransferPage transferPage = this.dashboardPage.makeDepositSecondCard();
        this.dashboardPage = transferPage.makeDeposit(amount, DataHelper.CardsInfo.getValidCardNumbers().getSecondCardNumber());

        int firstCardEndBalance = this.dashboardPage.getFirstCardBalance();
        int secondCardEndBalance = this.dashboardPage.getSecondCardBalance();

        int[] actual = {firstCardEndBalance, secondCardEndBalance};

        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldTransferMoreToSecondFromSecond() {
        int firstCardStartBalance = this.dashboardPage.getFirstCardBalance();
        int secondCardStartBalance = this.dashboardPage.getSecondCardBalance();
        int amount = secondCardStartBalance * 2;

        int[] expected = {firstCardStartBalance, secondCardStartBalance};

        TransferPage transferPage = this.dashboardPage.makeDepositSecondCard();
        this.dashboardPage = transferPage.makeDeposit(amount, DataHelper.CardsInfo.getValidCardNumbers().getSecondCardNumber());

        int firstCardEndBalance = this.dashboardPage.getFirstCardBalance();
        int secondCardEndBalance = this.dashboardPage.getSecondCardBalance();

        int[] actual = {firstCardEndBalance, secondCardEndBalance};

        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldTransferLessToSecondFromSecond() {
        int firstCardStartBalance = this.dashboardPage.getFirstCardBalance();
        int secondCardStartBalance = this.dashboardPage.getSecondCardBalance();
        int amount = secondCardStartBalance * (-1);

        int[] expected = {firstCardStartBalance, secondCardStartBalance};

        TransferPage transferPage = this.dashboardPage.makeDepositSecondCard();
        this.dashboardPage = transferPage.makeDeposit(amount, DataHelper.CardsInfo.getValidCardNumbers().getSecondCardNumber());

        int firstCardEndBalance = this.dashboardPage.getFirstCardBalance();
        int secondCardEndBalance = this.dashboardPage.getSecondCardBalance();

        int[] actual = {firstCardEndBalance, secondCardEndBalance};

        assertArrayEquals(actual, expected);
    }

//-1->-2----------------------------------------------------------------------------

    @Test
    public void shouldTransferZeroToSecondFromFirst() {
        int firstCardStartBalance = this.dashboardPage.getFirstCardBalance();
        int secondCardStartBalance = this.dashboardPage.getSecondCardBalance();
        int amount = 0;

        int[] expected = {firstCardStartBalance - amount, secondCardStartBalance + amount};

        TransferPage transferPage = this.dashboardPage.makeDepositSecondCard();
        this.dashboardPage = transferPage.makeDeposit(amount, DataHelper.CardsInfo.getValidCardNumbers().getFirstCardNumber());

        int firstCardEndBalance = this.dashboardPage.getFirstCardBalance();
        int secondCardEndBalance = this.dashboardPage.getSecondCardBalance();

        int[] actual = {firstCardEndBalance, secondCardEndBalance};

        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldTransferHalfToSecondFromFirst() {
        int firstCardStartBalance = this.dashboardPage.getFirstCardBalance();
        int secondCardStartBalance = this.dashboardPage.getSecondCardBalance();
        int amount = firstCardStartBalance / 2;

        int[] expected = {firstCardStartBalance - amount, secondCardStartBalance + amount};

        TransferPage transferPage = this.dashboardPage.makeDepositSecondCard();
        this.dashboardPage = transferPage.makeDeposit(amount, DataHelper.CardsInfo.getValidCardNumbers().getFirstCardNumber());

        int firstCardEndBalance = this.dashboardPage.getFirstCardBalance();
        int secondCardEndBalance = this.dashboardPage.getSecondCardBalance();

        int[] actual = {firstCardEndBalance, secondCardEndBalance};

        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldTransferAllToSecondFromFirst() {
        int firstCardStartBalance = this.dashboardPage.getFirstCardBalance();
        int secondCardStartBalance = this.dashboardPage.getSecondCardBalance();
        int amount = firstCardStartBalance;

        int[] expected = {firstCardStartBalance - amount, secondCardStartBalance + amount};

        TransferPage transferPage = this.dashboardPage.makeDepositSecondCard();
        this.dashboardPage = transferPage.makeDeposit(amount, DataHelper.CardsInfo.getValidCardNumbers().getFirstCardNumber());

        int firstCardEndBalance = this.dashboardPage.getFirstCardBalance();
        int secondCardEndBalance = this.dashboardPage.getSecondCardBalance();

        int[] actual = {firstCardEndBalance, secondCardEndBalance};

        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldTransferMoreToSecondFromFirst() {
        int firstCardStartBalance = this.dashboardPage.getFirstCardBalance();
        int secondCardStartBalance = this.dashboardPage.getSecondCardBalance();
        int amount = firstCardStartBalance * 2;

        int[] expected = {firstCardStartBalance - amount, secondCardStartBalance + amount};

        TransferPage transferPage = this.dashboardPage.makeDepositSecondCard();
        this.dashboardPage = transferPage.makeDeposit(amount, DataHelper.CardsInfo.getValidCardNumbers().getFirstCardNumber());

        int firstCardEndBalance = this.dashboardPage.getFirstCardBalance();
        int secondCardEndBalance = this.dashboardPage.getSecondCardBalance();

        int[] actual = {firstCardEndBalance, secondCardEndBalance};

        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldTransferLessToSecondFromFirst() {
        int firstCardStartBalance = this.dashboardPage.getFirstCardBalance();
        int secondCardStartBalance = this.dashboardPage.getSecondCardBalance();
        int amount = firstCardStartBalance * (-1);

        int[] expected = {firstCardStartBalance + amount, secondCardStartBalance - amount};

        TransferPage transferPage = this.dashboardPage.makeDepositSecondCard();
        this.dashboardPage = transferPage.makeDeposit(amount, DataHelper.CardsInfo.getValidCardNumbers().getFirstCardNumber());

        int firstCardEndBalance = this.dashboardPage.getFirstCardBalance();
        int secondCardEndBalance = this.dashboardPage.getSecondCardBalance();

        int[] actual = {firstCardEndBalance, secondCardEndBalance};

        assertArrayEquals(actual, expected);
    }

//-2->-1----------------------------------------------------------------------------

    @Test
    public void shouldTransferZeroToFirstFromSecond() {
        int firstCardStartBalance = this.dashboardPage.getFirstCardBalance();
        int secondCardStartBalance = this.dashboardPage.getSecondCardBalance();
        int amount = 0;

        int[] expected = {firstCardStartBalance + amount, secondCardStartBalance - amount};

        TransferPage transferPage = this.dashboardPage.makeDepositFirstCard();
        this.dashboardPage = transferPage.makeDeposit(amount, DataHelper.CardsInfo.getValidCardNumbers().getSecondCardNumber());

        int firstCardEndBalance = this.dashboardPage.getFirstCardBalance();
        int secondCardEndBalance = this.dashboardPage.getSecondCardBalance();

        int[] actual = {firstCardEndBalance, secondCardEndBalance};

        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldTransferHalfToFirstFromSecond() {
        int firstCardStartBalance = this.dashboardPage.getFirstCardBalance();
        int secondCardStartBalance = this.dashboardPage.getSecondCardBalance();
        int amount = secondCardStartBalance / 2;

        int[] expected = {firstCardStartBalance + amount, secondCardStartBalance - amount};

        TransferPage transferPage = this.dashboardPage.makeDepositFirstCard();
        this.dashboardPage = transferPage.makeDeposit(amount, DataHelper.CardsInfo.getValidCardNumbers().getSecondCardNumber());

        int firstCardEndBalance = this.dashboardPage.getFirstCardBalance();
        int secondCardEndBalance = this.dashboardPage.getSecondCardBalance();

        int[] actual = {firstCardEndBalance, secondCardEndBalance};

        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldTransferAllToFirstFromSecond() {
        int firstCardStartBalance = this.dashboardPage.getFirstCardBalance();
        int secondCardStartBalance = this.dashboardPage.getSecondCardBalance();
        int amount = secondCardStartBalance;

        int[] expected = {firstCardStartBalance + amount, secondCardStartBalance - amount};

        TransferPage transferPage = this.dashboardPage.makeDepositFirstCard();
        this.dashboardPage = transferPage.makeDeposit(amount, DataHelper.CardsInfo.getValidCardNumbers().getSecondCardNumber());

        int firstCardEndBalance = this.dashboardPage.getFirstCardBalance();
        int secondCardEndBalance = this.dashboardPage.getSecondCardBalance();

        int[] actual = {firstCardEndBalance, secondCardEndBalance};

        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldTransferMoreToFirstFromSecond() {
        int firstCardStartBalance = this.dashboardPage.getFirstCardBalance();
        int secondCardStartBalance = this.dashboardPage.getSecondCardBalance();
        int amount = secondCardStartBalance * 2;

        int[] expected = {firstCardStartBalance + amount, secondCardStartBalance - amount};

        TransferPage transferPage = this.dashboardPage.makeDepositFirstCard();
        this.dashboardPage = transferPage.makeDeposit(amount, DataHelper.CardsInfo.getValidCardNumbers().getSecondCardNumber());

        int firstCardEndBalance = this.dashboardPage.getFirstCardBalance();
        int secondCardEndBalance = this.dashboardPage.getSecondCardBalance();

        int[] actual = {firstCardEndBalance, secondCardEndBalance};

        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldTransferLessToFirstFromSecond() {
        int firstCardStartBalance = this.dashboardPage.getFirstCardBalance();
        int secondCardStartBalance = this.dashboardPage.getSecondCardBalance();
        int amount = secondCardStartBalance * (-1);

        int[] expected = {firstCardStartBalance - amount, secondCardStartBalance + amount};

        TransferPage transferPage = this.dashboardPage.makeDepositFirstCard();
        this.dashboardPage = transferPage.makeDeposit(amount, DataHelper.CardsInfo.getValidCardNumbers().getSecondCardNumber());

        int firstCardEndBalance = this.dashboardPage.getFirstCardBalance();
        int secondCardEndBalance = this.dashboardPage.getSecondCardBalance();

        int[] actual = {firstCardEndBalance, secondCardEndBalance};

        assertArrayEquals(actual, expected);
    }
}
