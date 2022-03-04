package ru.skypro.ExceptionsHomeWork.services;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.skypro.ExceptionsHomeWork.entities.Employee;
import ru.skypro.ExceptionsHomeWork.exceptions.AlreadyExistsEmployeeException;
import ru.skypro.ExceptionsHomeWork.exceptions.EmployeeNotFoundException;
import ru.skypro.ExceptionsHomeWork.exceptions.NotCorrectNameException;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {ru.skypro.ExceptionsHomeWork.services.EmployeeServiceImpl.class})
public class EmployeeServiceImplTests {

    private EmployeeServiceImpl service;

    @BeforeEach
    public void setUp(){
        service = new EmployeeServiceImpl();
        service.addEmployee("Olga", "Gontsova", 1, 500000);
        service.addEmployee("Kirill", "Kachalov", 2, 1000);
        service.addEmployee("Nata", "Kukushka", 2, 25000);
    }

    @Test
    public void whenAddNewEmployeeThenReturnMapWithNewEmployee(){
        assertEquals(service.getEmployees().size(), 3);
        service.addEmployee("Petr", "Petrov", 1, 5000);
        assertEquals(service.getEmployees().size(), 4);
    }

    @Test
    public void whenAddNewEmployeeThenCapitalizeEmployeeName(){
        Employee expected = new Employee("Aleksey", "Smirnov", 1, 5000);
        service.addEmployee("aleksey", "smirnov", 1, 5000);
        assertTrue(service.getEmployees().containsValue(expected));
    }

    @Test
    public void shouldThrowAlreadyExistsEmployeeExceptionWhenAddExistingEmployee() {
        assertThrows(AlreadyExistsEmployeeException.class,
                () -> service.addEmployee("Nata", "Kukushka", 2, 25000));
    }

    @Test
    public void shouldThrowNotCorrectNameExceptionWhenAddNotCharacterEmployeeName() {
        assertThrows(NotCorrectNameException.class,
                () -> service.addEmployee("123", "Ivanov", 1, 4500));
    }

    @Test
    public void whenRemoveEmployeeThenRemoveEmployeeByName(){
        assertEquals(service.getEmployees().size(), 3);
        service.removeEmployee("Kirill", "Kachalov");
        assertEquals(service.getEmployees().size(), 2);
    }

    @Test
    public void shouldThrowNotCorrectNameExceptionWhenRemoveNotCharacterEmployeeName() {
        assertThrows(NotCorrectNameException.class,
                () -> service.removeEmployee("Ivan", "@123"));
    }

    @Test
    public void shouldThrowEmployeeNotFoundExceptionWhenRemoveNotExistEmployee() {
        assertThrows(EmployeeNotFoundException.class,
                () -> service.removeEmployee("Oleg", "olegovich"));
    }

    @Test
    public void shouldThrowNotCorrectNameExceptionWhenGetNotCharacterEmployeeName() {
        assertThrows(NotCorrectNameException.class,
                () -> service.getEmployee("Ivan", "@123"));
    }

    @Test
    public void whenGetEmployeeThenReturnEmployee(){
        Employee result = service.getEmployee("Olga", "Gontsova");

        Employee expected = new Employee("Olga", "Gontsova", 1, 500000);

        assertEquals(result, expected);
    }

    @Test
    public void shouldThrowEmployeeNotFoundExceptionWhenGetNotExistEmployee() {
        assertThrows(EmployeeNotFoundException.class,
                () -> service.getEmployee("vladimir", "putler"));
    }

    @Test
    public void whenGetEmployeesThenReturnEmployees(){
        Map<Integer, Employee> result = service.getEmployees();

        Map<Integer, Employee> expected = getExpectedEmployees();

        assertEquals(result, expected);
    }

    private Map<Integer, Employee> getExpectedEmployees(){
        Map<Integer, Employee> expected = new HashMap<>();
        expected.put(1, new Employee("Olga", "Gontsova", 1, 500000));
        expected.put(2, new Employee("Kirill", "Kachalov", 2, 1000));
        expected.put(3, new Employee("Nata", "Kukushka", 2, 25000));
        return expected;
    }
}
