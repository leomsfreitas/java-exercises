package prova03.prova.ticket;

import prova03.prova.costs.PeriodCostDAO;
import prova03.prova.costs.PeriodCostDTO;
import prova03.prova.customer.Customer;
import prova03.prova.customer.CustomerDAO;
import prova03.prova.customer.CustomerDTO;
import prova03.prova.customer.VehicleType;
import prova03.prova.persistence.PeriodCostDaoImpl;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

public class RegisterExitService {
    private final CustomerDAO customerDAO;
    private final TicketDAO ticketDAO;
    private final PeriodCostDAO periodCostDAO;

    public RegisterExitService(CustomerDAO customerDAO, TicketDAO ticketDAO, PeriodCostDAO periodCostDAO) {
        this.customerDAO = customerDAO;
        this.ticketDAO = ticketDAO;
        this.periodCostDAO = periodCostDAO;
    }

    public double register(String plate) throws SQLException {
        if (plate == null) throw new IllegalArgumentException("Plate don't be NULL");

        CustomerDTO optionalCustomerDTO = customerDAO.findOne(plate).orElseThrow();

        Optional<EntryTicketDTO> openTicket = ticketDAO.findOpenTicket().stream()
                .filter(t -> t.plate().equals(plate))
                .findFirst();

        if (openTicket.isEmpty()) throw new IllegalStateException("Don't have open tickets for this plate!");

        Customer customer = new Customer(plate, optionalCustomerDTO.phone(), VehicleType.valueOf(optionalCustomerDTO.type()));
        Ticket ticket = new Ticket(customer);
        ticket.exit();

        int duration = (int) ticket.parkingDuration();
        double fee = calculateFee(duration);

        ticketDAO.updateExit(new ExitTicketDTO(
                plate,
                dateToString(ticket.getExit()),
                fee));

        return fee;
    }

    private double calculateFee(int totalHours) throws SQLException {
        List<PeriodCostDTO> periods = periodCostDAO.findAll();

        periods.sort(Comparator.comparingInt(PeriodCostDTO::hours).reversed());

        double minFee = Double.MAX_VALUE;

        Map<Integer, Double> dp = new HashMap<>();
        dp.put(0, 0.0);

        for (int h = 1; h <= totalHours; h++) {
            double best = Double.MAX_VALUE;

            for (PeriodCostDTO period : periods) {
                if (period.hours() <= h) {
                    double remaining = dp.get(h - period.hours());
                    best = Math.min(best, remaining + period.fee());
                }
            }

            dp.put(h, best);
        }

        minFee = dp.get(totalHours);
        return minFee;
    }


    private String uuidToString(UUID id) {
        return id.toString();
    }

    private String dateToString(LocalDateTime dateTime) {
        return dateTime.toString();
    }
}
