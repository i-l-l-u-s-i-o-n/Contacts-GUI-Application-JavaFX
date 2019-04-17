package authentication;

import database.Connect_to_DB;

import java.sql.*;

/**
 * created by Shivam on 15-04-2019.
 */
public class User_Authentication {


    private static Connection conn=null;
    private static Statement statement=null;
    private static ResultSet resultSet=null;

    public static int uid=-1;






    public static int isValid(String name ,String pass) {


        try {
            conn= Connect_to_DB.connect();
            statement = conn.createStatement();
            resultSet=statement.executeQuery("Select * from USER where uname='" + name + "' and upass='" + pass + "';");
            while (resultSet.next()) {
                uid = resultSet.getInt(1);
            }

            Connect_to_DB.disconnect();

            return uid;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return uid;
    }


    public static String addUser(String name,String email,String pass){

        try {


            conn = Connect_to_DB.connect();
            statement = conn.createStatement();
            resultSet=statement.executeQuery("SELECT * from USER where uname='"+name+"';");
            if (resultSet.next()){
                return "Username not available.";
            }else{

                boolean isAdded=statement.execute("INSERT INTO USER(uname,upass,uemail) VALUES('"+name+"','"+pass+"','"+email+"');");
                if (isAdded)
                    return "Successfully added! Now login again.";
            }


        }catch (Exception e){
            System.out.println(e);
        }
        return null;


    }

}
