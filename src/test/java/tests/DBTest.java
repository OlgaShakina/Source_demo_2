package tests;

import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBTest {
    @Test
    public void checkQuery() throws SQLException {
        try (Connection connection = DataBaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet rs = statement.executeQuery(
                    "SELECT * FROM information_schema.sql_features LIMIT 5");

            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        }
    }
}
