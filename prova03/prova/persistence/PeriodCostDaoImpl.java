package prova03.prova.persistence;

import prova03.prova.costs.PeriodCostDAO;
import prova03.prova.costs.PeriodCostDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PeriodCostDaoImpl implements PeriodCostDAO {

    @Override
    public List<PeriodCostDTO> findAll() throws SQLException {
        String sql = "SELECT * FROM period_cost";

        try (var stmt = ConnectionFactory.getPreparedStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            List<PeriodCostDTO> list = new ArrayList<>();
            while(rs.next()) {
                list.add(new PeriodCostDTO(
                        rs.getInt("hours"),
                        rs.getDouble("cost")
                ));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
