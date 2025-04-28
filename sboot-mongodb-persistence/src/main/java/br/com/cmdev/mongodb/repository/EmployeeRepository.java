package br.com.cmdev.mongodb.repository;

import br.com.cmdev.mongodb.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface EmployeeRepository extends MongoRepository<Employee, String> {
}
