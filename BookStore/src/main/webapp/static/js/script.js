const container = document.querySelector(".container"),
    pwShowHide = document.querySelectorAll(".showHidePw"),
    pwFields = document.querySelectorAll(".password");

console.log("vo js");

pwShowHide.forEach(eyeIcon => {
    eyeIcon.addEventListener("click", () => {
        pwFields.forEach(pwField => {
            if (pwField.type === "password") {
                pwField.type = "text";

                pwShowHide.forEach(icon => {
                    icon.classList.replace("uil-eye-slash", "uil-eye");
                })
            } else {
                pwField.type = "password";

                pwShowHide.forEach(icon => {
                    icon.classList.replace("uil-eye", "uil-eye-slash");
                })
                }
            })
        })
    });
    
    
$("#registerForm").submit(function(event) {
  event.preventDefault();
  console.log("submit register form");

  // Enhanced loading indicator integration
  var $loadingIndicator = $('<div class="loading-indicator"><span class="spinner"></span> Processing...</div>');
  $('body').append($loadingIndicator);  // Append globally for better visibility

  $.ajax({
    url: $(this).attr("action"),
    method: "POST",
    data: $(this).serialize(),
    beforeSend: function() {
      $loadingIndicator.show(); // Show loading indicator before sending request
    },
    success: function(data) {
      console.log(data);
      $loadingIndicator.hide(); // Hide loading indicator on success

      if (data.error == null) {
        location.replace("http://localhost:8080/notify-check-email");
      } else {
	    var errorMessage = $('<div class="error-message">' + data.error + '</div>');
		$("#registerForm").prepend(errorMessage); // Prepend error message for visibility
	  }
      
    },
    error: function(error) {
      console.log(error);
      $loadingIndicator.hide(); // Hide loading indicator on error (optional)
      // Display error message on the registration page
      var errorMessage = $('<div class="error-message">' + data.error + '</div>');
      $("#registerForm").prepend(errorMessage); // Prepend error message for visibility
    }
  });
});
