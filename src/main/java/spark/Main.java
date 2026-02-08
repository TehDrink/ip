package spark;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import spark.ui.MainWindow;

/**
 * Launches the Spark application.
 */
public class Main extends Application {
    private static final String DATA_PATH = "./data/spark.txt";
    private Spark spark;

    /**
     * Starts the JavaFX application by setting up the main window and injecting the Spark instance.
     *
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        try {
            spark = new Spark(DATA_PATH);

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Spark");
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setSpark(spark); // inject the Spark instance
            stage.show();
        } catch (FileNotFoundException e) {
            System.err.println("Bark! Error! Data file not found at " + DATA_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
