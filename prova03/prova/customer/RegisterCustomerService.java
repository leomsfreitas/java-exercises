package prova03.prova.customer;

import prova03.prova.persistence.EntityAlreadyExistsException;

import java.sql.SQLException;
import java.util.Optional;

public class RegisterCustomerService {
    private final CustomerDAO customerDAO;

    public RegisterCustomerService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public void register(String plate, String phone, VehicleType type) throws SQLException {
        if (plate == null) throw new IllegalArgumentException("Plate must not be null.");

        String normalizedPlate = plate.trim().toUpperCase();

        Optional<CustomerDTO> optionalCustomer = customerDAO.findOne(normalizedPlate);
        if (optionalCustomer.isPresent())
            throw new EntityAlreadyExistsException("Customer already exists!");

        customerDAO.save(new CustomerDTO(normalizedPlate, phone, type.name()));
    }
}
