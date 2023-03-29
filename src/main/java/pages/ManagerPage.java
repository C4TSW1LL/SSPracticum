package pages;

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

    public ManagerPage clickAddCustomerTab() throws InterruptedException {
        waitUntilVisible(driver, AddCustomerTab);
        AddCustomerTab.click();
        return this;
    }

    public ManagerPage inputFirstName(String firstName) {
        FirstNameField.sendKeys(firstName);
        return this;
    }

    public ManagerPage inputLastName(String lastName) {
        LastNameField.sendKeys(lastName);
        return this;
    }

    public ManagerPage inputPostCode(String postCode) {
        PostCodeField.sendKeys(postCode);
        return this;
    }

    public ManagerPage clickAddCustomerButton() {
        AddCustomerButton.click();
        return this;
    }

    public void checkTextOnAlert(String text) {
        Alert alert = driver.switchTo().alert();
        String text1 = alert.getText();
        Assert.assertTrue(text1.contains(text));
        alert.accept();

    }


}
