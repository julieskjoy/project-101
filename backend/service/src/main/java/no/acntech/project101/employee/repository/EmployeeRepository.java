package no.acntech.project101.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import no.acntech.project101.employee.Employee;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}