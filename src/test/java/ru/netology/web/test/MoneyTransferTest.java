package ru.netology.web.test;

import io.opentelemetry.sdk.trace.samplers.SamplingResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPageV2;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {
    @Test
        void shouldTransferMoneyFromFirstToSecondV2() {
        var loginPage = open("http://localhost:9999", LoginPageV2.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var firstCardInfo = DataHelper.getFirstCardNumber();
        var secondCardInfo = DataHelper.getSecondCardNumber();
//        var firstCardBalance = dashboardPage.getCardBalance(firstCardInfo);
//        var secondCardBalance = dashboardPage.getCardBalance(secondCardInfo);
        var firstCardBalance = dashboardPage.getFirstCardBalance();
        var secondCardBalance = dashboardPage.getSecondCardBalance();
        var transferAmount = DataHelper.generateValidTransferAmountValue(firstCardBalance);
        var expectedBalanceOfFirstCard = firstCardBalance + transferAmount;
        var expectedBalanceOfSecondCard = secondCardBalance - transferAmount;
        var transferPage = dashboardPage.selectCardToTransfer();
//        var transferPage = dashboardPage.selectCardToTransfer(secondCardInfo);
        dashboardPage = transferPage.makeValidTransfer(String.valueOf(transferAmount),secondCardInfo);
//        var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo);
//        var actualBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo);
        var actualBalanceFirstCard = dashboardPage.getFirstCardBalance();
        var actualBalanceSecondCard = dashboardPage.getSecondCardBalance();
//
//        System.out.println("loginPage              " + loginPage);
//        System.out.println("authInfo               " + authInfo);
//        System.out.println("verificationPage       " + verificationPage);
//        System.out.println("verificationCode       " + verificationCode);
//        System.out.println("dashboardPage          " + dashboardPage);
//        System.out.println("firstCardInfo          " + firstCardInfo);
//        System.out.println("secondCardInfo         " + secondCardInfo);
//        System.out.println("firstCardBalance       " + firstCardBalance);
//        System.out.println("secondCardBalance      " + secondCardBalance);
//        System.out.println("transferAmount         " + transferAmount);
//        System.out.println("transferPage           " + transferPage);
//
//        System.out.println("ожидаемый 1 карта      " + expectedBalanceOfFirstCard);
//        System.out.println("фактический 1 карта    " + actualBalanceFirstCard);
//        System.out.println("ожидаемый 2 карта      " + expectedBalanceOfSecondCard);
//        System.out.println("фактический 2 карта    " + actualBalanceSecondCard);
        Assertions.assertEquals(expectedBalanceOfFirstCard, actualBalanceFirstCard);
        Assertions.assertEquals(expectedBalanceOfSecondCard, actualBalanceSecondCard);



    }
}
