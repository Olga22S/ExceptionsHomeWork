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

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/employee")
@SpringBootApplication
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/add", produces = APPLICATION_JSON_VALUE)
    public Employee addEmployee(@RequestParam String firstName, @RequestParam String lastName)
            throws ArrayIsFullException, AlreadyExistsEmployeeException {
        employeeService.addEmployee(firstName, lastName);
        return new Employee(firstName, lastName);
    }

    @GetMapping(value = "/remove", produces = APPLICATION_JSON_VALUE)
    public Employee removeEmployee(@RequestParam String firstName, @RequestParam String lastName)
            throws EmployeeNotFoundException {
        employeeService.removeEmployee(firstName, lastName);
        return new Employee(firstName, lastName);
    }

    @GetMapping(value = "/find", produces = APPLICATION_JSON_VALUE)
    public Employee findEmployee(@RequestParam String firstName, @RequestParam String lastName)
            throws EmployeeNotFoundException {
        return employeeService.getEmployee(firstName, lastName);
    }
}
