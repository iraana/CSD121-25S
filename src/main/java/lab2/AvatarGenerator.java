package lab2;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AvatarGenerator {

    // main method
    public static void main(String[] args) {

        try {
            var avatarStream = AvatarGenerator.getRandomAvatarStream(); // class method
            AvatarGenerator.showAvatar(avatarStream); // class method
        } catch (IOException | InterruptedException e) {
            e.printStackTrace(); // class method
        }

    }

    public static InputStream getRandomAvatarStream() throws IOException, InterruptedException {
        // Pick a random style
        String[] styles = { "adventurer", "adventurer-neutral", "avataaars", "big-ears", "big-ears-neutral", "big-smile", "bottts", "croodles", "croodles-neutral", "fun-emoji", "icons", "identicon", "initials", "lorelei", "micah", "miniavs", "open-peeps", "personas", "pixel-art", "pixel-art-neutral" };
        var style = styles[(int)(Math.random() * styles.length)]; // class method

        // Generate a random seed
        var seed = (int)(Math.random() * 10000); // class method

        // Create an HTTP request for a random avatar
        var uri = URI.create("https://api.dicebear.com/9.x/%s/png?seed=%d".formatted(style, seed)); // class method
        var request = HttpRequest.newBuilder(uri).build(); // class method

        // Send the request
        try (var client = HttpClient.newHttpClient()) { // class method
            var response = client.send(request, HttpResponse.BodyHandlers.ofInputStream()); // class method
            return response.body();
        }
    }

    public static void showAvatar(InputStream imageStream) {
            JFrame frame = new JFrame("PNG Viewer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // class method
            frame.setResizable(false); // class method
            frame.setSize(200, 200); // class method
            frame.getContentPane().setBackground(Color.BLACK); // class method

            try {
                // Load the PNG image
                Image image = ImageIO.read(imageStream); // class method

                // Create a JLabel to display the image
                JLabel imageLabel = new JLabel(new ImageIcon(image));
                frame.add(imageLabel, BorderLayout.CENTER); // class method

            } catch (IOException e) {
                e.printStackTrace(); // class method
            }

            frame.setVisible(true);
    }
}
