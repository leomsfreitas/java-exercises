package prova03.prova.customer;

import java.sql.SQLException;
import java.util.Optional;

public interface CustomerDAO {
    public void save(CustomerDTO customer);
    public Optional<CustomerDTO> findOne(String plate) throws SQLException;
}
