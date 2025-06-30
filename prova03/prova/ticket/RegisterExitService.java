package prova03.prova.ticket;

import prova03.prova.costs.PeriodCostDao;
import prova03.prova.costs.PeriodCostDto;
import prova03.prova.customer.Customer;
import prova03.prova.customer.CustomerDao;
import prova03.prova.customer.CustomerDto;
import prova03.prova.utils.MapCustomer;
import prova03.prova.utils.MapTicket;
import prova03.prova.utils.StringUtils;

import java.util.Comparator;
import java.util.List;
import java.sql.SQLException;

public class RegisterExitService {
    private final CustomerDao customerDao;
    private final TicketDao ticketDao;
    private final PeriodCostDao periodCostDao;
    public RegisterExitService(CustomerDao customerDao, TicketDao ticketDao, PeriodCostDao periodCostDao) {
        this.customerDao = customerDao;
        this.ticketDao = ticketDao;
        this.periodCostDao = periodCostDao;
    }

    public void register(String plate) throws SQLException {
        StringUtils.validateString(plate);

        CustomerDto customerDto = customerDao.findOne(plate).orElseThrow();
        Customer customer = MapCustomer.fromDto(customerDto);

        EntryTicketDto entryTicketDto = ticketDao.findOpenTicket(plate).orElseThrow();
        Ticket ticket = MapTicket.fromEntryTicketDto(customer, entryTicketDto);

        ticket.exit();
        long duration = ticket.parkingDuration();
        ticket.setFee(calculateFee(duration));

        ticketDao.updateExit(new ExitTicketDto(plate, ticket.getExit().toString(), ticket.getFee()));
    }

    public double calculateFee(Long duration) throws SQLException {
        List<PeriodCostDto> costs = periodCostDao.findAll();
        
        costs.sort(Comparator.comparingLong(PeriodCostDto::hours));
        
        double totalFee = 0;
        long remainingDuration = duration;
        
        for (PeriodCostDto cost : costs) {
            while (remainingDuration >= cost.hours()) {
                totalFee += cost.fee();
                remainingDuration -= cost.hours();
            }
        }

        if (remainingDuration > 0) {
            throw new IllegalArgumentException("Nenhuma taxa encontrada para a duração fornecida.");
        }

        return totalFee;
    }

}
