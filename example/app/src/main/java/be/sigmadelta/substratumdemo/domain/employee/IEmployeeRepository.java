package be.sigmadelta.substratumdemo.domain.employee;

import be.sigmadelta.substratumdemo.domain.item.Item;
import be.sigmadelta.substratumdemo.domain.util.status.Status;

/**
 * Creator: Bojan Belic
 * Date:    23/01/18
 * Company: Sigma Delta Software Solutions
 */

public interface IEmployeeRepository {
    Status assignItemToEmployee(Item item, Employee employee);
}
