package service;

import dao.ManagerSanPham;
import module.SanPham;

import java.sql.SQLException;
import java.util.ArrayList;

public class SanPhamService {
    public ArrayList<SanPham> listSanPham = new ArrayList<>();

    public SanPhamService() {
        try {
            listSanPham = (ArrayList<SanPham>) ManagerSanPham.seclect();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void save(SanPham sanPham) throws SQLException {
        ManagerSanPham.create(sanPham);
        listSanPham.add(sanPham);
    }

    public void edit(SanPham sanPham,int index) throws SQLException {
        ManagerSanPham.edit(sanPham);
        listSanPham.set(index,sanPham);
    }

    public void delete(int index) throws SQLException {
        ManagerSanPham.delete(listSanPham.get(index).getTenSP());
        listSanPham.remove(index);
    }


    public ArrayList<SanPham>findByName(String tenSP) throws SQLException {
        return ManagerSanPham.findByName(tenSP);

    }

}
