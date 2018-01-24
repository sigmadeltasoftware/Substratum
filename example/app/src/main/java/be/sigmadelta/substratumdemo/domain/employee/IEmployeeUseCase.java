package be.sigmadelta.substratumdemo.domain.employee;

import java.util.List;

import be.sigmadelta.substratumdemo.domain.employee.models.Employee;
import be.sigmadelta.substratumdemo.domain.item.Item;
import be.sigmadelta.substratumdemo.domain.util.error.Error;

/**
 * Creator: Bojan Belic
 * Date:    23/01/18
 * Company: Sigma Delta Software Solutions
 */

public interface IEmployeeUseCase {

    int ERROR_FAILED_TO_RETRIEVE_SPINNER_DATA = -1;
    int ERROR_SPINNER_DATA_EMPTY = -2;

    interface AssignItemToEmployeeCallback {
        void onItemAssignedToEmployee(Item item, Employee employee, String msg);
        void onFailedToAssignItemToEmployee(Item item, Employee employee, Error error);
    }

    interface RetrieveSpinnerDataCallback {
        void onRetrievedSpinnerData(List<Item> itemList, List<Employee> employeeList, String msg);
        void onFailedToRetrieveSpinnerData(Error error);
    }
}
