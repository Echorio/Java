import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Validation {

    private final static Scanner in = new Scanner(System.in);

    public static String checkInputString() {
        while (true) {
            String result = in.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println("String cannot be empty");
                System.out.print("Enter again: ");
            } else {
                return result;
            }
        }
    }
    
    public static int checkInputInt() {
        while (true) {
            try {
                int result = Integer.parseInt(in.nextLine());
                if(result>=0)
                    return result;
                else {
                    System.err.println("Must be larger than 0!");
                    System.out.print("Enter again: ");
                }
            } catch (NumberFormatException e) {
                System.err.println("Please input int data type");
                System.out.print("Enter again: ");
            }

        }
    }

    public static boolean checkInputYN() {
        while (true) {
            String result = checkInputString();
            if (result.equalsIgnoreCase("Y")) {
                return true;
            }
            if (result.equalsIgnoreCase("N")) {
                return false;
            }
            System.err.println("Please input y/Y or n/N.");
            System.out.print("Enter again: ");
        }
    }
    
    public static String checkDepartureTime() {
        DateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        dateTimeFormat.setLenient(false);

        while (true) {
            System.out.print("Enter departure time (dd/MM/YYYY HH:mm): ");
            String departure = checkInputString();

            try {
                dateTimeFormat.parse(departure);
                return departure;
            } catch (ParseException e) {
                System.err.println("Wrong format!");
                System.out.print("Enter again: ");
            }
        }
    }
    
    public static String checkArrivalTime(String departure) {
        DateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        dateTimeFormat.setLenient(false);

        while (true) {
            System.out.print("Enter arrival time (dd/MM/YYYY HH:mm): ");
            String arrival = checkInputString();

            try {
                if (dateTimeFormat.parse(arrival).after(dateTimeFormat.parse(departure)))
                    return arrival;
                else {
                    System.err.println("Arrival time must be later than departure time!");
                    System.out.print("Enter again: ");
                }
            } catch (ParseException e) {
                System.err.println("Wrong format!");
                System.out.print("Enter again: ");
            }
        }
    }
    
    public static String checkInputFlight() {
        while (true) {
            String result = checkInputString();
            if (result.matches("F\\d{4}"))
                return result;
            System.err.println("Wrong flight number format!");
            System.out.print("Enter again: ");
        }
    }

    public static boolean checkIdExist(ArrayList<Passenger> passengers, int iD) {
        for (Passenger p : passengers) {
            if (iD == p.getiD())
                return true;
            }
        return false;
    }
}