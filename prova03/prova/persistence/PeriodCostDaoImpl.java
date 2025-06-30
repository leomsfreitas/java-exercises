package prova03.prova.persistence;

import prova03.prova.costs.PeriodCostDao;
import prova03.prova.costs.PeriodCostDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PeriodCostDaoImpl implements PeriodCostDao {
    @Override
    public List<PeriodCostDto> findAll() throws SQLException {
        String sql = "SELECT * FROM period_cost";

        try (var stmt = ConnectionFactory.getPreparedStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            List<PeriodCostDto> list = new ArrayList<>();

            while (rs.next()) {
                list.add(new PeriodCostDto(
                        rs.getInt("hours"),
                        rs.getDouble("cost")
                ));
            }

            return list;
        }
    }
}
