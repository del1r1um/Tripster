package ru.netology.tests;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.SQLHelper;
import ru.netology.pages.MainPage;
import ru.netology.pages.PurchasePage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataHelper.*;

public class PaymentByCreditTest extends BaseTestPage {

    MainPage mainPage = new MainPage();
    PurchasePage purchasePage = new PurchasePage();

    @BeforeEach
    void setUpForPayWithCard() {
        mainPage.payWithCredit();
    }

    @Test
    @DisplayName("Successful purchase of a tour when paying by card with APPROVED status")
    public void shouldSuccessPayIfValidApprovedCard() {
        val cardData = getApprovedCardNumber();
        purchasePage.fillPurchaseForm(cardData);
        purchasePage.successTransactionNotification();
        val paymentStatus = SQLHelper.getStatusCreditRequestEntity();
        assertEquals("APPROVED", paymentStatus);
    }

    @Test
    @DisplayName("Refusal to purchase a tour when paying by card with DECLINED status")
    public void shouldFailurePayIfValidDeclinedCard() {
        val cardData = getDeclinedCardNumber();
        purchasePage.fillPurchaseForm(cardData);
        purchasePage.failureTransactionNotification();
        val paymentStatus = SQLHelper.getStatusCreditRequestEntity();
        assertEquals("DECLINED", paymentStatus);
    }

    @Test
    @DisplayName("Card number field is empty")
    public void shouldHaveEmptyCardNumber() {
        val cardData = getEmptyNumber();
        purchasePage.fillPurchaseForm(cardData);
        purchasePage.emptyFieldError();
    }

    @Test
    @DisplayName("Number of digits in the card number is less than 16")
    public void shouldHaveNumberIfFewDigits() {
        val cardData = getCardNumberLess16Digits();
        purchasePage.fillPurchaseForm(cardData);
        purchasePage.incorrectFormatError();
    }

    @Test
    @DisplayName("Non-existent card number")
    public void shouldHaveNotExistentCardNumber() {
        val cardData = getNumberIfNotExistInBase();
        purchasePage.fillPurchaseForm(cardData);
        purchasePage.failureTransactionNotification();
    }

    @Test
    @DisplayName("Different types of cards")
    public void shouldHaveNumberIfDifferentTypeOfCard() {
        val cardData = getNumberFaker();
        purchasePage.fillPurchaseForm(cardData);
        purchasePage.failureTransactionNotification();
    }

    @Test
    @DisplayName("Month field is empty")
    public void shouldHaveEmptyMonth() {
        val cardData = getEmptyMonth();
        purchasePage.fillPurchaseForm(cardData);
        purchasePage.emptyFieldError();
    }

    @Test
    @DisplayName("Month field contains zero values")
    public void shouldHaveMonthWithZeroValue() {
        val cardData = getMonthWithZero();
        purchasePage.fillPurchaseForm(cardData);
        purchasePage.invalidCardExpirationDateError();
    }

    @Test
    @DisplayName("Month field contains a value greater than 12")
    public void shouldHaveMonthMoreThan12() {
        val cardData = getMonthMoreThen12();
        purchasePage.fillPurchaseForm(cardData);
        purchasePage.invalidCardExpirationDateError();
    }

    @Test
    @DisplayName("Month field contains one digit")
    public void shouldHaveMonthWithOneDigit() {
        val cardData = getMonthWithOneDigit();
        purchasePage.fillPurchaseForm(cardData);
        purchasePage.incorrectFormatError();
    }

    @Test
    @DisplayName("Year field is empty")
    public void shouldHaveEmptyYear() {
        val cardData = getEmptyYear();
        purchasePage.fillPurchaseForm(cardData);
        purchasePage.emptyFieldError();
    }

    @Test
    @DisplayName("Expired card number")
    public void shouldHaveYearBeforeCurrentYear() {
        val cardData = getExpiredCard();
        purchasePage.fillPurchaseForm(cardData);
        purchasePage.expiredDateError();
    }

    @Test
    @DisplayName("Year field contains zero values")
    public void shouldHaveYearWithZero() {
        val cardData = getYearWithZero();
        purchasePage.fillPurchaseForm(cardData);
        purchasePage.expiredDateError();
    }

    @Test
    @DisplayName("Value in the year field exceeds the value of the current year by 6")
    public void shouldHaveYearInTheFarFuture() {
        val cardData = getYearExceedingCurrentYearBy6();
        purchasePage.fillPurchaseForm(cardData);
        purchasePage.invalidCardExpirationDateError();
    }

    @Test
    @DisplayName("Year field contains one digit")
    public void shouldHaveYearWithOneDigit() {
        val cardData = getYearWithOneDigit();
        purchasePage.fillPurchaseForm(cardData);
        purchasePage.incorrectFormatError();
    }

    @Test
    @DisplayName("Holder name field is empty")
    public void shouldHaveEmptyHolder() {
        val cardData = getEmptyHolderName();
        purchasePage.fillPurchaseForm(cardData);
        purchasePage.emptyFieldError();
    }

    @Test
    @DisplayName("Holder name field contains only last name")
    public void shouldHaveHolderWithoutName() {
        val cardData = getHolderWithoutName();
        purchasePage.fillPurchaseForm(cardData);
        purchasePage.incorrectFormatError();
    }

    @Test
    @DisplayName("Holder name field contains first and last name in cyrillic")
    public void shouldHaveRussianHolder() {
        val cardData = getHolderWithCyrillicLetters();
        purchasePage.fillPurchaseForm(cardData);
        purchasePage.incorrectFormatError();
    }

    @Test
    @DisplayName("Holder name field contains digits")
    public void shouldHaveDigitsInHolder() {
        val cardData = getDigitsInHolderName();
        purchasePage.fillPurchaseForm(cardData);
        purchasePage.incorrectFormatError();
    }

    @Test
    @DisplayName("Holder name field contains special symbols")
    public void shouldHaveSpecialCharactersInHolder() {
        val cardData = getSpecialCharactersInHolderName();
        purchasePage.fillPurchaseForm(cardData);
        purchasePage.incorrectFormatError();
    }

    @Test
    @DisplayName("Holder name field contains a lot of spaces between first and last name")
    public void shouldHaveHolderWithManySpaces() {
        val cardData = getHolderWithManySpaces();
        purchasePage.fillPurchaseForm(cardData);
        purchasePage.incorrectFormatError();
    }

    @Test
    @DisplayName("Holder name field contains 100 latin letters")
    public void shouldHaveHolderWithManyLetters() {
        val cardData = getHolderWith100Letters();
        purchasePage.fillPurchaseForm(cardData);
        purchasePage.incorrectFormatError();
    }

    @Test
    @DisplayName("CVC/CVV field is empty")
    public void shouldHaveEmptyCvcCode() {
        val cardData = getEmptyCvcCode();
        purchasePage.fillPurchaseForm(cardData);
        purchasePage.emptyFieldError();
    }

    @Test
    @DisplayName("CVC/CVV field contains 2 digits")
    public void shouldHaveCvcCodeWithTwoDigits() {
        val cardData = getCvcCodeWithTwoDigits();
        purchasePage.fillPurchaseForm(cardData);
        purchasePage.incorrectFormatError();
    }
}