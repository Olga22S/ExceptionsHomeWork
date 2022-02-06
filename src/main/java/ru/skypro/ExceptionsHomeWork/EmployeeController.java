package ru.skypro.ExceptionsHomeWork;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.ExceptionsHomeWork.entities.Employee;
import ru.skypro.ExceptionsHomeWork.exceptions.AlreadyExistsEmployeeException;
import ru.skypro.ExceptionsHomeWork.exceptions.ArrayIsFullException;
import ru.skypro.ExceptionsHomeWork.exceptions.EmployeeNotFoundException;
import ru.skypro.ExceptionsHomeWork.services.EmployeeService;

@RestController
@RequestMapping("/employee")
@SpringBootApplication
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/add")
    public Employee addEmployee(@RequestParam String firstName, @RequestParam String lastName)
            throws ArrayIsFullException, AlreadyExistsEmployeeException {
        return employeeService.addEmployee(firstName, lastName);
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
}
