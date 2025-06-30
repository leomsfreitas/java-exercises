package prova03.prova.ticket;

import java.sql.SQLException;
import java.util.Optional;

public interface TicketDao {
    void save(EntryTicketDto ticket) throws SQLException;
    Optional<EntryTicketDto> findOpenTicket(String plate) throws SQLException;
    void updateExit(ExitTicketDto ticket) throws SQLException;
}
