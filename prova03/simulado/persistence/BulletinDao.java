package prova03.simulado.persistence;

import java.sql.SQLException;
import java.util.List;

public interface BulletinDao<T, K> {
    void insert(T type);
    void delete(K key);
    void update(T type);
    boolean existById(K key);
    List<T> findAll();
}