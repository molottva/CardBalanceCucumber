package ru.netology.steps;

import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import ru.netology.page.CardBalancePage;
import ru.netology.page.LoginPage;
import ru.netology.page.TransferPage;
import ru.netology.page.VerificationPage;

import static java.lang.Integer.*;

public class TransferSteps {
    private static LoginPage loginPage = new LoginPage();
    private static VerificationPage verificationPage = new VerificationPage();
    private static CardBalancePage cardBalancePage = new CardBalancePage();
    private static TransferPage transferPage = new TransferPage();

    @Пусть("пользователь залогинен с именем {string} и паролем {string}")
    public void auth(String login, String password) {
        loginPage.login(login, password);
    }

    @И("c проверочным кодом {string}")
    public void verify(String verifyCode) {
        verificationPage.verify(verifyCode);
    }

    @Когда("пользователь переводит {string} рублей с карты с номером {string} на свою {string} карту")
    public void transfer(String amount, String cardFrom, String indexCardTo) {
        cardBalancePage.transferClick(valueOf(indexCardTo) - 1);
        transferPage.cucumberTransfer(amount, cardFrom);
    }

    @Тогда("баланс его {string} карты из списка на главной странице должен стать {string} рублей")
    public void matchesBalance(String indexCard, String expectedBalance) {
        transferPage.cucumberMatchBalance(valueOf(indexCard) - 1, expectedBalance);
    }
}
