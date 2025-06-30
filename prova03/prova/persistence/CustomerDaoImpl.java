package prova03.prova.persistence;

import prova03.prova.customer.CustomerDao;
import prova03.prova.customer.CustomerDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public void save(CustomerDto customer) throws SQLException {
        String sql = "INSERT INTO customer (plate, phone, type) VALUES (?, ?, ?)";

        try (var stmt = ConnectionFactory.getPreparedStatement(sql)) {
            stmt.setString(1, customer.plate());
            stmt.setString(2, customer.phone());
            stmt.setString(3, customer.type());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<CustomerDto> findOne(String plate) throws SQLException{
        String sql = "SELECT * FROM customer WHERE plate = ?";

        try (var stmt = ConnectionFactory.getPreparedStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(new CustomerDto(
                        rs.getString("plate"),
                        rs.getString("phone"),
                        rs.getString("type")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }
}
