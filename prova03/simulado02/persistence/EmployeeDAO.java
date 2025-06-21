package prova03.simulado02.persistence;

import prova03.simulado02.services.EmployeeDTO;

import java.util.List;
import java.util.Optional;

public interface EmployeeDAO {
    void save(EmployeeDTO employee);
    void update(EmployeeDTO employee);
    Optional<EmployeeDTO> findById(String id);
    List<EmployeeDTO> findAll();
    void delete(String id);
}
