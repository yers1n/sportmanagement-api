package sportmanagement.dao;

import org.springframework.stereotype.Repository;
import sportmanagement.entity.Athlete;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AthleteJdbcDao {

    private final DataSource dataSource;

    public AthleteJdbcDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Athlete> findAll() {
        String sql = "SELECT a.id, a.name, a.age, a.rank FROM athlete a ORDER BY a.id";
        List<Athlete> list = new ArrayList<>();

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Athlete a = new Athlete();
                a.setName(rs.getString("name"));
                a.setAge(rs.getInt("age"));
                a.setRank(rs.getInt("rank"));
                // id не сеттим (read-only для демо)
                list.add(a);
            }
        } catch (SQLException e) {
            throw new RuntimeException("JDBC findAll failed", e);
        }
        return list;
    }

    public void create(String name, int age, int rank, long sportId) {
        String sql = "INSERT INTO athlete(name, age, rank, sport_id) VALUES (?, ?, ?, ?)";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setInt(3, rank);
            ps.setLong(4, sportId);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("JDBC create failed", e);
        }
    }

    public void delete(long id) {
        String sql = "DELETE FROM athlete WHERE id = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("JDBC delete failed", e);
        }
    }
}