package prova03.prova.customer;

import prova03.prova.persistence.EntityAlreadyExistsException;
import prova03.prova.utils.StringUtils;

import java.sql.SQLException;
import java.util.Objects;

public class RegisterCustomerService {
    private final CustomerDao customerDao;

    public RegisterCustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public void register(String plate, String phone, VehicleType type) throws SQLException {
        StringUtils.validateString(plate);
        StringUtils.validateString(phone);
        Objects.requireNonNull(type, "Type must not be null");

        if (customerDao.findOne(plate).isPresent())
            throw new EntityAlreadyExistsException("Customer already exists");

        customerDao.save(new CustomerDto(phone, phone, type.toString()));
    }

}