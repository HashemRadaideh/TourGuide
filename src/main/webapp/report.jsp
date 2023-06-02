<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Date" %>
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

        <form action="Report" method="post">
          <input type="hidden" name="time" value="<%= new Date() %>" readonly>

          <div class="question">
            Enter phone number
          </div>
          <div class="answer">
            <input type="tel" name="phone" required>
          </div>

          <div class="question">
            Country
          </div>
          <div class="answer">
            <select name="country" required>
              <option value="">Select a country</option>
              <option value="Jordan">Jordan</option>
              <%= request.getAttribute("countrys") != null ? request.getAttribute("countrys") : "" %>
            </select>
          </div>

          <div class="question">
            City
          </div>
          <div class="answer">
            <select name="city" required>
              <option value="">Select a city</option>
              <option value="Jordan">Jordan</option>
              <%= request.getAttribute("citys") != null ? request.getAttribute("citys") : "" %>
            </select>
          </div>

          <div class="question">
            Upload a picture/video - max 20 MB- of the violation
          </div>
          <div class="answer">
            <input type="file" name="media" accept="image/*, video/*" >
          </div>

          <div class="question">
            Select the type of violation:
          </div>
          <div class="answer">
            <label>
              <input type="radio" name="violation" value="red-light" required>
              Red light crossing
            </label>
            <label>
              <input type="radio" name="violation" value="stop-sign" required>
              Running a stop sign
            </label>
            <label>
              <input type="radio" name="violation" value="jaywalking" required>
              Jaywalking
            </label>
            <label>
              <input type="radio" name="violation" value="littering" required>
              Littering
            </label>
          </div>

          <input type="submit" value="Submit Report">
        </form>

      </div>
    </main>
  </body>

</html>
