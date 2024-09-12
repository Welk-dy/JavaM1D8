import java.util.LinkedHashMap;
import java.util.Map;

public class Event {
    private final String name;
    private final Map<String, Ticket> tickets;

    public Event(String name) {
        this.name = name;
        this.tickets = new LinkedHashMap<>(); // Use LinkedHashMap to maintain insertion order
    }

    public String getEventName() {
        return name;
    }

    public Map<String, Ticket> getTickets() {
        return tickets;
    }

    public void addTicket(Ticket ticket) {
        tickets.put(ticket.getTicketId(), ticket);
    }

    public Ticket getFirstAvailableTicketByType(String ticketType) {
        for (Ticket ticket : tickets.values()) {
            if (ticket.getTicketType().equalsIgnoreCase(ticketType) && !ticket.isBooked()) {
                return ticket;
            }
        }
        return null;
    }

    public Ticket getTicketById(String ticketId) {
        return tickets.get(ticketId);
    }
}