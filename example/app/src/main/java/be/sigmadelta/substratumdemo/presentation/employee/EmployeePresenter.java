package be.sigmadelta.substratumdemo.presentation.employee;

import java.util.List;

import be.sigmadelta.substratum.presenter.AbstractPresenter;
import be.sigmadelta.substratum.threading.Executor;
import be.sigmadelta.substratum.threading.IMainThread;
import be.sigmadelta.substratum.usecase.IUseCaseFactory;
import be.sigmadelta.substratumdemo.domain.employee.IEmployeeRepository;
import be.sigmadelta.substratumdemo.domain.employee.AssignItemToEmployeeUseCase;
import be.sigmadelta.substratumdemo.domain.employee.models.Employee;
import be.sigmadelta.substratumdemo.domain.employee.IEmployeePresenter;
import be.sigmadelta.substratumdemo.domain.employee.IEmployeeUseCase;
import be.sigmadelta.substratumdemo.domain.employee.RetrieveSpinnerDataUseCase;
import be.sigmadelta.substratumdemo.domain.item.Item;
import be.sigmadelta.substratumdemo.domain.util.error.Error;

/**
 * Creator: Bojan Belic
 * Date:    23/01/18
 * Company: Sigma Delta Software Solutions
 */

public class EmployeePresenter extends AbstractPresenter implements IEmployeePresenter,
        IEmployeeUseCase.AssignItemToEmployeeCallback, IEmployeeUseCase.RetrieveSpinnerDataCallback {

    private EmployeeView _view;
    private IEmployeeRepository _repo;

    public EmployeePresenter(Executor executor, IMainThread mainThread, IUseCaseFactory useCaseFactory, EmployeeView view,
                             IEmployeeRepository repo) {
        super(executor, mainThread, useCaseFactory);
        _view = view;
        _repo = repo;
    }

    @Override
    public void assignItemToEmployee(Item item, Employee employee) {
        getUseCaseFactory()
                .getUseCaseInstance(new AssignItemToEmployeeUseCase(getExecutor(), this, _repo, item, employee))
                .execute();
    }

    @Override
    public void retrieveSpinnerData() {
        getUseCaseFactory()
                .getUseCaseInstance(new RetrieveSpinnerDataUseCase(getExecutor(), this, _repo))
                .execute();
    }

    @Override
    public void onItemAssignedToEmployee(final Item item, final Employee employee, final String msg) {
        getMainThread().post(() -> _view.showItemAssignedToEmployee(item, employee, msg));
    }

    @Override
    public void onFailedToAssignItemToEmployee(Item item, Employee employee, Error error) {
        getMainThread().post(() -> _view.showFailedToAssignItemToEmployee(item, employee, error));
    }

    @Override
    public void onRetrievedSpinnerData(List<Item> itemList, List<Employee> employeeList, String msg) {
        getMainThread().post(() -> _view.showRetrievedSpinnerData(itemList, employeeList, msg));
    }

    @Override
    public void onFailedToRetrieveSpinnerData(Error error) {
        getMainThread().post(() -> _view.showFailedToRetrieveSpinnerData(error.getMsg()));
    }
}
