package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;
import java.util.stream.Collectors;

public class ListPage {

    private WebDriver driver;

    @FindBy(xpath = "//button[contains(@ng-class, 'btnClass3')]")
    private WebElement CustomersTable;

    @FindBy(xpath = "//a[contains(text(), 'First Name')]")
    private WebElement FirstNameSortedButton;

    @FindBy(xpath = "//td[contains(@class, 'ng-binding')]")
    private WebElement CustomersData;

    @FindBy(xpath = "//input[contains(@type, 'text')]")
    private WebElement SearchField;

    public ListPage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ListPage clickCustomersTable() throws InterruptedException {
        //waitUntilVisible(driver, CustomersTable);
        CustomersTable.click();
        return this;
    }

    public ListPage clickFirstName() {
        FirstNameSortedButton.click();
        return this;

    }

    public ListPage checkCustomersFirstName() {

        //Сбор First Name, Last Name, Post Code в один список
        List<String> list = new ArrayList<>();
        List<WebElement> elements = CustomersTable.findElements(By.xpath("//td[contains(@class, 'ng-binding')]"));
        for (WebElement element : elements) {
            list.add(element.getText());
        }
        //Создание списка с First Name с страницы - проверяемый список
        List<String> list2 = new ArrayList<>();
        for (int i = 0; i < list.size(); i = i + 3) {
            list2.add(list.get(i));
        }

        //Создание эталонного списка с FirstName в обратном алфавитном порядке
        List<String> list3 = list2.stream()
                .sorted((Comparator.reverseOrder()))
                .collect(Collectors.toList());

        //Сравнение поступившего списка First Name с отсортированным
        Assert.assertEquals(list3, list2);
        return this;
    }

    public ListPage checkBySearch(String testName) {

        /**
         * Сбор данных из таблицы
         */
        //Сбор First Name, Last Name, Post Code в один список
        List<String> list = new ArrayList<>();
        var elements = CustomersTable.findElements(By.xpath("//td[contains(@class, 'ng-binding')]"));
        for (WebElement element : elements) {
            list.add(element.getText());
        }
        //Создание списка с First Name
        List<String> list2 = new ArrayList<>();
        for (int i = 0; i < list.size(); i = i + 3) {
            list2.add(list.get(i));
        }
        int FirstNameCount = Collections.frequency(list2, testName);

        //Отправка тестовых данных
        SearchField.sendKeys(testName);

        /**
         * Сбор данных из таблицы
         */
        //Сбор First Name, Last Name, Post Code в один список
        List<String> newlist = new ArrayList<>();
        var elements1 = CustomersTable.findElements(By.xpath("//td[contains(@class, 'ng-binding')]"));
        for (WebElement element : elements1) {
            newlist.add(element.getText());
        }
        //Создание списка с First Name
        List<String> newList2 = new ArrayList<>();
        for (int i = 0; i < newlist.size(); i = i + 3) {
            newList2.add(newlist.get(i));
        }

        int TrueFirstNameCount = Collections.frequency(list2, testName);
        Assert.assertEquals(TrueFirstNameCount, FirstNameCount);
        return this;
    }
}
//