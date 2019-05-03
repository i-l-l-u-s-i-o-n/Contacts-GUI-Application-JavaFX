package shivam.signup_window;

import shivam.authentication.User_Authentication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * created by Shivam on 15-04-2019.
 */
public class Signup_Controller {

    @FXML
    private TextField name;
    @FXML
    private TextField email;
    @FXML
    private TextField pass;
    @FXML
    private Label error;



    @FXML
    public void handelSignpClick(ActionEvent event){

        String uname=name.getText().trim();
        String uemail=email.getText().trim();
        String upass=pass.getText().trim();
        String status=null;

        if (uemail.length()!=0 && uname.length()!=0 && upass.length()!=0){
            status=User_Authentication.addUser(uname,uemail,upass);
            error.setText(status);
            System.out.println(status);
            if(status.equals("Successfully added! Now login again.")) {

                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getClassLoader().getResource("shivam/login_window/login.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("Successfully signed up! Now login again.");

                    stage.setScene(new Scene(root, 800, 400));
                    stage.setResizable(false);
                    stage.show();
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }else {
            error.setText("Please fill all the fields !");
        }
    }
}
