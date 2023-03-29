package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static helpers.Waiters.waitUntilVisible;

public class ManagerPage {

    private WebDriver driver;

    @FindBy(xpath = "//button[contains(@ng-click,'addCust()')]")
    private WebElement AddCustomerTab;

    @FindBy(xpath = "//input[contains(@placeholder, \"First Name\")]")
    private WebElement FirstNameField;

    @FindBy(xpath = "//input[contains(@placeholder, \"Last Name\")]")
    private WebElement LastNameField;

    @FindBy(xpath = "//input[contains(@placeholder, \"Post Code\")]")
    private WebElement PostCodeField;

    @FindBy(xpath = "//button[contains(@type,'submit')]")
    private WebElement AddCustomerButton;

    public ManagerPage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Выбор страницы создания нового клиента")
    public ManagerPage clickAddCustomerTab() throws InterruptedException {
        waitUntilVisible(driver, AddCustomerTab);
        AddCustomerTab.click();
        return this;
    }

    @Step("Ввод имени нового клиента")
    public ManagerPage inputFirstName(String firstName) {
        FirstNameField.sendKeys(firstName);
        return this;
    }

    @Step("Ввод фамилии нового клиента")
    public ManagerPage inputLastName(String lastName) {
        LastNameField.sendKeys(lastName);
        return this;
    }

    @Step("Ввод почтового индекса нового клиента")
    public ManagerPage inputPostCode(String postCode) {
        PostCodeField.sendKeys(postCode);
        return this;
    }
    @Step("Нажатие кнопки \"Добавить нового клиента\"")
    public ManagerPage clickAddCustomerButton() {
        AddCustomerButton.click();
        return this;
    }
    @Step("Провека текста в оповещении")
    public void checkTextOnAlert(String text) {
        Alert alert = driver.switchTo().alert();
        String text1 = alert.getText();
        Assert.assertTrue(text1.contains(text));
        alert.accept();

    }


}
