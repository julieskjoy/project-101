package no.acntech.project101.company;

import no.acntech.project101.employee.Employee;
import org.hibernate.mapping.Set;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "COMPANY")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "COMPANY_NAME")
    private String companyName;

    @Column(name = "ORG_NR")
    private String orgNr;


    //TODO add mapping for list of employees if you have time
    @OneToMany(mappedBy = "company")
    private List<Employee> employees = new ArrayList<Employee>();

    public Company() {
    }

    public Company(final String companyName, final String orgNr) {
        this.companyName = companyName;
        this.orgNr = orgNr;
    }


    public Long getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(final String companyName) {
        this.companyName = companyName;
    }

    public String getOrgNr() {
        return orgNr;
    }

    public void setOrgNr(final String orgNr) {
        this.orgNr = orgNr;
    }

}
