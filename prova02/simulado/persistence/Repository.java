package prova02.simulado.persistence;

import java.util.Optional;

public interface Repository <T, K> {
    void save(T employee);
    void update(T employee);
    Optional<T> findById(K id);
}
