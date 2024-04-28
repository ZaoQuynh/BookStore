# **BookStore**
## Hướng dẫn kết nối Docker sử dụng Docker Compose trong BookStore

### Giới thiệu
Hướng dẫn này mô tả cách sử dụng Docker Compose để triển khai một ứng dụng Spring Boot kết nối với cơ sở dữ liệu MySQL thông qua Docker.

### Yêu cầu
- Docker
- Docker Compose

### Bắt đầu
1. **Tải mã nguồn**
   
    ```terminal
    git clone https://github.com/ZaoQuynh/BookStore.git
    ```

2. **Cấu hình MySQL trong Docker Compose**
    - Mở file docker-compose.yml.
    - Đảm bảo rằng các biến môi trường như MYSQL_ROOT_PASSWORD, MYSQL_DATABASE, MYSQL_USER, và MYSQL_PASSWORD đã được cấu hình đúng.

3. **Cấu hình cơ sở dữ liệu trong MySQL Workbench**

    - Connection Name: BookStore
    - Hostname: localhost
    - Port: 3306
    - Username: root
    - Password: <Mật khẩu MySQL của bạn>

4. **Chạy cơ sở dữ liệu trong Docker**
    
    **Lưu ý:** Bạn phải ở trong source code và đảm bảo cơ sở dữ liệu nằm ở port `3306`.

    **Chạy Docker**
    ```terminal
    docker-compose up -d
    ```
    ![Alt text](run-db-on-docker.png)

    **Xóa container nhưng không muốn xóa cơ sở dữ liệu**
    ```teminal
    docker-compose down -v
    ```



