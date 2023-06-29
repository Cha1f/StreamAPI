package com.steamtest.streamapiandoptional.employeecontroller;

import com.steamtest.streamapiandoptional.database.Employee;
import com.steamtest.streamapiandoptional.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/sum")
    public double getSum(@RequestParam("departmentId") int department) {
        return departmentService.getSumSalaryByDepartment(department);
    }

    @GetMapping("/max-salary")
    public double getMax(@RequestParam("departmentId") int department) {
        return departmentService.getEmployeeWithMaxSalary(department);
    }

    @GetMapping("/min-salary")
    public double getMin(@RequestParam("departmentId") int department) {
        return departmentService.getEmployeeWithMinSalary(department);
    }

    @GetMapping(value = "/all", params = "departmentId")
    public List<Employee> getAll(@RequestParam("departmentId") int department) {
        return departmentService.getEmployeeByDepartment(department);
    }

    @GetMapping(value = "/all")
    public Map<Integer, List<Employee>> getAll() {
        return departmentService.getEmployeeAll();
    }
}
