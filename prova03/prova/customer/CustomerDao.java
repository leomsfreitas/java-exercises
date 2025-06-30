package prova03.prova.customer;

import java.sql.SQLException;
import java.util.Optional;

public interface CustomerDao {
    void save(CustomerDto customer) throws SQLException;
    Optional<CustomerDto> findOne(String plate) throws SQLException;

}
