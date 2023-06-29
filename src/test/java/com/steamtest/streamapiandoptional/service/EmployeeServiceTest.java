package com.steamtest.streamapiandoptional.service;

import com.steamtest.streamapiandoptional.database.Employee;
import com.steamtest.streamapiandoptional.exceptions.EmployeeAlreadyAddedException;
import com.steamtest.streamapiandoptional.exceptions.EmployeeNotFoundException;
import com.steamtest.streamapiandoptional.exceptions.EmployeeStorageIsFullException;
import com.steamtest.streamapiandoptional.exceptions.WrongDataException;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {

    EmployeeService employeeService = new EmployeeService();

    @Test
    void getAllEmployees() {
        Employee e1 = new Employee("ivan", "petrov", 1, 10000.0);
        employeeService.add(e1);
        Employee e2 = new Employee("olga", "ivanova", 2, 20000.0);
        employeeService.add(e2);
        List<Employee> expected = Arrays.asList(e1, e2);

        assertEquals(2, employeeService.getAll().size());

        assertIterableEquals(expected, employeeService.getAll());
    }

    @Test
    void addEmployees() {
        int previousSize = employeeService.getAll().size();
        Employee e1 = new Employee("petya", "razin", 3, 30000.0);
        employeeService.add(e1);

        assertEquals(previousSize + 1, employeeService.getAll().size());

        assertTrue(employeeService.getAll().contains(e1));
    }

    @Test
    void whenEmployeeAlreadyAtListBrokeException() {
        Employee e1 = new Employee("dima", "pervov", 4, 40000.0);

        assertDoesNotThrow(() -> employeeService.add(e1));

        assertThrows(EmployeeAlreadyAddedException.class, () -> employeeService.add(e1));
    }

    @Test
    void whenEmployeesMaxInListBrokeException() {
        Employee e1 = new Employee("ivan", "ivanov", 1, 2000);
        employeeService.add(e1);
        Employee e2 = new Employee("petya", "petrov", 1, 3000);
        employeeService.add(e2);
        Employee e3 = new Employee("olya", "ivanova", 2, 4000);
        employeeService.add(e3);
        Employee e4 = new Employee("masha", "petrova", 2, 5000);
        employeeService.add(e4);
        Employee e5 = new Employee("lesha", "alekseev", 6, 2000);
        employeeService.add(e5);
        assertDoesNotThrow(() -> employeeService.getAll().size());

        assertThrows(EmployeeStorageIsFullException.class,
                () -> employeeService.add(new Employee("dima", "pervov", 4, 40000.0)));
    }

    @Test
    void findEmployee() {
        Employee expected = new Employee("dima", "pervov", 4, 40000.0);
        employeeService.add(expected);
        Employee actual = employeeService.find("dima", "pervov");

        assertNotNull(actual);

        assertEquals(expected, actual);
    }

    @Test
    void findNotFoundEmployee() {
        Employee expected = new Employee("dima", "pervov", 4, 40000.0);
        employeeService.add(expected);

        assertThrows(EmployeeNotFoundException.class, () -> employeeService.find("oleg", "pervov"));
    }

    @Test
    void remove() {
        Employee e = new Employee("dima", "pervov", 4, 40000.0);
        employeeService.add(e);

        assertTrue(employeeService.getAll().contains(e));
        employeeService.remove("dima", "pervov");

        assertFalse(employeeService.getAll().contains(e));
    }
}

