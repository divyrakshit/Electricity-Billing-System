package electricity.billing.system;

import java.sql.DriverManager;
import java.sql.*;



public class Conn {

    Connection c;
    Statement s;
    Conn() {
        try {
            c = DriverManager.getConnection("jdbc:mysql:///ebs", "root", "divy123");
            s = c.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}