package be.sigmadelta.substratumdemo.presentation.employee;

import java.util.List;

import be.sigmadelta.substratum.usecase.AbstractUseCase;
import be.sigmadelta.substratumdemo.domain.employee.models.Employee;
import be.sigmadelta.substratumdemo.domain.item.Item;
import be.sigmadelta.substratumdemo.domain.util.error.Error;

/**
 * Creator: Bojan Belic
 * Date:    23/01/18
 * Company: Sigma Delta Software Solutions
 */

public interface EmployeeView {
    void showFailedToExecuteUseCase(String errorMsg);
    void showItemAssignedToEmployee(Item item, Employee employee, String msg);
    void showFailedToAssignItemToEmployee(Item item, Employee employee, Error error);
    void showRetrievedSpinnerData(List<Item> itemList, List<Employee> employeeList, String msg);
    void showFailedToRetrieveSpinnerData(String msg);
}
