package ru.netology.transfer.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {
    private SelenideElement dashboardHeader = $("[data-test-id=dashboard]");
    private ElementsCollection cardsText = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private ElementsCollection depositButtons = $$("[data-test-id=action-deposit]");

    public DashboardPage() {
        dashboardHeader.shouldBe(Condition.visible);
    }

    private int extractBalance(String text) {
        int start = text.indexOf(balanceStart);
        int finish = text.indexOf(balanceFinish);
        String value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public int getFirstCardBalance() {
        String text = cardsText.first().text();
        return extractBalance(text);
    }

    public int getSecondCardBalance() {
        String text = cardsText.last().text();
        return extractBalance(text);
    }

    public TransferPage makeDepositFirstCard () {
        depositButtons.first().click();
        return new TransferPage();
    }

    public TransferPage makeDepositSecondCard () {
        depositButtons.last().click();
        return new TransferPage();
    }
}
