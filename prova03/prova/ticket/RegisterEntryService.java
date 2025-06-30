package prova03.prova.ticket;

import prova03.prova.customer.Customer;
import prova03.prova.customer.CustomerDao;
import prova03.prova.customer.CustomerDto;
import prova03.prova.utils.MapCustomer;
import prova03.prova.utils.MapTicket;
import prova03.prova.utils.StringUtils;
import java.sql.SQLException;

public class RegisterEntryService {
    private final CustomerDao customerDao;
    private final TicketDao ticketDao;

    public RegisterEntryService(CustomerDao customerDao, TicketDao ticketDao) {
        this.customerDao = customerDao;
        this.ticketDao = ticketDao;
    }

    public void register(String plate) throws SQLException {
        StringUtils.validateString(plate);

        CustomerDto customerDto = customerDao.findOne(plate).orElseThrow();

        Customer customer = MapCustomer.fromDto(customerDto);
        Ticket ticket = new Ticket(customer);

        ticketDao.save(MapTicket.toEntryTicketDto(ticket));
    }

}
