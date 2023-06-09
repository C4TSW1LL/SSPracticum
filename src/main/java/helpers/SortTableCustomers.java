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

    public static List<String> getOneCustomersFromTable(List<WebElement> table) {

        List<String> oneCustomerList = new ArrayList<>();
        for (int i = 0; i <= 1; i = i + 1) {
            oneCustomerList.add(table.get(i).getText());
        }
        return oneCustomerList;
    }
}
