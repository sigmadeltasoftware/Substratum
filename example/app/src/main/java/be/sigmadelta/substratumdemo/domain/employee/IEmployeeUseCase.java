package be.sigmadelta.substratumdemo.domain.employee;

import be.sigmadelta.substratumdemo.domain.item.Item;
import be.sigmadelta.substratumdemo.domain.util.error.Error;

/**
 * Creator: Bojan Belic
 * Date:    23/01/18
 * Company: Sigma Delta Software Solutions
 */

public interface IEmployeeUseCase {
    interface AssignItemToEmployeeCallback {
        void onItemAssignedToEmployee(Item item, Employee employee, String msg);
        void onFailedToAssignItemToEmployee(Item item, Employee employee, Error error);
    }
}
