package dao;

import module.SanPham;
import module.SelectDM;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SelectDao {
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


    public static ArrayList<SelectDM> selectSanPham() throws SQLException, ClassNotFoundException {
        String select = "select * from category";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(select);
        ArrayList<SelectDM> listDanhMuc = new ArrayList<>();
        while (resultSet.next()) {
            int id_category = resultSet.getInt("id_category");
            String ten_danhMuc = resultSet.getString("ten_danhMuc");

            listDanhMuc.add(new SelectDM(id_category,ten_danhMuc));

        }
        return listDanhMuc;
    }
}
