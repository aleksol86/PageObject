package ru.netology.domain.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.domain.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class ReplenishPage {
    private SelenideElement amountField = $("[data-test-id=\"amount\"] input");
    private SelenideElement fromField = $("[data-test-id=from] input");
    private SelenideElement toField = $("[data-test-id=to] input");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");
    private SelenideElement cancelButton = $("[data-test-id=action-cancel]");

    public void replenishCard(DataHelper.CardInfo fromCardInfo) {
        String amountToAddForTest = "500";
        amountField.setValue(amountToAddForTest);
        fromField.setValue(fromCardInfo.getCardNumber());
        transferButton.click();
    }
}
