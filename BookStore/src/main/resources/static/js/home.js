function searchProduct(event) {
    event.preventDefault(); // Ngăn chặn biểu mẫu gửi dữ liệu theo cách mặc định
    const searchQuery = document.querySelector('input[name="searchQuery"]').value;
    window.location.href = '/products/search?query=' + encodeURIComponent(searchQuery);
}

function goToEdit(event) {
    event.preventDefault();
    location.replace("http://localhost:8080/profile/edit");
}

function goToProfile(event) {
    event.preventDefault();
    location.replace("http://localhost:8080/profile");
}

$("#updateUserForm").submit(function(event) {
    event.preventDefault();
    console.log("vo");
    $.ajax({
        url: $(this).attr("action"),
        method: "POST",
        data: $(this).serialize(),
        success: function(response) {
            console.log(response.message);
            if (response.message === "success") {
                window.location.replace("http://localhost:8080/profile");
            } else {
                console.error("Cập nhật không thành công.");
                // Xử lý trường hợp cập nhật không thành công ở đây
            }
        },
        error: function(xhr, status, error) {
            console.error("Lỗi khi gửi yêu cầu: " + error);
            // Xử lý lỗi khi gửi yêu cầu ở đây
        }
    });
});

$("#deleteForm").submit(function(event) {
    event.preventDefault();
    console.log("vo")
    $.ajax({
        url: $(this).attr("action"),
        method: "POST",
        data: $(this).serialize(),
        success: function(response) {
            console.log(response.message);
            if (response.message === "success") {
                window.location.replace("http://localhost:8080/login");
            } else {
                window.location.replace("http://localhost:8080/profile");
            }
        },
        error: function(xhr, status, error) {
            console.error("Lỗi khi gửi yêu cầu: " + error);
            // Xử lý lỗi khi gửi yêu cầu ở đây
        }
    });
});

