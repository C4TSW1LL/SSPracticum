package helpers;

import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class SortTableCustomers {

    public static List<String> sortTableCustomersByFirstName(List<WebElement> table) {

        List<String> firstNameList = new ArrayList<>();
        for (int i = 0; i < table.size(); i = i + 3) {
            firstNameList.add(table.get(i).getText());
        }
        return firstNameList;
    }

    public static List<String> sortTableCustomersByLastName(List<WebElement> table) {

        List<String> lastNameList = new ArrayList<>();
        for (int i = 1; i < table.size(); i = i + 3) {
            lastNameList.add(table.get(i).getText());
        }
        return lastNameList;
    }

    public static List<String> sortTableCustomersByPostCode(List<WebElement> table) {

        List<String> postCodeList = new ArrayList<>();
        for (int i = 2; i < table.size(); i = i + 3) {
            postCodeList.add(table.get(i).getText());
        }
        return postCodeList;
    }
}
