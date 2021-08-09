package module;

import java.util.ArrayList;

public class SelectDM {
    int id_category;
    String ten_danhMuc;

    public SelectDM() {
    }

    public SelectDM(int id_category, String ten_danhMuc) {
        this.id_category = id_category;
        this.ten_danhMuc = ten_danhMuc;
    }



    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public String getTen_danhMuc() {
        return ten_danhMuc;
    }

    public void setTen_danhMuc(String ten_danhMuc) {
        this.ten_danhMuc = ten_danhMuc;
    }
}
