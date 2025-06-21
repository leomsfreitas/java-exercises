package prova03.prova.persistence;

import prova03.prova.customer.CustomerDAO;
import prova03.prova.customer.CustomerDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CustomerDaoImpl implements CustomerDAO {
    @Override
    public void save(CustomerDTO customer) {
        String sql = "INSERT INTO customer (plate, phone, type) VALUES (?, ?, ?)";

        try(var stmt = ConnectionFactory.getPreparedStatement(sql)) {
            stmt.setString(1, customer.plate());
            stmt.setString(2, customer.phone());
            stmt.setString(3, customer.type());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<CustomerDTO> findOne(String plate) throws SQLException {
        String normalizedPlate = plate.trim().toUpperCase();

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM customer WHERE plate = ?");
        statement.setString(1, normalizedPlate);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String foundPlate = resultSet.getString("plate");
            String phone = resultSet.getString("phone");
            String type = resultSet.getString("type");
            return Optional.of(new CustomerDTO(foundPlate, phone, type));
        }

        return Optional.empty();
    }

}
