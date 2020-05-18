package ru.netology.domain.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.domain.data.DataHelper;
import ru.netology.domain.page.DashboardPage;
import ru.netology.domain.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {
    int amountToAddForTest = 500;

    @Test
    void shouldCheckTransactionIsOkFromSecondToFirst() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode(authInfo);
        val dashboardPage = verificationPage.verify(verificationCode);
        val balanceOfFirstCardBefore = DashboardPage.getCurrentBalanceOfFirstCard();
        val balanceOfSecondCardBefore = DashboardPage.getCurrentBalanceOfSecondCard();
        val rechargingPage = dashboardPage.chooseFirstCardToRecharge();
        val cardInfo = DataHelper.getSecondCardInformation();
        rechargingPage.rechargeCard(cardInfo);
        val balanceAfterTransactionOnRecharged = DataHelper.checkBalanceOfRechargedCard(balanceOfFirstCardBefore, amountToAddForTest);
        val balanceAfterTransaction = DataHelper.checkBalanceOfCardFromWhereRechargeWasMade(balanceOfSecondCardBefore, amountToAddForTest);
        val balanceOfFirstCardAfter = DashboardPage.getCurrentBalanceOfFirstCard();
        val balanceOfSecondCardAfter = DashboardPage.getCurrentBalanceOfSecondCard();
        assertEquals(balanceAfterTransactionOnRecharged, balanceOfFirstCardAfter);
        assertEquals(balanceAfterTransaction, balanceOfSecondCardAfter);
    }

    @Test
    void shouldCheckTransactionIsOkFromFirstToSecond() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode(authInfo);
        val dashboardPage = verificationPage.verify(verificationCode);
        val balanceOfFirstCardBefore = DashboardPage.getCurrentBalanceOfFirstCard();
        val balanceOfSecondCardBefore = DashboardPage.getCurrentBalanceOfSecondCard();
        val rechargingPage = dashboardPage.chooseSecondCardToRecharge();
        val cardInfo = DataHelper.getFirstCardInformation();
        rechargingPage.rechargeCard(cardInfo);
        val balanceAfterTransactionOnRecharged = DataHelper.checkBalanceOfRechargedCard(balanceOfSecondCardBefore, amountToAddForTest);
        val balanceAfterTransaction = DataHelper.checkBalanceOfCardFromWhereRechargeWasMade(balanceOfFirstCardBefore, amountToAddForTest);
        val balanceOfFirstCardAfter = DashboardPage.getCurrentBalanceOfSecondCard();
        val balanceOfSecondCardAfter = DashboardPage.getCurrentBalanceOfFirstCard();
        assertEquals(balanceAfterTransactionOnRecharged, balanceOfFirstCardAfter);
        assertEquals(balanceAfterTransaction, balanceOfSecondCardAfter);
    }
}
