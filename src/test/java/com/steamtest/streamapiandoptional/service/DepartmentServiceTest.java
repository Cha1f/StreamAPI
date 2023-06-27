package com.steamtest.streamapiandoptional.service;

import com.steamtest.streamapiandoptional.database.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    EmployeeService employeeService;

    @InjectMocks
    DepartmentService departmentService;

    List<Employee> employees = Arrays.asList(
            new Employee("ivan", "ivanov", 1, 2000),
            new Employee("petya", "petrov", 1, 3000),
            new Employee("olya", "ivanova", 2, 4000),
            new Employee("masha", "petrova", 2, 5000),
            new Employee("lesha", "alekseev", 3, 2000)
    );

    @BeforeEach
    void main() {
        Mockito.when(employeeService.getAll()).thenReturn(employees);
    }

    @Test
    void sum() {
        double real = departmentService.getSumSalaryByDepartment(2);
        assertEquals(9000, real);
    }

    @Test
    void min() {
        double real = departmentService.getEmployeeWithMinSalary(1);
        assertEquals(2000, real);
    }

    @Test
    void max() {
        double real = departmentService.getEmployeeWithMaxSalary(2);
        assertEquals(5000, real);
    }

    @Test
    void getAllEmployeesByDepartment() {
        List<Employee> real = departmentService.getEmployeeByDepartment(2);
        List<Employee> expected = Arrays.asList(
                new Employee("olya", "ivanova", 2, 4000),
                new Employee("masha", "petrova", 2, 5000));

        assertEquals(expected.size(), real.size());

        assertTrue(expected.containsAll(real));
    }

    @Test
    void getAll() {
        List<Integer> unicalDepartments = employees.stream()
                .map(Employee::getDepartment)
                .distinct()
                .collect(Collectors.toList());

        Map<Integer, List<Employee>> allEmployees = departmentService.getEmployeeAll();

        assertEquals(unicalDepartments.size(), allEmployees.keySet().size());

        assertTrue(unicalDepartments.containsAll(allEmployees.keySet()));
    }
}
