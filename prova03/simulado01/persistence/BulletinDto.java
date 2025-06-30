package prova03.simulado01.persistence;

public record BulletinDto(int id, String city, String state, int infected, int deaths, double icu_ratio, String date) {
}
