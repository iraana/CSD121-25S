/**
 * Iryna Nazar 24048851
 * Lab 2
 * 26.05.2025
 */
package lab2;

// import statements
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Package Descriptions:
 * javax.imageio: Provides classes for reading and writing images. It was used here for ImageIO.read() to convert the avatar stream into an Image.
 * javax.swing: Contains classes for building GUI(Graphical User Interface) applications. It was used here for JFrame, JLabel, and ImageIcon.
 * java.awt: Used for GUI components like layout managers, colors, and images. We used Color, BorderLayout, and Image in this project.
 * java.io: Provides input and output functions. We used InputStream for image data and IOException for error handling.
 * java.net: Supports working with URLs and URIs. We used URI.
 * java.net.http: A  HTTP client for sending and receiving HTTP requests and responses. We used it here for HttpClient, HttpRequest, and HttpResponse.
 */

// Defined a class, it's our main application that will generate and display random avatars
public class AvatarGenerator {

    // main method: entry point of the program
    // args is a String[](reference type)
    public static void main(String[] args) {
        // try {...} catch used for exception handling. If there's an error this block catches it
        try {
            // .getRandomAvatarStream() is a call to a static (class) method of AvatarGenerator
            // Returns an InputStream (reference type) containing image data
            var avatarStream = AvatarGenerator.getRandomAvatarStream();
            // .showAvatar is a static (class) method of AvatarGenerator
            // It takes an InputStream (reference type) as an argument to display the image
            AvatarGenerator.showAvatar(avatarStream); // class method
        } catch (IOException | InterruptedException e) {
            // printStackTrace is an instance method of Throwable
            // It prints error information to the console
            e.printStackTrace();
        }

    }

    // Static method that returns an InputStream.
    // It creates a random avatar URL using random style from a string array(reference type) and random seed(int, primitive type)
    // Then it constructs a URI object and sends an HTTP GET request.
    // It returns the InputStream for the image.
    public static InputStream getRandomAvatarStream() throws IOException, InterruptedException {
        // Variable: styles – a String array (reference type) of available avatar styles
        // It stores available avatar styles
        String[] styles = { "adventurer", "adventurer-neutral", "avataaars", "big-ears", "big-ears-neutral", "big-smile", "bottts", "croodles", "croodles-neutral", "fun-emoji", "icons", "identicon", "initials", "lorelei", "micah", "miniavs", "open-peeps", "personas", "pixel-art", "pixel-art-neutral" };

        // Math.random() is a class method that returns a double, but we cast to int using (int)
        // style is a String (reference type)
        var style = styles[(int)(Math.random() * styles.length)];

        // Generates a random seed
        // Math.random() returns a double, but we cast to int using (int)
        // seed is a random int (primitive type)
        var seed = (int)(Math.random() * 10000);

        // Create an HTTP request for a random avatar
        // URI.create is a class method from java.net.URI that returns a URI object (reference type)
        // String.formatted is a class method of String that replaces %s and %d with style and seed
        // uri is an object of type URI (reference)
        var uri = URI.create("https://api.dicebear.com/9.x/%s/png?seed=%d".formatted(style, seed));
        // HttpRequest.newBuilder is a class method
        // build() is an instance method
        // request is of type HttpRequest (reference type)
        var request = HttpRequest.newBuilder(uri).build();

        // Send the request
        // HttpClient.newHttpClient is a class method that returns a new HttpClient object (reference type)
        // client is of type HttpClient (reference)
        try (var client = HttpClient.newHttpClient()) {
            // send() is an instance method of HttpClient that sends a request
            // BodyHandlers.ofInputStream() is a class method of HttpResponse.BodyHandlers that returns a body handler
            // response is an HttpResponse<InputStream> object (reference type)
            var response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
            // response.body() is an instance method that returns the InputStream (reference type)
            return response.body();
        }
    }
    // Static method to display an avatar image in a GUI window
    // imageStream is an InputStream (reference type) passed from main
    public static void showAvatar(InputStream imageStream) {
            // JFrame constructor create a new window with a title PNG Viewer
            JFrame frame = new JFrame("PNG Viewer");
            // .setDefaultCloseOperation is an instance method of JFrame to set close operation
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // .setResizable(false) is an instance method of JFrame to disallow window resizing(disallow because of false)
            frame.setResizable(false);
            // .setSize is an instance method of JFrame to set window size
            frame.setSize(200, 200);
            // It sets background color to black
            // getContentPane is an instance method of JFrame. It returns a Container object (reference type)
            // setBackground is an instance method to set the background color
            frame.getContentPane().setBackground(Color.BLACK);

            // try {...} catch used for exception handling. If there's an error this block catches it.
            try {
                // Load the PNG image
                // ImageIO.read is a class method that decodes an image stream into an Image object
                // image is a reference type
                Image image = ImageIO.read(imageStream);

                // Create a JLabel to display the image
                // ImageIcon constructor wraps the Image object in an ImageIcon (reference type)
                // JLabel constructor creates a label with the image icon
                // imageLabel is a JLabel object (reference type)
                JLabel imageLabel = new JLabel(new ImageIcon(image));
                // add is an instance method of JFrame to place the label in the center of the frame using BorderLayout
                frame.add(imageLabel, BorderLayout.CENTER);

            } catch (IOException e) {
                // printStackTrace is an instance method of Throwable
                // It prints error information to the console if loading the image fails
                e.printStackTrace();
            }
            // Makes the frame visible on screen
            // setVisible is an instance method that displays the JFrame on screen
            frame.setVisible(true);
    }
}
