package ru.skypro.ExceptionsHomeWork.services;

import org.springframework.stereotype.Service;
import ru.skypro.ExceptionsHomeWork.entities.Employee;
import ru.skypro.ExceptionsHomeWork.exceptions.AlreadyExistsEmployeeException;
import ru.skypro.ExceptionsHomeWork.exceptions.ArrayIsFullException;
import ru.skypro.ExceptionsHomeWork.exceptions.EmployeeNotFoundException;

import static java.util.Objects.isNull;

@Service
public class EmployeeService {

    private Employee[] employees = new Employee[10];
    private int counter = 0;

    public Employee addEmployee(String firstName, String lastName)
            throws ArrayIsFullException, AlreadyExistsEmployeeException {
        if (counter == 10) {
            throw new ArrayIsFullException("Array is full!");
        }
        if (!isNull(findEmployee(firstName, lastName))) {
            throw new AlreadyExistsEmployeeException("This employee already exists");
        }
        Employee employee = new Employee(firstName, lastName);
        employees[counter++] = employee;
        return employee;
    }

    public Employee removeEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        if (isNull(findEmployee(firstName, lastName))) {
            throw new EmployeeNotFoundException("This employee doesn't exist");
        }
        Employee employee;
        for (int i = 0; i < counter; i++) {
            if (employees[i].getFirstName().equals(firstName)
                    && employees[i].getLastName().equals(lastName)) {
                employee = employees[i];
                employees[i] = null;
                moveArray(i);
                counter--;
                return employee;
            }
        }
        return null;
    }

    public Employee getEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        Employee employee = findEmployee(firstName, lastName);
        if (isNull(employee)) {
            throw new EmployeeNotFoundException("Employee is not found!");
        }
        return employee;
    }

    private Employee findEmployee(String firstName, String lastName) {
        for (int i = 0; i < counter; i++) {
            if (employees[i].getFirstName().equals(firstName)
                    && employees[i].getLastName().equals(lastName)) {
                return employees[i];
            }
        }
        return null;
    }

    private void moveArray(int i) {
        for (int j = i; j < counter - 1; j++) {
            employees[j] = employees[j + 1];
        }
        if (counter != 10) {
            employees[counter] = null;
        }
    }
}
