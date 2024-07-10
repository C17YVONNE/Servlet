package obj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingHistory {
	private static Map<String, List<Ticket>> history = new HashMap<>();

    public static void addBooking(String username, Ticket ticket) {
        history.computeIfAbsent(username, k -> new ArrayList<>()).add(ticket);
    }

    public static List<Ticket> getHistory(String username) {
        return history.getOrDefault(username, new ArrayList<>());
    }
}
