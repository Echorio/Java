import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class FlightManagement {
    private ArrayList<Flight> flights;
    private ArrayList<Passenger> passengers;
    private ArrayList<CrewMember> crewMembers;
    private boolean isAdminLoggedIn;

    public FlightManagement() {
        flights = new ArrayList<>();
        passengers = new ArrayList<>();
        crewMembers = new ArrayList<>();
        isAdminLoggedIn = true;
    }

    public void run() {
        boolean running = true;

        while (running) {
            displayMenu();
            int choice = Validation.checkInputInt();

            switch (choice) {
                case 1:
                    if (isAdminLoggedIn)
                        addFlight();
                    else System.out.println("You need to be logged in as an administrator to access this feature.");
                    break;
                case 2:
                    searchFlights();
                    break;
                case 3:
                    makeReservation();
                    break;
                case 4:
                    checkIn();
                    break;
                case 5:
                    if (isAdminLoggedIn)
                        manageCrew();
                    else System.out.println("You need to be logged in as an administrator to access this feature.");
                    break;
                case 6:
                    if (isAdminLoggedIn) {
                        isAdminLoggedIn = false;
                        System.out.println("Logged out successfully.");
                    } else System.out.println("You are already logged out.");
                    break;
                case 7:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        System.out.println("----------End Program----------");
    }

    private void displayMenu() {
        System.out.println("Flight Management System Menu");
        System.out.println("1. Add Flight");
        System.out.println("2. Search Flights");
        System.out.println("3. Make Reservation");
        System.out.println("4. Check-In");
        System.out.println("5. Manage Crew");
        System.out.println("6. Administration Log Out");
        System.out.println("7. Quit");
        System.out.print("Enter your choice: ");
    }

    private void addFlight() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter flight number: ");
        String flightNumber = Validation.checkInputString();
        System.out.print("Enter departure city: ");
        String departureCity = Validation.checkInputString();
        System.out.print("Enter destination city: ");
        String destinationCity = Validation.checkInputString();
        System.out.print("Enter departure time: ");
        String departureTime = Validation.checkDepartureTime();
        System.out.print("Enter arrival time: ");
        String arrivalTime = Validation.checkArrivalTime(departureTime);
        System.out.print("Enter available seats: ");
        int availableSeats = Validation.checkInputInt();
        scanner.nextLine();

        Flight flight = new Flight(flightNumber, departureCity, destinationCity, departureTime, arrivalTime, availableSeats);
        flights.add(flight);

        System.out.println("Flight added successfully!");
    }

    private void searchFlights() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter departure city: ");
        String departureCity = Validation.checkInputString();
        System.out.print("Enter destination city: ");
        String destinationCity = Validation.checkInputString();

        ArrayList<Flight> matchingFlights = new ArrayList<>();
        for (Flight flight : flights) {
            if (flight.getDepartureCity().equalsIgnoreCase(departureCity) && flight.getDestinationCity().equalsIgnoreCase(destinationCity)) {
                matchingFlights.add(flight);
            }
        }

        if (matchingFlights.isEmpty()) {
            System.out.println("No flights found for the given criteria.");
        } else {
            System.out.println("Matching Flights:");
            for (Flight flight : matchingFlights) {
                System.out.println(flight);
            }
        }
    }

    private void makeReservation() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter passenger name: ");
        String passengerName = scanner.nextLine();
        System.out.print("Enter flight number: ");
        String flightNumber = scanner.nextLine();

        Flight selectedFlight = null;
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equalsIgnoreCase(flightNumber)) {
                selectedFlight = flight;
                break;
            }
        }

        if (selectedFlight == null) {
            System.out.println("Invalid flight number. Reservation failed.");
        } else if (selectedFlight.getAvailableSeats() == 0) {
            System.out.println("No available seats on the selected flight. Reservation failed.");
        } else {
            Random random = new Random();
            int reservationId;
            do{
                reservationId = random.nextInt(1000000);
            } while(Validation.checkIdExist(passengers, reservationId));
            Passenger passenger = new Passenger(passengerName, reservationId, selectedFlight);
            passengers.add(passenger);
            selectedFlight.setAvailableSeats(selectedFlight.getAvailableSeats() - 1);
            System.out.println("Reservation made successfully!");
        }
    }
    
    private void checkIn() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter passenger name: ");
        String passengerName = scanner.nextLine();

        Passenger selectedPassenger = null;
        for (Passenger passenger : passengers) {
            if (passenger.getName().equalsIgnoreCase(passengerName)) {
                selectedPassenger = passenger;
                break;
            }
        }

        if (selectedPassenger == null) {
            System.out.println("Passenger not found. Check-in failed.");
        } else if (selectedPassenger.isCheckedIn()) {
            System.out.println("Passenger is already checked in.");
        } else {
            selectedPassenger.setCheckedIn(true);
            System.out.println("Check-in successful!");
        }
    }

    private void manageCrew() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Crew Management Menu");
        System.out.println("1. Add Crew Member");
        System.out.println("2. Remove Crew Member");
        System.out.println("3. Modify Crew Member");
        System.out.print("Enter your choice: ");
        int choice = Validation.checkInputInt();

        switch (choice) {
            case 1:
                addCrewMember();
                break;
            case 2:
                removeCrewMember();
                break;
            case 3:
                modifyCrewMember();
                break;
            default:
                System.out.println("Invalid choice. Returning to main menu.");
        }
    }

    private void addCrewMember() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter crew member name: ");
        String crewMemberName = Validation.checkInputString();
        // Add the crew member to the list of crew members
        System.out.println("Crew member added successfully!");
    }

    private void removeCrewMember() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter crew member name: ");
        String crewMemberName = Validation.checkInputString();
        // Remove the crew member from the list of crew members
        System.out.println("Crew member removed successfully!");
    }

    private void modifyCrewMember() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter crew member name: ");
        String crewMemberName = Validation.checkInputString();
        // Search for the crew member in the list of crew members
        // If found, modify the crew member's details
        System.out.println("Crew member modified successfully!");
    }

    public static void main(String[] args) {
        FlightManagement system = new FlightManagement();
        system.run();
    }
}
