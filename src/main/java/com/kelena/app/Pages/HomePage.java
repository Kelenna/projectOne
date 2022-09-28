package com.kelena.app.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.kelena.app.PageConstants.HomePageConstants.*;

public class HomePage {
    public WebDriver driver;

    By toggle = By.xpath(TOGGLE_MENU);
    By homePageLink = By.linkText(HOMEPAGE_LINK);
    By accordionLink = By.linkText(ACCORDION_LINK);
    By browserTabLink = By.linkText(BROWSER_TAB_LINK);
    By buttonsLink = By.linkText(BUTTONS_LINK);
    By calcLink = By.linkText(CALC_LINK);
    By contactUsLink = By.linkText(CONTACT_LINK);
    By datePickerLink = By.linkText(DATE_PICKER_LINK);
    By dropdownLink = By.linkText(DROPDOWN_LINK);
    By fileUpload = By.linkText(FILE_UPLOAD_LINK);
    By hiddenElementsLink = By.linkText(HIDDEN_ELEMENTS_LINK);
    By testStoreLink = By.linkText(TEST_STORE_LINK);

    public HomePage (WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getToggle() {
        return driver.findElement(toggle);
    }

    public WebElement getHomepageLink() {
        return driver.findElement(homePageLink);
    }

    public WebElement getAccordionLink() {
        return driver.findElement(accordionLink);
    }

    public WebElement getBrowserTabLink() {
        return driver.findElement(browserTabLink);
    }

    public WebElement getButtonLink() {
        return driver.findElement(buttonsLink);
    }

    public WebElement getCalcLink() {
        return driver.findElement(calcLink);
    }

    public WebElement getContactUsLink() {
        return driver.findElement(contactUsLink);
    }

    public WebElement getDatePickerLink() {
        return driver.findElement(datePickerLink);
    }

    public WebElement getDropdownLink() {
        return driver.findElement(dropdownLink);
    }

    public WebElement getFileUploadLink() {
        return driver.findElement(fileUpload);
    }

    public WebElement getHiddenElementsLink() {
        return driver.findElement(hiddenElementsLink);
    }

    public WebElement getTestStoreLink() {
        return driver.findElement(testStoreLink);
    }
}
