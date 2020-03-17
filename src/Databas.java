import java.sql.*;
import java.util.Scanner;

public class Databas {
    public static void main(String[] args) {
        try {
            // Set up connection to database
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://"+DatabasLoginData.DBURL + ":" + DatabasLoginData.port + "/" + DatabasLoginData.DBname +
                            "? allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                    DatabasLoginData.user, DatabasLoginData.password);

            // Setup statement
            Statement stmt = conn.createStatement();
            // Create query and execute
            int story_id = 1;
            int target_id = 1;
            String strSelect = "select body from story where id = " + story_id;
            System.out.println("The SQL statement is: " + strSelect + "\n");
            ResultSet rset = stmt.executeQuery(strSelect);

            // Loop through the result set and print

            System.out.println("The records selected are:");
            while(rset.next()) {
                String body = rset.getString("body");
                System.out.println(body);
            }

            while (target_id != 22) {
                String strSelect2 = "select target_id, description from links where story_id = " + story_id;
                System.out.println("The SQL statement is: " + strSelect2 + "\n");
                ResultSet rset2 = stmt.executeQuery(strSelect2);

                while (rset2.next()) {
                    int target_ids = rset2.getInt("target_id");
                    String description = rset2.getString("description");
                    System.out.println(target_ids + ": " + description);
                }

                Scanner in = new Scanner(System.in);
                target_id = in.nextInt();
                story_id = target_id;

                String strSelect3 = "select body from story where id = " + target_id;
                System.out.println("The SQL statement is: " + strSelect3 + "\n");
                ResultSet rset3 = stmt.executeQuery(strSelect3);

                while (rset3.next()) {
                    String body = rset3.getString("body");
                    System.out.println(body);
                }


            }

            // Close conn and stmt
            conn.close();
            stmt.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
}

