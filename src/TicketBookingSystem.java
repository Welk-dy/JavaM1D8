import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// TicketBookingSystem.java
public class TicketBookingSystem {
    private Map<String, Event> events; // Maps event names to Event objects

    // Constructor
    public TicketBookingSystem() {
        events = new HashMap<>();
    }

    // Method to create an event
    public void createEvent(String eventName) {
        events.put(eventName.toLowerCase(), new Event(eventName)); // Store event names in lowercase
    }

    // Method to add a ticket to an event with a type description
    public void addTicketToEvent(String eventName, double price, String ticketType) {
        Event event = events.get(eventName.toLowerCase());
        if (event != null) {
            Ticket ticket = new Ticket(eventName, price, ticketType);  // Pass ticket type as well
            event.addTicket(ticket);
        } else {
            System.out.println("Event not found.");
        }
    }

    // Method to display all available events and their tickets
    public void displayAvailableEvents() {
        if (events.isEmpty()) {
            System.out.println("No events available.");
            return;
        }

        System.out.println("Available Events and Tickets:");
        int index = 1;
        for (Event event : events.values()) {
            System.out.println(index + ". Event: " + event.getEventName());
            Map<String, Double> availableTickets = event.getAvailableTickets();
            for (Map.Entry<String, Double> entry : availableTickets.entrySet()) {
                System.out.println("  Type: " + entry.getKey() + ", Price: $" + entry.getValue());
            }
            index++;
        }
    }

    // Method to get event name by index
    public String getEventNameByIndex(int index) {
        int currentIndex = 1;
        for (Event event : events.values()) {
            if (currentIndex == index) {
                return event.getEventName();
            }
            currentIndex++;
        }
        return null;
    }

    // Method to book a ticket
    public void bookTicket(String eventName, String ticketType, User user) {
        Event event = events.get(eventName.toLowerCase());
        if (event != null) {
            Ticket ticketToBook = event.getFirstAvailableTicketByType(ticketType);

            if (ticketToBook != null) {
                ticketToBook.bookTicket();
                System.out.println("Ticket booked successfully for " + user.getName() + " (Email: " + user.getEmail() + "). Ticket details:");
                ticketToBook.printTicketDetails(); // Print ticket details including ticket ID
            } else {
                System.out.println("Ticket of type '" + ticketType + "' not available for event '" + eventName + "'.");
            }
        } else {
            System.out.println("Event not found.");
        }
    }

    // Method to validate user input (case-insensitive for event name)
    private boolean isEventNameValid(int eventIndex) {
        return getEventNameByIndex(eventIndex) != null;
    }

    private boolean isTicketTypeValid(String eventName, String ticketType) {
        Event event = events.get(eventName.toLowerCase());
        if (event != null) {
            return event.isTicketTypeAvailable(ticketType);
        }
        return false;
    }

    // Main method to interact with the system
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem();
        system.createEvent("Music Concert");
        system.addTicketToEvent("Music Concert", 50.0, "Standard");
        system.addTicketToEvent("Music Concert", 60.0, "VIP");

        system.createEvent("Art Exhibition");
        system.addTicketToEvent("Art Exhibition", 30.0, "Standard");
        system.addTicketToEvent("Art Exhibition", 40.0, "VIP");

        Scanner scanner = new Scanner(System.in);

        // Display all available events and tickets
        system.displayAvailableEvents();

        // Get user details
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        User user = new User(name, email);

        String eventName = "";
        String ticketType = "";

        // Loop to prompt for a valid event name
        while (true) {
            System.out.print("Enter the number corresponding to the event you want to book a ticket for (or type 'exit' to quit): ");
            String eventInput = scanner.nextLine();
            if (eventInput.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the booking process.");
                return;
            }
            try {
                int eventIndex = Integer.parseInt(eventInput);
                if (system.isEventNameValid(eventIndex)) {
                    eventName = system.getEventNameByIndex(eventIndex);
                    break;
                } else {
                    System.out.println("Invalid event selection. Please enter a valid number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number corresponding to the event.");
            }
        }

        // Loop to prompt for a valid ticket type
        while (true) {
            System.out.print("Enter the Ticket Type (1 for Standard, 2 for VIP, or type 'exit' to quit): ");
            String ticketTypeInput = scanner.nextLine();
            if (ticketTypeInput.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the booking process.");
                return;
            }

            if (ticketTypeInput.equals("1")) {
                ticketType = "Standard";
            } else if (ticketTypeInput.equals("2")) {
                ticketType = "VIP";
            } else {
                System.out.println("Invalid ticket type. Please enter '1' for Standard or '2' for VIP.");
                continue;
            }

            if (system.isTicketTypeValid(eventName, ticketType)) {
                break;
            } else {
                System.out.println("Invalid ticket type for the selected event. Please try again.");
            }
        }

        // Book the selected ticket
        system.bookTicket(eventName, ticketType, user);

        // Display total tickets sold
        System.out.println("Total tickets sold: " + Ticket.getTotalTicketsSold());
    }
}