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
    <style>
      .container {
        display: none;
      }

      .container:target {
        display: block;
      }
    </style>

    <aside>
      <ul>
        <li><a href="#user-reports">Users Reports</a></li>
        <li><a href="#another-tab">Another tab</a></li>
        <li><a href="/Home">Back to home</a></li>
      </ul>
    </aside>

    <main style="margin-left:25%;padding:1px 16px;height:1000px; color:white;">
      <%= request.getAttribute("content") != null ? request.getAttribute("content") : "" %>
    </main>
  </body>

</html>
