import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TicketBookingSystem {
    private Map<String, Event> events; // Maps event names to Event objects

    // Constructor
    public TicketBookingSystem() {
        events = new HashMap<>();
    }

    // Method to create an event
    public void createEvent(String eventName) {
        events.put(eventName, new Event(eventName));
    }

    // Method to add a ticket to an event
    public void addTicketToEvent(String eventName, double price) {
        Event event = events.get(eventName);
        if (event != null) {
            Ticket ticket = new Ticket(eventName, price);
            event.addTicket(ticket);
            System.out.println("Ticket created for event: " + eventName);
        } else {
            System.out.println("Event not found.");
        }
    }

    // Method to book a ticket
    public void bookTicket(String eventName, User user) {
        Event event = events.get(eventName);
        if (event != null) {
            Ticket ticket = event.findAvailableTicket();
            if (ticket != null) {
                ticket.bookTicket();
                System.out.println("Ticket booked successfully for " + user.getName() + ". Ticket details:");
                ticket.printTicketDetails();
            } else {
                System.out.println("No tickets available for this event.");
            }
        } else {
            System.out.println("Event not found.");
        }
    }

    // Main method to interact with the system
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem();
        system.createEvent("Music Concert");
        system.addTicketToEvent("Music Concert", 50.0);
        system.addTicketToEvent("Music Concert", 60.0);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        User user = new User(name, email);

        system.bookTicket("Music Concert", user);

        System.out.println("Total tickets sold: " + Ticket.getTotalTicketsSold());
    }
}