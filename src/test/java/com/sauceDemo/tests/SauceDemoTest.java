package com.sauceDemo.tests;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.sauceDemo.testBase.TestBase;
import com.sauceDemo.utility.DataProviderUtils;
import com.sauceDemo.utility.ExplicitWaitUtil;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class SauceDemoTest extends TestBase {

	@BeforeMethod
	public void launchBrowser() {
		initialization();

	}

	@Severity(SeverityLevel.CRITICAL)
	@Epic("Account Management")
	@Feature("Login")
	@Test(dataProvider = "validLoginData", dataProviderClass = DataProviderUtils.class)
	private void validateLogin(String username, String password) {
		login(username, password);
		String actualLoginConfirmationMsgText = ExplicitWaitUtil.getText(By.cssSelector(".title"));
		Assert.assertEquals(actualLoginConfirmationMsgText, "Products");
	}

	@Severity(SeverityLevel.MINOR)
	@Epic("Product Management")
	@Feature("Sort Items")
	@Test(dataProvider = "validLoginData", dataProviderClass = DataProviderUtils.class)
	private void validateSortByPriceLowToHigh(String username, String password) {
		login(username, password);
		sortProductPricesLowToHigh();
		List<String> actualPrices = new ArrayList<>();
		ExplicitWaitUtil.getTextsForWebListElements(By.className("inventory_item_price"), actualPrices);
		List<String> expectedPrices = new ArrayList<>();
		expectedPrices.add("$7.99");
		expectedPrices.add("$9.99");
		expectedPrices.add("$15.99");
		expectedPrices.add("$15.99");
		expectedPrices.add("$29.99");
		expectedPrices.add("$49.99");
		Assert.assertEquals(actualPrices, expectedPrices, "Prices from low to high are not matching after sorting");
	}

	@Severity(SeverityLevel.NORMAL)
	@Epic("Product Management")
	@Feature("Add to Cart")
	@Test(dataProvider = "validLoginData", dataProviderClass = DataProviderUtils.class)
	private void validateAddItemsToCartFromProductsPage(String username, String password) {
		login(username, password);
		sortProductPricesLowToHigh();
		addItemsToCart();
		String product1Text = ExplicitWaitUtil.getText(By.id("item_1_title_link"));
		String product2Text = ExplicitWaitUtil.getText(By.id("item_3_title_link"));
		Assert.assertEquals(product1Text, "Sauce Labs Bolt T-Shirt",
				"Sauce Labs Bolt T-Shirt added is not showing in the cart.");
		Assert.assertEquals(product2Text, "Test.allTheThings() T-Shirt (Red)",
				"Test.allTheThings() T-Shirt (Red) added is not showing in the cart.");
	}

	@Severity(SeverityLevel.CRITICAL)
	@Epic("Product Management")
	@Feature("Add to Cart")
	@Test(dataProvider = "validLoginData", dataProviderClass = DataProviderUtils.class)
	private void validateAddItemsFromIndividualProductPage(String username, String password) {
		login(username, password);
		sortProductPricesLowToHigh();
		ExplicitWaitUtil.clickOnElement(By.id("item_1_title_link"));
		ExplicitWaitUtil.clickOnElement(By.id("add-to-cart"));
		ExplicitWaitUtil.clickOnElement(By.id("back-to-products"));
		sortProductPricesLowToHigh();
		ExplicitWaitUtil.clickOnElement(By.id("item_3_title_link"));
		ExplicitWaitUtil.clickOnElement(By.id("add-to-cart"));
		ExplicitWaitUtil.clickOnElement(By.cssSelector(".shopping_cart_link"));
		String product1Text = ExplicitWaitUtil.getText(By.id("item_1_title_link"));
		String product2Text = ExplicitWaitUtil.getText(By.id("item_3_title_link"));
		Assert.assertEquals(product1Text, "Sauce Labs Bolt T-Shirt",
				"Sauce Labs Bolt T-Shirt added is not showing in the cart.");
		Assert.assertEquals(product2Text, "Test.allTheThings() T-Shirt (Red)",
				"Test.allTheThings() T-Shirt (Red) added is not showing in the cart.");
	}

	@Severity(SeverityLevel.CRITICAL)
	@Epic("User Transactions")
	@Feature("Product Purchase")
	@Test(dataProvider = "incompleteFirstNameData", dataProviderClass = DataProviderUtils.class)
	private void validatePurchaseWithIncompleteFirstName(String username, String password, String lastName,
			String zipCode) {
		login(username, password);
		sortProductPricesLowToHigh();
		addItemsToCart();
		ExplicitWaitUtil.clickOnElement(By.id("checkout"));
		ExplicitWaitUtil.sendText(By.id("last-name"), lastName);
		ExplicitWaitUtil.sendText(By.id("postal-code"), zipCode);
		ExplicitWaitUtil.submitElement(By.id("continue"));
		String actualErrorMsgText = ExplicitWaitUtil.getText(By.cssSelector(".error-message-container"));
		Assert.assertEquals(actualErrorMsgText, "Error: First Name is required", "Error message is not matching");
	}

	@Severity(SeverityLevel.CRITICAL)
	@Epic("User Transactions")
	@Feature("Product Purchase")
	@Test(dataProvider = "incompleteLastNameData", dataProviderClass = DataProviderUtils.class)
	private void validatePurchaseWithIncompleteLastName(String username, String password, String firstName,
			String zipCode) {
		login(username, password);
		sortProductPricesLowToHigh();
		addItemsToCart();
		ExplicitWaitUtil.clickOnElement(By.id("checkout"));
		ExplicitWaitUtil.sendText(By.id("first-name"), firstName);
		ExplicitWaitUtil.sendText(By.id("postal-code"), zipCode);
		ExplicitWaitUtil.submitElement(By.id("continue"));
		String actualErrorMsgText = ExplicitWaitUtil.getText(By.cssSelector(".error-message-container"));
		Assert.assertEquals(actualErrorMsgText, "Error: Last Name is required", "Error message is not matching");

	}

	@Severity(SeverityLevel.CRITICAL)
	@Epic("User Transactions")
	@Feature("Product Purchase")
	@Test(dataProvider = "incompleteZipCodeData", dataProviderClass = DataProviderUtils.class)
	private void validatePurchaseWithIncompleteZipCode(String username, String password, String firstName,
			String lastName) {
		login(username, password);
		sortProductPricesLowToHigh();
		addItemsToCart();
		ExplicitWaitUtil.clickOnElement(By.id("checkout"));
		ExplicitWaitUtil.sendText(By.id("first-name"), firstName);
		ExplicitWaitUtil.sendText(By.id("last-name"), lastName);
		ExplicitWaitUtil.submitElement(By.id("continue"));
		String actualErrorMsgText = ExplicitWaitUtil.getText(By.cssSelector(".error-message-container"));
		Assert.assertEquals(actualErrorMsgText, "Error: Postal Code is required", "Error message is not matching");

	}

	@Severity(SeverityLevel.CRITICAL)
	@Epic("Order Processing")
	@Feature("Payment Processing")
	@Test(dataProvider = "userData", dataProviderClass = DataProviderUtils.class)
	private void validatePaymentInfo(String username, String password, String firstName, String lastName,
			String zipCode) {
		login(username, password);
		sortProductPricesLowToHigh();
		addItemsToCart();
		ExplicitWaitUtil.clickOnElement(By.id("checkout"));
		completePurchaseDetails(firstName, lastName, zipCode);
		String actualPaymentInfoText = ExplicitWaitUtil.getText(By.cssSelector(".summary_info div:nth-of-type(2)"));
		Assert.assertEquals(actualPaymentInfoText, "SauceCard #31337", "Payment info is not matching");
	}

	@Severity(SeverityLevel.CRITICAL)
	@Epic("Order Processing")
	@Feature("Shipping Information")
	@Test(dataProvider = "userData", dataProviderClass = DataProviderUtils.class)
	private void validateShippingInfo(String username, String password, String firstName, String lastName,
			String zipCode) {
		login(username, password);
		sortProductPricesLowToHigh();
		addItemsToCart();
		ExplicitWaitUtil.clickOnElement(By.id("checkout"));
		completePurchaseDetails(firstName, lastName, zipCode);
		String actualShippingInfoText = ExplicitWaitUtil.getText(By.cssSelector(".summary_info div:nth-of-type(4)"));
		Assert.assertEquals(actualShippingInfoText, "Free Pony Express Delivery!", "Shipping info is not matching");
	}

	@Severity(SeverityLevel.CRITICAL)
	@Epic("Order Processing")
	@Feature("Order Price Information")
	@Test(dataProvider = "userData", dataProviderClass = DataProviderUtils.class)
	private void validatePrice(String username, String password, String firstName, String lastName, String zipCode) {
		login(username, password);
		sortProductPricesLowToHigh();
		addItemsToCart();
		ExplicitWaitUtil.clickOnElement(By.id("checkout"));
		completePurchaseDetails(firstName, lastName, zipCode);
		String actualItemTotalPriceText = ExplicitWaitUtil.getText(By.cssSelector(".summary_subtotal_label"));
		String actualTaxText = ExplicitWaitUtil.getText(By.cssSelector(".summary_tax_label"));
		String actualTotalPriceText = ExplicitWaitUtil.getText(By.cssSelector(".summary_total_label"));
		Assert.assertEquals(actualItemTotalPriceText, "Item total: $31.98", "Item total price info is not matching");
		Assert.assertEquals(actualTaxText, "Tax: $2.56", "Tax price info is not matching");
		Assert.assertEquals(actualTotalPriceText, "Total: $34.54", "Total price info is not matching");
	}

	@Severity(SeverityLevel.BLOCKER)
	@Epic("User Transactions")
	@Feature("Product Purchase")
	@Test(dataProvider = "userData", dataProviderClass = DataProviderUtils.class)
	private void validateCompletePurchase(String username, String password, String firstName, String lastName,
			String zipCode) {
		login(username, password);
		sortProductPricesLowToHigh();
		addItemsToCart();
		ExplicitWaitUtil.clickOnElement(By.id("checkout"));
		completePurchaseDetails(firstName, lastName, zipCode);
		ExplicitWaitUtil.clickOnElement(By.id("finish"));
		String actualconfirmationMsgText = ExplicitWaitUtil.getText(By.cssSelector(".complete-header"));
		Assert.assertEquals(actualconfirmationMsgText, "Thank you for your order!",
				"Order confirmation text is not matching.");

	}

	@Severity(SeverityLevel.NORMAL)
	@Epic("Account Management")
	@Feature("Logout")
	@Test(dataProvider = "validLoginData", dataProviderClass = DataProviderUtils.class)
	private void validateLogout(String username, String password) {
		login(username, password);
		ExplicitWaitUtil.clickOnElement(By.id("react-burger-menu-btn"));
		ExplicitWaitUtil.clickOnElement(By.xpath("//a[text()='Logout']"));
		String actualLogoutConfirmationTxt = ExplicitWaitUtil
				.getText(By.xpath("//h4[text() = 'Accepted usernames are:']"));
		Assert.assertEquals(actualLogoutConfirmationTxt, "Accepted usernames are:",
				"Logout confirmation text is not matching");

	}

	@Severity(SeverityLevel.MINOR)
	@Epic("Account Management")
	@Feature("Login")
	@Test(dataProvider = "invalidUsernameData", dataProviderClass = DataProviderUtils.class)
	private void validateLoginWithInvalidUsername(String username, String password) {
		ExplicitWaitUtil.sendText(By.id("user-name"), username);
		ExplicitWaitUtil.sendText(By.id("password"), password);
		ExplicitWaitUtil.submitElement(By.id("login-button"));
		String actualInvalidUserNameMsgText = ExplicitWaitUtil.getText(By.cssSelector(".error-message-container"));
		Assert.assertEquals(actualInvalidUserNameMsgText,
				"Epic sadface: Username and password do not match any user in this service");
	}

	@Severity(SeverityLevel.MINOR)
	@Epic("Account Management")
	@Feature("Login")
	@Test(dataProvider = "invalidPasswordData", dataProviderClass = DataProviderUtils.class)
	private void validateLoginWithInvalidPassword(String username, String password) {
		ExplicitWaitUtil.sendText(By.id("user-name"), username);
		ExplicitWaitUtil.sendText(By.id("password"), password);
		ExplicitWaitUtil.submitElement(By.id("login-button"));
		String actualInvalidPasswordMsgText = ExplicitWaitUtil.getText(By.cssSelector(".error-message-container"));
		Assert.assertEquals(actualInvalidPasswordMsgText,
				"Epic sadface: Username and password do not match any user in this service");

	}

	private void login(String username, String password) {
		ExplicitWaitUtil.sendText(By.id("user-name"), username);
		ExplicitWaitUtil.sendText(By.id("password"), password);
		ExplicitWaitUtil.submitElement(By.id("login-button"));
	}

	private void sortProductPricesLowToHigh() {
		ExplicitWaitUtil.selectOptionFromDrpdwn(By.cssSelector(".product_sort_container"), "Price (low to high)",
				"lohi");
	}

	private void addItemsToCart() {
		ExplicitWaitUtil.clickOnElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt"));
		ExplicitWaitUtil.clickOnElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)"));
		ExplicitWaitUtil.clickOnElement(By.cssSelector(".shopping_cart_link"));
	}

	private void completePurchaseDetails(String firstName, String lastName, String zipCode) {
		ExplicitWaitUtil.sendText(By.id("first-name"), firstName);
		ExplicitWaitUtil.sendText(By.id("last-name"), lastName);
		ExplicitWaitUtil.sendText(By.id("postal-code"), zipCode);
		ExplicitWaitUtil.submitElement(By.id("continue"));
	}

	@AfterMethod
	public void closeBrowser() {
		tearDown();
	}
}
