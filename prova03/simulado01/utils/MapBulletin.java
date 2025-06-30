package prova03.simulado01.utils;

import prova03.simulado01.model.Bulletin;
import prova03.simulado01.model.State;
import prova03.simulado01.persistence.BulletinDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class MapBulletin {
    private MapBulletin() {
    }

    public static Bulletin fromRs(ResultSet rs) throws SQLException {
        return new Bulletin(
                rs.getInt("id"),
                rs.getString("city"),
                State.fromName(rs.getString("state")),
                rs.getInt("infected"),
                rs.getInt("deaths"),
                rs.getDouble("icu_ratio"),
                LocalDate.parse(rs.getString("date"))
        );
    }

    public static Bulletin fromDto(BulletinDto bulletinDto) {
        return new Bulletin(
                bulletinDto.id(),
                bulletinDto.city(),
                State.fromName(bulletinDto.state()),
                bulletinDto.infected(),
                bulletinDto.deaths(),
                bulletinDto.icu_ratio(),
                LocalDate.parse(bulletinDto.date())
        );
    }

}
