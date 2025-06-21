package prova03.simulado02.services;

import prova03.simulado02.model.Employee;
import prova03.simulado02.persistence.EmployeeDAO;
import prova03.simulado02.utils.EmployeeUtils;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class RemoveEmployeeService {
    private final EmployeeDAO eDAO;

    public RemoveEmployeeService(EmployeeDAO eDAO) {
        this.eDAO = eDAO;
    }

    public void remove(String id) {
        Optional<EmployeeDTO> employeeDTO = eDAO.findById(id);
        if (employeeDTO.isEmpty()) throw new NoSuchElementException("Employee not found");

        List<EmployeeDTO> subordinates = eDAO.findAll().stream()
                .filter(e -> e.inChargeId().equals(id))
                .toList();

        for (EmployeeDTO e : subordinates) {
            EmployeeDTO updateEmployee = new EmployeeDTO(
                    e.id(),
                    e.name(),
                    e.birthDate(),
                    e.soldValue(),
                    employeeDTO.get().inChargeId()
            );
        }
    }
}
