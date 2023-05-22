package com.steamtest.streamapiandoptional.employeecontroller;

import com.steamtest.streamapiandoptional.database.Employee;
import com.steamtest.streamapiandoptional.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employee getMax(@RequestParam("departmentId") int department) {
        return departmentService.getEmployeeWithMaxSalary(department);
    }


    @GetMapping("/min-salary")
    public Employee getMin(@RequestParam("departmentId") int department) {
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
