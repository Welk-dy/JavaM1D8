import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Event.java
public class Event {
    private String eventName;
    private List<Ticket> tickets;

    // Constructor
    public Event(String eventName) {
        this.eventName = eventName;
        this.tickets = new ArrayList<>();
    }

    // Getter for event name
    public String getEventName() {
        return eventName;
    }

    // Method to add a ticket to the event
    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    // Method to get available tickets by type
    public Map<String, Double> getAvailableTickets() {
        Map<String, Double> availableTickets = new HashMap<>();
        for (Ticket ticket : tickets) {
            if (ticket.isAvailable()) {
                availableTickets.put(ticket.getType(), ticket.getPrice());
            }
        }
        return availableTickets;
    }

    // Method to check if a ticket type is available
    public boolean isTicketTypeAvailable(String ticketType) {
        for (Ticket ticket : tickets) {
            if (ticket.getType().equalsIgnoreCase(ticketType) && ticket.isAvailable()) {
                return true;
            }
        }
        return false;
    }

    // Method to get the first available ticket of a specific type
    public Ticket getFirstAvailableTicketByType(String ticketType) {
        for (Ticket ticket : tickets) {
            if (ticket.getType().equalsIgnoreCase(ticketType) && ticket.isAvailable()) {
                return ticket;
            }
        }
        return null;
    }
}