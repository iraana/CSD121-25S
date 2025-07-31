package lab6.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import lab6.data.FlashardTopics;

/**
 * MainMenuUI represents the main menu screen where the user selects a flashcard topic
 */
public class MainMenuUI extends VBox {
    /**
     * Interface for listening to topic selection events
     */
    public interface TopicSelectedListener {
        /**
         * Called when the user selects a topic
         * @param topicName The name of the selected topic
         */
        void onTopicSelected(String topicName);
    }

    /**
     * Constructs the main menu UI
     * @param listener A listener that handles what happens when a topic is selected
     */
    public MainMenuUI(TopicSelectedListener listener) {
        // Layout settings
        setSpacing(20);
        setAlignment(Pos.CENTER);
        setStyle("-fx-padding: 40; -fx-background-color: linear-gradient(to bottom, #f0f8ff, #e0ffff);");

        // Title text
        Text title = new Text("Language Flashcards");
        title.setFont(new Font("Arial", 32));

        // Instruction text
        Text instruction = new Text("Choose a Topic to Begin");
        instruction.setFont(new Font("Arial", 18));

        // Adding title and instruction to the VBox
        getChildren().addAll(title, instruction);

        // Create a button for each topic
        for (String topic : FlashardTopics.getTopics().keySet()) {
            Button button = new Button(topic);
            button.setPrefWidth(220);
            button.setStyle("-fx-font-size: 14; -fx-background-color: #87cefa;");
            // Notify listener of selected topic if it was clicked
            button.setOnAction(e -> listener.onTopicSelected(topic));
            getChildren().add(button);
        }
    }
}
