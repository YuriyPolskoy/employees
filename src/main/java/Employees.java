import java.sql.*;

public class Employees {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost/my_schema?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Helsinki";
        try(
                Connection conn = DriverManager.getConnection (url, "root", "123");
                Statement st = conn.createStatement()
        ) {
            ResultSet rs = st.executeQuery("select last_name from employee as e\n" +
                    " join tasks as t\n" +
                    " on t.employee_id=e.id\n" +
                    " where t.finish_date > t.estimate_date and t.finish_date + INTERVAL 7 DAY > SYSDATE();");
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        }
    }

}
