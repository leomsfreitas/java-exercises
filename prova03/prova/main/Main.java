package prova03.prova.main;

import prova03.prova.costs.PeriodCostDAO;
import prova03.prova.customer.*;
import prova03.prova.persistence.CustomerDaoImpl;
import prova03.prova.persistence.DatabaseBuilder;
import prova03.prova.persistence.EntryTicketDaoImpl;
import prova03.prova.persistence.PeriodCostDaoImpl;
import prova03.prova.ticket.RegisterEntryService;
import prova03.prova.ticket.RegisterExitService;
import prova03.prova.ticket.TicketDAO;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        DatabaseBuilder databaseBuilder = new DatabaseBuilder();
        databaseBuilder.createTables();
        databaseBuilder.populateDatabase();

        CustomerDAO customerDAO = new CustomerDaoImpl();
        TicketDAO ticketDAO = new EntryTicketDaoImpl();
        PeriodCostDAO periodCostDAO = new PeriodCostDaoImpl();

        RegisterCustomerService registerCustomerSevice = new RegisterCustomerService(customerDAO);
        RegisterExitService registerExitService = new RegisterExitService(customerDAO, ticketDAO, periodCostDAO);
        RegisterEntryService registerEntryService = new RegisterEntryService(customerDAO, ticketDAO);

        Customer customer1 = new Customer("ABC1234", "11999999999", VehicleType.CAR);

        Customer customer2 = new Customer("YZX1002", "1188888888", VehicleType.CAR);

        RegisterCustomerService customerService = new RegisterCustomerService(customerDAO);

        customerService.register(customer1.getPlate(), customer1.getPhone(), customer1.getType());
        customerService.register(customer2.getPlate(), customer2.getPhone(), customer2.getType());


    }
}
