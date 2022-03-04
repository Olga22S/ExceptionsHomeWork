package ru.skypro.ExceptionsHomeWork.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.ExceptionsHomeWork.entities.Employee;
import ru.skypro.ExceptionsHomeWork.services.DepartmentServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private DepartmentServiceImpl departmentService;

    public DepartmentController(DepartmentServiceImpl departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(value = "/min-salary", params = {"departmentId"})
    public Employee getDepartmentMinSalaryEmployee(@RequestParam (value = "departmentId") int department) {
        return departmentService.getDepartmentMinSalaryEmployee(department);
    }

    @GetMapping(value = "/max-salary", params = {"departmentId"})
    public Employee getDepartmentMaxSalaryEmployee(@RequestParam(value = "departmentId") int department) {
        return departmentService.getDepartmentMaxSalaryEmployee(department);
    }

    @GetMapping(value = "/all", params = {"departmentId"})
    public List<Employee> getDepartmentEmployees(@RequestParam(value = "departmentId") int department) {
            return departmentService.getDepartmentEmployees(department);
    }

    @GetMapping(value = "/all")
    public String getEmployee() {
        return departmentService.getEmployees();
    }
}
