package aula06.exercicio04;

public interface Repository<T, K>{
    void saveEntity(T entity);

    T findById(K id);

}
