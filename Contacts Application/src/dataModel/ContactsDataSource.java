package dataModel;

import database.Connect_to_DB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactsDataSource {
    public static final String DB_NAME="sql9283549";

    public static final String CONNECTION_STRING="jdbc:mysql://localhost/"+DB_NAME;

    public static final String TABLE_CONTACT="contacts";

    public static final String COLUMN_CID="cid";
    public static final String COLUMN_FNAME="First_Name";
    public static final String COLUMN_LNAME="Last_Name";
    public static final String COLUMN_MNO="Mobile_No";
    public static final String COLUMN_EMAIL="E_mail";

    private Connection conn;



//    public boolean open(){
//        try{
//
//
//            // Using H2 database which is a embedded database for JAVA.
////            Class.forName("com.mysql.cj.jdbc.Driver");
//            Class.forName ("org.h2.Driver");
////            conn=DriverManager.getConnection(CONNECTION_STRING,"root","vision22@");
//            conn = DriverManager.getConnection ("jdbc:h2:~/test", "shivam","vision22@");
//            return true;
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//            return false;
//        }
//    }


//    public void close(){
//        try {
//            if (conn!=null){
//                conn.close();
//            }
//        }catch (SQLException e){
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }
//    }

    public boolean createTableContact(){
        try {
            conn= Connect_to_DB.connect();
            Statement statement=conn.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS "+TABLE_CONTACT+"("
                            +"cid INT ,"+
                    COLUMN_FNAME+" TEXT ,"+
                            COLUMN_LNAME+" TEXT ,"+
                            COLUMN_MNO+" TEXT ,"+
                            COLUMN_EMAIL+" TEXT)");
            return true;

        }catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
     public boolean insertData(String fname,String lname,String mno,String email,int uid){
        try {
            Statement statement=conn.createStatement();
            statement.execute("INSERT INTO "+TABLE_CONTACT+" VALUES ('"+fname+"', '"+lname+"', '"+mno+"', '"+email+"',"+uid+");");
            return true;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
     }


     public boolean deleteContact(String mno){
        try
        {
            conn=Connect_to_DB.connect();
            Statement statement=conn.createStatement();
            statement.execute("DELETE FROM "+TABLE_CONTACT+" WHERE "+COLUMN_MNO+" = \""+mno+"\"");
            return true;
        }catch (SQLException e){
            System.out.println("Error while deleting : "+e);
        }
        return false;

     }

     public boolean updateContactData(ContactDataModel contact, String Mno){
        try {

            conn=Connect_to_DB.connect();
            Statement statement=conn.createStatement();
            statement.execute("UPDATE "+TABLE_CONTACT+" SET "+COLUMN_FNAME+"='"+contact.getF_name()+"' ,"+
                                COLUMN_LNAME+"='"+contact.getL_name()+"',"+
                                COLUMN_MNO+"='"+contact.getM_no()+"',"+
                                COLUMN_EMAIL+"='"+contact.getEmail()+"' WHERE "+COLUMN_MNO+"='"+Mno+"'");
            return true;
        }catch (SQLException e){
            System.out.println("Error while updating : "+e);
        }
        return false;
     }
     public List<ContactDataModel> queryContact(int uid){
        try {
            conn=Connect_to_DB.connect();
            Statement statement=conn.createStatement();
            ResultSet result=statement.executeQuery("SELECT * FROM "+TABLE_CONTACT +" WHERE cid = "+uid);
            List<ContactDataModel> contactDataModels =new ArrayList<ContactDataModel>();
//            while (result.next()){
//                contactDataModels.add(new ContactDataModel(result.getString(1),result.getString(2),
//                            result.getString(3),result.getString(4)));
//            }
            while (result.next()){
                contactDataModels.add(new ContactDataModel(result.getString(1),result.getString(2),
                        result.getString(3),result.getString(4)));
            }
            return contactDataModels;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
     }




}



































