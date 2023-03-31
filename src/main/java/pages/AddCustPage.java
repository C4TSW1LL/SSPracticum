package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddCustPage {

    private WebDriver driver;

    @FindBy(xpath = "//input[contains(@placeholder, \"First Name\")]")
    private WebElement firstNameField;

    @FindBy(xpath = "//input[contains(@placeholder, \"Last Name\")]")
    private WebElement LastNameField;

    @FindBy(xpath = "//input[contains(@placeholder, \"Post Code\")]")
    private WebElement PostCodeField;

    @FindBy(xpath = "//button[contains(@type,'submit')]")
    private WebElement AddCustomerButton;

    public AddCustPage (final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Ввод имени нового клиента")
    public AddCustPage inputFirstName(String firstName) {
        firstNameField.sendKeys(firstName);
        return this;
    }

    @Step("Ввод фамилии нового клиента")
    public AddCustPage inputLastName(String lastName) {
        LastNameField.sendKeys(lastName);
        return this;
    }

    @Step("Ввод почтового индекса нового клиента")
    public AddCustPage inputPostCode(String postCode) {
        PostCodeField.sendKeys(postCode);
        return this;
    }
    @Step("Нажатие кнопки \"Добавить нового клиента\"")
    public AddCustPage clickAddCustomerButton() {
        AddCustomerButton.click();
        return this;
    }
    @Step("Получение текста в оповещении")
    public String getTextAlert() {
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
        return alertText;
    }

    @Step("Нажатие на ОК в оповещении")
    public AddCustPage clickAlertAccept() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
        return this;
    }
}
