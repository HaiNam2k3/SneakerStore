package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    public static Connection con = null;
    public static ConnectDB instance = new ConnectDB();

    public static Connection getCon() {
        return con;
    }

    public static ConnectDB getInstance() {
        return instance;
    }

    public void connect() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String uri = "jdbc:sqlserver://localhost:1433;DatabaseName=SneakerStore";
            String user = "sa";
            String pw = "123456";

            con = DriverManager.getConnection(uri, user, pw);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void diconnect() {
        if (con != null) {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
