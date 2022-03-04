package ru.skypro.ExceptionsHomeWork.services;

import org.springframework.stereotype.Service;
import ru.skypro.ExceptionsHomeWork.entities.Employee;
import ru.skypro.ExceptionsHomeWork.exceptions.AlreadyExistsEmployeeException;
import ru.skypro.ExceptionsHomeWork.exceptions.EmployeeNotFoundException;
import ru.skypro.ExceptionsHomeWork.exceptions.NotCorrectNameException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.capitalize;
import static org.apache.commons.lang3.StringUtils.isAlpha;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private int uid = 1;
    private Map<Integer, Employee> employees = new HashMap<>();

    public Employee addEmployee(String firstName, String lastName, int dep, double salary) {
        checkOnlyLettersInName(firstName, lastName);
        if (!isNull(getEmployeeSet(firstName, lastName))) {
            throw new AlreadyExistsEmployeeException("This employee already exists");
        }
        Employee employee = new Employee(capitalize(firstName), capitalize(lastName), dep, salary);
        employees.put(uid++, employee);
        return employee;
    }

    public Employee removeEmployee(String firstName, String lastName) {
        checkOnlyLettersInName(firstName, lastName);
        Entry<Integer, Employee> employeeSet = getEmployeeSet(firstName, lastName);
        checkFindingEmployeeSet(employeeSet);
        Employee employee = employeeSet.getValue();
        employees.remove(employeeSet.getKey());
        return employee;
    }

    public Employee getEmployee(String firstName, String lastName) {
        checkOnlyLettersInName(firstName, lastName);
        Entry<Integer, Employee> employeeSet = getEmployeeSet(firstName, lastName);
        checkFindingEmployeeSet(employeeSet);
        return employeeSet.getValue();
    }

    public Map<Integer, Employee> getEmployees() {
        return Collections.unmodifiableMap(employees);
    }

    private void checkFindingEmployeeSet(Entry<Integer, Employee> employeeSet){
        if (isNull(employeeSet)) {
            throw new EmployeeNotFoundException("This employee doesn't exist");
        }
    }

    private void checkOnlyLettersInName(String firstName, String lastName) throws NotCorrectNameException {
        if (!isAlpha(firstName) || !isAlpha(lastName)) {
            throw new NotCorrectNameException("Name must contain only characters!");
        }
    }

    private Entry<Integer, Employee> getEmployeeSet(String firstName, String lastName) {
        return employees.entrySet()
                .stream()
                .filter(s -> s.getValue().getFirstName().equals(capitalize(firstName))
                        && s.getValue().getLastName().equals(capitalize(lastName)))
                .findFirst()
                .orElse(null);
    }
}
