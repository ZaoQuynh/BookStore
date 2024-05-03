<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Iconscout CSS -->
<link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.8/css/line.css">
<!-- CSS -->
<link rel="stylesheet" href="static/css/login.css">
<script
  src="https://code.jquery.com/jquery-3.7.1.js"
  integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
  crossorigin="anonymous"></script>
<title>Login Form</title>
</head>
<body>
	<div class="container">
        <div class="forms">
            <div class="form login">
                <span class="title">Login</span>
                <form id="loginForm" action="/authentication" method="post">
                    <div class="input-field">
                        <input type="text" name="username" placeholder="Enter your username" required>
                        <i class="uil uil-user icon"></i>
                    </div>
                    <div class="input-field">
                        <input type="password" name="password" class="password" placeholder="Enter your password" required>
                        <i class="uil uil-lock icon"></i>
                        <i class="uil uil-eye-slash showHidePw"></i>
                    </div>

                    <div class="checkbox-text">
                        <div class="checkbox-content">
                            <input type="checkbox" id="logCheck" name="rememberme">
                            <label for="logCheck" class="text">Remember me</label>
                        </div>

                        <a href="forgetPassword.html" class="text">Forgot password?</a>
                    </div>

                    <div class="input-field button">
                        <input type="submit" value="Login Now">
                    </div>
                </form>

                <div class="login-signup">
                    <span class="text">Not a member?
                        <a href="/register" class="text signup-text">Signup now</a>
                    </span>
                </div>
            </div>
        </div>
    </div>
    
	<script src="static/js/login.js"></script>
    <script src="static/js/script.js"></script>
</body>
</html>

