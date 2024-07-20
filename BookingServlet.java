package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import obj.Booking;

/**
 * Servlet implementation class BookingServlet
 */
@WebServlet("/bookings")
public class BookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookingServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String date = request.getParameter("date");
		if (date == null || date.isEmpty()) {
			response.getWriter().println("Please provide a date in the format YYYY/MM/DD.");
			return;
		}

		Connection conn = null;
		PreparedStatement pst = null;
		List<Booking> bookings = new ArrayList<>();

		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
			if (conn != null) {
		        System.out.println("Database connected successfully!");
		    }
			
			String sql = "SELECT movie_title, customer_name FROM bookings WHERE  booking_date = ? :: date";
			pst = conn.prepareStatement(sql);
			pst.setString(1, date);
			ResultSet rs = pst.executeQuery();
			
			System.out.println("Executing query: " + sql + " with date: " + date);
			
			

			while (rs.next()) {
				String movieTitle = rs.getString("movie_title");
				String customerName = rs.getString("customer_name");
				System.out.println("Movie Title: " + movieTitle + ", Customer Name: " + customerName);
				bookings.add(new Booking(movieTitle, customerName));
			}
			rs.close();
			pst.close();
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			response.getWriter().println("データベース接続エラー");
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		for (Booking booking : bookings) {
            System.out.println("Movie Title: " + booking.getMovieTitle() + ", Customer Name: " + booking.getCustomerName());
        }
		request.setAttribute("bookings", bookings);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/displayBookings.jsp");
		dispatcher.forward(request, response);
	}

}
