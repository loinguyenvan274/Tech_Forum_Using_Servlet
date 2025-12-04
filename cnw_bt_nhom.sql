-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 04, 2025 lúc 02:49 PM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `cnw_bt_nhom`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cau_hoi`
--

CREATE TABLE `cau_hoi` (
  `maCauHoi` int(11) NOT NULL,
  `tieuDe` varchar(255) NOT NULL,
  `noiDung` text NOT NULL,
  `maNguoiHoi` int(11) NOT NULL,
  `tenNguoiHoi` varchar(100) NOT NULL,
  `ngayDang` datetime NOT NULL,
  `soLuongTraLoi` int(11) DEFAULT 0,
  `danhSachTagText` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `cau_hoi`
--

INSERT INTO `cau_hoi` (`maCauHoi`, `tieuDe`, `noiDung`, `maNguoiHoi`, `tenNguoiHoi`, `ngayDang`, `soLuongTraLoi`, `danhSachTagText`) VALUES
(1, 'Làm thế nào để kết SQl ở java', 'kfjlf kfdjk trong tất cả các \r\n\r\n kfjlf kfdjk trong tất cả các \r\n\r\n kfjlf kfdjk trong tất cả các \r\n\r\n kfjlf kfdjk trong tất cả các \r\n\r\n kfjlf kfdjk trong tất cả các \r\n\r\n kfjlf kfdjk trong tất cả các \r\n\r\n kfjlf kfdjk trong tất cả các \r\n\r\n kfjlf kfdjk trong tất cả các', 1, 'Nguyễn Văn Lợi', '2025-12-04 20:34:17', 2, 'html, css');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cau_hoi_tag`
--

CREATE TABLE `cau_hoi_tag` (
  `maCauHoi` int(11) NOT NULL,
  `maTag` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `cau_hoi_tag`
--

INSERT INTO `cau_hoi_tag` (`maCauHoi`, `maTag`) VALUES
(1, 1),
(1, 2);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cau_tra_loi`
--

CREATE TABLE `cau_tra_loi` (
  `maTraLoi` int(11) NOT NULL,
  `maCauHoi` int(11) NOT NULL,
  `noiDung` text NOT NULL,
  `maNguoiTraLoi` int(11) NOT NULL,
  `tenNguoiTraLoi` varchar(100) NOT NULL,
  `ngayTraLoi` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `cau_tra_loi`
--

INSERT INTO `cau_tra_loi` (`maTraLoi`, `maCauHoi`, `noiDung`, `maNguoiTraLoi`, `tenNguoiTraLoi`, `ngayTraLoi`) VALUES
(1, 1, 'Văn lợi trong tât Văn lợi trong tât Văn lợi trong tât', 1, 'Nguyễn Văn Lợi', '2025-12-04 20:46:29');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nguoi_dung`
--

CREATE TABLE `nguoi_dung` (
  `maNguoiDung` int(11) NOT NULL,
  `tenDangNhap` varchar(50) NOT NULL,
  `tenHienThi` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `ngayThamGia` datetime NOT NULL,
  `matKhau` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `nguoi_dung`
--

INSERT INTO `nguoi_dung` (`maNguoiDung`, `tenDangNhap`, `tenHienThi`, `email`, `ngayThamGia`, `matKhau`) VALUES
(1, 'admin', 'Quản Trị Viên', 'admin@diendan.com', '2025-12-04 20:08:26', '123'),
(2, 'nguoidung1', 'Nguyễn Văn A', 'nguyenvana@email.com', '2025-12-04 20:08:26', '123'),
(3, 'nguoidung2', 'Trần Thị B', 'tranthib@email.com', '2025-12-04 20:08:26', '123');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tag`
--

CREATE TABLE `tag` (
  `maTag` int(11) NOT NULL,
  `tenTag` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `tag`
--

INSERT INTO `tag` (`maTag`, `tenTag`) VALUES
(2, 'css'),
(1, 'html');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `cau_hoi`
--
ALTER TABLE `cau_hoi`
  ADD PRIMARY KEY (`maCauHoi`),
  ADD KEY `maNguoiHoi` (`maNguoiHoi`);

--
-- Chỉ mục cho bảng `cau_hoi_tag`
--
ALTER TABLE `cau_hoi_tag`
  ADD PRIMARY KEY (`maCauHoi`,`maTag`),
  ADD KEY `maTag` (`maTag`);

--
-- Chỉ mục cho bảng `cau_tra_loi`
--
ALTER TABLE `cau_tra_loi`
  ADD PRIMARY KEY (`maTraLoi`),
  ADD KEY `maCauHoi` (`maCauHoi`),
  ADD KEY `maNguoiTraLoi` (`maNguoiTraLoi`);

--
-- Chỉ mục cho bảng `nguoi_dung`
--
ALTER TABLE `nguoi_dung`
  ADD PRIMARY KEY (`maNguoiDung`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Chỉ mục cho bảng `tag`
--
ALTER TABLE `tag`
  ADD PRIMARY KEY (`maTag`),
  ADD UNIQUE KEY `tenTag` (`tenTag`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `cau_hoi`
--
ALTER TABLE `cau_hoi`
  MODIFY `maCauHoi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `cau_tra_loi`
--
ALTER TABLE `cau_tra_loi`
  MODIFY `maTraLoi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `nguoi_dung`
--
ALTER TABLE `nguoi_dung`
  MODIFY `maNguoiDung` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `tag`
--
ALTER TABLE `tag`
  MODIFY `maTag` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `cau_hoi`
--
ALTER TABLE `cau_hoi`
  ADD CONSTRAINT `cau_hoi_ibfk_1` FOREIGN KEY (`maNguoiHoi`) REFERENCES `nguoi_dung` (`maNguoiDung`);

--
-- Các ràng buộc cho bảng `cau_hoi_tag`
--
ALTER TABLE `cau_hoi_tag`
  ADD CONSTRAINT `cau_hoi_tag_ibfk_1` FOREIGN KEY (`maCauHoi`) REFERENCES `cau_hoi` (`maCauHoi`),
  ADD CONSTRAINT `cau_hoi_tag_ibfk_2` FOREIGN KEY (`maTag`) REFERENCES `tag` (`maTag`);

--
-- Các ràng buộc cho bảng `cau_tra_loi`
--
ALTER TABLE `cau_tra_loi`
  ADD CONSTRAINT `cau_tra_loi_ibfk_1` FOREIGN KEY (`maCauHoi`) REFERENCES `cau_hoi` (`maCauHoi`),
  ADD CONSTRAINT `cau_tra_loi_ibfk_2` FOREIGN KEY (`maNguoiTraLoi`) REFERENCES `nguoi_dung` (`maNguoiDung`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
