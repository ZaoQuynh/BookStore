
function toggleMenu() {
    var menu = document.getElementById("userMenu");
    if (menu.style.display === "flex") {
        menu.style.display = "none";
    } else {
        menu.style.display = "flex";
    }
}