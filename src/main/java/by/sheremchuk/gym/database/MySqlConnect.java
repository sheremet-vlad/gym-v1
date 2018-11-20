package by.sheremchuk.gym.database;


import java.sql.*;

public class MySqlConnect {

    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/gym-v1";

    //Database credentials
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
            String sql;
            sql = "SELECT id, surname, name FROM clients";
            ResultSet request = statement.executeQuery(sql);
            while (request.next()) {
                int id = request.getInt("id");
                String surname = request.getString("surname");
                String name = request.getString("name");

                System.out.printf("id: %d; surname: %s; name: %s", id, surname, name);

            }

            request.close();
            statement.close();
            connection.close();


        }  catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
