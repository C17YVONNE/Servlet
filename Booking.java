package obj;

public class Booking {
	private String movieTitle;
	private String customerName;
	
	public Booking(String movieTitle, String customerName) {
		this.movieTitle = movieTitle;
		this.customerName = customerName;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
}
