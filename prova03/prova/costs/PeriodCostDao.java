package prova03.prova.costs;

import java.sql.SQLException;
import java.util.List;

@FunctionalInterface
public interface PeriodCostDao {
    List<PeriodCostDto> findAll() throws SQLException;
}
