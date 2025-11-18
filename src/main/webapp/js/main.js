// JavaScript chính cho Diễn Đàn Công Nghệ

// Khởi tạo khi DOM đã load xong
document.addEventListener('DOMContentLoaded', function() {
    // Validation form đăng câu hỏi
    const questionForm = document.getElementById('questionForm');
    if (questionForm) {
        questionForm.addEventListener('submit', function(e) {
            if (!validateQuestionForm()) {
                e.preventDefault();
            }
        });
    }
    
    // Validation form trả lời
    const answerForm = document.getElementById('answerForm');
    if (answerForm) {
        answerForm.addEventListener('submit', function(e) {
            if (!validateAnswerForm()) {
                e.preventDefault();
            }
        });
    }
    
    // Tự động focus vào ô input tìm kiếm nếu có
    const searchInput = document.querySelector('.search-input');
    if (searchInput && searchInput.value === '') {
        // Chỉ focus nếu không phải mobile
        if (window.innerWidth > 768) {
            searchInput.focus();
        }
    }
});

/**
 * Validate form đăng câu hỏi
 */
function validateQuestionForm() {
    const tenNguoiHoi = document.getElementById('tenNguoiHoi').value.trim();
    const tieuDe = document.getElementById('tieuDe').value.trim();
    const noiDung = document.getElementById('noiDung').value.trim();
    
    // Kiểm tra các trường bắt buộc
    if (tenNguoiHoi === '') {
        alert('Vui lòng nhập tên của bạn!');
        document.getElementById('tenNguoiHoi').focus();
        return false;
    }
    
    if (tieuDe === '') {
        alert('Vui lòng nhập tiêu đề câu hỏi!');
        document.getElementById('tieuDe').focus();
        return false;
    }
    
    if (tieuDe.length < 10) {
        alert('Tiêu đề câu hỏi phải có ít nhất 10 ký tự!');
        document.getElementById('tieuDe').focus();
        return false;
    }
    
    if (noiDung === '') {
        alert('Vui lòng nhập nội dung câu hỏi!');
        document.getElementById('noiDung').focus();
        return false;
    }
    
    if (noiDung.length < 20) {
        alert('Nội dung câu hỏi phải có ít nhất 20 ký tự để mô tả chi tiết vấn đề!');
        document.getElementById('noiDung').focus();
        return false;
    }
    
    return true;
}

/**
 * Validate form trả lời câu hỏi
 */
function validateAnswerForm() {
    const tenNguoiTraLoi = document.getElementById('tenNguoiTraLoi').value.trim();
    const noiDung = document.getElementById('noiDung').value.trim();
    
    // Kiểm tra các trường bắt buộc
    if (tenNguoiTraLoi === '') {
        alert('Vui lòng nhập tên của bạn!');
        document.getElementById('tenNguoiTraLoi').focus();
        return false;
    }
    
    if (noiDung === '') {
        alert('Vui lòng nhập nội dung trả lời!');
        document.getElementById('noiDung').focus();
        return false;
    }
    
    if (noiDung.length < 10) {
        alert('Nội dung trả lời phải có ít nhất 10 ký tự!');
        document.getElementById('noiDung').focus();
        return false;
    }
    
    return true;
}

/**
 * Hiển thị thông báo cho người dùng
 */
function showMessage(message, type) {
    const alertDiv = document.createElement('div');
    alertDiv.className = 'alert alert-' + type;
    alertDiv.textContent = message;
    
    const mainContent = document.querySelector('.main-content');
    if (mainContent) {
        mainContent.insertBefore(alertDiv, mainContent.firstChild);
        
        // Tự động ẩn sau 5 giây
        setTimeout(function() {
            alertDiv.remove();
        }, 5000);
    }
}
