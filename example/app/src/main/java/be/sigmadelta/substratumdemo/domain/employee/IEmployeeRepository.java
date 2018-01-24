package be.sigmadelta.substratumdemo.domain.employee;

import be.sigmadelta.substratumdemo.domain.employee.models.Employee;
import be.sigmadelta.substratumdemo.domain.employee.models.SpinnerDataReturn;
import be.sigmadelta.substratumdemo.domain.item.Item;
import be.sigmadelta.substratumdemo.domain.util.status.Status;
import be.sigmadelta.substratumdemo.domain.util.status.StatusPair;

/**
 * Creator: Bojan Belic
 * Date:    23/01/18
 * Company: Sigma Delta Software Solutions
 */

public interface IEmployeeRepository {
    Status assignItemToEmployee(Item item, Employee employee);
    StatusPair<SpinnerDataReturn> retrieveSpinnerData();
}
