package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement transferHeader = $(byText("Пополнение карты"));
    private SelenideElement amountToTransferInput = $("[data-test-id=amount]");
    private SelenideElement cardFromInput = $("[data-test-id=from]");
    private SelenideElement acceptTransferButton = $("[data-test-id=action-transfer]");

    public void TransferPage() {
        transferHeader.shouldBe(Condition.visible);
    }

    public void dataEntryForTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo){
        amountToTransferInput.setValue(amountToTransfer);
        cardFromInput.setValue(cardInfo.getCardNumber());
        acceptTransferButton.click();
    }

    public DashboardPage makeValidTransfer (String amountToTransfer, DataHelper.CardInfo cardInfo){
        dataEntryForTransfer(amountToTransfer, cardInfo);
        return new DashboardPage();
    }
}
