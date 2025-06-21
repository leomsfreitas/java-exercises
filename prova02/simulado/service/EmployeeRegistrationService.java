package prova02.simulado.service;

import prova02.simulado.model.Reseller;
import prova02.simulado.model.Employee;
import java.time.LocalDate;
import java.util.Optional;

interface EmployeeRepository {
    Optional<Employee> findById(String id);
    void save(Employee employee);
}

public class EmployeeRegistrationService {
    private final EmployeeRepository repo;

    public EmployeeRegistrationService(EmployeeRepository repo) {
        this.repo = repo;
    }

    public void register(String id, String name, LocalDate birthDate, double solidValue, String inCharge) {
        final Employee maybeInCharge = repo.findById(inCharge).orElse(null);

        Reseller newEmployee = new Reseller(id, name, birthDate, solidValue);
        repo.save(newEmployee);
    }
}
