package ru.skypro.ExceptionsHomeWork;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.ExceptionsHomeWork.entities.Employee;
import ru.skypro.ExceptionsHomeWork.services.EmployeeDepartmentService;

import java.util.List;

@RestController
@RequestMapping("/departments")
@SpringBootApplication
public class EmployeeDepartmentController {

    private EmployeeDepartmentService employeeAdvancedService;

    public EmployeeDepartmentController(EmployeeDepartmentService employeeAdvancedService) {
        this.employeeAdvancedService = employeeAdvancedService;
    }

    @GetMapping(value = "/min-salary", params = {"departmentId"})
    public Employee getDepartmentMinSalaryEmployee(@RequestParam (value = "departmentId") int department) {
        return employeeAdvancedService.getDepartmentMinSalaryEmployee(department);
    }

    @GetMapping(value = "/max-salary", params = {"departmentId"})
    public Employee getDepartmentMaxSalaryEmployee(@RequestParam(value = "departmentId") int department) {
        return employeeAdvancedService.getDepartmentMaxSalaryEmployee(department);
    }

    @GetMapping(value = "/all", params = {"departmentId"})
    public List<Employee> getDepartmentEmployees(@RequestParam(value = "departmentId") int department) {
            return employeeAdvancedService.getDepartmentEmployees(department);
    }

    @GetMapping(value = "/all")
    public String getEmployee() {
        return employeeAdvancedService.getEmployees();
    }
}
