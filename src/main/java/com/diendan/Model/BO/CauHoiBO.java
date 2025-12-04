package com.diendan.Model.BO;

import com.diendan.Model.bean.CauHoi;
import com.diendan.Model.dao.CauHoiDAO;

import java.util.List;

public class CauHoiBO {

    CauHoiDAO cauHoiDAO;
    public CauHoiBO() {
        cauHoiDAO = new CauHoiDAO();
    }

    public CauHoi layCauHoiTheoMa(int maCauHoi) {
      return  cauHoiDAO.layCauHoiTheoMa(maCauHoi);
    }

    public void themCauHoi(CauHoi cauHoi) {
        cauHoiDAO.themCauHoi(cauHoi);
    }

    public List<CauHoi> layTatCaCauHoi() {
        return  cauHoiDAO.layTatCaCauHoi();
    }

    public List<CauHoi> timKiem(String tuKhoa, String tuKhoa1) {
        return cauHoiDAO.timKiem(tuKhoa,tuKhoa1);
    }
}
