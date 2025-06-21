package prova03.prova.costs;

import java.sql.SQLException;
import java.util.List;

@FunctionalInterface
public interface PeriodCostDAO {
    public List<PeriodCostDTO> findAll() throws SQLException;
}
