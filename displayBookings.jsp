<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>映画予約情報</title>
</head>
<body>
	<h2>映画予約情報</h2>
	<table border="1">
		<tr>
			<th>映画タイトル</th>
			<th>顧客名</th>
		</tr>
		<c:forEach var="booking" items="${bookings }">
			<tr>
				<td>${booking.movieTitle }</td>
				<td>${booking.customerName }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>