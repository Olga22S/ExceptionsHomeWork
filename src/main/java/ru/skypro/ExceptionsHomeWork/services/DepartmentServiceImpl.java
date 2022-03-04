package ru.skypro.ExceptionsHomeWork.services;

import org.springframework.stereotype.Service;
import ru.skypro.ExceptionsHomeWork.entities.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    private EmployeeServiceImpl employeeService;

    public DepartmentServiceImpl(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    public Employee getDepartmentMinSalaryEmployee(int dep) {
        return getEmployeesByDepartment().get(dep)
                .stream()
                .min(Comparator.comparingDouble(e -> e.getSalary()))
                .get();
    }

    public Employee getDepartmentMaxSalaryEmployee(int dep) {
        return getEmployeesByDepartment().get(dep)
                .stream()
                .max(Comparator.comparingDouble(e -> e.getSalary()))
                .get();
    }

    public List<Employee> getDepartmentEmployees(int dep) {
        return getEmployeesByDepartment().get(dep);
    }

    public String getEmployees() {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Integer, List<Employee>> item : getEmployeesByDepartment().entrySet()) {
            result.append("Department " + item.getKey() + ": ");
            for (Employee employee : item.getValue()) {
                result.append(getEmployee(employee));
            }
        }
        return result.toString();
    }

    private Map<Integer, List<Employee>> getEmployeesByDepartment() {
        return employeeService.getEmployees().values()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    private String getEmployee(Employee employee) {
        return employee.getFirstName() + " " + employee.getLastName() + " salary=" + employee.getSalary() + "; ";
    }
}
