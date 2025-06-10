package aula06.exercicio04;

public class RegisterEmployeeService {
    private final Repository<Employee, String> repository;

    public RegisterEmployeeService(Repository<Employee, String> repository) {
        this.repository = repository;
    }

    public void register(Employee e) {
        if (repository.findById(e.getId()) != null) {
            repository.saveEntity(e);
            System.out.printf("Employee registered: %s%n", e.getName());
        } else {
            System.out.printf("Employee already registered: %s%n", e.getName());
        }
    }

}
