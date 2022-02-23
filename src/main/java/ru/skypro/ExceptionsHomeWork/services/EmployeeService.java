package ru.skypro.ExceptionsHomeWork.services;

import ru.skypro.ExceptionsHomeWork.model.Employee;

public interface EmployeeService {

    Employee addEmployee(String firstName, String lastName, int dep, double salary);

    Employee removeEmployee(String firstName, String lastName);

    Employee getEmployee(String firstName, String lastName);
}
