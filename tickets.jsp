<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="controller.Ticket" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>利用可能なチケット</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1 class="mt-5">利用可能なチケット</h1>
        <table class="table table-striped mt-3">
            <thead>
                <tr>
                    <th scope="col">イベント名</th>
                    <th scope="col">日付</th>
                    <th scope="col">利用可能な座席数</th>
                </tr>
            </thead>
            <tbody>
                 <%
                List tickets = (java.util.List) request.getAttribute("tickets");
                                    if (tickets != null) {
                                        for (Object obj : tickets) {
                                            Ticket ticket = (Ticket) obj;
                %>
                    <tr>
                        <td><%= ticket.getEvent() %></td>
                        <td><%= ticket.getDate() %></td>
                        <td><%= ticket.getAvailableSeats() %></td>
                    </tr>
                <%
                        }
                    }
                %>
            </tbody>
        </table>
    </div>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
