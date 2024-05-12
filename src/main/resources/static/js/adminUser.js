function block(id) {
    fetch('/admin/users/' + id + '/block', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to block user');
            } else {
                window.location.replace("http://localhost:8080/admin/users");
            }
        })
        .catch(error => {
            console.error('Error blocking user:', error);
            // Xử lý lỗi tại đây nếu cần
        });
}

function unBlock(id) {
    fetch('/admin/users/' + id + '/unBlock', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to block user');
            } else {
                window.location.replace("http://localhost:8080/admin/users");
            }
        })
        .catch(error => {
            console.error('Error blocking user:', error);
            // Xử lý lỗi tại đây nếu cần
        });
}


function deleted(id) {
    fetch('/admin/users/' + id + '/deleted', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to block user');
            } else {
                window.location.replace("http://localhost:8080/admin/users");
            }
        })
        .catch(error => {
            console.error('Error blocking user:', error);
            // Xử lý lỗi tại đây nếu cần
        });
}



function undelete(id) {
    fetch('/admin/users/' + id + '/undeleted', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to block user');
            } else {
                window.location.replace("http://localhost:8080/admin/users");
            }
        })
        .catch(error => {
            console.error('Error blocking user:', error);
            // Xử lý lỗi tại đây nếu cần
        });
}

function searchUser(event) {
    event.preventDefault(); // Ngăn chặn biểu mẫu gửi dữ liệu theo cách mặc định
    const searchQuery = document.querySelector('input[name="searchQuery"]').value;
    window.location.href = '/admin/users/search?query=' + encodeURIComponent(searchQuery);
}



