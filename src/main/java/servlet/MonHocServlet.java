package servlet;

import dao.MonHocDAO;
import model.MonHoc;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/monhoc")
public class MonHocServlet extends HttpServlet {

    private MonHocDAO monHocDAO;

    @Override
    public void init() {
        monHocDAO = new MonHocDAO();
    }

    // =====================================================
    // GET
    // =====================================================

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {

            case "list":
                listMonHoc(request, response);
                break;

            case "edit":
                showEditForm(request, response);
                break;

            case "delete":
                deleteMonHoc(request, response);
                break;

            case "search":
                searchMonHoc(request, response);
                break;

            default:
                listMonHoc(request, response);
                break;
        }
    }

    // =====================================================
    // POST
    // =====================================================

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        if (action == null) {
            action = "insert";
        }

        switch (action) {

            case "insert":
                insertMonHoc(request, response);
                break;

            case "update":
                updateMonHoc(request, response);
                break;

            default:
                response.sendRedirect(
                        request.getContextPath() + "/monhoc"
                );
                break;
        }
    }

    // =====================================================
    // HIỂN THỊ DANH SÁCH
    // =====================================================

    private void listMonHoc(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        List<MonHoc> list = monHocDAO.getAll();
       // System.out.println("so mon hoc:" + list.size());
        request.setAttribute("listMonHoc", list);

        request.getRequestDispatcher("/monhoc/course.jsp")
               .forward(request, response);
    }

    // =====================================================
    // THÊM
    // =====================================================

    private void insertMonHoc(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        String mamh = request.getParameter("mamh");
        String tenmh = request.getParameter("tenmh");
        String sotinchiStr = request.getParameter("sotinchi");
        try {
            int sotinchi = Integer.parseInt(sotinchiStr);
            MonHoc mh = new MonHoc(
                    mamh,
                    tenmh,
                    sotinchi
            );

            monHocDAO.insert(mh);
            response.sendRedirect(
                    request.getContextPath() + "/monhoc"
            );

        } catch (NumberFormatException e) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/monhoc?error=sotinchi"
            );
        }
    }

    // =====================================================
    // HIỂN THỊ FORM SỬA
    // =====================================================

    private void showEditForm(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String mamh = request.getParameter("mamh");

        MonHoc mh = monHocDAO.getById(mamh);

        request.setAttribute("monHoc", mh);

        List<MonHoc> list = monHocDAO.getAll();

        request.setAttribute("listMonHoc", list);

        request.getRequestDispatcher("/monhoc/course.jsp")
               .forward(request, response);
    }

    // =====================================================
    // CẬP NHẬT
    // =====================================================

    private void updateMonHoc(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        String mamh = request.getParameter("mamh");
        String tenmh = request.getParameter("tenmh");
        String sotinchiStr = request.getParameter("sotinchi");

        try {

            int sotinchi = Integer.parseInt(sotinchiStr);

            MonHoc mh = new MonHoc(
                    mamh,
                    tenmh,
                    sotinchi
            );

            monHocDAO.update(mh);

            response.sendRedirect(
                    request.getContextPath() + "/monhoc"
            );

        } catch (NumberFormatException e) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/monhoc?action=edit&mamh=" + mamh
                    + "&error=sotinchi"
            );
        }
    }

    // =====================================================
    // XÓA
    // =====================================================

    private void deleteMonHoc(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        String mamh = request.getParameter("mamh");

        monHocDAO.delete(mamh);

        response.sendRedirect(
                request.getContextPath() + "/monhoc"
        );
    }

    // =====================================================
    // TÌM KIẾM
    // =====================================================

    private void searchMonHoc(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String keyword = request.getParameter("keyword");

        if (keyword == null) {
            keyword = "";
        }

        List<MonHoc> list = monHocDAO.search(keyword);

        request.setAttribute("listMonHoc", list);
        request.setAttribute("keyword", keyword);

        request.getRequestDispatcher("/monhoc/course.jsp")
               .forward(request, response);
    }
}