package prova03.simulado01.services;

import prova03.simulado01.model.Bulletin;
import prova03.simulado01.model.State;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FilterBulletinService {
    public List<Bulletin> filter(List<Bulletin> bulletins,
                                     Optional<String> city,
                                     Optional<State> state,
                                     Optional<LocalDate> begin,
                                     Optional<LocalDate> end) {
        return bulletins.stream()
                .filter(bulletin -> city.isEmpty() || bulletin.getCity().equalsIgnoreCase(city.get()))
                .filter(bulletin -> state.isEmpty() || bulletin.getState().equals(state.get()))
                .filter(bulletin -> begin.isEmpty() || !bulletin.getDate().isBefore(begin.get()))
                .filter(bulletin -> end.isEmpty() || !bulletin.getDate().isAfter(end.get()))
                .collect(Collectors.toList());
    }
}
