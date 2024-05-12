function block(id) {
    fetch('/admin/products/' + id + '/block', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to block user');
            } else {
                window.location.replace("http://localhost:8080/admin/products");
            }
        })
        .catch(error => {
            console.error('Error blocking user:', error);
            // Xử lý lỗi tại đây nếu cần
        });
}

function unBlock(id) {
    fetch('/admin/products/' + id + '/unBlock', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to block user');
            } else {
                window.location.replace("http://localhost:8080/admin/products");
            }
        })
        .catch(error => {
            console.error('Error blocking user:', error);
            // Xử lý lỗi tại đây nếu cần
        });
}


function deleted(id) {
    fetch('/admin/products/' + id + '/deleted', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to block user');
            } else {
                window.location.replace("http://localhost:8080/admin/products");
            }
        })
        .catch(error => {
            console.error('Error blocking user:', error);
            // Xử lý lỗi tại đây nếu cần
        });
}



function undelete(id) {
    fetch('/admin/products/' + id + '/undeleted', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to block user');
            } else {
                window.location.replace("http://localhost:8080/admin/products");
            }
        })
        .catch(error => {
            console.error('Error blocking user:', error);
            // Xử lý lỗi tại đây nếu cần
        });
}

function searchProduct(event) {
    event.preventDefault(); // Ngăn chặn biểu mẫu gửi dữ liệu theo cách mặc định
    const searchQuery = document.querySelector('input[name="searchQuery"]').value;
    window.location.href = '/admin/products/search?query=' + encodeURIComponent(searchQuery);
}



