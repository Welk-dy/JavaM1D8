import java.util.ArrayList;
import java.util.List;

public class Event {
    private String eventName;
    private List<Ticket> tickets;

    // Constructor
    public Event(String eventName) {
        this.eventName = eventName;
        this.tickets = new ArrayList<>();
    }

    // Getters and Setters
    public String getEventName() {
        return eventName;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    // Method to add a ticket to the event
    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    // Method to find an available ticket
    public Ticket findAvailableTicket() {
        for (Ticket ticket : tickets) {
            if (!ticket.isBooked()) {
                return ticket;
            }
        }
        return null; // No available tickets
    }
}