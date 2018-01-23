package be.sigmadelta.substratumdemo.presentation.employee;

import be.sigmadelta.substratumdemo.domain.employee.Employee;
import be.sigmadelta.substratumdemo.domain.item.Item;
import be.sigmadelta.substratumdemo.domain.util.error.Error;

/**
 * Creator: Bojan Belic
 * Date:    23/01/18
 * Company: Sigma Delta Software Solutions
 */

public interface EmployeeView {
    void showItemAssignedToEmployee(Item item, Employee employee, String msg);
    void showFailedToAssignItemToEmployee(Item item, Employee employee, Error error);
}
