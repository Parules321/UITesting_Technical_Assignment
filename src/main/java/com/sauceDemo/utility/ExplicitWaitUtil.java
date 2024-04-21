package com.sauceDemo.utility;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sauceDemo.testBase.TestBase;

public class ExplicitWaitUtil extends TestBase {

	private static int defaultTimeout = 25;
	WebDriverWait wait;

	public static WebElement waitForElementToBeClickable(By by) {
		return new WebDriverWait(driver, defaultTimeout).until(ExpectedConditions.elementToBeClickable(by));
	}

	public static WebElement waitForVisibilityOfElement(By by) {
		return new WebDriverWait(driver, defaultTimeout).until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public static List<WebElement> waitForVisibilityOfElements(By by) {
		return new WebDriverWait(driver, defaultTimeout).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
	}

	public static Alert waitforAlertToBePresent() {
		return new WebDriverWait(driver, defaultTimeout).until(ExpectedConditions.alertIsPresent());
	}

	public static void getTextsForWebListElements(By by, List<String> listName) {
		waitForVisibilityOfElements(by).forEach(element -> listName.add(element.getText()));

	}

	public static void selectOptionFromDrpdwn(By by, String text, String value) {
		Select sc = new Select(waitForVisibilityOfElement(by));
		try {
			sc.selectByVisibleText(text);
		} catch (Exception e) {
			sc.selectByValue(value);
		}
	}

	public static void clickOnElement(By by) {
		waitForElementToBeClickable(by).click();
	}

	public static void submitElement(By by) {
		waitForElementToBeClickable(by).submit();
	}

	public static String getText(By by) {
		return waitForVisibilityOfElement(by).getText();
	}

	public static void sendText(By by, String text) {
		waitForVisibilityOfElement(by).sendKeys(text);
	}

	public void acceptAlert() {
		if (waitforAlertToBePresent() != null) {
			driver.switchTo().alert().accept();
		}
	}

	public void dismissAlert() {
		if (waitforAlertToBePresent() != null) {
			driver.switchTo().alert().dismiss();
		}
	}

	public static void clearElement(By by) {
		waitForElementToBeClickable(by).clear();
	}

}