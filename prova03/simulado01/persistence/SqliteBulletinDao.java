package prova03.simulado01.persistence;

import prova03.simulado01.model.Bulletin;
import prova03.simulado01.utils.MapBulletin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteBulletinDao implements BulletinDao<Bulletin, Integer> {

    @Override
    public void insert(Bulletin bulletin) {
        String sql = "INSERT INTO bulletins (id, city, state, infected, deaths, icu_ratio, date) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (var stmt = ConnectionFactory.getPreparedStatement(sql)) {
            stmt.setInt(1, bulletin.getId());
            stmt.setString(2, bulletin.getCity());
            stmt.setString(3, bulletin.getState().toString());
            stmt.setInt(4, bulletin.getInfected());
            stmt.setInt(5, bulletin.getDeaths());
            stmt.setDouble(6, bulletin.getIcuRatio());
            stmt.setString(7, bulletin.getDate().toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM bulletins WHERE id = ?";

        try (var stmt = ConnectionFactory.getPreparedStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Integer id, Bulletin bulletin) {
        String sql = "UPDATE bulletins SET city = ?, state = ?, infected = ?, deaths = ?, icu_ratio = ?, date = ? WHERE id = ?";

        try (var stmt = ConnectionFactory.getPreparedStatement(sql)) {
            stmt.setString(1, bulletin.getCity());
            stmt.setString(2, bulletin.getState().toString());
            stmt.setInt(3, bulletin.getInfected());
            stmt.setInt(4, bulletin.getDeaths());
            stmt.setDouble(5, bulletin.getIcuRatio());
            stmt.setString(6, bulletin.getDate().toString());
            stmt.setInt(7, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existsById(Integer id) {
        String sql =  "SELECT * FROM bulletins WHERE id = ?";

        try (var stmt = ConnectionFactory.getPreparedStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public List<Bulletin> findAll() {
        String sql = "SELECT * FROM bulletins";

        try (var stmt = ConnectionFactory.getPreparedStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            List<Bulletin> bulletins = new ArrayList<>();
            while (rs.next()) {
                bulletins.add(MapBulletin.fromRs(rs));
            }

            return bulletins;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Bulletin> findById(Integer id) {
        String sql = "SELECT * FROM bulletins WHERE id = ?";

        try (var stmt = ConnectionFactory.getPreparedStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(MapBulletin.fromRs(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}
