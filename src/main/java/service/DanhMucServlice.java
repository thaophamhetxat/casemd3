package service;

import dao.SelectDao;
import module.SelectDM;

import java.sql.SQLException;
import java.util.ArrayList;

public class DanhMucServlice {
    public static ArrayList<SelectDM> listDanhMuc = new ArrayList<>();
    public DanhMucServlice() throws SQLException, ClassNotFoundException {
        listDanhMuc = SelectDao.selectSanPham();


    }
}
