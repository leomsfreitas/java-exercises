package prova03.prova.persistence;

import prova03.prova.ticket.EntryTicketDto;
import prova03.prova.ticket.ExitTicketDto;
import prova03.prova.ticket.TicketDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class EntryTicketDaoImpl implements TicketDao {
    @Override
    public void save(EntryTicketDto ticket) throws SQLException {
        String sql = "INSERT INTO ticket (id, plate, entry) VALUES (?,?,?)";

        try (var stmt = ConnectionFactory.getPreparedStatement(sql)) {
            stmt.setString(1, ticket.id());
            stmt.setString(2, ticket.plate());
            stmt.setString(3, ticket.entry());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<EntryTicketDto> findOpenTicket(String plate) throws SQLException {
        String sql = "SELECT * FROM ticket WHERE plate = ? AND exit is NULL";

        try (var stmt = ConnectionFactory.getPreparedStatement(sql)) {
            stmt.setString(1, plate);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(new EntryTicketDto(
                        rs.getString("id"),
                        rs.getString("plate"),
                        rs.getString("entry")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public void updateExit(ExitTicketDto ticket) throws SQLException {
        String sql = "UPDATE ticket SET exit = ? WHERE plate = ?";

        try (var stmt = ConnectionFactory.getPreparedStatement(sql)) {
            stmt.setString(1, ticket.exit());
            stmt.setString(2, ticket.plate());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
