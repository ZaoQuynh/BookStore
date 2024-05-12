$("#loginForm").submit(function(event) {
	event.preventDefault();
	console.log("vo")
	$.ajax({
		url: $(this).attr("action"),
		method: "POST",
		data: $(this).serialize(),
		success: function(data) {
			console.log(data)
			if (data.message == null ) {
				if(data.role === "CUSTOMER" || data.role === "SELLER"){
					window.location.replace("http://localhost:8080/hello");
				} else {
					window.location.replace("http://localhost:8080/admin");
				}

			} else {
				var errorMessage = $('<div class="error-message">' + data.message + '</div>');
				$("#loginForm").prepend(errorMessage);
			}
		},
		error: function(error) {
			console.log(error);
			$loadingIndicator.hide(); // Hide loading indicator on error (optional)
			// Display error message on the registration page
			var errorMessage = $('<div class="error-message">' + data.message + '</div>');
			$("#loginForm").prepend(errorMessage); // Prepend error message for visibility
		}
	});
});