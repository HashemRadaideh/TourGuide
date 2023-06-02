<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

  <head>
    <meta charset="UTF-8">
    <title>Tour Guide</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="public/style.css">
  </head>

  <body>
    <%@include file="components/navbar.jsp" %>

    <main>
      <div class="home-page">
        <%= request.getAttribute("content") != null ? request.getAttribute("content") : "" %>
      </div>
    </main>

    <%
    response.setHeader("Refresh", "10; /Tour");
    %>
  </body>

</html>
