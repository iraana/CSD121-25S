/**
 * Iryna Nazar
 * Lab-1
 * 12.05.2025
 */

package lab1;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    /**
     * This function saves the student information (name, ID, courses) to a text file studentInfo.txt.
     *
     * @param studentName   The name of the student
     * @param studentId     The student ID
     * @param courses       The list of courses the student is enrolled in
     */
    public static  void  saveStudentInfoToFile(String studentName, int studentId, List<String> courses){
        File file = new File("studentInfo.txt"); // creates a file for saving student info

        // Writing student info to the file ( Student name, ID and enrolled courses )
        try (FileWriter fw = new FileWriter(file,false)) { // 'false' for append  means overwriting the file if it already exists with some content inside
            fw.write("Student name: " + studentName + "\n");
            fw.write("Student ID: " + studentId + "\n");
            fw.write("Enrolled Courses:\n");

            for (String course : courses) {
                fw.write("- " + course + "\n");
            }

            System.out.println("Student information has been saved to studentInfo.txt");
        } catch (IOException e) {
            System.out.println("An error occurred while saving to the file: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        int exit = 1; // a variable responsible for exit condition of the program

        // Loop to run the program printing out student's information until the user decides to exit
        while (exit != 0) {
            List<String> courses = new ArrayList<>(); // initialize a list for enrolled courses
            // Adding courses the student is enrolled this term in to the list
            courses.add("Programming Concepts I");
            courses.add("Databases I");
            courses.add("Intro to Web Development");
            courses.add("System Analysis and Design");
            courses.add("Business Application");

            try {
                int student_id = 24048851;
                String student_name = "Iryna Nazar";

                // Prompt the user to enter their student ID
                System.out.print("Enter your student number: ");
                Scanner scanner = new Scanner(System.in);
                int student_number = scanner.nextInt();

                // Check if student's id is valid
                if (student_number == student_id) {
                    System.out.println("Welcome, " + student_name);
                    System.out.println("Your have "+ courses.size()+" courses");
                    //Print the list of enrolled courses for the student
                    System.out.println("Enrolled Courses:");
                    for (String course : courses) {
                        System.out.println("- " + course);
                    }

                    // Call the method to save student info to the file
                    saveStudentInfoToFile(student_name, student_id, courses);

                } else {
                    // If the id is wrong, prints the error message and asks t
                    System.out.println("Not a valid student number");
                }
                // Ask the user if he wants to end the program
                System.out.println("Do you want to exit the program? (0 to exit, 1 to continue): ");
                exit = scanner.nextInt();

            } catch (Exception e) { // it catches any exceptions that might happen during the user input, for example if he types in 'abcdefg' for a student id prompt
                System.out.println("Something went wrong. Please try again.");
            }

        }

    }
}
