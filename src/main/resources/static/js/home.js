function searchProduct(event) {
	event.preventDefault(); // Ngăn chặn biểu mẫu gửi dữ liệu theo cách mặc định
	const searchQuery = document.querySelector('input[name="searchQuery"]').value;
	window.location.href = '/products/search?query=' + encodeURIComponent(searchQuery);
}
