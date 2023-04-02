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

        addCustPage.clickCustomersTable();

        listPage.inputNameInSearchField(TestData.FIRSTNAME);

        String customerName = listPage.getOneCustomerFromTable();

        Assert.assertTrue(alertText.contains(TestData.TEXT_ON_ALERT));
        Assert.assertTrue((customerName.contains(TestData.FIRSTNAME)));
    }

    @Test
    @Feature("Проверка сортировки клиентов по имени")
    public void sortedTableByFirstName() throws InterruptedException {

        ListPage listPage = new ListPage(driver);

        listPage.clickCustomersTable();
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
        addCustPage.clickAlertAccept();

        managerPage.clickCustomersTable();

        int trueFirstNameCount = listPage.collectAllCustomersInTable(TestData.FIRSTNAME);

        listPage.inputNameInSearchField(TestData.FIRSTNAME);

        int FirstNameCount = listPage.collectAllCustomersInTable(TestData.FIRSTNAME);

        Assert.assertEquals(trueFirstNameCount, FirstNameCount);
    }
}
