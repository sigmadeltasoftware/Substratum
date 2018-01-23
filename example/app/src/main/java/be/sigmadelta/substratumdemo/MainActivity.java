package be.sigmadelta.substratumdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import be.sigmadelta.substratum.threading.MainThread;
import be.sigmadelta.substratum.threading.ThreadExecutor;
import be.sigmadelta.substratum.usecase.UseCaseFactory;
import be.sigmadelta.substratumdemo.data.employee.EmployeeRepository;
import be.sigmadelta.substratumdemo.domain.employee.Employee;
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

    private Item _currentlySelectedItem = _itemList.get(0);
    private Employee _currentlySelectedEmployee = _employeeList.get(0);

    private TextView _txtResult;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        _txtResult = findViewById(R.id.main_activity_result_txt);
        final Spinner itemSpinner = findViewById(R.id.main_activity_item_spinner);
        final Spinner employeeSpinner = findViewById(R.id.main_activity_employee_spinner);
        ThreadExecutor.Companion.getINSTANCE();
        MainThread.Companion.getINSTANCE();

        IEmployeePresenter presenter = new EmployeePresenter(ThreadExecutor.Companion.getINSTANCE(),
                MainThread.Companion.getINSTANCE(),
                new UseCaseFactory(),
                this,
                new EmployeeRepository());

        itemSpinner.setAdapter(
                new ArrayAdapter<Item>(this, android.R.layout.simple_spinner_dropdown_item, _itemList));

        itemSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                _currentlySelectedItem = _itemList.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        employeeSpinner.setAdapter(
                new ArrayAdapter<Employee>(this, android.R.layout.simple_spinner_dropdown_item, _employeeList)
        );

        employeeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                _currentlySelectedEmployee = _employeeList.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        findViewById(R.id.main_activity_btn).setOnClickListener(
                (v) -> presenter.assignItemToEmployee(_currentlySelectedItem, _currentlySelectedEmployee)
        );
    }

    @Override
    public void showItemAssignedToEmployee(Item item, Employee employee, String msg) {
        _txtResult.setText(msg);
    }

    @Override
    public void showFailedToAssignItemToEmployee(Item item, Employee employee, Error error) {
        _txtResult.setText(error.getMsg());
    }
}
