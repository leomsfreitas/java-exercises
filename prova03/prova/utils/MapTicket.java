package prova03.prova.utils;

import prova03.prova.customer.Customer;
import prova03.prova.ticket.EntryTicketDto;
import prova03.prova.ticket.Ticket;
import java.time.LocalDateTime;
import java.util.UUID;

public class MapTicket {
    private MapTicket() {
    }

    public static Ticket fromEntryTicketDto( Customer customer, EntryTicketDto entryTicketDto) {
        return new Ticket(
                UUID.fromString(entryTicketDto.id()),
                customer,
                LocalDateTime.parse(entryTicketDto.entry())
        );
    }

    public static EntryTicketDto toEntryTicketDto(Ticket ticket) {
        return new EntryTicketDto(
                ticket.getId().toString(),
                ticket.getPlate(),
                ticket.getEntry().toString()
        );
    }
}
