import java.sql.*;

public class Employees {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost/my_schema?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Helsinki";
        try(
                Connection conn = DriverManager.getConnection (url, "root", "123");
                Statement st = conn.createStatement()
        ) {
            ResultSet rs = st.executeQuery("select * from employee as e\n" +
                    " join tasks as t\n" +
                    " on t.employee_id=e.id\n" +
                    " where t.finish_date > t.estimate_date and t.finish_date + INTERVAL 7 DAY > SYSDATE();");
            System.out.println("Employees with tasks finished after deadline during last 7 days:");
            while (rs.next()) {
                System.out.println(rs.getString(3) + " " + rs.getString(4));
            }

            ResultSet rst = st.executeQuery("select * from tasks as t\n" +
                    "join office as o\n" +
                    "join employee as e\n" +
                    "on e.office_id = o.id and t.employee_id=e.id\n" +
                    "where t.status_id = 2 and o.city = 'Dnipro';");
            System.out.println("\nAmount of tasks in progress in Dnipro:");
            int i = 0;
            while (rst.next()) {
                i++;
            }
            System.out.println(i);

            ResultSet resultSet = st.executeQuery("Select * from employee as e\n" +
                    " join office as o\n" +
                    " on e.office_id = o.id\n" +
                    " where o.country = 'Ukraine' and monthname(SYSDATE()) = monthname(e.birth_day);");
            System.out.println("\nEmployees that will have birthday in this month for Ukraine:");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(3) + " " + resultSet.getString(4));
            }
        }
    }

}
