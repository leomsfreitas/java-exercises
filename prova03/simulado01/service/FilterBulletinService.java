package prova03.simulado.service;

import prova03.simulado.model.Bulletin;
import prova03.simulado.model.State;
import prova03.simulado.persistence.BulletinDao;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class FilterBulletinService {
    private final BulletinDao<Bulletin, Integer> bulletinDao;

    public FilterBulletinService(BulletinDao<Bulletin, Integer> bulletinDao) {
        this.bulletinDao = bulletinDao;
    }

    public List<Bulletin> filter(List<Bulletin> allBulletins, String city, State state, LocalDate begin, LocalDate end) {
        return allBulletins.stream()
                .filter(b -> city == null || b.getCity().toLowerCase().contains(city.toLowerCase()))
                .filter(b -> state == null || b.getState() == state)
                .filter(b -> begin == null || !b.getDate().isBefore(begin))
                .filter(b -> end == null || !b.getDate().isAfter(end))
                .collect(Collectors.toList());
    }
}
