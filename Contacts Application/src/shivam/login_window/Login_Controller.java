package shivam.login_window;

import shivam.authentication.User_Authentication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * created by Shivam on 14-04-2019.
 */

// It is the class where we implement our business logic.

public class Login_Controller {

    // This annotation is used to refer a element of fxml file.
    @FXML
    private Button login;  // The LogIn button has id 'login' in fxml file.
    @FXML
    private TextField uname;
    @FXML
    private PasswordField upass;
    @FXML
    private Button signup;
    @FXML
    private Label error;





    @FXML
    public void handelLoginClick(ActionEvent event){


        String name=uname.getText();
        String pass=upass.getText();

        System.out.println(name+" "+pass);

        int uid= User_Authentication.isValid(name,pass);

        if (uid!=-1){
            Parent root;
            try {
                // We removed getClassLoader() after getClass() as it was causing error in loading contacts window after login.
                root = FXMLLoader.load(getClass().getResource("/shivam/contacts_window/mainWindow.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Contacts Application");
                stage.setScene(new Scene(root, 1200, 700));
                stage.show();
                // Hide this current window (if this is what you want)
                ((Node)(event.getSource())).getScene().getWindow().hide();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            error.setText("Invalid Credentials ! Try again .");
        }
    }

    @FXML
    public void handelSignupClick(ActionEvent event){
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("shivam/signup_window/signup_win.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Signup");
            stage.setScene(new Scene(root, 800, 400));
            stage.show();
            stage.setResizable(false);
            // Hide this current window (if this is what you want)

            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
