package no.acntech.project101.web.employee.resources;
import java.net.URI;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import no.acntech.project101.company.Company;
import no.acntech.project101.employee.Employee;
import no.acntech.project101.employee.service.EmployeeService;
import no.acntech.project101.web.company.resources.CompanyDto;
import no.acntech.project101.web.company.resources.converter.CompanyDtoConverter;
import no.acntech.project101.web.employee.resources.converter.EmployeeConverter;
import no.acntech.project101.web.employee.resources.converter.EmployeeDtoConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("employees")
public class EmployeeResource {

    private final EmployeeService employeeService;
    private final EmployeeDtoConverter employeeDtoConverter;
    private final EmployeeConverter employeeConverter;

    public EmployeeResource(final EmployeeService employeeService,
                            final EmployeeDtoConverter employeeDtoConverter,
                            final EmployeeConverter employeeConverter) {
        this.employeeService = employeeService;
        this.employeeDtoConverter = employeeDtoConverter;
        this.employeeConverter = employeeConverter;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> findAll() {
        final List<Employee> employees = employeeService.findAll();
        final List<EmployeeDto> collect = employees.stream()
                .map(employeeDtoConverter::convert)
                .collect(Collectors.toList());

        return ResponseEntity.ok(collect);
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> findById(@PathVariable final Long id) {
        final Optional<Employee> employee = employeeService.findById(id);

        if (employee.isPresent()) {
            final EmployeeDto convert = employeeDtoConverter.convert(employee.get());
            return ResponseEntity.ok(convert);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity createEmployee(@RequestBody final EmployeeDto employeeDto) {
        final Employee convert = employeeConverter.convert(employeeDto);
        final Employee saved = employeeService.save(convert);
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteEmployee(@PathVariable final Long id) {
        final Optional<Employee> employee = employeeService.findById(id);

        if (employee.isPresent()) {
            employeeService.delete(id);
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity updateEmployee(@PathVariable final Long id, @RequestBody final EmployeeDto employeeDto) {
        final Optional<Employee> optionalEmployee = employeeService.findById(id);

        if (optionalEmployee.isPresent()) {
            Employee existingEmployee = optionalEmployee.get();
            existingEmployee.setFirstName(employeeDto.getFirstName());
            existingEmployee.setLastName(employeeDto.getLastName());
            existingEmployee.setDateOfBirth(employeeDto.getDateOfBirth());

            Employee saved = employeeService.save(existingEmployee);

            final EmployeeDto convert = employeeDtoConverter.convert(saved);
            return ResponseEntity.ok(convert);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

/*
@CrossOrigin(origins = "http://localhost:3000")
//TODO This is a REST controller and should receive request on path employees
@RestController //Annotasjon for at denne klassen skal fungere som web endepunkter
@RequestMapping("employees") //Path til denne kontrolleren er employees
public class EmployeeResource {

    //TODO The constructor needs to accept the required dependencies and assign them to class variables
    public EmployeeResource() {
        //Skulle vi skrevet ferdig så ville vi hatt en Employee server her
    }

    @GetMapping //findAll vil svare på en GET request (Denne metoden svarer når vi kaller på GET i APIet)
    public ResponseEntity<List<EmployeeDto>> findAll() {
        //TODO create a GET endpoint find all employees in the database and return them
        final EmployeeDto julie = new EmployeeDto(1l, "Julie", "Skjoy", LocalDate.of(1997, 11, 9), 1l);
        final EmployeeDto johannes = new EmployeeDto(2l,"Johannes", "Schatzer", LocalDate.of(2008, 2, 4), 1l);
        //EmployeeDto julius = new EmployeeDto(3l, "Julius", "Kristiansen", LocalDate.of(1990, 24, 12), 1l);
        final List<EmployeeDto> employees = Arrays.asList(julie, johannes);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("{id}") //findByID vil svare på en GET request med et gitt parameter
    //Det er mulig å definere flere variables til GET mappringen, disse må også være definert i metoden under
    public ResponseEntity<EmployeeDto> findById(@PathVariable final Long id) {
        // TODO create a GET endpoint that fetches a specific employee based on its ID
        final EmployeeDto julie = new EmployeeDto(1l, "Julie", "Skjoy", LocalDate.of(1997, 11, 9), 1l);
        final EmployeeDto johannes = new EmployeeDto(2l,"Johannes", "Schatzer", LocalDate.of(2008, 2, 4), 1l);
        //EmployeeDto julius = new EmployeeDto(3l, "Julius", "Kristiansen", LocalDate.of(1990, 24, 12), 1l);
        final List<EmployeeDto> employees = Arrays.asList(julie, johannes);
        for (EmployeeDto employee : employees){
            if (employee.getId().equals(id)){
                return ResponseEntity.ok(employee);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity createEmployee(@RequestBody @Validated EmployeeDto employeeDto) { //Med validated vil det sendes en bad request hvis employeeDTO ikke finnes
        //TODO Create a POST endpoint that accepts an employeeDTO and saves it in the database
        long id = new Random().nextLong();
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .pathSegment(String.valueOf(id))
                .build()
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteEmployee(@PathVariable final Long id) {
        // TODO Create a DELETE endpoint that deletes a specific employee
        //final EmployeeDto julie = new EmployeeDto(1l, "Julie", "Skjoy", LocalDate.of(1997, 11, 9), 1l);
        //final EmployeeDto johannes = new EmployeeDto(2l,"Johannes", "Schatzer", LocalDate.of(2008, 2, 4), 1l);
        //EmployeeDto julius = new EmployeeDto(3l, "Julius", "Kristiansen", LocalDate.of(1990, 24, 12), 1l);
        //final List<EmployeeDto> employees = Arrays.asList(julie, johannes);
        //for (EmployeeDto employee : employees){
        //    if (employee.getId().equals(id)){
        //        employees.remove(id);
        //        return ResponseEntity.ok(employee);
        //    }
        //}
        return ResponseEntity.accepted().build();
    }

    @PatchMapping("{id}") //PATCH (Ender du noe av det du putter inn) eller PUT (Sletter forgjenger og endrer alt)
    public ResponseEntity updateEmployee(@PathVariable("id") final Long id, @RequestBody EmployeeDto employeeDto) {
        //TODO Create a PATCH endpoint that updates an employee with new values
        final EmployeeDto fraDatabasen = EmployeeDto.builder().build(); //Builder må lages først! Se litt på hvordan dette gjøres.
        //Fordel for å ikke få følgefeil dersom noe endres i koden, fordi alle variables må defineres separat.

        if (employeeDto.getFirstName() != null) {
            fraDatabasen.setFirstName = employeeDto.getFirstName(); //SetFirsName er ikke laget
        }
        return null;
    }
}

*/
