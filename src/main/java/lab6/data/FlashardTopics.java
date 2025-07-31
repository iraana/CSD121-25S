package lab6.data;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides predefined flashcard topics for the language flashcard application.
 */
public class FlashardTopics {
    /**
     * Returns a map of topics, where each topic is associated with a list of flashcards.
     * @return A map of topic names to lists of Flashcard objects
     */
    public static Map<String, List<Flashcard>> getTopics() {
        Map<String, List<Flashcard>> topics = new LinkedHashMap<>();


        topics.put("Food", List.of(
                new Flashcard("bread", "хліб"),
                new Flashcard("cheese", "сир"),
                new Flashcard("egg", "яйце"),
                new Flashcard("milk", "молоко"),
                new Flashcard("apple","яблуко")
        ));

        topics.put("Animals", List.of(
                new Flashcard("dog", "собака"),
                new Flashcard("horse", "кінь"),
                new Flashcard("bird", "птах"),
                new Flashcard("fish", "риба"),
                new Flashcard("cat","кіт")
        ));

        return topics;
    }
}
