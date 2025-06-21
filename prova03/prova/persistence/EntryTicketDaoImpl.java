package prova03.prova.persistence;

import prova03.prova.ticket.EntryTicketDTO;
import prova03.prova.ticket.ExitTicketDTO;
import prova03.prova.ticket.TicketDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EntryTicketDaoImpl implements TicketDAO {

    @Override
    public void save(EntryTicketDTO entryTicket) {
        String sql = "INSERT INTO ticket (id, plate, entry) VALUES (?, ?, ?)";

        try(var stmt = ConnectionFactory.getPreparedStatement(sql)) {
            stmt.setString(1, entryTicket.id());
            stmt.setString(2, entryTicket.plate());
            stmt.setString(3, entryTicket.entry());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<EntryTicketDTO> findOpenTicket() throws SQLException {
        String sql = "SELECT * FROM ticket WHERE exit = null";

        try (var stmt = ConnectionFactory.getPreparedStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            List<EntryTicketDTO> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new EntryTicketDTO(
                        rs.getString("id"),
                        rs.getString("plate"),
                        rs.getString("entry")
                ));
            }

            return list;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void updateExit(ExitTicketDTO exitTicket) {
        String sql = "UPDATE ticket SET exit = ?, SET fee = ? WHERE plate = ? ";

        try (var stmt = ConnectionFactory.getPreparedStatement(sql)) {
            stmt.setString(1, exitTicket.exit());
            stmt.setDouble(2, exitTicket.fee());
            stmt.setString(3, exitTicket.plate());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
