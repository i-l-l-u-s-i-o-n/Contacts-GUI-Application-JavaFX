package login_window;

import authentication.User_Authentication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * created by Shivam on 14-04-2019.
 */

// It is the class where we implement our business logic.

public class Controller {

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
    public void showLoginWindow(ActionEvent event){

        String name=uname.getText();
        String pass=upass.getText();

        System.out.println(name+" "+pass);

        int uid= User_Authentication.isValid(name,pass);

        if (uid!=-1){
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("sample/mainwindow.fxml"));
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
        }


    }






}
