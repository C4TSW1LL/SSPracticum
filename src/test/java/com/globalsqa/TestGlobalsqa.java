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
    public void CreatingAClientWithValidValues() throws InterruptedException {

        ManagerPage managerPage = new ManagerPage(driver);
        ListPage listPage = new ListPage(driver);

        managerPage.clickCustomersTable();

        int firstName = listPage.collectAllCustomersInTable(TestData.FIRSTNAME);

        managerPage.clickAddCustomerButton();

        String alertText = new AddCustPage(driver)
                .inputFirstName(TestData.FIRSTNAME)
                .inputLastName(TestData.LASTNAME)
                .inputPostCode(TestData.POSTCODE)
                .clickAddCustomerButton()
                .getTextAlertAndQuit();


        managerPage.clickCustomersTable();

        int firstNameCount = listPage.collectAllCustomersInTable(TestData.FIRSTNAME);

        Assert.assertTrue(alertText.contains(TestData.TEXT_ON_ALERT));
        Assert.assertNotEquals(firstNameCount, firstName);
    }

    @Test
    @Feature("Проверка сортировки клиентов по имени")
    public void SortedTableByFirstName() throws InterruptedException {

        List<String> firstNameList = new ListPage(driver)
                .clickCustomersTable()
                .clickFirstName()
                .getCustomersFirstName();

        Assert.assertEquals(firstNameList.stream()
                .sorted((Comparator.reverseOrder()))
                .collect(Collectors.toList()), firstNameList);
    }

    @Test
    @Feature("Проверка поиска клиентов по имени")
    public void CheckSearchByFirstName() throws InterruptedException {

        AddCustPage addCustPage = new AddCustPage(driver);
        ManagerPage managerPage = new ManagerPage(driver);
        ListPage listPage = new ListPage(driver);

        managerPage.clickAddCustomerButton();

        addCustPage.inputFirstName(TestData.FIRSTNAME);
        addCustPage.inputLastName(TestData.LASTNAME);
        addCustPage.inputPostCode(TestData.POSTCODE);
        addCustPage.clickAddCustomerButton();
        addCustPage.clickAlertAccept();

        managerPage.clickCustomersTable();

        int trueFirstNameCount = new ListPage(driver)
                .collectAllCustomersInTable(TestData.FIRSTNAME);

        listPage.inputNameInSearchField(TestData.FIRSTNAME);

        int FirstNameCount = new ListPage(driver)
                .collectAllCustomersInTable(TestData.FIRSTNAME);

        Assert.assertEquals(trueFirstNameCount, FirstNameCount);
    }
}
