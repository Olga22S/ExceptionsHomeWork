package ru.skypro.ExceptionsHomeWork.services;

import ru.skypro.ExceptionsHomeWork.entities.Employee;

import java.util.Map;

public interface EmployeeService {

    public Employee addEmployee(String firstName, String lastName, int dep, double salary);

    public Employee removeEmployee(String firstName, String lastName);

    public Employee getEmployee(String firstName, String lastName);

    public Map<Integer, Employee> getEmployees();
}
