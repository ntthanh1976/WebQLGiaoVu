package servlet;

import dao.SinhVienDAO;
import model.SinhVien;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/sinhvien")
public class SinhVienServlet extends HttpServlet {

    private SinhVienDAO sinhVienDAO;

    @Override
    public void init() {
        sinhVienDAO = new SinhVienDAO();
    }


    // =====================================================
    // GET
    // =====================================================
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        // -------------------------------------------------
        // SỬA
        // -------------------------------------------------
        if ("edit".equals(action)) {

            String masv = request.getParameter("masv");

            SinhVien sv = sinhVienDAO.getById(masv);

            request.setAttribute("sv", sv);
            request.setAttribute("editMode", true);

            // Lấy danh sách để hiển thị bảng
            List<SinhVien> listSinhVien =
                    sinhVienDAO.getAll();

            request.setAttribute(
                    "listSinhVien",
                    listSinhVien
            );

            request.getRequestDispatcher("sinhvien.jsp")
                   .forward(request, response);

            return;
        }


        // -------------------------------------------------
        // TÌM KIẾM
        // -------------------------------------------------
        if ("search".equals(action)) {

            String keyword =
                    request.getParameter("keyword");

            List<SinhVien> listSinhVien;

            if (keyword == null || keyword.trim().isEmpty()) {

                listSinhVien = sinhVienDAO.getAll();

            } else {

                listSinhVien =
                        sinhVienDAO.search(keyword.trim());
            }

            request.setAttribute(
                    "listSinhVien",
                    listSinhVien
            );

            request.getRequestDispatcher("sinhvien.jsp")
                   .forward(request, response);

            return;
        }


        // -------------------------------------------------
        // MẶC ĐỊNH: HIỂN THỊ DANH SÁCH
        // -------------------------------------------------

        List<SinhVien> listSinhVien =
                sinhVienDAO.getAll();

        request.setAttribute(
                "listSinhVien",
                listSinhVien
        );

        request.getRequestDispatcher("sinhvien.jsp")
               .forward(request, response);
    }


    // =====================================================
    // POST
    // =====================================================
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String action =
                request.getParameter("action");


        // =================================================
        // THÊM
        // =================================================
        if ("insert".equals(action)) {

            String masv =
                    request.getParameter("masv");

            String hoten =
                    request.getParameter("hoten");

            String ngaysinh =
                    request.getParameter("ngaysinh");

            String gioitinh =
                    request.getParameter("gioitinh");

            String diachi =
                    request.getParameter("diachi");

            String makhoa =
                    request.getParameter("makhoa");


            SinhVien sv = new SinhVien();

            sv.setMasv(masv);
            sv.setHoten(hoten);

            if (ngaysinh != null &&
                !ngaysinh.isEmpty()) {

                sv.setNgaysinh(
                        Date.valueOf(ngaysinh)
                );
            }

            sv.setGioitinh(
                    "1".equals(gioitinh)
            );

            sv.setDiachi(diachi);
            sv.setMakhoa(makhoa);


            sinhVienDAO.insert(sv);


            // Quay lại danh sách
            response.sendRedirect("sinhvien");

            return;
        }


        // =================================================
        // CẬP NHẬT
        // =================================================
        if ("update".equals(action)) {

            String masv =
                    request.getParameter("masv");

            String hoten =
                    request.getParameter("hoten");

            String ngaysinh =
                    request.getParameter("ngaysinh");

            String gioitinh =
                    request.getParameter("gioitinh");

            String diachi =
                    request.getParameter("diachi");

            String makhoa =
                    request.getParameter("makhoa");


            SinhVien sv = new SinhVien();

            sv.setMasv(masv);
            sv.setHoten(hoten);

            if (ngaysinh != null &&
                !ngaysinh.isEmpty()) {

                sv.setNgaysinh(
                        Date.valueOf(ngaysinh)
                );
            }

            sv.setGioitinh(
                    "1".equals(gioitinh)
            );

            sv.setDiachi(diachi);
            sv.setMakhoa(makhoa);


            sinhVienDAO.update(sv);


            // Quay lại danh sách
            response.sendRedirect("sinhvien");

            return;
        }


        // =================================================
        // XÓA
        // =================================================
        if ("delete".equals(action)) {

            String masv =
                    request.getParameter("masv");

            sinhVienDAO.delete(masv);

            response.sendRedirect("sinhvien");

            return;
        }
    }
}