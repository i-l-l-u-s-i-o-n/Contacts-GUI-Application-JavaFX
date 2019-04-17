package signup_window;

import authentication.User_Authentication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
    public void initialize(){
        Image img=new Image("https://service.mycontacts-app.com/contacts/static/img/start_page_image.png");
//        Image img=new Image("signup_window/image/contact2.png");
        image.setImage(img);
    }

    @FXML
    public void handelSignpClick(){

        String uname=name.getText().trim();
        String uemail=email.getText().trim();
        String upass=pass.getText().trim();
        String status=null;

        if (uemail!=null || uname!=null || upass!=null){
            status=User_Authentication.addUser(uname,uemail,upass);
            error.setText(status);
        }else {
            error.setText("Please fill all the fields !");
        }





    }
}
