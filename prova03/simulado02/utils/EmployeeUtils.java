package prova03.simulado02.utils;

import prova03.simulado02.persistence.EmployeeDAO;

public class EmployeeUtils {
    public static boolean hasEmployee(String id, EmployeeDAO employeeDAO) {
        return employeeDAO.findById(id).isPresent();
    }
}
