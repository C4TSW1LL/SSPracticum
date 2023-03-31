package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;

import static helpers.SortTableCustomers.sortTableCustomersByFirstName;

public class ListPage {

    private WebDriver driver;

    @FindBy(xpath = "//button[contains(@ng-class, 'btnClass3')]")
    private WebElement customersTableButton;

    @FindBy(xpath = "//a[contains(text(), 'First Name')]")
    private WebElement firstNameSortedButton;

    @FindBy(xpath = "//td[contains(@class, 'ng-binding')]")
    private WebElement customersData;

    @FindBy(xpath = "//input[contains(@type, 'text')]")
    private WebElement searchField;

    @FindBy(xpath = "//td[contains(@class, 'ng-binding')]")
    private List<WebElement> tableElement;

    public ListPage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Выбор страницы со списком клиентов")
    public ListPage clickCustomersTable() throws InterruptedException {
        customersTableButton.click();
        return this;
    }

    @Step("Нажатие на сортировку посредством имени")
    public ListPage clickFirstName() {
        firstNameSortedButton.click();
        return this;
    }

    @Step("Проверка корректности сортировки таблицы")
    public List<String> getCustomersFirstName() {
        return sortTableCustomersByFirstName(tableElement);
    }

    @Step("Проверка отображения всех пользователей по поиску")
    public int collectAllCustomersInTable(String testName) {
        return Collections.frequency(sortTableCustomersByFirstName(tableElement), testName);
    }

    @Step("Ввод имени в строку поиска")
    public ListPage inputNameInSearchField(String searchName) {
        searchField.sendKeys(searchName);
        return this;
    }
}
