<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html lang="en">
  <head>
    <meta charset="UTF-8">
    <title>Tour Guide</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="public/style.css">
  </head>

  <body>

    <main>

      <div class="login-box">
        <style>
          .container {
            display: none;
          }

          .container:target {
            display: flex;
            justify-content: center;
            align-items: center;
          }

          .button {
            background-color: transparent;
            border: none;
            color: #fff;
            display: inline-block;
            font-size: 16px;
            padding: 0;
            margin-top: 0px;
            letter-spacing: 4px;
            text-decoration: none;
            text-transform: uppercase;
            cursor: pointer;
            transition: 0.5s;
          }

          .button:hover {
            color: #03e9f4;
            background-color: transparent;
          }

          button a, button a:hover, button a:focus, button a:active {
            text-decoration: none;
            color: inherit;
          }
        </style>

        <div>
          <button class="button" style="margin: 10px;"><a href="#in">Sign in</a></button>
          <button class="button" style="margin: 10px;"><a href="#up">Sign up</a></button>
        </div>

        <div style="color: #ff0000">
          <%= request.getAttribute("msg") != null ? request.getAttribute("msg") : "" %>
        </div>

        <div id="in" class="container">
          <form method="post" action="Sign">
            <div class="user-box">
              <input type="text" name="username" required>
              <label>Username</label>
            </div>

            <div class="user-box">
              <input type="password" name="password" required>
              <label>Password</label>
            </div>

            <span class="user-box">
              <label for="remember">Remember:</label>
              <input type="checkbox" id="remember" name="remember" value="1"/>
            </span>

            <button type="submit" class="button"><a>Submit</a></button>
          </form>
        </div>

        <div id="up" class="container">
          <form method="put" action="Sign">
            <div class="user-box">
              <input type="text" name="username" required>
              <label>Username</label>
            </div>

            <div class="user-box">
              <input type="email" name="email" required>
              <label>Email</label>
            </div>

            <div class="user-box">
              <input type="password" name="password" required>
              <label>Password</label>
            </div>

            <button type="submit" class="button"><a>Submit</a></button>
          </form>
        </div>
      </div>

    </main>

  </body>

</html>
