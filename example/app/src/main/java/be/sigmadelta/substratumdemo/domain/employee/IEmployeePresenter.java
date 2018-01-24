package be.sigmadelta.substratumdemo.domain.employee;

import be.sigmadelta.substratumdemo.domain.employee.models.Employee;
import be.sigmadelta.substratumdemo.domain.item.Item;

/**
 * Creator: Bojan Belic
 * Date:    23/01/18
 * Company: Sigma Delta Software Solutions
 */

public interface IEmployeePresenter {
    void assignItemToEmployee(Item item, Employee employee);
    void retrieveSpinnerData();
}
