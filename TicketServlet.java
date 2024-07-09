package controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TicketServlet
 */
@WebServlet("/tickets")
public class TicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TicketServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Ticket> tickets = new ArrayList<>();
        tickets.add(new Ticket("Concert A", "2024-07-10", 100));
        tickets.add(new Ticket("Theater B", "2024-07-15", 50));
        tickets.add(new Ticket("Sports C", "2024-07-20", 200));
        

        request.setAttribute("tickets", tickets);

        RequestDispatcher dispatcher = request.getRequestDispatcher("tickets.jsp");
		dispatcher.forward(request, response);

	}

}
