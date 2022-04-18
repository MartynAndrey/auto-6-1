package ru.netology.transfer.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement dashboardHeader = $("[data-test-id=dashboard]");
    private SelenideElement amountField = $("[data-test-id=amount] input");
    private SelenideElement fromField = $("[data-test-id=from] input");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");

    public TransferPage() {
        dashboardHeader.shouldBe(Condition.visible);
    }

    private void clearField(SelenideElement field) {
        if (!field.getValue().isEmpty()) {
            field.sendKeys(Keys.END);
            field.sendKeys(Keys.SHIFT, Keys.HOME);
            field.sendKeys(Keys.DELETE);
        }
    }

    public DashboardPage makeDeposit(int amount, String from) {
        clearField(amountField);
        amountField.setValue(Integer.toString(amount));
        clearField(fromField);
        fromField.setValue(from);
        transferButton.click();
        return new DashboardPage();
    }
}
