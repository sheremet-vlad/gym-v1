package by.sheremchuk.gym.database;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectorDB {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";
    private static final String URL ="jdbc:mysql://localhost:3306/gym-v1?useSSL=false";

    public static Connection getConnection() throws SQLException {
        Driver driver;
        try {
            driver = new FabricMySQLDriver();
        }
        catch (SQLException ex) {
            System.out.println("Произошла ошибка при создании драйвера");
            return null;
        }

        try {
            DriverManager.registerDriver(driver);
        }
        catch (SQLException ex) {
            System.out.println("Не удалось зарегистрировать драйвер");
            return null;
        }

        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

}
