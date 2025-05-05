package lab1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        int exit = 1;


        while (exit != 0) {
            try {
                int student_id = 24048851;
                String student_name = "Iryna Nazar";
                List<String> courses = new ArrayList<>();
                courses.add("Java Programming");
                courses.add("Data Structures");
                courses.add("Web Development");
                courses.add("Algorithms");

                System.out.print("Enter your student number: ");
                Scanner scanner = new Scanner(System.in);
                int student_number = scanner.nextInt();

                if (student_number == student_id) {
                    System.out.println("Welcome, " + student_name);
                    System.out.println("Your have "+ courses.size()+" courses");
                    System.out.println("Enrolled Courses:");
                    for (String course : courses) {
                        System.out.println("- " + course);
                    }

                } else {
                    System.out.println("Not a valid student number");
                }
                System.out.println("Do you want to exit the program? (0 to exit, 1 to continue): ");
                exit = scanner.nextInt();

            } catch (Exception e) {
                System.out.println("Something went wrong");
            }

        }

    }
}
