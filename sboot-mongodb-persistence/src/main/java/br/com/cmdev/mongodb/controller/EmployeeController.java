package br.com.cmdev.mongodb.controller;

import br.com.cmdev.mongodb.entity.Employee;
import br.com.cmdev.mongodb.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody Employee employee) {
        Employee employeeResponse = service.createEmployee(employee);
        return employeeResponse;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = service.getAllEmployees();
        return employeeList;
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable("id") String id) {
        Employee employee = service.getEmployeeById(id);
        return employee;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Employee updateEmployee(@RequestBody Employee employee) {
        Employee employeeResponse = service.createEmployee(employee);
        return service.getEmployeeById(employee.getId());
    }

    @DeleteMapping("/{id}")
    public List<Employee> deleteEmployee(@PathVariable("id") String id) {
        return service.deleteEmployee(id);
    }

}
