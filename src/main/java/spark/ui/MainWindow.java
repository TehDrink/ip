package spark.ui;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import spark.Spark;

/**
 * Controls the main GUI. (Controller)
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInputField;
    @FXML
    private Button sendButton;

    private Spark spark;

    // Spark Lingonberry: https://www.tiktok.com/@spark.lingonberry
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image sparkImage = new Image(this.getClass().getResourceAsStream("/images/Spark.png"));

    /**
     * Initializes the main window and binds the scroll pane to the dialog container height.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setSpark(Spark s) {
        spark = s;
        spark.getGreeting();
        dialogContainer.getChildren().add(
                DialogBox.getSparkDialog(spark.getGreeting(), sparkImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws InterruptedException {
        String userInput = userInputField.getText();
        String sparkOutput = spark.run(userInput);
        if (sparkOutput == "") {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userInput, userImage),
                    DialogBox.getSparkDialog(spark.getGoodbye(), sparkImage)
            );

            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> System.exit(0));
            delay.play();
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userInput, userImage),
                    DialogBox.getSparkDialog(sparkOutput, sparkImage)
            );
            userInputField.clear();
        }

    }
}
