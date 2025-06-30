package prova03.prova.utils;

import prova03.prova.customer.Customer;
import prova03.prova.customer.CustomerDto;
import prova03.prova.customer.VehicleType;

public class MapCustomer {
    private MapCustomer() {}

    public static Customer fromDto(CustomerDto customerDto) {
        return new Customer(
                customerDto.plate(),
                customerDto.phone(),
                VehicleType.valueOf(customerDto.type()));
    }

    public static CustomerDto toDto(Customer customer) {
        return new CustomerDto(
                customer.getPlate(),
                customer.getPhone(),
                customer.getType().toString()
        );
    }
}
