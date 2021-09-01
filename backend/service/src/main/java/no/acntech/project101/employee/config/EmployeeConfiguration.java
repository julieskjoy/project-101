package no.acntech.project101.employee.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "no.acntech.project101.employee")
//TODO Add componentScan of employee package so that Spring can create beans
public class EmployeeConfiguration {

}
