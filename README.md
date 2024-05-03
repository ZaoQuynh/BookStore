# **BookStore**
## Hướng dẫn kết nối Docker sử dụng Docker Compose trong BookStore

### Giới thiệu
Hướng dẫn này mô tả cách sử dụng Docker Compose để triển khai một ứng dụng Spring Boot kết nối với cơ sở dữ liệu MySQL thông qua Docker.

### Yêu cầu
- Docker

### Bắt đầu
1. **Tải mã nguồn**
   
    ```terminal
    git clone https://github.com/ZaoQuynh/BookStore.git
    ```

2. **Chạy cơ sở dữ liệu trong Docker**
    
    **Lưu ý:** Bạn phải ở trong source code và đảm bảo cơ sở dữ liệu nằm ở port `3307`.

    **Chạy Docker**
    ```terminal
    docker-compose up -d
    ```
    ![Alt text](run-db-on-docker.png)

    **Xóa container nhưng không muốn xóa cơ sở dữ liệu**
    ```teminal
    docker-compose down -v
    ```

3. **Cấu hình cơ sở dữ liệu trong MySQL Workbench**

    - Connection Name: BookStore
    - Hostname: localhost
    - Port: 3307
    - Username: root
    - Password: root



