package ru.skypro.ExceptionsHomeWork.services;

import org.springframework.stereotype.Service;
import ru.skypro.ExceptionsHomeWork.model.Employee;
import ru.skypro.ExceptionsHomeWork.exceptions.AlreadyExistsEmployeeException;
import ru.skypro.ExceptionsHomeWork.exceptions.ArrayIsFullException;
import ru.skypro.ExceptionsHomeWork.exceptions.EmployeeNotFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import static java.util.Objects.isNull;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private int uid = 1;
    private Map<Integer, Employee> employees = new HashMap<>();

    public Employee addEmployee(String firstName, String lastName, int dep, double salary) {
        if (employees.size() == 10) {
            throw new ArrayIsFullException("Storage is full!");
        }
        if (!isNull(getEmployeeSet(firstName, lastName))) {
            throw new AlreadyExistsEmployeeException("This employee already exists");
        }
        Employee employee = new Employee(firstName, lastName, dep, salary);
        employees.put(uid++, employee);
        return employee;
    }

    public Employee removeEmployee(String firstName, String lastName){
        Entry<Integer, Employee> employeeSet = getEmployeeSet(firstName, lastName);
        if (isNull(employeeSet)) {
            throw new EmployeeNotFoundException("This employee doesn't exist");
        }
        Employee employee = employeeSet.getValue();
        employees.remove(employeeSet.getKey());
        return employee;
    }

    public Employee getEmployee(String firstName, String lastName){
        Entry<Integer, Employee> employeeSet = getEmployeeSet(firstName, lastName);
        if (isNull(employeeSet)) {
            throw new EmployeeNotFoundException("Employee is not found!");
        }
        return employeeSet.getValue();
    }

    public Map<Integer, Employee> getEmployees() {
        return employees;
    }

    private Entry<Integer, Employee> getEmployeeSet(String firstName, String lastName) {
        return employees.entrySet()
                .stream()
                .filter(s -> s.getValue().getFirstName().equals(firstName) && s.getValue().getLastName().equals(lastName))
                .findFirst()
                .orElse(null);
    }
}
