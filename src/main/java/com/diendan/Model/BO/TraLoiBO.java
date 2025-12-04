package com.diendan.Model.BO;

import com.diendan.Model.bean.TraLoi;
import com.diendan.Model.dao.TraLoiDAO;

import java.util.List;

public class TraLoiBO {
    TraLoiDAO traLoiDAO;
    public TraLoiBO(){
        traLoiDAO = new TraLoiDAO();
    }
    public List<TraLoi> layDanhSachTraLoiTheoCauHoi(int maCauHoi) {
        return traLoiDAO.layDanhSachTraLoiTheoCauHoi(maCauHoi);
    }

    public void themTraLoi(TraLoi traLoi) {
        traLoiDAO.themTraLoi(traLoi);
    }
}
