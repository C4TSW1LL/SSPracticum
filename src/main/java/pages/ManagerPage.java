package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


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
        AddCustomerButton.click();
        return this;
    }

    @Step("Выбор страницы со списком клиентов")
    public ManagerPage clickCustomersTable() throws InterruptedException {
        customersTableButton.click();
        return this;
    }
}
