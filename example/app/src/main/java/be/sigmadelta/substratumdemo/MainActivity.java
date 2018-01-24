package be.sigmadelta.substratumdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import be.sigmadelta.substratum.threading.MainThread;
import be.sigmadelta.substratum.threading.ThreadExecutor;
import be.sigmadelta.substratum.usecase.UseCaseFactory;
import be.sigmadelta.substratumdemo.data.employee.EmployeeRepository;
import be.sigmadelta.substratumdemo.domain.employee.models.Employee;
import be.sigmadelta.substratumdemo.domain.employee.IEmployeePresenter;
import be.sigmadelta.substratumdemo.domain.item.Item;
import be.sigmadelta.substratumdemo.domain.util.error.Error;
import be.sigmadelta.substratumdemo.presentation.employee.EmployeePresenter;
import be.sigmadelta.substratumdemo.presentation.employee.EmployeeView;

/**
 * Creator: Bojan Belic
 * Date:    23/01/18
 * Company: Sigma Delta Software Solutions
 */

public class MainActivity extends AppCompatActivity implements EmployeeView {

    private Item _currentlySelectedItem;
    private Employee _currentlySelectedEmployee;
    private IEmployeePresenter _employeePresenter;

    private TextView _txtResult;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        _txtResult = findViewById(R.id.main_activity_result_txt);

        _employeePresenter = new EmployeePresenter(ThreadExecutor.Companion.getINSTANCE(),
                MainThread.Companion.getINSTANCE(),
                new UseCaseFactory(),
                this,
                new EmployeeRepository());

        _employeePresenter.retrieveSpinnerData();

    }

    @Override
    public void showItemAssignedToEmployee(Item item, Employee employee, String msg) {
        _txtResult.setText(msg);
    }

    @Override
    public void showFailedToAssignItemToEmployee(Item item, Employee employee, Error error) {
        _txtResult.setText(error.getMsg());
    }

    @Override
    public void showRetrievedSpinnerData(List<Item> itemList, List<Employee> employeeList, String msg) {
        final Spinner itemSpinner = findViewById(R.id.main_activity_item_spinner);
        final Spinner employeeSpinner = findViewById(R.id.main_activity_employee_spinner);

        _currentlySelectedItem = itemList.get(0);
        _currentlySelectedEmployee = employeeList.get(0);

        itemSpinner.setAdapter(
                new ArrayAdapter<Item>(this, android.R.layout.simple_spinner_dropdown_item, itemList));

        itemSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                _currentlySelectedItem = itemList.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        employeeSpinner.setAdapter(
                new ArrayAdapter<Employee>(this, android.R.layout.simple_spinner_dropdown_item, employeeList)
        );

        employeeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                _currentlySelectedEmployee = employeeList.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        findViewById(R.id.main_activity_btn).setOnClickListener(
                (v) -> _employeePresenter.assignItemToEmployee(_currentlySelectedItem, _currentlySelectedEmployee)
        );
    }

    @Override
    public void showFailedToRetrieveSpinnerData(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        _txtResult.setText(msg);
    }
}
