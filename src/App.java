import java.net.URL;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.application.Application;

public class App extends Application {

public static void main(String[] args) throws Exception { launch(args); }

    @Override public void start(Stage stage) throws Exception {

        URL fxml = getClass().getClassLoader().getResource("views/CrudView.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxml);

        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        stage.setTitle("RETO 4 GUI");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }
}
