package com.globalsqa;

import config.TestData;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.Assert;
import org.junit.Test;
import pages.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Epic("Globalsqa")
public class TestGlobalsqa extends TestsSetUp {


    @Test
    @Feature("Создание клиента с валидными значениями")
    public void creatingAClientWithValidValues() throws InterruptedException {

        ManagerPage managerPage = new ManagerPage(driver);
        ListPage listPage = new ListPage(driver);
        AddCustPage addCustPage = new AddCustPage(driver);

        managerPage.clickAddCustomerButton();

        addCustPage.createNewCustomer(TestData.FIRSTNAME, TestData.LASTNAME, TestData.POSTCODE);

        String alertText = addCustPage.getTextAlertAndAccept();

        managerPage.clickCustomersTable();

        String textFromLine = listPage.getTableRowText(6);

        listPage.inputNameInSearchField(TestData.FIRSTNAME);

        String textAfterSearch = listPage.getTableRowText(1);

        Assert.assertTrue(alertText.contains(TestData.TEXT_ON_ALERT));
        Assert.assertTrue(textFromLine.equals(textAfterSearch));
    }

    @Test
    @Feature("Проверка сортировки клиентов по имени")
    public void sortedTableByFirstName() throws InterruptedException {

        ManagerPage managerPage = new ManagerPage(driver);
        ListPage listPage = new ListPage(driver);

        managerPage.clickCustomersTable();
        listPage.clickFirstName();

        List<String> firstNameList = listPage.getCustomersFirstName();

        Assert.assertEquals(firstNameList.stream()
                .sorted((Comparator.reverseOrder()))
                .collect(Collectors.toList()), firstNameList);
    }

    @Test
    @Feature("Проверка поиска клиентов по имени")
    public void checkSearchByFirstName() throws InterruptedException {

        AddCustPage addCustPage = new AddCustPage(driver);
        ManagerPage managerPage = new ManagerPage(driver);
        ListPage listPage = new ListPage(driver);

        managerPage.clickAddCustomerButton();

        addCustPage.createNewCustomer(TestData.FIRSTNAME, TestData.LASTNAME, TestData.POSTCODE);
        String alertText = addCustPage.getTextAlertAndAccept();

        managerPage.clickCustomersTable();

        listPage.inputNameInSearchField(TestData.FIRSTNAME);

        List<String> customersTableDataAfterSearch = listPage.collectCostumersData();

        Assert.assertTrue(alertText.contains(TestData.TEXT_ON_ALERT));
        Assert.assertTrue(customersTableDataAfterSearch.contains(TestData.FIRSTNAME));
    }
}
