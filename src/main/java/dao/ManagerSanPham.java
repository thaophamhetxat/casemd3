package dao;

import module.SanPham;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerSanPham {
    static Connection connection;

    static {
        try {
            connection = ConnectMySQL.getConnect();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static List<SanPham> seclect() throws SQLException, ClassNotFoundException {
        String select = "select * from sanphamnd";
        ArrayList<SanPham> listSanPham = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(select);

        while (resultSet.next()) {
            String tenSP = resultSet.getString("tenSP");
            int giaSP = resultSet.getInt("giaSP");
            int soLuong = resultSet.getInt("soLuong");
            String color = resultSet.getString("color");
            int danhMuc = resultSet.getInt("danhMuc");


            listSanPham.add(new SanPham(tenSP, giaSP, soLuong, color, danhMuc));
        }
        return listSanPham;
    }

    public static void create(SanPham sanPham) throws SQLException {
        String create = "insert into sanphamnd value(?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(create);
        preparedStatement.setString(1, sanPham.getTenSP());
        preparedStatement.setInt(2, sanPham.getGiaSP());
        preparedStatement.setInt(3, sanPham.getSoLuong());
        preparedStatement.setString(4, sanPham.getColor());
        preparedStatement.setInt(5, sanPham.getDanhMuc());
        preparedStatement.execute();

    }

    public static void edit(SanPham sanPham) throws SQLException {
        String edit = "update sanphamnd set giaSP=?,soLuong=?,color=?,danhMuc=? where tenSP=?";
        PreparedStatement preparedStatement = connection.prepareStatement(edit);
        preparedStatement.setInt(1, sanPham.getGiaSP());
        preparedStatement.setInt(2, sanPham.getSoLuong());
        preparedStatement.setString(3, sanPham.getColor());
        preparedStatement.setInt(4, sanPham.getDanhMuc());
        preparedStatement.setString(5, sanPham.getTenSP());
        preparedStatement.execute();
    }


    public static void delete(String tenSP) throws SQLException {
        String delete = "delete from sanphamnd where tenSP=?";
        PreparedStatement preparedStatement = connection.prepareStatement(delete);
        preparedStatement.setString(1, tenSP);
        preparedStatement.execute();
    }

    public static ArrayList<SanPham> findByName(String findName) throws SQLException {
        ArrayList<SanPham> findList = new ArrayList<>();
        String findByName = "select * from sanphamnd where tenSP like '%" + findName + "%'";
        PreparedStatement preparedStatement = connection.prepareStatement(findByName);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String tenSP = resultSet.getString("tenSP");
            int giaSP = resultSet.getInt("giaSP");
            int soLuong = resultSet.getInt("soLuong");
            String color = resultSet.getString("color");
            int danhMuc = resultSet.getInt("danhMuc");

            findList.add(new SanPham(tenSP, giaSP, soLuong, color,danhMuc));
        }
        return findList;
    }
}
