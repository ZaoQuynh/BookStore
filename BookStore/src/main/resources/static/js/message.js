document.addEventListener("DOMContentLoaded", function() {
    var closeButtons = document.querySelectorAll(".close-mess");

    closeButtons.forEach(function(button) {
        button.addEventListener("click", function() {
            var parentDiv = this.parentElement;
            parentDiv.style.display = "none";
        });
    });
});
