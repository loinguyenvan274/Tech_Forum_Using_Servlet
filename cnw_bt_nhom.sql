-- =====================================================
-- DATABASE: cnw_bt_nhom
-- =====================================================

DROP DATABASE IF EXISTS cnw_bt_nhom;
CREATE DATABASE cnw_bt_nhom
CHARACTER SET utf8mb4
COLLATE utf8mb4_general_ci;

USE cnw_bt_nhom;

-- =====================================================
-- BẢNG NGƯỜI DÙNG
-- =====================================================
CREATE TABLE nguoi_dung (
    maNguoiDung INT AUTO_INCREMENT PRIMARY KEY,
    tenDangNhap VARCHAR(50) NOT NULL,
    tenHienThi VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    ngayThamGia DATETIME NOT NULL,
    matKhau VARCHAR(255) NOT NULL
) ENGINE=InnoDB;

-- =====================================================
-- BẢNG TAG
-- =====================================================
CREATE TABLE tag (
    maTag INT AUTO_INCREMENT PRIMARY KEY,
    tenTag VARCHAR(50) NOT NULL UNIQUE
) ENGINE=InnoDB;

-- =====================================================
-- BẢNG CÂU HỎI
-- =====================================================
CREATE TABLE cau_hoi (
    maCauHoi INT AUTO_INCREMENT PRIMARY KEY,
    tieuDe VARCHAR(255) NOT NULL,
    noiDung TEXT NOT NULL,
    maNguoiHoi INT NOT NULL,
    tenNguoiHoi VARCHAR(100) NOT NULL,
    ngayDang DATETIME NOT NULL,
    soLuongTraLoi INT DEFAULT 0,
    danhSachTagText VARCHAR(255),

    CONSTRAINT fk_cauhoi_nguoidung
        FOREIGN KEY (maNguoiHoi)
        REFERENCES nguoi_dung(maNguoiDung)
        ON DELETE CASCADE
) ENGINE=InnoDB;

-- =====================================================
-- BẢNG CÂU TRẢ LỜI
-- =====================================================
CREATE TABLE cau_tra_loi (
    maTraLoi INT AUTO_INCREMENT PRIMARY KEY,
    maCauHoi INT NOT NULL,
    noiDung TEXT NOT NULL,
    maNguoiTraLoi INT NOT NULL,
    tenNguoiTraLoi VARCHAR(100) NOT NULL,
    ngayTraLoi DATETIME NOT NULL,

    CONSTRAINT fk_traloi_cauhoi
        FOREIGN KEY (maCauHoi)
        REFERENCES cau_hoi(maCauHoi)
        ON DELETE CASCADE,

    CONSTRAINT fk_traloi_nguoidung
        FOREIGN KEY (maNguoiTraLoi)
        REFERENCES nguoi_dung(maNguoiDung)
        ON DELETE CASCADE
) ENGINE=InnoDB;

-- =====================================================
-- BẢNG TRUNG GIAN CÂU HỎI - TAG
-- =====================================================
CREATE TABLE cau_hoi_tag (
    maCauHoi INT NOT NULL,
    maTag INT NOT NULL,
    PRIMARY KEY (maCauHoi, maTag),

    CONSTRAINT fk_cauhoi_tag_cauhoi
        FOREIGN KEY (maCauHoi)
        REFERENCES cau_hoi(maCauHoi)
        ON DELETE CASCADE,

    CONSTRAINT fk_cauhoi_tag_tag
        FOREIGN KEY (maTag)
        REFERENCES tag(maTag)
        ON DELETE CASCADE
) ENGINE=InnoDB;

-- =====================================================
-- DỮ LIỆU MẪU
-- =====================================================

INSERT INTO nguoi_dung (tenDangNhap, tenHienThi, email, ngayThamGia, matKhau) VALUES
('admin', 'Quản Trị Viên', 'admin@diendan.com', NOW(), '123'),
('nguoidung1', 'Nguyễn Văn A', 'nguyenvana@email.com', NOW(), '123'),
('nguoidung2', 'Trần Thị B', 'tranthib@email.com', NOW(), '123');

INSERT INTO tag (tenTag) VALUES
('html'),
('css');

INSERT INTO cau_hoi
(tieuDe, noiDung, maNguoiHoi, tenNguoiHoi, ngayDang, soLuongTraLoi, danhSachTagText)
VALUES
(
    'Làm thế nào để kết nối SQL trong Java?',
    'Hướng dẫn kết nối SQL trong Java bằng JDBC...',
    1,
    'Nguyễn Văn Lợi',
    NOW(),
    2,
    'html, css'
);

INSERT INTO cau_hoi_tag (maCauHoi, maTag) VALUES
(1, 1),
(1, 2);

INSERT INTO cau_tra_loi
(maCauHoi, noiDung, maNguoiTraLoi, tenNguoiTraLoi, ngayTraLoi)
VALUES
(
    1,
    'Bạn có thể sử dụng JDBC Driver để kết nối MySQL trong Java.',
    1,
    'Nguyễn Văn Lợi',
    NOW()
);
