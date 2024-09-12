import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TicketBookingSystem {
    private final Map<String, Event> events;

    public TicketBookingSystem() {
        events = new HashMap<>();
    }

    public void createEvent(String eventName) {
        events.put(eventName.toLowerCase(), new Event(eventName));
    }

    public void addTicketToEvent(String eventName, double price, String ticketType) {
        Event event = events.get(eventName.toLowerCase());
        if (event != null) {
            Ticket ticket = new Ticket(eventName, price, ticketType);
            event.addTicket(ticket);
        } else {
            System.out.println("Event not found.");
        }
    }

    public void bookTicket(String eventName, String ticketType, User user) {
        Event event = events.get(eventName.toLowerCase());
        if (event != null) {
            Ticket ticketToBook = event.getFirstAvailableTicketByType(ticketType);

            if (ticketToBook != null) {
                if (!ticketToBook.isBooked()) {
                    ticketToBook.bookTicket();
                    System.out.println("Ticket booked successfully for " + user.getName() + " (Email: " + user.getEmail() + ").");
                    ticketToBook.printTicketDetails();
                }
            } else {
                System.out.println("Ticket of type '" + ticketType + "' is fully booked for '" + eventName + "' event.");
                }
        }
    }

    public void validateTicket(String ticketId) {
        for (Event event : events.values()) {
            Ticket ticket = event.getTicketById(ticketId);
            if (ticket != null) {
                System.out.println("Event: " + ticket.getEventName());
                System.out.print("- " + ticket.getTicketType() + " Ticket");
                System.out.print(" $" + ticket.getPrice());
                System.out.println(", Status: " + (ticket.isBooked() ? "Booked" : "Available"));
                return;
            }
        }
        System.out.println("Ticket ID not found.");
    }

    public void printEventDetails() {
        for (Event event : events.values()) {
            System.out.println("Event: " + event.getEventName());
            for (Ticketable ticket : event.getTickets().values()) {
                ticket.printTicketDetails(); // Polymorphic call
            }
        }
    }

    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem();
        system.createEvent("Music Concert");
        system.addTicketToEvent("Music Concert", 50.0, "Standard");
        system.addTicketToEvent("Music Concert", 60.0, "VIP");

        system.createEvent("Art Exhibition");
        system.addTicketToEvent("Art Exhibition", 30.0, "Standard");
        system.addTicketToEvent("Art Exhibition", 40.0, "VIP");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Book Ticket");
            System.out.println("2. Validate Ticket");
            System.out.println("3. Exit");
            System.out.print("Enter your choice (1, 2, or 3): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                system.printEventDetails(); // Print event details before booking
                System.out.print("Enter the event name or 'exit' to quit: ");
                String eventInput = scanner.nextLine();
                if (eventInput.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting the booking process.");
                    break;
                }

                String eventName;
                if (eventInput.equalsIgnoreCase("music concert")) {
                    eventName = "Music Concert";
                } else if (eventInput.equalsIgnoreCase("art exhibition")) {
                    eventName = "Art Exhibition";
                } else {
                    System.out.println("Invalid event selection. Please enter Music Concert or Art Exhibition.");
                    continue;
                }

                System.out.print("Enter the ticket type or 'exit' to quit: ");
                String ticketTypeInput = scanner.nextLine();
                if (ticketTypeInput.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting the booking process.");
                    break;
                }

                String ticketType;
                if (ticketTypeInput.equalsIgnoreCase("standard")) {
                    ticketType = "Standard";
                } else if (ticketTypeInput.equalsIgnoreCase("vip")) {
                    ticketType = "VIP";
                } else {
                    System.out.println("Invalid ticket type. Please enter Standard or VIP.");
                    continue;
                }

                System.out.print("Enter your name: ");
                String name = scanner.nextLine();
                System.out.print("Enter your email: ");
                String email = scanner.nextLine();
                User user = new User(name, email);

                system.bookTicket(eventName, ticketType, user);

            } else if (choice == 2) {
                System.out.print("Enter ticket ID to validate: ");
                String ticketId = scanner.nextLine();
                system.validateTicket(ticketId);

            } else if (choice == 3) {
                System.out.println("Exiting...");
                break;

            } else {
                System.out.println("Invalid choice. Please select again.");
            }
        }
        scanner.close();
    }
}