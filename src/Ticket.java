import java.util.UUID;

public class Ticket {
    private static int totalTicketsSold = 0;
    private String ticketId;
    private String eventName;
    private double price;
    private String type;
    private boolean available;

    // Constructor
    public Ticket(String eventName, double price, String type) {
        this.ticketId = UUID.randomUUID().toString();  // Generate a unique ticket ID
        this.eventName = eventName;
        this.price = price;
        this.type = type;
        this.available = true;
    }

    // Method to book the ticket
    public void bookTicket() {
        if (available) {
            available = false;
            totalTicketsSold++;
        } else {
            System.out.println("Ticket is not available.");
        }
    }

    // Method to check if the ticket is available
    public boolean isAvailable() {
        return available;
    }

    // Getter for the ticket type
    public String getType() {
        return type;
    }

    // Getter for the ticket price
    public double getPrice() {
        return price;
    }

    // Getter for the ticket ID
    public String getTicketId() {
        return ticketId;
    }

    // Print ticket details
    public void printTicketDetails() {
        System.out.println("Ticket ID: " + ticketId + ", Event: " + eventName + ", Type: " + type + ", Price: $" + price);
    }

    // Static method to get total tickets sold
    public static int getTotalTicketsSold() {
        return totalTicketsSold;
    }
}