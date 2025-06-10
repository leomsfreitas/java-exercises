package prova03.simulado.service;

import prova03.simulado.model.Bulletin;
import prova03.simulado.persistence.BulletinDao;

import java.util.List;

public class StatisticsService {
    private final BulletinDao<Bulletin, Integer> bulletinDao;

    public StatisticsService(BulletinDao<Bulletin, Integer> bulletinDao) {
        this.bulletinDao = bulletinDao;
    }

    public StatisticData createStatistic(List<Bulletin> bulletins) {
        int totalCases = bulletins.stream().mapToInt(Bulletin::getInfected).sum();
        int totalDeaths = bulletins.stream().mapToInt(Bulletin::getDeaths).sum();
        double avgIcuRatio = bulletins.stream().mapToDouble(Bulletin::getIcuRatio).average().orElse(0.0);

        return new StatisticData(totalCases, totalDeaths, avgIcuRatio);
    }
}
