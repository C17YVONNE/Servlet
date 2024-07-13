package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static Map<String, String> users = new HashMap<>();

    static {
        users.put("user1", "psw1");
        users.put("user2", "psw2");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");

        if (users.containsKey(username) && users.get(username).equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            
            if("on".equals(rememberMe)) {
            	Cookie cookie = new Cookie("username", username);
            	cookie.setMaxAge(60*60*24*7);
            	response.addCookie(cookie);
            }
            response.sendRedirect("tickets");
        } else {
            response.sendRedirect("login.html?error=true");
        }
    }
}