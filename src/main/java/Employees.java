import java.sql.*;

public class Employees {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost/my_schema?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Helsinki";
        try(
                Connection conn = DriverManager.getConnection (url, "root", "123");
                Statement st = conn.createStatement()
        ) {
            ResultSet rs = st.executeQuery("select count(*) from employee");
            while (rs.next()) {
                System.out.println(rs.getInt(1));
            }
        }
    }

}
