package login_window;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene=new Scene(root, 800, 400);
//        scene.setUserAgentStylesheet("shivam/app.css");
//        scene.getStylesheets().add(getClass().getResource("login_style.css").toExternalForm());

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
//        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
