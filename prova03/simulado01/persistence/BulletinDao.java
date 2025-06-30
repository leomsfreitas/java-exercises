package prova03.simulado01.persistence;

import java.util.List;
import java.util.Optional;

public interface BulletinDao <T, K>{
    void insert(T bulletin);
    void delete(K id);
    void update(K id, T bulletin);
    boolean existsById(K id);
    List<T> findAll();
    Optional<T> findById(K id);
}
