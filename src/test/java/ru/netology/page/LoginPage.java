package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {
    private SelenideElement loginInput = $x("//span[@data-test-id='login']//input");
    private SelenideElement passwordInput = $x("//span[@data-test-id='password']//input");
    private SelenideElement loginButton = $x("//button[@data-test-id='action-login']");

    public VerificationPage login(String login, String password) {
        open("http://localhost:9999");
        loginInput.val(login);
        passwordInput.val(password);
        loginButton.click();
        return new VerificationPage();
    }
}
