package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;

public class ListPage {

    private WebDriver driver;

    @FindBy(xpath = "//a[contains(text(), 'First Name')]")
    private WebElement firstNameSortedButton;

    @FindBy(xpath = "//td[contains(@class, 'ng-binding')]")
    private WebElement customersData;

    @FindBy(xpath = "//input[contains(@placeholder, 'Search Customer')]")
    private WebElement searchField;

    @FindBy(xpath = "//td[contains(@class, 'ng-binding')]")
    private List<WebElement> tableElements;

    @FindBy(xpath = "//td[contains(@class, 'ng-binding')][1]")
    private List<WebElement> tableElementName;

    @FindBy(xpath = "//tr[contains(@class, 'ng-scope')]")
    private List<WebElement> tableString;


    public ListPage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Нажатие на сортировку посредством имени")
    public void clickFirstName() {
        firstNameSortedButton.click();
    }

    @Step("Получение имен из таблицы с клиентами")
    public List<String> getCustomersFirstName() {
        List<String> customersName = new ArrayList<>();
        for (WebElement name : tableElementName) {
            customersName.add((name.getText()));
        }
        return customersName;
    }

    @Step("Получение данных из выбранной строки")
    public String getTableRowText(int rowNumber) {
        return String.join(", ", tableString.get(rowNumber - 1).getText());
    }

    @Step("Ввод имени в строку поиска")
    public void inputNameInSearchField(String searchName) {
        searchField.sendKeys(searchName);
    }

    @Step("Получение данных пользователей из таблицы")
    public List<String> collectCostumersData() {
        List<String> customersData = new ArrayList<>();
        for (WebElement name : tableElements) {
            customersData.add((name.getText()));
        }
        return customersData;
    }

    @Step("Получение размера таблицы")
    public int tableSize() {
        return tableString.size();
    }
}
