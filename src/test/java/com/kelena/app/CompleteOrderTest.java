package com.kelena.app;

import com.kelena.app.Pages.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class CompleteOrderTest extends BasePage {
    public CompleteOrderTest() throws IOException {
    }

    @BeforeTest
    public void setup() throws IOException {
        driver = getDriver();
        driver.get(getUrl());
    }

    @AfterTest
    public void tearDown() {
        driver.close();
        driver = null;
    }

    @Test
    public void endToEndTest() throws InterruptedException {
        HomePage home = new HomePage(driver);
        home.getToggle().click();
        home.getTestStoreLink()
                .click();

        ShopHomePage shopHome = new ShopHomePage(driver);
        shopHome.getProductOne().click();

        ProductPage shopProd = new ProductPage(driver);
        Select option = new Select(shopProd.getSizeOption());
        option.selectByVisibleText("M");
        shopProd.getQuantIncrease().click();
        shopProd.getAddToCartBtn().click();

        Thread.sleep(5000);

        ShopContentPanelPage cPanel = new ShopContentPanelPage(driver);
        cPanel.getCheckoutBtn().click();

        ShoppingCartPage cart = new ShoppingCartPage(driver);
        cart.getHavePromo().click();
        cart.getPromoTextbox().sendKeys("20OFF");
        cart.getPromoAddBtn().click();
        Thread.sleep(5000);
        cart.getProceedCheckoutBtn().click();

        // creating an object of the order personal information page
        OrderFormPersInfoPage pInfo = new OrderFormPersInfoPage(driver);
        pInfo.getGenderMr().click();
        pInfo.getFirstNameField().sendKeys("John");
        pInfo.getLastnameField().sendKeys("Smith");
        pInfo.getEmailField().sendKeys("johnsmith@test.com");
        pInfo.getTermsConditionsCheckbox().click();
        pInfo.getContinueBtn().click();

        // creating an object of the order delivery info page
        OrderFormDeliveryPage orderDelivery = new OrderFormDeliveryPage(driver);
        orderDelivery.getAddressField().sendKeys("123 Main Street");
        orderDelivery.getCityField().sendKeys("Houston");
        Select state = new Select(orderDelivery.getStateDropdown());
        state.selectByVisibleText("Texas");
        orderDelivery.getPostcodeField().sendKeys("77021");
        orderDelivery.getContinueBtn().click();

        // creating an object of the shipping method page
        OrderFormShippingMethodPage shipMethod = new OrderFormShippingMethodPage(driver);
        shipMethod.getDeliveryMsgTextbox().sendKeys("If I am not in, please leave my delivery on my porch.");
        shipMethod.getContinueBtn().click();

        // creating an object of the payment options page
        OrderFormPaymentPage orderPay = new OrderFormPaymentPage(driver);
        orderPay.getPayByCheckRadioBtn().click();
        orderPay.getTermsConditionsCheckbox().click();
        orderPay.getOrderBtn().click();
    }
}
