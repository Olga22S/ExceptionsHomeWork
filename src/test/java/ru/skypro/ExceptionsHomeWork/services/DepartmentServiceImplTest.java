package ru.skypro.ExceptionsHomeWork.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import ru.skypro.ExceptionsHomeWork.entities.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {ru.skypro.ExceptionsHomeWork.services.DepartmentServiceImpl.class,
        ru.skypro.ExceptionsHomeWork.services.EmployeeServiceImpl.class})
@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {

    @Mock
    private EmployeeServiceImpl employeeService;
    @InjectMocks
    private DepartmentServiceImpl out;

    @BeforeEach
    public void initMock(){
        when(employeeService.getEmployees())
                .thenReturn(getExpectedMapEmployees());
    }

    @Test
    public void whenGetDepartmentMinSalaryEmployeeThenReturnEmployeeWithMinSalary(){
        Employee expected = new Employee("Kirill", "Kachalov", 2, 1000);

        Employee result = out.getDepartmentMinSalaryEmployee(2);

        assertEquals(expected, result);
    }

    @Test
    public void whenGetDepartmentMaxSalaryEmployeeThenReturnEmployeeWithMaxSalary(){
        Employee expected = new Employee("Nata", "Kukushka", 2, 25000);

        Employee result = out.getDepartmentMaxSalaryEmployee(2);

        assertEquals(expected, result);
    }

    @Test
    public void whenGetDepartmentEmployeesThenReturnEmployeesOfDepartment(){
        List<Employee> expected = getExpectedListEmployees();

        List<Employee> result = out.getDepartmentEmployees(2);

        assertEquals(expected, result);
    }

    @Test
    public void whenGetEmployeesThenReturnEmployeesByDepartments(){
        String expected = getExpectedEmployeesByDepartments();

        String result = out.getEmployees();

        assertEquals(expected, result);
    }

    private Map<Integer, Employee> getExpectedMapEmployees(){
        Map<Integer, Employee> expected = new HashMap<>();
        expected.put(1, new Employee("Olga", "Gontsova", 1, 500000));
        expected.put(2, new Employee("Kirill", "Kachalov", 2, 1000));
        expected.put(3, new Employee("Nata", "Kukushka", 2, 25000));
        return expected;
    }

    private List<Employee> getExpectedListEmployees(){
        List<Employee> expected = new ArrayList<>();
        expected.add(new Employee("Kirill", "Kachalov", 2, 1000));
        expected.add(new Employee("Nata", "Kukushka", 2, 25000));
        return expected;
    }

    private String getExpectedEmployeesByDepartments(){
        return "Department 1: Olga Gontsova salary=500000.0; " +
                "Department 2: Kirill Kachalov salary=1000.0; " +
                "Nata Kukushka salary=25000.0; ";
    }
}
