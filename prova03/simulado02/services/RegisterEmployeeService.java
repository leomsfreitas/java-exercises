package prova03.simulado02.services;

import prova03.simulado02.exceptions.EntityAlreadyExists;
import prova03.simulado02.persistence.EmployeeDAO;

import java.util.NoSuchElementException;
import java.util.Optional;

public class RegisterEmployeeService {
    private final EmployeeDAO eDAO;

    public RegisterEmployeeService(EmployeeDAO employeeDAO) {
        this.eDAO = employeeDAO;
    }

    public void register(EmployeeDTO employee) {
        Optional<EmployeeDTO> employeeDTO = eDAO.findById(employee.id());
        if (employeeDTO.isEmpty()) throw new EntityAlreadyExists("Employee already exists");

        Optional<EmployeeDTO> bossEmployeeDTO = eDAO.findById(employeeDTO.get().inChargeId());
        if (bossEmployeeDTO.isEmpty()) throw new NoSuchElementException("Boss employee doesn't exists");

        eDAO.save(employee);
    }





}
