package be.sigmadelta.substratumdemo.data.employee;

import java.util.Arrays;
import java.util.List;

import be.sigmadelta.substratumdemo.domain.employee.models.Employee;
import be.sigmadelta.substratumdemo.domain.employee.IEmployeeRepository;
import be.sigmadelta.substratumdemo.domain.employee.models.SpinnerDataReturn;
import be.sigmadelta.substratumdemo.domain.item.Item;
import be.sigmadelta.substratumdemo.domain.util.status.Status;
import be.sigmadelta.substratumdemo.domain.util.status.StatusPair;

/**
 * Creator: Bojan Belic
 * Date:    23/01/2018
 * Company: Sigma Delta Software Solutions
 */

public class EmployeeRepository implements IEmployeeRepository {

    private final List<Item> _itemList =  Arrays.asList(
            new Item("Stapler"),
            new Item("Pen"),
            new Item("Keyboard")
    );

    private final List<Employee> _employeeList = Arrays.asList(
            new Employee("John", "Accountant"),
            new Employee("Mary", "Secretary"),
            new Employee("Bob", "Android developer")
    );

    @Override
    public Status assignItemToEmployee(Item item, Employee employee) {
        return new Status(true,
                "Successfully assigned " + item.getType() + " to " + employee.getName(),
                null);
    }

    @Override
    public StatusPair<SpinnerDataReturn> retrieveSpinnerData() {
        return new StatusPair<>(
                new Status(true, "Successfully retrieved spinner data!", null),
                new SpinnerDataReturn(_itemList, _employeeList)
        );
    }
}
