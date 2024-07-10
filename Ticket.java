package obj;

public class Ticket {
	private String event;
    private String date;
    private int availableSeats;

    public Ticket(String event, String date, int availableSeats) {
        this.event = event;
        this.date = date;
        this.availableSeats = availableSeats;
    }

    public String getEvent() {
        return event;
    }

    public String getDate() {
        return date;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
}
