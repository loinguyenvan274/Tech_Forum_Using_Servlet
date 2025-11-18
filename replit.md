# Diễn Đàn Công Nghệ

## Tổng Quan
Trang web diễn đàn công nghệ đơn giản tương tự Stack Overflow, xây dựng bằng kiến trúc MVC với Java Servlet và Tomcat. Toàn bộ code và comment được viết bằng tiếng Việt.

## Công Nghệ Sử Dụng
- **Backend**: Java 19 (GraalVM), Servlet API 4.0
- **Server**: Apache Tomcat 7.0.47 (embedded)
- **Build Tool**: Apache Maven 3.8.6
- **Frontend**: HTML5, CSS3, JavaScript (thuần, không framework)
- **View Engine**: JSP với JSTL
- **Storage**: In-memory (ArrayList/HashMap)

## Kiến Trúc MVC

### Model Layer
**Business Objects (BO)** - `src/main/java/com/diendan/bo/`:
- `NguoiDungBO.java` - Đại diện cho người dùng
- `CauHoiBO.java` - Đại diện cho câu hỏi
- `TraLoiBO.java` - Đại diện cho câu trả lời

**Data Access Objects (DAO)** - `src/main/java/com/diendan/dao/`:
- `NguoiDungDAO.java` - Quản lý dữ liệu người dùng (Singleton pattern)
- `CauHoiDAO.java` - Quản lý dữ liệu câu hỏi (Singleton pattern)
- `TraLoiDAO.java` - Quản lý dữ liệu trả lời (Singleton pattern)

### Controller Layer
**Servlets** - `src/main/java/com/diendan/servlet/`:
- `DanhSachCauHoiServlet.java` - Hiển thị danh sách câu hỏi (/)
- `ChiTietCauHoiServlet.java` - Hiển thị chi tiết câu hỏi (/chitiet)
- `DangCauHoiServlet.java` - Đăng câu hỏi mới (/dangcauhoi)
- `TraLoiServlet.java` - Trả lời câu hỏi (/traloi)
- `TimKiemServlet.java` - Tìm kiếm câu hỏi (/timkiem)

### View Layer
**JSP Pages** - `src/main/webapp/WEB-INF/views/`:
- `danhsach.jsp` - Trang chủ hiển thị danh sách câu hỏi
- `chitiet.jsp` - Trang chi tiết câu hỏi và trả lời
- `dangcauhoi.jsp` - Form đăng câu hỏi mới
- `timkiem.jsp` - Trang kết quả tìm kiếm

**Static Resources**:
- `src/main/webapp/css/style.css` - CSS thuần cho toàn bộ trang
- `src/main/webapp/js/main.js` - JavaScript thuần cho validation

## Tính Năng Chính
1. ✅ Hiển thị danh sách câu hỏi với tiêu đề, nội dung tóm tắt, số lượng trả lời
2. ✅ Xem chi tiết câu hỏi và các câu trả lời liên quan
3. ✅ Đăng câu hỏi mới với tiêu đề, nội dung, và tags
4. ✅ Trả lời câu hỏi với nội dung chi tiết
5. ✅ Tìm kiếm câu hỏi theo tiêu đề hoặc nội dung
6. ✅ Validation form với JavaScript
7. ✅ Giao diện responsive với CSS thuần

## Cách Chạy Ứng Dụng
Ứng dụng đã được cấu hình workflow tự động chạy với lệnh:
```bash
mvn tomcat7:run
```

Server sẽ chạy tại: `http://localhost:5000/`

## Cấu Trúc Thư Mục
```
.
├── src/
│   └── main/
│       ├── java/
│       │   └── com/diendan/
│       │       ├── bo/          # Business Objects
│       │       ├── dao/         # Data Access Objects
│       │       └── servlet/     # Controllers (Servlets)
│       └── webapp/
│           ├── WEB-INF/
│           │   ├── views/       # JSP Views
│           │   └── web.xml      # Servlet configuration
│           ├── css/             # Stylesheets
│           └── js/              # JavaScript files
├── pom.xml                      # Maven configuration
└── replit.md                    # Documentation

```

## Dữ Liệu Mẫu
Ứng dụng được khởi tạo với:
- 3 người dùng mẫu
- 3 câu hỏi mẫu về Java, OOP, deployment
- 3 câu trả lời mẫu

## Đặc Điểm Kỹ Thuật
- **Encoding**: UTF-8 cho toàn bộ ứng dụng
- **Session Timeout**: 30 phút
- **Port**: 5000 (cấu hình cho Replit)
- **Design Pattern**: 
  - MVC (Model-View-Controller)
  - Singleton (cho DAO classes)
  - DTO/BO pattern
- **Code Convention**: Tất cả tên biến, class, method đều bằng tiếng Việt

## Tính Năng Nâng Cao (Đề Xuất)
- [ ] Hệ thống vote (upvote/downvote)
- [ ] Tích hợp database MySQL
- [ ] Đánh dấu câu trả lời được chấp nhận
- [ ] Phân trang
- [ ] Hệ thống điểm reputation
- [ ] Đăng nhập/đăng ký người dùng
- [ ] Editor Markdown cho câu hỏi/trả lời

## Ghi Chú
- Dữ liệu được lưu trữ trong bộ nhớ (in-memory), sẽ mất khi restart
- Code được viết hoàn toàn bằng tiếng Việt theo yêu cầu
- Tách biệt rõ ràng giữa DAO và BO layer
- Không sử dụng framework CSS/JS, hoàn toàn thuần HTML/CSS/JavaScript
