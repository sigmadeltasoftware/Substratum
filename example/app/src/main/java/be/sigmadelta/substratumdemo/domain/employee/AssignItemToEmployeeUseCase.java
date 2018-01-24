package be.sigmadelta.substratumdemo.domain.employee;

import be.sigmadelta.substratum.threading.Executor;
import be.sigmadelta.substratum.usecase.AbstractUseCase;
import be.sigmadelta.substratumdemo.domain.employee.models.Employee;
import be.sigmadelta.substratumdemo.domain.item.Item;
import be.sigmadelta.substratumdemo.domain.util.status.Status;

/**
 * Creator: Bojan Belic
 * Date:    23/01/18
 * Company: Sigma Delta Software Solutions
 */

public class AssignItemToEmployeeUseCase extends AbstractUseCase implements IEmployeeUseCase {

    private AssignItemToEmployeeCallback _callback;
    private IEmployeeRepository _repo;
    private Item _item;
    private Employee _employee;

    public AssignItemToEmployeeUseCase(Executor executor, AssignItemToEmployeeCallback callback, IEmployeeRepository repo, Item item, Employee employee) {
        super(executor);
        _callback = callback;
        _repo = repo;
        _item = item;
        _employee = employee;
    }

    @Override
    public void run() {

        final Status operationStatus = _repo.assignItemToEmployee(_item, _employee);

        if (operationStatus.getHasSucceeded()) {
            _callback.onItemAssignedToEmployee(_item, _employee, operationStatus.getSuccessMsg());
        } else {
            _callback.onFailedToAssignItemToEmployee(_item, _employee, operationStatus.getError());
        }
    }
}
