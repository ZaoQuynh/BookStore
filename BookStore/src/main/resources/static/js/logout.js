function logout() {
	document.cookie = "accessToken=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
	document.cookie = "refreshToken=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
	// Xóa cookie 'username' và chỉ định thời gian hết hạn trong quá khứ
	document.cookie = "username=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
	location.replace("http://localhost:8080/login")
}