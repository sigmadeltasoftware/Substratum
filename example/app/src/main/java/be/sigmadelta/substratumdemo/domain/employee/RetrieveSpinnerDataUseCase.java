package be.sigmadelta.substratumdemo.domain.employee;

import be.sigmadelta.substratum.threading.Executor;
import be.sigmadelta.substratum.usecase.AbstractUseCase;
import be.sigmadelta.substratumdemo.domain.employee.models.SpinnerDataReturn;
import be.sigmadelta.substratumdemo.domain.util.error.Error;
import be.sigmadelta.substratumdemo.domain.util.status.Status;
import be.sigmadelta.substratumdemo.domain.util.status.StatusPair;

/**
 * Creator: Bojan Belic
 * Date:    24/01/18
 * Company: Sigma Delta Software Solutions
 */

public class RetrieveSpinnerDataUseCase extends AbstractUseCase implements IEmployeeUseCase {

    private RetrieveSpinnerDataCallback _callback;
    private IEmployeeRepository _repo;

    public RetrieveSpinnerDataUseCase(Executor executor, RetrieveSpinnerDataCallback callback, IEmployeeRepository repo) {
        super(executor);
        _callback = callback;
        _repo = repo;
    }

    @Override
    public void run() {
        final StatusPair<SpinnerDataReturn> statusPair = _repo.retrieveSpinnerData();
        final Status status = statusPair.getStatus();

        if (status.getHasSucceeded()) {
            if (statusPair.getPayload() != null) {
                _callback.onRetrievedSpinnerData(statusPair.getPayload().getItemList(),
                        statusPair.getPayload().getEmployeeList(),
                        "Successfully retrieved data");
            } else {
                _callback.onFailedToRetrieveSpinnerData(
                        new Error("Spinner data is empty!", ERROR_FAILED_TO_RETRIEVE_SPINNER_DATA)
                );
            }
        } else {
            _callback.onFailedToRetrieveSpinnerData(
                    new Error("Failed to retrieve spinner data", ERROR_SPINNER_DATA_EMPTY));
        }
    }
}
