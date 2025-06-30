package prova03.prova.main;

import prova03.prova.costs.PeriodCostDao;
import prova03.prova.customer.CustomerDao;
import prova03.prova.customer.RegisterCustomerService;
import prova03.prova.customer.VehicleType;
import prova03.prova.persistence.CustomerDaoImpl;
import prova03.prova.persistence.DatabaseBuilder;
import prova03.prova.persistence.EntryTicketDaoImpl;
import prova03.prova.persistence.PeriodCostDaoImpl;
import prova03.prova.ticket.RegisterEntryService;
import prova03.prova.ticket.RegisterExitService;
import prova03.prova.ticket.TicketDao;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        DatabaseBuilder databaseBuilder = new DatabaseBuilder();
        databaseBuilder.createTables();
        databaseBuilder.populateDatabase();

        CustomerDao customerDao = new CustomerDaoImpl();
        TicketDao ticketDao = new EntryTicketDaoImpl();
        PeriodCostDao periodCostDao = new PeriodCostDaoImpl();

        RegisterCustomerService customerService = new RegisterCustomerService(customerDao);
        RegisterEntryService registerEntryService = new RegisterEntryService(customerDao, ticketDao);
        RegisterExitService registerExitService = new RegisterExitService(customerDao, ticketDao, periodCostDao);

        customerService.register("OO0007", "11999999999", VehicleType.CAR);
        customerService.register("POO0015", "11888888888", VehicleType.MOTORCYCLE);

    }
}
