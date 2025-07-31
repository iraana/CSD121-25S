package lab6;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lab6.data.*;
import lab6.controller.FlashcardController;
import lab6.ui.FlashcardUI;
import lab6.ui.MainMenuUI;
import java.util.List;

/**
 * FlashcardApp is the main entry point for the JavaFX application
 */
public class FlashcardApp extends Application {
    private Stage primaryStage;

    /**
     * Called automatically when the application starts
     * @param primaryStage The main application window
     */
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        showMainMenu();
    }

    /**
     * Displays the main menu UI, allowing users to select a topic. When the topic is selected, shows the flashcard game UI.
     */
    private void showMainMenu() {
        MainMenuUI menu = new MainMenuUI(topic -> {
            // Load selected topic's flashcards
            List<Flashcard> cards = FlashardTopics.getTopics().get(topic);
            // Create deck and controller
            FlashcardDeck deck = new FlashcardDeck(cards);
            FlashcardController controller = new FlashcardController(deck);
            // Create and display the flashcard UI
            FlashcardUI ui = new FlashcardUI(controller, this::showMainMenu);
            Scene scene = new Scene(ui, 700, 500);
            primaryStage.setScene(scene);
        });
        // Set and show the main menu scene
        Scene scene = new Scene(menu, 700, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Flashcard Trainer - Main Menu");
        primaryStage.show();
    }

    /**
     * Main method
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
