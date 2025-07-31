package lab6.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import lab6.controller.FlashcardController;

/**
 * FlashcardUI handles the graphical user interface for the flashcard game
 */
public class FlashcardUI extends BorderPane {

    private final FlashcardController controller;

    private final Label wordLabel = new Label();
    private final TextField answerField = new TextField();
    private final Button checkButton = new Button("Check");
    private final Button nextButton = new Button("Next");
    private final Button skipButton = new Button("Skip");
    private final Button backButton = new Button("Back to Menu");
    private final Label feedbackLabel = new Label();
    private final Rectangle feedbackBox = new Rectangle(20, 20, Color.LIGHTGRAY);
    private final Label scoreLabel = new Label("Correct: 0 | Wrong: 0");

    private final Runnable backToMenuCallback;
    private int correctCount = 0;
    private int wrongCount = 0;
    private boolean answered = false;

    /**
     * Constructor for FlashcardUI
     * @param controller Controller managing flashcard logic
     * @param backToMenuCallback Callback to return to the main menu
     */
    public FlashcardUI(FlashcardController controller, Runnable backToMenuCallback) {
        this.controller = controller;
        this.backToMenuCallback = backToMenuCallback;

        setupLayout();
        setupEvents();
        loadWord();
    }

    /**
     * Sets up the visual layout of the flashcard UI, all buttons, labels, and styles
     */
    private void setupLayout() {
        VBox centerBox = new VBox(15);
        centerBox.setPadding(new Insets(30));
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setStyle("-fx-background-color: linear-gradient(to bottom right, #c3d9ff, #7aaaff);");

        Text title = new Text("Flashcard Challenge");
        title.setFont(new Font("Arial", 36));

        wordLabel.setFont(new Font("Arial", 28));
        feedbackLabel.setFont(new Font("Arial", 16));
        scoreLabel.setFont(new Font("Arial", 16));

        answerField.setPrefWidth(250);
        answerField.setStyle("-fx-font-size: 16;");

        checkButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        skipButton.setStyle("-fx-background-color: #ff9800; -fx-text-fill: white;");
        nextButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
        backButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");

        HBox buttonBox = new HBox(12, checkButton, skipButton, nextButton);
        buttonBox.setAlignment(Pos.CENTER);

        VBox scoreBox = new VBox(5, scoreLabel, feedbackBox);
        scoreBox.setAlignment(Pos.CENTER);

        centerBox.getChildren().addAll(
                title,
                new Label("Translate to Ukrainian:"),
                wordLabel,
                answerField,
                buttonBox,
                feedbackLabel,
                scoreBox,
                backButton
        );
        this.setCenter(centerBox);
    }

    /**
     * Sets up the event handlers for buttons.
     */
    private void setupEvents() {
        checkButton.setOnAction(new CheckHandler());

        skipButton.setOnAction(e -> {
            if (!answered) {
                wrongCount++;
                feedbackLabel.setText(" Skipped! Correct answer: " + controller.getCurrentUkrainianWord());
                feedbackLabel.setTextFill(Color.DARKBLUE);
                feedbackBox.setFill(Color.BLUE);
                scoreLabel.setText("Correct: " + correctCount + " | Wrong: " + wrongCount);
                answered = true;
                checkButton.setDisable(true);
                skipButton.setDisable(true);
            }
        });

        nextButton.setOnAction(e -> {
            if (!answered) {
                feedbackLabel.setText("Please check your answer or skip.");
                feedbackLabel.setTextFill(Color.LIGHTYELLOW);
                feedbackBox.setFill(Color.PINK);
                return;
            }
            if (controller.showNextCard()) {
                loadWord();
                checkButton.setDisable(false);
                skipButton.setDisable(false);
            } else {
                endGame();
            }
        });

        backButton.setOnAction(e -> backToMenuCallback.run());
    }

    /**
     * Loads a new word to be displayed and resets input and feedback.
     */
    private void loadWord() {
        String word = controller.getCurrentEnglishWord();
        wordLabel.setText(word);
        answerField.clear();
        feedbackLabel.setText("");
        feedbackBox.setFill(Color.LIGHTGRAY);
        answered = false;
        checkButton.setDisable(false);
        skipButton.setDisable(false);
    }

    /**
     * Called when all flashcards are completed
     */
    private void endGame() {
        wordLabel.setText("Done!");
        answerField.setDisable(true);
        checkButton.setDisable(true);
        skipButton.setDisable(true);
        nextButton.setDisable(true);
        feedbackLabel.setText("All words completed! \nFinal score: " + correctCount + " correct, " + wrongCount + " wrong.");
        feedbackLabel.setFont(Font.font("Arial", 16));
        feedbackLabel.setTextFill(Color.WHITE);
        feedbackLabel.setWrapText(true);
        feedbackLabel.setMaxWidth(400);
        feedbackLabel.setAlignment(Pos.CENTER);
        feedbackLabel.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        feedbackBox.setFill(Color.ORANGE);
    }

    /**
     * Handles the check button event to validate user input and give feedback
     */
    private class CheckHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            if (answered) return;

            String answer = answerField.getText();
            boolean correct = controller.checkAnswer(answer);
            if (correct) {
                correctCount++;
                feedbackLabel.setText("Correct!");
                feedbackBox.setFill(Color.LIGHTGREEN);
                answered = true;
                checkButton.setDisable(true);
                skipButton.setDisable(true);
            } else {
                wrongCount++;
                String correctAnswer = controller.getCurrentUkrainianWord();
                feedbackLabel.setText("Incorrect. Correct answer: " + correctAnswer + ". \nPlease move to next.");
                feedbackLabel.setTextFill(Color.RED);
                feedbackLabel.setWrapText(true);
                feedbackLabel.setMaxWidth(400);
                feedbackLabel.setAlignment(Pos.CENTER);
                feedbackLabel.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
                feedbackBox.setFill(Color.RED);
                answered = true;
                checkButton.setDisable(true);
                skipButton.setDisable(true);
            }
            scoreLabel.setText("Correct: " + correctCount + " | Wrong: " + wrongCount);
        }
    }
}
