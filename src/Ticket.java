import java.util.UUID;

public class Ticket implements Ticketable {
    private final String ticketId;
    private final String eventName;
    private final double price;
    private final String ticketType;
    private boolean isBooked;
    private boolean printTicketId; // Flag to control ticket ID printing

    public Ticket(String eventName, double price, String ticketType) {
        this.ticketId = UUID.randomUUID().toString();
        this.eventName = eventName;
        this.price = price;
        this.ticketType = ticketType;
        this.isBooked = false;
        this.printTicketId = false; // Default to not print ticket ID
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getEventName() {
        return eventName;
    }

    public double getPrice() {
        return price;
    }

    public String getTicketType() {
        return ticketType;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void bookTicket() {
        this.isBooked = true;
        this.printTicketId = true; // Set to true when booking
    }

    @Override
    public void printTicketDetails() {
        System.out.print("- " + ticketType + " Ticket");
        System.out.println(" $" + price);
        if (printTicketId) {
            System.out.print("Ticket ID: " + ticketId);
        System.out.println(", Status: " + (isBooked ? "Booked" : "Available"));

        }
    }
}