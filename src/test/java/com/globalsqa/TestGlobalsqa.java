package com.globalsqa;

import config.TestData;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.Test;
import pages.*;

@Epic("Globalsqa")
public class TestGlobalsqa extends TestsSetUp {


    @Test
    @Feature("Создание клиента с валидными значениями")
    public void CreatingAClientWithValidValues() throws InterruptedException {

        new ManagerPage(driver)
                .clickAddCustomerTab()
                .inputFirstName(TestData.FIRSTNAME)
                .inputLastName(TestData.LASTNAME)
                .inputPostCode(TestData.POSTCODE)
                .clickAddCustomerButton()
                .checkTextOnAlert(TestData.TEXT_ON_ALERT);
    }
    @Test
    @Feature("Проверка сортировки клиентов по имени")
    public void SortedTableByFirstName() throws InterruptedException {
        new ListPage(driver)
                .clickCustomersTable()
                .clickFirstName()
                .checkCustomersFirstName();
    }
    @Test
    @Feature("Проверка поиска клиентов по имени")
    public void CheckSearchByFirstName() throws InterruptedException {
        new ListPage(driver)
                .clickCustomersTable()
                .checkBySearch(TestData.NAME_FOR_SEARCH);
    }
}
