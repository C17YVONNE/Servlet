package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterBookingServlet
 */
@WebServlet("/registerBooking")
public class RegisterBookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterBookingServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String movieTitle = request.getParameter("movieTitle");
		String bookingDateStr = request.getParameter("bookingDate");
		String customerName = request.getParameter("customerName");
		
		System.out.println("Received booking date: " + bookingDateStr);

		Connection conn = null;
		PreparedStatement pst = null;

		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
			if (conn != null) {
				System.out.println("Database connected successfully!");
			}

			String sql = "INSERT INTO bookings(movie_title, booking_date, customer_name) VALUES(?,?,?);";
			pst = conn.prepareStatement(sql);
			pst.setString(1, movieTitle);

			Date bookingDate = null;
			try {
				bookingDate = Date.valueOf(bookingDateStr);
			} catch (IllegalArgumentException e) {
				response.getWriter().println("Invalid date format. Please use YYYY-MM-DD.");
				return;
			}
			pst.setDate(2, bookingDate);
			pst.setString(3, customerName);
			pst.executeUpdate();

			response.sendRedirect("success.jsp");

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
	}
}
