package com.steamtest.streamapiandoptional.service;

import com.steamtest.streamapiandoptional.database.Employee;
import com.steamtest.streamapiandoptional.exceptions.EmployeeAlreadyAddedException;
import com.steamtest.streamapiandoptional.exceptions.EmployeeNotFoundException;
import com.steamtest.streamapiandoptional.exceptions.EmployeeStorageIsFullException;
import com.steamtest.streamapiandoptional.exceptions.WrongDataException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {

    private static final int sizeLimit = 5;
    private final Map<String, Employee> employees = new HashMap<>(sizeLimit);


    public Collection<Employee> getAll() {
        return employees.values();
    }

    public Employee add(Employee employee) {
        if (employees.size() >= sizeLimit) {
            throw new EmployeeStorageIsFullException();
        }
        if (employees.containsKey(createEmployee(employee))) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(createEmployee(employee), employee);
        return employee;
    }

    public Employee find(String firstName, String lastName) {
        Employee employee = employees.get(createEmployee(firstName, lastName));
        if (employee == null) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    public Employee remove(String firstName, String lastName) {
        return employees.remove(createEmployee(firstName, lastName));
    }

    private static String createEmployee(Employee employee) {
        return createEmployee(employee.getFirstName(), employee.getLastName());
    }

    public static String createEmployee(String firstName, String lastName) {
        return (firstName + lastName).toLowerCase();
    }

}
