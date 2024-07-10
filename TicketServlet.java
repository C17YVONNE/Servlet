package controller;

import java.io.IOException;
import java.util.ArrayList;
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
 * Servlet implementation class TicketServlet
 */

@WebServlet("/tickets")
public class TicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<Ticket> tickets = new ArrayList<>();

	public TicketServlet() {
		tickets.add(new Ticket("Concert A", "2024-07-10", 100));
		tickets.add(new Ticket("Theater B", "2024-07-15", 50));
		tickets.add(new Ticket("Sports C", "2024-07-20", 200));
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("username") == null) {
			response.sendRedirect("login.html");
			return;
		}

		request.setAttribute("tickets", tickets);
		request.getRequestDispatcher("/tickets.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("username") == null) {
			response.sendRedirect("login.html");
			return;
		}

		String username = (String) session.getAttribute("username");
		String event = request.getParameter("event");
		int seats = Integer.parseInt(request.getParameter("seats"));

		Ticket ticket = findTicket(event);
		if (ticket != null && ticket.getAvailableSeats() >= seats) {
			ticket.setAvailableSeats(ticket.getAvailableSeats() - seats);
			BookingHistory.addBooking(username, new Ticket(event, ticket.getDate(), seats));
			request.setAttribute("message", "予約が成功しました！");
		} else {
			request.setAttribute("message", "座席が不足しています。");
		}

		request.setAttribute("tickets", tickets);
		request.getRequestDispatcher("/tickets.jsp").forward(request, response);
	}

	private Ticket findTicket(String event) {
		for (Ticket ticket : tickets) {
			if (ticket.getEvent().equals(event)) {
				return ticket;
			}
		}
		return null;
	}
}
