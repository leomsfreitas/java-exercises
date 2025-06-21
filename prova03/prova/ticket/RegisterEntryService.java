package prova03.prova.ticket;

import prova03.prova.customer.Customer;
import prova03.prova.customer.CustomerDAO;
import prova03.prova.customer.CustomerDTO;
import prova03.prova.customer.VehicleType;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

public class RegisterEntryService {
    private final CustomerDAO customerDAO;
    private final TicketDAO ticketDAO;

    public RegisterEntryService(CustomerDAO customerDAO, TicketDAO ticketDAO) {
        this.customerDAO = customerDAO;
        this.ticketDAO = ticketDAO;
    }

    public void register(String plate) throws SQLException {
        if (plate == null) throw new IllegalArgumentException("Plate don't be NULL");

        CustomerDTO optionalCustomerDTO = customerDAO.findOne(plate).orElseThrow();

        Customer customer = new Customer(plate, optionalCustomerDTO.phone(), VehicleType.valueOf(optionalCustomerDTO.type()));
        Ticket ticket = new Ticket(customer);

        ticketDAO.save(new EntryTicketDTO(
                uuidToString(ticket.getId()),
                ticket.getPlate(),
                dateToString(ticket.getEntry())));
    }

    private String uuidToString(UUID id) {
        return id.toString();
    }
    private String dateToString(LocalDateTime dateTime) {
        return dateTime.toString();
    }

}
