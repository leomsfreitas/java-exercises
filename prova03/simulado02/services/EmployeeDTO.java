package prova03.simulado02.services;

import java.time.LocalDate;

public record EmployeeDTO(
        String id,
        String name,
        LocalDate birthDate,
        double soldValue,
        String inChargeId
) { }
