package signup_window;

import authentication.User_Authentication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private Button signup;
    @FXML
    private Label error;
    @FXML
    private ImageView image;



    @FXML
    public void handelSignpClick(ActionEvent event){

        String uname=name.getText().trim();
        String uemail=email.getText().trim();
        String upass=pass.getText().trim();
        String status=null;

        if (uemail!=null || uname!=null || upass!=null){
            status=User_Authentication.addUser(uname,uemail,upass);
            error.setText(status);
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("login_window/login.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Login");

                stage.setScene(new Scene(root, 500, 500));
                stage.show();
                ((Node)(event.getSource())).getScene().getWindow().hide();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }else {
            error.setText("Please fill all the fields !");
        }
    }
}
