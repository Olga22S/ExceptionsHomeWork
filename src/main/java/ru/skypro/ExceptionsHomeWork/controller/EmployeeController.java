package ru.skypro.ExceptionsHomeWork.controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.ExceptionsHomeWork.model.Employee;
import ru.skypro.ExceptionsHomeWork.exceptions.AlreadyExistsEmployeeException;
import ru.skypro.ExceptionsHomeWork.exceptions.ArrayIsFullException;
import ru.skypro.ExceptionsHomeWork.exceptions.EmployeeNotFoundException;
import ru.skypro.ExceptionsHomeWork.services.EmployeeServiceImpl;

import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/add")
    public Employee addEmployee(@RequestParam String firstName, @RequestParam String lastName,
                                @RequestParam int dep, @RequestParam double salary)
            throws ArrayIsFullException, AlreadyExistsEmployeeException {
        return employeeService.addEmployee(firstName, lastName, dep, salary);
    }

    @GetMapping(value = "/remove")
    public Employee removeEmployee(@RequestParam String firstName, @RequestParam String lastName)
            throws EmployeeNotFoundException {
        return employeeService.removeEmployee(firstName, lastName);
    }

    @GetMapping(value = "/find")
    public Employee findEmployee(@RequestParam String firstName, @RequestParam String lastName)
            throws EmployeeNotFoundException {
        return employeeService.getEmployee(firstName, lastName);
    }

    @GetMapping(value = "/getAll")
    public Map<Integer, Employee> getEmployees() {
        return employeeService.getEmployees();
    }
}