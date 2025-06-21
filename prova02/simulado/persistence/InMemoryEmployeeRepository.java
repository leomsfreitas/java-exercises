package prova02.simulado.persistence;

import prova02.simulado.model.Consultant;
import prova02.simulado.model.Employee;
import prova02.simulado.exception.EntityAlreadyExistsException;

import java.util.Collection;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.LinkedHashMap;

public class InMemoryEmployeeRepository implements Repository<Employee, String> {
    private static final Map<String, Employee> db = new LinkedHashMap<>();

    @Override
    public void save(Employee employee) {
        if (db.containsKey(employee.getId())) {
            throw new EntityAlreadyExistsException("Entity with id is already exists");
        }
        db.put(employee.getId(), employee);
    }

    @Override
    public void update(Employee employee) {
        final Employee replaced = db.replace(employee.getId(), employee);
        if (replaced == null) {
            throw new NoSuchElementException("Entity not found: " + employee.getId());
        }
    }

    @Override
    public Optional<Employee> findById(String id) {
        final Collection<Employee> children = db.values().stream()
                .filter(e -> e.getConsultantInCharge().getId().equals(id))
                .toList();

        final Employee employee = db.get(id);

        if (children.isEmpty()) {
            return Optional.of(employee);
        }
        final Consultant consultant = (Consultant) employee;

        children.forEach(e -> consultant.addEmployee(findById(e.getId()).orElseThrow()));
        return Optional.ofNullable(consultant);
    }
}
