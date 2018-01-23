package be.sigmadelta.substratumdemo.data.employee;

import be.sigmadelta.substratumdemo.domain.employee.Employee;
import be.sigmadelta.substratumdemo.domain.employee.IEmployeeRepository;
import be.sigmadelta.substratumdemo.domain.item.Item;
import be.sigmadelta.substratumdemo.domain.util.status.Status;

/**
 * Creator: Bojan Belic
 * Date:    23/01/2018
 * Company: Sigma Delta Software Solutions
 */

public class EmployeeRepository implements IEmployeeRepository {

    @Override
    public Status assignItemToEmployee(Item item, Employee employee) {
        return new Status(true,
                "Successfully assigned " + item.getType() + " to " + employee.getName(),
                null);
    }
}
