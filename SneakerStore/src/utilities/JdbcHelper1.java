package utilities;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class JdbcHelper1 {
    
    public static Connection getConnection() {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=SneakerStore";
            String username = "sa";
            String pass = "123456";
            return DriverManager.getConnection(url, username, pass);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
     public static void main(String[] args) {
        System.out.println(getConnection());

    }
}
