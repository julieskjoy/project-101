package no.acntech.project101.employee;

import no.acntech.project101.company.Company;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {

    //TODO Create the enitity for Employee
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "COMPANY_ID", nullable = false)
    private Company company;

    public Employee() {}

    public Employee(final String firstName, final String lastName, final LocalDate dateOfBirth){
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public Long getId() {return Id;}

    public String getFirstName() {return firstName;}

    public void setFirstName(final String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}

    public void setLastName(final String lastName) {this.lastName = lastName;}

    public LocalDate getDateOfBirth() {return dateOfBirth;}

    public void setDateOfBirth(final LocalDate dateOfBirth) {this.dateOfBirth = dateOfBirth;}



}
