package br.com.cmdev.mongodb.service;

import br.com.cmdev.mongodb.entity.Employee;
import br.com.cmdev.mongodb.exception.EmployeeNotFoundException;
import br.com.cmdev.mongodb.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Employee createEmployee(Employee employee) {
        return repository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Employee getEmployeeById(String id) {
        return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("not found some employee for id: " + id));
    }

    public List<Employee> deleteEmployee(String id) {
        repository.deleteById(id);
        return repository.findAll();
    }
}
