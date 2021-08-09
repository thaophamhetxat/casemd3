package controller;

import module.SanPham;
import service.DanhMucServlice;
import service.SanPhamService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SanPhamController", value = "/sanpham")
public class SanPhamController extends HttpServlet {
    SanPhamService sanPhamService = new SanPhamService();
    DanhMucServlice danhMucServlice = new DanhMucServlice();

    public SanPhamController() throws SQLException, ClassNotFoundException {
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        RequestDispatcher requestDispatcher;
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                request.setAttribute("listDanhMuc", danhMucServlice.listDanhMuc);
                response.sendRedirect("views/createSanPham.jsp");
                break;
            case "edit":
                showEdit(request, response);
                break;
            case "delete":
                try {
                    deleteSanPham(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                request.setAttribute("listSanPham", sanPhamService.listSanPham);
                requestDispatcher = request.getRequestDispatcher("views/home.jsp");
                requestDispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        RequestDispatcher requestDispatcher;

        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                try {
                    create(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "edit":
                request.setAttribute("listDanhMuc", danhMucServlice.listDanhMuc);
                try {
                    editSanPham(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "find":
                find(request, response);
                break;

        }
    }


    protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String tenSP = request.getParameter("tenSP");
        int giaSP = Integer.parseInt(request.getParameter("giaSP"));
        int soLuong = Integer.parseInt(request.getParameter("soLuong"));
        String color = request.getParameter("color");
        int danhMuc = Integer.parseInt(request.getParameter("danhMuc"));


        SanPham sanPham = new SanPham(tenSP, giaSP, soLuong, color, danhMuc);
        sanPhamService.save(sanPham);

        request.setAttribute("listSanPham", sanPhamService.listSanPham);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/home.jsp");
        requestDispatcher.forward(request, response);
    }


    protected void editSanPham(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String tenSPEdit = request.getParameter("tenSP");
        int giaSPEdit = Integer.parseInt(request.getParameter("giaSP"));
        int soLuongEdit = Integer.parseInt(request.getParameter("soLuong"));
        String colorEdit = request.getParameter("color");
        int danhMucEdit = Integer.parseInt(request.getParameter("danhMuc"));

        SanPham sanPhamEdit = new SanPham(tenSPEdit, giaSPEdit, soLuongEdit, colorEdit, danhMucEdit);

        int index = Integer.parseInt(request.getParameter("index"));
        sanPhamService.edit(sanPhamEdit, index);

        request.setAttribute("listSanPham", sanPhamService.listSanPham);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/home.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void showEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int indexEdit = Integer.parseInt(request.getParameter("index"));
        request.setAttribute("listSanPham", sanPhamService.listSanPham.get(indexEdit));
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/EditSanPham.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void deleteSanPham(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        RequestDispatcher requestDispatcher;
        int index = Integer.parseInt(request.getParameter("index"));
        sanPhamService.delete(index);
        response.sendRedirect("views/home");
    }

    protected void find(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String findName = request.getParameter("findName");
        try {
            request.setAttribute("listSanPham", sanPhamService.findByName(findName));
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/home.jsp");
            requestDispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

