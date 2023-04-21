/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskperf6;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TaskPerf6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isLoggedIn = false;
        String filePath = "C:\\Users\\hello\\OneDrive\\Documents\\NetBeansProjects\\TaskPerf6\\src\\taskperf6\\records.txt";

        System.out.println("Select an option:");
        System.out.println("1. Register");
        System.out.println("2. Login");

        int option = scanner.nextInt();
        scanner.nextLine(); // Consume the leftover newline character

        if (option == 1) {
            System.out.print("Enter your desired username: ");
            String username = scanner.nextLine();
            System.out.print("Enter your desired password: ");
            String password = scanner.nextLine();

            if (isValidInput(username) && isValidInput(password)) {
                try {
                    FileWriter writer = new FileWriter(filePath, true);
                    writer.write(username + "," + password + "\n");
                    writer.close();
                    System.out.println("Registration successful.");
                } catch (IOException e) {
                    System.out.println("An error occurred while writing to file.");
                    e.printStackTrace();
                }
            } else {
                System.out.println("Invalid username or password. Only alphanumeric characters are allowed.");
            }
        } else if (option == 2) {
            System.out.print("Enter your username: ");
            String username = scanner.nextLine();
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();

            try {
                File file = new File(filePath);
                Scanner fileScanner = new Scanner(file);

                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    String[] parts = line.split(",");

                    if (username.equals(parts[0]) && password.equals(parts[1])) {
                        isLoggedIn = true;
                        break;
                    }
                }

                fileScanner.close();
            } catch (IOException e) {
                System.out.println("An error occurred while reading the file.");
                e.printStackTrace();
            }

            if (isLoggedIn) {
                System.out.println("Successfully logged in.");
            } else {
                System.out.println("Incorrect username or password.");
            }
        } else {
            System.out.println("Invalid option selected.");
        }
    }

    public static boolean isValidInput(String input) {
        String pattern = "^[a-zA-Z0-9]*$";
        return input.matches(pattern);
    }
}
