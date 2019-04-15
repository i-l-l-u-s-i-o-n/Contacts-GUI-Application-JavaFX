//package sample;
//
//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//
//public class Main extends Application {
//
//    @Override
//    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
//        Scene scene=new Scene(root, 1200, 700);
//        setUserAgentStylesheet(STYLESHEET_MODENA);
//        scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
//        scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Montserrat" );
//        primaryStage.setTitle("Contacts");
//        primaryStage.setScene(scene);
//
//        primaryStage.show();
//    }
//
//
//    public static void main(String[] args) {
//        launch(args);
//
//    }
//}
