# Đề tài: Viết Ứng Dụng Quản Lý Sinh Viên Kết Hợp Nhiều Docker Với Nhau
## Danh Sách Thành Viên:
1. Phan Hồng Sơn - 20110560
2. Huỳnh Thanh Tuấn - 20110120
3. KHAMSAVATH Bounmy - 20110L02

# Hướng dẫn chạy chương trình
![Alt](https://github.com/HT-Tuan/Project-Cloud-Computing/blob/main/DB/picture.png?raw=true)

## Trên local với hệ điều hành win 11
1. cd Project-Cloud-Computing (Sau khi download project)
2. Thực hiện lệnh "docker compose up"
3. Truy cập vào đường dẫn: http://localhost:8091 sau khi các container đều chạy hoàn tất để xem kết quả

## Trên cloud AWS EC2 Linux
### Cài đặt git
1. Mở terminal chạy sudo yum update -y
2. sudo yum install git -y
3. git clone https://github.com/HT-Tuan/Project-Cloud-Computing.git
### Cài đặt docker
4. sudo yum install docker -y
5. sudo systemctl enable docker
6. sudo service docker start
7. sudo usermod -a -G docker ec2-user
8. docker info
### Cài đặt docker-compose
9. wget https://github.com/docker/compose/releases/latest/download/docker-compose-linux-x86_64
10. sudo mv docker-compose-linux-x86_64 /usr/local/bin/docker-compose
cd ./usr/local/bin
11. sudo chmod +x /usr/local/bin/docker-compose
12. docker-compose version
### Thực hiện chạy các container docker
13. Vào thư mục "Project-Cloud-Computing" Thực hiện lệnh "docker-compose up"

# Tài liệu tham khảo
1. https://docs.docker.com/get-started/07_multi_container/
2. https://docs.docker.com/get-started/07_multi_container/
3. https://stackoverflow.com/questions/72187612/installing-docker-compose-plugin-on-amazon-linux-2
4. https://www.youtube.com/watch?v=BCx8e9RDjB0

