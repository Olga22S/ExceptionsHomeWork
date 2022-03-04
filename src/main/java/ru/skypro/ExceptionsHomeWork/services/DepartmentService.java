package ru.skypro.ExceptionsHomeWork.services;

import ru.skypro.ExceptionsHomeWork.entities.Employee;

import java.util.List;

public interface DepartmentService {

    public Employee getDepartmentMinSalaryEmployee(int dep);

    public Employee getDepartmentMaxSalaryEmployee(int dep);

    public List<Employee> getDepartmentEmployees(int dep);

    public String getEmployees();
}
