package aula06.exercicio04;

import java.util.ArrayList;

public class FakeEmployeeRepository implements Repository<Employee, String>{
    private ArrayList<Employee> employees = new ArrayList<>();

    public FakeEmployeeRepository(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public void saveEntity(Employee e) {
        employees.add(e);
    }

    @Override
    public Employee findById(String id) {
        for (Employee e : employees) {
            if (e.getId().equals(id))
                return e;
        }
        return null;
    }
}
