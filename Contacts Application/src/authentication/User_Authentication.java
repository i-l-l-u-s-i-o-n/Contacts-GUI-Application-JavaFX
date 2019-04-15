package authentication;

import java.sql.*;

/**
 * created by Shivam on 15-04-2019.
 */
public class User_Authentication {

    private static Connection conn=null;
    private static Statement statement=null;
    private static ResultSet resultSet=null;

    public static int uid=-1;



    private static boolean connect() {
        try {


            // Using H2 database which is a embedded database for JAVA.
//            Class.forName("com.mysql.cj.jdbc.Driver");
            Class.forName("org.h2.Driver");
//            conn=DriverManager.getConnection(CONNECTION_STRING,"root","");
            conn = DriverManager.getConnection("jdbc:h2:~/test", "shivam", "1234");
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }


    public static int isValid(String name ,String pass) {

        if (connect()) {
            try {
                statement = conn.createStatement();
                statement.executeQuery("Select * from USER where unmae='" + name + "' and upass='" + pass + "';");
                while (resultSet.next()) {
                    uid = resultSet.getInt(1);
                }

                return uid;

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return uid;
        }
        return uid;


    }

}
