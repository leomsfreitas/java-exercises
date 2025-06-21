package prova03.simulado02.services;

import prova03.simulado02.model.Consultant;
import prova03.simulado02.model.Employee;
import prova03.simulado02.model.Reseller;
import prova03.simulado02.persistence.EmployeeDAO;

import java.util.NoSuchElementException;
import java.util.Optional;

public class CommissionService {
    private final EmployeeDAO eDAO;

    public CommissionService(EmployeeDAO eDAO) {
        this.eDAO = eDAO;
    }

    public double calculateCommission(String id) {
        Optional<EmployeeDTO> employeeDTO = eDAO.findById(id);
        if (employeeDTO.isEmpty()) throw new NoSuchElementException("Employee ID not found");

        EmployeeDTO dto = employeeDTO.get();

        Employee employee;

        if (dto.inChargeId() == null) {
            employee = new Consultant(
                    dto.id(),
                    dto.name(),
                    dto.birthDate(),
                    dto.soldValue(),
                    null
            );
        } else {
            Optional<EmployeeDTO> optionalBossDTO = eDAO.findById(dto.inChargeId());
            if (optionalBossDTO.isEmpty()) throw new NoSuchElementException("Boss not found");
            EmployeeDTO bossDTO = optionalBossDTO.get();
            Consultant boss = new Consultant(
                    bossDTO.id(),
                    bossDTO.name(),
                    bossDTO.birthDate(),
                    bossDTO.soldValue(),
                    null
                    );

            employee = new Reseller(
                    dto.id(),
                    dto.name(),
                    dto.birthDate(),
                    dto.soldValue(),
                    boss
            );
        }

        return employee.getCommission();
    }
}
