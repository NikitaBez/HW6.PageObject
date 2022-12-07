package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private SelenideElement dashboardHeader = $("[data-test-id='dashboard']");
    private SelenideElement refillButtonFirstCard = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] .button__text");
    private SelenideElement refillButtonSecondCard = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] .button__text");

//    public void DashboardPage() {
//        dashboardHeader.shouldBe(visible);
//    }

//    public int getCardBalance(DataHelper.CardInfo cardInfo){
//        var text = cards.findBy(Condition.text(cardInfo.getCardNumber().substring(12,16))).getText();
//        return extractBalance(text);
//    }

    public int getFirstCardBalance() {
        var text = cards.first().text();
        return extractBalance(text);
}

    public int getSecondCardBalance() {
        var text = cards.last().text();
        return extractBalance(text);
    }

    private int extractBalance(String text){
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
    public TransferPage selectCardToTransfer(){
        refillButtonFirstCard.click();
        return new TransferPage();
    }


}
