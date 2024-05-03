<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Iconscout CSS -->
<link rel="stylesheet"
	href="https://unicons.iconscout.com/release/v4.0.8/css/line.css">

<!-- CSS -->
<link rel="stylesheet" href="static/css/register.css">
<link rel="stylesheet" href="static/css/style.css">
<script
  src="https://code.jquery.com/jquery-3.7.1.js"
  integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
  crossorigin="anonymous"></script>
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="forms">
			<div class="form register">
				<span class="title">Registration</span>
				<form id="registerForm" action="/register" method="post">
					<div class="input-row">
						<div class="input-field">
							<input type="text" name="fullname" placeholder="Enter your name"
								required> <i class="uil uil-user icon"></i>
						</div>
						<div class="input-field">
							<input type="text" name="username"
								placeholder="Enter your username" required> <i
								class="uil uil-user icon"></i>
						</div>
					</div>
					<div class="input-row">
						<div class="input-field">
							<input type="email" name="email" placeholder="Enter your email"
								required> <i class="uil uil-envelope icon"></i>
						</div>
						<div class="input-field">
							<input type="text" placeholder="Enter your phone number" required>
							<i class="uil uil-phone icon"></i>
						</div>
					</div>
					<div class="input-row">
						<div class="input-field">
							<input type="password" class="password"
								placeholder="Enter your password" required> <i
								class="uil uil-lock icon"></i>
						</div>
						<div class="input-field">
							<input type="password" name="password" class="password"
								placeholder="Confirm your password" required> <i
								class="uil uil-lock icon"></i> <i
								class="uil uil-eye-slash showHidePw"></i>
						</div>
					</div>

					<div class="input-row">
						<div class="input-field">
							<label for="gender">Gender:</label> <select id="gender"
								name="gender">
								<option value="MALE">Male</option>
    							<option value="FEMALE">Female</option>
    							<option value="PREFER_NOT_TO_SAY">Prefer not to say</option>
							</select>
						</div>
						<div class="input-field">
							<label for="role">Role:</label> <select id="role" name="role">
								<option value="CUSTOMER">Customer</option>
								<option value="SELLER">Seller</option>
							</select>
						</div>
					</div>

					<div class="checkbox-text">
						<div class="checkbox-content">
							<input type="checkbox" id="logCheck"> <label
								for="logCheck" class="text">I accept all terms &
								conditions</label>
						</div>
					</div>

					<div class="input-field button">
						<input type="submit" value="Register Now">
					</div>
				</form>

				<div class="login-signup">
					<span class="text">Already have an account? <a href="/login"
						class="text signup-text">Login now</a>
					</span>
				</div>
			</div>
		</div>
	</div>

	<script src="static/js/script.js"></script>
</body>
</html>