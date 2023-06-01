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

        <form action="Report" method="post">
          <div class="question">
            i. Date (automatically entered)
          </div>
          <div class="answer">
          <!-- Date input (automatically filled) -->
            <input type="text" value="<?php echo date('Y-m-d'); ?>" readonly>
          </div>

          <div class="question">
            ii. Enter phone number (required field)
          </div>
          <div class="answer">
          <!-- Phone number input -->
            <input type="tel" required>
          </div>

          <div class="question">
            iii. Country (required field)
          </div>
          <div class="answer">
          <!-- Country dropdown list -->
            <select required>
              <option value="">Select a country</option>
              <option value="country1">Country 1</option>
              <option value="country2">Country 2</option>
            <!-- Add more options as needed -->
            </select>
          </div>

          <div class="question">
            iv. City (required field)
          </div>
          <div class="answer">
          <!-- City dropdown list based on the selected country -->
            <select required>
              <option value="">Select a city</option>
            <!-- Add options dynamically using JavaScript based on the selected country -->
            </select>
          </div>

          <div class="question">
            v. Upload a picture/video – max 20 MB- of the violation (required field)
          </div>
          <div class="answer">
          <!-- File upload input -->
            <input type="file" accept="image/*, video/*" required>
          </div>

          <div class="question">
            vi. Select the type of violation: (required field)
          </div>
          <div class="answer">
          <!-- Violation type radio buttons -->
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
