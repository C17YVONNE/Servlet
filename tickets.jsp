<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Available Tickets</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1 class="mt-5">Available Tickets</h1>
        <c:if test="${not empty message}">
            <div class="alert alert-info">${message}</div>
        </c:if>
        <table class="table table-striped mt-3">
            <thead>
                <tr>
                    <th scope="col">Event</th>
                    <th scope="col">Date</th>
                    <th scope="col">Available Seats</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="ticket" items="${tickets}">
                    <tr>
                        <td>${ticket.event}</td>
                        <td>${ticket.date}</td>
                        <td>${ticket.availableSeats}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <h2 class="mt-5">Book Tickets</h2>
        <form method="post" action="tickets">
            <div class="form-group">
                <label for="event">Event</label>
                <input type="text" class="form-control" id="event" name="event" required>
            </div>
            <div class="form-group">
                <label for="seats">Seats</label>
                <input type="number" class="form-control" id="seats" name="seats" required>
            </div>
            <button type="submit" class="btn btn-primary" style="margin-right: 50px;">Book</button>
            <a href="history" class="btn btn-primary">Booking History</a>
        </form>
        <a href="logout" class="btn btn-secondary mt-3">Logout</a>
    </div>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>