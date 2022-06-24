package ru.netology.steps;

import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import ru.netology.page.CardBalancePage;
import ru.netology.page.LoginPage;
import ru.netology.page.VerificationPage;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferSteps {
    LoginPage loginPage;
    VerificationPage verifyPage;
    CardBalancePage dashboard;

    @Пусть("пользователь залогинен с именем {string} и паролем {string}")
    public void auth(String login, String password) {
        open("http://localhost:9999/");
        loginPage = new LoginPage();
        var verifyPage = loginPage.login(login, password);
    }

    @И("c проверочным кодом {string}")
    public void verify(String verifyCode) {
        verifyPage = new VerificationPage();
        dashboard = verifyPage.verify(verifyCode);
    }

    @Когда("пользователь переводит {string} рублей с карты с номером {string} на свою {string} карту")
    public void successTransfer(String amount, String cardFrom, String indexCardTo) {
        var transferPage = dashboard.transferClick(Integer.valueOf(indexCardTo) - 1);
        transferPage.transfer(amount, cardFrom);
        dashboard = transferPage.checkNotification(hidden);
    }

    @Тогда("баланс его {string} карты из списка на главной странице должен стать {string} рублей")
    public void matchesBalance(String indexCard, String expectedBalance) {
        dashboard.reloadBalance();
        String[] balance = expectedBalance.split(" ");
        String sum = "";
        for (String tmp : balance) {
            sum = sum + tmp;
        }
        int actualBalance = dashboard.getBalance(Integer.valueOf(indexCard) - 1);
        assertEquals(Integer.valueOf(sum), actualBalance);
    }
}
