package be.sigmadelta.substratumdemo.domain.employee.models

import be.sigmadelta.substratumdemo.domain.item.Item

/**
 * Creator: Bojan Belic
 * Date:    24/01/18
 * Company: Sigma Delta Software Solutions
 */

data class SpinnerDataReturn (val itemList: List<Item>,
                              val employeeList: List<Employee>)