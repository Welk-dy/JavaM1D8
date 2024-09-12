import java.util.UUID;

public class Ticket implements Ticketable {
    private static int totalTicketsSold = 0; // Static field to keep track of tickets sold
    private String ticketId;
    private String eventName;
    private double price;
    private boolean isBooked;

    // Constructor
    public Ticket(String eventName, double price) {
        this.ticketId = UUID.randomUUID().toString(); // Unique identifier for each ticket
        this.eventName = eventName;
        this.price = price;
        this.isBooked = false;
    }

    // Getters and Setters for Encapsulation
    public String getTicketId() {
        return ticketId;
    }

    public String getEventName() {
        return eventName;
    }

    public double getPrice() {
        return price;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public static int getTotalTicketsSold() {
        return totalTicketsSold;
    }

    // Method to book a ticket
    public void bookTicket() {
        if (!this.isBooked) {
            this.isBooked = true;
            totalTicketsSold++; // Increment total tickets sold
        }
    }

    // Implemented method from Ticketable interface
    @Override
    public void printTicketDetails() {
        System.out.println("Ticket ID: " + ticketId + ", Event: " + eventName + ", Price: $" + price + ", Booked: " + isBooked);
    }
}