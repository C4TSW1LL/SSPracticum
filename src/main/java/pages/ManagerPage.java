package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static helpers.Waiters.waitUntilVisible;

public class ManagerPage {

    private WebDriver driver;

    @FindBy(xpath = "//button[contains(@ng-click,'addCust()')]")
    private WebElement AddCustomerButton;

    @FindBy(xpath = "//button[contains(@ng-class, 'btnClass3')]")
    private WebElement customersTableButton;

    public ManagerPage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

     @Step("Выбор страницы создания нового клиента")
     public ManagerPage clickAddCustomerButton() throws InterruptedException {
         //waitUntilVisible(driver, AddCustomerTab);
         AddCustomerButton.click();
         return this;
    }

    @Step("Выбор страницы со списком клиентов")
    public ManagerPage clickCustomersTable() throws InterruptedException {
        waitUntilVisible(driver, customersTableButton);
        customersTableButton.click();
        return this;
    }
}
