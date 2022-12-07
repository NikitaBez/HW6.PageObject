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
        var firstCardBalance = dashboardPage.getFirstCardBalance();
        var secondCardBalance = dashboardPage.getSecondCardBalance();
        var transferAmount = DataHelper.generateValidTransferAmountValue(firstCardBalance);
        var expectedBalanceOfFirstCard = firstCardBalance + transferAmount;
        var expectedBalanceOfSecondCard = secondCardBalance - transferAmount;
        var transferPage = dashboardPage.selectCardToTransfer();
        dashboardPage = transferPage.makeValidTransfer(String.valueOf(transferAmount),secondCardInfo);
        var actualBalanceFirstCard = dashboardPage.getFirstCardBalance();
        var actualBalanceSecondCard = dashboardPage.getSecondCardBalance();
        Assertions.assertEquals(expectedBalanceOfFirstCard, actualBalanceFirstCard);
        Assertions.assertEquals(expectedBalanceOfSecondCard, actualBalanceSecondCard);



    }
}
