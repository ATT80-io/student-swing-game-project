import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlayerService {

   
    // Login
    public Player login(String username, String password) {

        String sql = "SELECT * FROM players WHERE username = ? AND password = ?";

        try (
                Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return new Player(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getInt("wins"),
                        rs.getInt("losses"),
                        rs.getInt("draws"),
                        rs.getInt("score")
                );

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    // Update Statistics
   
    public void updateStatistics(Player player, String result) {

        int additionalScore = 0;
        String sql = "";

        if (result.equalsIgnoreCase("WIN")) {

            additionalScore = 10;

            sql = """
                    UPDATE players
                    SET wins = wins + 1,
                        score = score + ?
                    WHERE id = ?
                    """;

            player.setWins(player.getWins() + 1);
            player.setScore(player.getScore() + additionalScore);

        } else if (result.equalsIgnoreCase("LOSE")) {

            sql = """
                    UPDATE players
                    SET losses = losses + 1
                    WHERE id = ?
                    """;

            player.setLosses(player.getLosses() + 1);

        } else if (result.equalsIgnoreCase("DRAW")) {

            additionalScore = 3;

            sql = """
                    UPDATE players
                    SET draws = draws + 1,
                        score = score + ?
                    WHERE id = ?
                    """;

            player.setDraws(player.getDraws() + 1);
            player.setScore(player.getScore() + additionalScore);

        }

        try (
                Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            if (result.equalsIgnoreCase("LOSE")) {

                stmt.setInt(1, player.getId());

            } else {

                stmt.setInt(1, additionalScore);
                stmt.setInt(2, player.getId());

            }

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

  
    // Top 5 Scorers
   
    public ArrayList<Player> getTopFiveScorers() {

        ArrayList<Player> players = new ArrayList<>();

        String sql = """
                SELECT *
                FROM players
                ORDER BY score DESC, wins DESC
                LIMIT 5
                """;

        try (
                Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {

            while (rs.next()) {

                players.add(
                        new Player(
                                rs.getInt("id"),
                                rs.getString("username"),
                                rs.getInt("wins"),
                                rs.getInt("losses"),
                                rs.getInt("draws"),
                                rs.getInt("score")
                        )
                );

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return players;
    }

   
    // Get Player by ID
   
    public Player getPlayerById(int id) {

        String sql = "SELECT * FROM players WHERE id = ?";

        try (
                Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return new Player(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getInt("wins"),
                        rs.getInt("losses"),
                        rs.getInt("draws"),
                        rs.getInt("score")
                );

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}