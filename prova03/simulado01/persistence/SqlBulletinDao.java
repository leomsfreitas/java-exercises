package prova03.simulado01.persistence;

import prova03.simulado01.model.Bulletin;

import java.sql.SQLException;
import java.util.List;

public class SqlBulletinDao implements BulletinDao<Bulletin, Integer> {
    @Override
    public void insert(Bulletin type) {
        String sql = "INSERT INTO bulletins (city, state, infected, deaths, icu_ratio, date) VALUES (?, ?, ?, ?, ?, ?)";
        try (var stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, type.getCity());
            stmt.setString(2, type.getState().toString());
            stmt.setInt(3, type.getInfected());
            stmt.setInt(4, type.getDeaths());
            stmt.setDouble(5, type.getIcuRatio());
            stmt.setString(6, type.getDate().toString());
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            throw  new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer key) {
        String sql = "DELETE FROM bulletins WHERE id = ?";
        try (var stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, key);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Bulletin type) {
        String sql = """
                    UPDATE bulletins
                    SET city = ?, state = ?, infected = ?, deaths = ?, icu_ratio = ?, date = ?
                    WHERE id = ?
                """;

        try (var stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, type.getCity());
            stmt.setString(2, type.getState().toString());
            stmt.setInt(3, type.getInfected());
            stmt.setInt(4, type.getDeaths());
            stmt.setDouble(5, type.getIcuRatio());
            stmt.setString(6, type.getDate().toString());
            stmt.setInt(7, type.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw  new RuntimeException(e);
        }

    }

    @Override
    public boolean existById(Integer key) {
        String sql = "SELECT COUNT(*) FROM bulletins WHERE id = ?";
        try (var stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, key);
            try (var rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Bulletin> findAll() {
        String sql = "SELECT * FROM bulletins";
        List<Bulletin> list = new java.util.ArrayList<>();

        try (var stmt = ConnectionFactory.createPreparedStatement(sql);
             var rs = stmt.executeQuery()) {
            while (rs.next()) {
                Bulletin b = new Bulletin(
                        rs.getInt("id"),
                        rs.getString("city"),
                        prova03.simulado01.model.State.fromName(rs.getString("state")),
                        rs.getInt("infected"),
                        rs.getInt("deaths"),
                        rs.getDouble("icu_ratio"),
                        java.time.LocalDate.parse(rs.getString("date"))
                );
                list.add(b);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }


}
