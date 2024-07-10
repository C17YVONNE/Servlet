package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import obj.BookingHistory;
import obj.Ticket;

/**
 * Servlet implementation class BookingHistoryServlet
 */
@WebServlet("/history")
public class BookingHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session = request.getSession(false);
    if (session == null || session.getAttribute("username") == null) {
        response.sendRedirect("login.html");
        return;
    }

    String username = (String) session.getAttribute("username");
    List<Ticket> bookings = BookingHistory.getHistory(username);
    request.setAttribute("bookings", bookings);
    request.getRequestDispatcher("/history.jsp").forward(request, response);
}
}
