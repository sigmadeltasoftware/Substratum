package be.sigmadelta.substratumdemo.domain.employee.models;

/**
 * Creator: Bojan Belic
 * Date:    24/01/18
 * Company: Sigma Delta Software Solutions
 */

public enum EmployeeUseCaseErrors {
    ERROR_FAILED_TO_RETRIEVE_SPINNER_DATA(-1),
    ERROR_SPINNER_DATA_EMPTY(-2);

    private int _errorCode;

    EmployeeUseCaseErrors(int errorCode) {
        _errorCode = errorCode;
    }

    public int getErrorCode() {
        return _errorCode;
    }
}
