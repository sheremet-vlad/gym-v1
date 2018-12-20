package by.sheremchuk.gym.database;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectorDB {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";
    private static final String URL ="jdbc:mysql://localhost:3306/gym-v1?useUnicode=true&characterEncoding=UTF-8&allowPublicKeyRetrieval=true&useSSL=false";

    public static Connection getConnection() throws SQLException {
        Driver driver;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            driver = new FabricMySQLDriver();
        }
        catch (Exception ex) {
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
