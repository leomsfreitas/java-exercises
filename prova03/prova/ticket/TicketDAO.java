package prova03.prova.ticket;

import java.sql.SQLException;
import java.util.List;

public interface TicketDAO {
    public void save(EntryTicketDTO entryTicket);
    public List<EntryTicketDTO> findOpenTicket() throws SQLException;
    public void updateExit(ExitTicketDTO exitTicket);

}
