package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferPage {
    private SelenideElement amountInput = $x("//span[@data-test-id='amount']//input");
    private SelenideElement fromInput = $x("//span[@data-test-id='from']//input");
    private SelenideElement toInput = $x("//span[@data-test-id='to']//input");
    private SelenideElement transferButton = $x("//button[@data-test-id='action-transfer']");
    private SelenideElement cancelButton = $x("//button[@data-test-id='action-cancel']");
    private SelenideElement errorNotification = $x("//div[@data-test-id='error-notification']");
    private SelenideElement errorButton = $x("//div[@data-test-id='error-notification']/button");
    CardBalancePage dashboard = new CardBalancePage();

    public void cucumberTransfer(String amount, String cardFrom) {
        amountInput.val(amount);
        fromInput.val(cardFrom);
        transferButton.click();
        errorNotification.should(hidden);
    }

    public void cucumberMatchBalance (int indexCard, String expectedBalance) {
        String[] balance = expectedBalance.split(" ");
        String sum = "";
        for (String tmp : balance) {
            sum = sum + tmp;
        }
        assertEquals(dashboard.getBalance(indexCard), Integer.valueOf(sum));
    }
}
