package ru.skypro.ExceptionsHomeWork.services;

import org.springframework.stereotype.Service;
import ru.skypro.ExceptionsHomeWork.entities.Employee;
import ru.skypro.ExceptionsHomeWork.exceptions.AlreadyExistsEmployeeException;
import ru.skypro.ExceptionsHomeWork.exceptions.ArrayIsFullException;
import ru.skypro.ExceptionsHomeWork.exceptions.EmployeeNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Service
public class EmployeeService {

    private List<Employee> employees = new ArrayList<>();

    public void addEmployee(String firstName, String lastName)
            throws ArrayIsFullException, AlreadyExistsEmployeeException {
        if (employees.size() == 10) {
            throw new ArrayIsFullException("Storage is full!");
        }
        if (!isNull(findEmployee(firstName, lastName))) {
            throw new AlreadyExistsEmployeeException("This employee already exists");
        }
        employees.add(new Employee(firstName, lastName));
    }

    public void removeEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        Employee employee = findEmployee(firstName, lastName);
        if (isNull(employee)) {
            throw new EmployeeNotFoundException("This employee doesn't exist");
        }
        employees.remove(employee);
    }

    public Employee getEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        Employee employee = findEmployee(firstName, lastName);
        if (isNull(employee)) {
            throw new EmployeeNotFoundException("Employee is not found!");
        }
        return employee;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    private Employee findEmployee(String firstName, String lastName) {
        return employees.stream()
                .filter(s -> s.getFirstName().equals(firstName) && s.getLastName().equals(lastName))
                .findFirst().orElse(null);
    }
}
