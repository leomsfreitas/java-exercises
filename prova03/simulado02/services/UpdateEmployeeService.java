package prova03.simulado02.services;

import prova03.simulado02.persistence.EmployeeDAO;
import prova03.simulado02.utils.EmployeeUtils;

import java.util.NoSuchElementException;
import java.util.Optional;

public class UpdateEmployeeService {
    private final EmployeeDAO eDAO;

    public UpdateEmployeeService(EmployeeDAO employeeDAO) {
        this.eDAO = employeeDAO;
    }

    public void updateEmployee(EmployeeDTO employee) {
        Optional<EmployeeDTO> employeeDTO = eDAO.findById(employee.id());
        if (employeeDTO.isEmpty()) throw new NoSuchElementException("Employee not found");

        Optional<EmployeeDTO> bossEmployeeDTO = eDAO.findById(employeeDTO.get().inChargeId());
        if (bossEmployeeDTO.isEmpty()) throw new NoSuchElementException("Boss employee doesn't exists");

        eDAO.update(employee);

    }
}
