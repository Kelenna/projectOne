package com.kelena.app.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.kelena.app.PageConstants.ShopHomePageConstants.PRODUCT_ONE;
import static com.kelena.app.PageConstants.ShopHomePageConstants.PRODUCT_TWO;

public class ShopHomePage {
    public WebDriver driver;

    By productOne = By.linkText(PRODUCT_ONE);
    By productTwo = By.linkText(PRODUCT_TWO);

    public ShopHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getProductOne() {
        return driver.findElement(productOne);
    }

    public WebElement getProductTwo() {
        return driver.findElement(productTwo);
    }

}
