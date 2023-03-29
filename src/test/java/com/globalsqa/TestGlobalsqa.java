package com.globalsqa;

import config.TestData;
import org.junit.Test;
import pages.ListPage;
import pages.ManagerPage;

public class TestGlobalsqa extends TestsSetUp {

    @Test
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
    public void SortedTableByFirstName() throws InterruptedException {
        new ListPage(driver)
                .clickCustomersTable()
                .clickFirstName()
                .checkCustomersFirstName();
    }
    @Test
    public void CheckSearchByFirstName() throws InterruptedException {
        new ListPage(driver)
                .clickCustomersTable()
                .checkBySearch(TestData.NAME_FOR_SEARCH);
    }

}
