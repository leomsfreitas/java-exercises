package prova03.simulado02.persistence;

import prova03.simulado02.services.EmployeeDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SQLiteEmployeeDAO implements EmployeeDAO {

    @Override
    public void save(EmployeeDTO employee) {
        String sql = "INSERT INTO employee VALUES (?,?,?,?,?,?)";

        try (var stmt = ConnectionFactory.getPreparedStatement(sql)) {
            stmt.setString(1, employee.id());
            stmt.setString(2, employee.name());
            stmt.setString(3, employee.birthDate().toString());
            stmt.setDouble(4, employee.soldValue());
            stmt.setString(5, employee.inChargeId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(EmployeeDTO employee) {
        String sql = "UPDATE employee SET name = ?, birthDate = ?, soldValue = ?, inChargeId = ? WHERE id = ?";

        try (var stmt = ConnectionFactory.getPreparedStatement(sql)) {
            stmt.setString(1, employee.name());
            stmt.setString(2, employee.birthDate().toString());
            stmt.setDouble(3, employee.soldValue());
            stmt.setString(4, employee.inChargeId());
            stmt.setString(5, employee.id());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<EmployeeDTO> findById(String id) {
        String sql = "SELECT * FROM employee WHERE id = ?";

        try (var stmt = ConnectionFactory.getPreparedStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new EmployeeDTO(
                        rs.getString("id"),
                        rs.getString("name"),
                        LocalDate.parse(rs.getString("birthDate")),
                        rs.getDouble("soldValue"),
                        rs.getString("inChargeId")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<EmployeeDTO> findAll() {
        String sql = "SELECT * FROM employee";

        try (var stmt = ConnectionFactory.getPreparedStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            List<EmployeeDTO> list = new ArrayList<>();

            while (rs.next()) {
                list.add(new EmployeeDTO(
                        rs.getString("id"),
                        rs.getString("name"),
                        LocalDate.parse(rs.getString("birthDate")),
                        rs.getDouble("soldValue"),
                        rs.getString("inChargeId")
                ));
            }

            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM employee WHERE id = ?";

        try (var stmt = ConnectionFactory.getPreparedStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
