package prova03.simulado01.services;

import prova03.simulado01.model.Bulletin;
import prova03.simulado01.model.StatisticsData;

import java.util.List;

public class StatisticsService {
    public StatisticsData createStatistics(List<Bulletin> bulletins) {
        int totalCases = bulletins.stream().mapToInt(Bulletin::getInfected).sum();
        int totalDeaths = bulletins.stream().mapToInt(Bulletin::getDeaths).sum();
        double avgIcuRatio = bulletins.stream().mapToDouble(Bulletin::getIcuRatio).average().orElse(0);
        return new StatisticsData(totalCases, totalDeaths, avgIcuRatio);
    }
}
