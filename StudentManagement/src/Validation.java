/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Scanner;

/**
 *
 * @author dell
 */
public class Validation {
    public int getIntInput(Scanner scanner, String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input! Please enter a valid integer: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    public String getStringInput(Scanner scanner, String message) {
        System.out.print(message);
        return scanner.next();
    }
}