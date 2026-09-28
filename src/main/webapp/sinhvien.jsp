<%-- 
    Document   : sinhvien
    Created on : Sep 10, 2026, 2:02:15 PM
    Author     : THANH
--%>

<%@page import="java.util.List"%>
<%@page import="model.SinhVien"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="vi">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Quản lý sinh viên</title>

        <link rel="stylesheet" href="css/main.css">
    </head>

    <body>

        <!-- =====================================================
             HEADER
        ===================================================== -->

        <header class="header">

            <h2>PHÒNG ĐÀO TẠO</h2>

            <nav class="menu">
                <a href="#">Trang chủ</a>
                <a href="sinhvien" class="active">Quản lý sinh viên</a>
                <a href="admin">Quản lý môn học</a>
                <a href="#">Đăng xuất</a>
            </nav>

        </header>


        <!-- =====================================================
             MAIN
        ===================================================== -->

        <main class="container">

            <!-- TITLE -->

            <div class="title">

                <h1>Quản lý sinh viên</h1>

                <p>
                    Quản lý thông tin sinh viên trong hệ thống
                </p>

            </div>


            <!-- =================================================
                 SEARCH + BUTTON
            ================================================== -->

            <div class="search-box">

                <input
                    type="text"
                    placeholder="Nhập mã sinh viên hoặc họ tên">

                <button class="btn-search">
                    Tìm kiếm
                </button>

                <button
                    class="btn-add"
                    onclick="openModal()">

                    + Thêm sinh viên

                </button>

            </div>


            <!-- =================================================
                 TABLE
            ================================================== -->

            <div class="table-box">

                <table>

                    <thead>

                        <tr>

                            <th>STT</th>

                            <th>Mã SV</th>

                            <th>Họ tên</th>

                            <th>Ngày sinh</th>

                            <th>Giới tính</th>

                            <th>Địa chỉ</th>

                            <th>Khoa</th>

                            <th>Thao tác</th>

                        </tr>

                    </thead>


                    <tbody>
                      <%
                         List<SinhVien> list = (List<SinhVien>)request.getAttribute("listSinhVien");
                         int stt=1;
                         for(SinhVien sv: list)
                         {
                      %>
                      
                        <tr>

                            <td><%= stt++ %></td>

                            <td><%= sv.getMasv() %></td>

                            <td><%= sv.getHoten() %> </td>

                            <td><%= sv.getNgaysinh() %></td>

                            <td><%=sv.isGioitinh()?"Nam":"Nữ" %></td>

                            <td><%= sv.getDiachi() %></td>

                            <td><%= sv.getMakhoa() %></td>

                            <td class="actions">
                                <a href="sinhvien?action=edit&masv=<%= sv.getMasv() %>">
                                  <button class="action-edit">
                                    Sửa
                                  </button>
                                </a>
                                <button class="action-delete">
                                    Xóa
                                </button>

                            </td>

                        </tr>
                       <%
                           } 
                       %>

                      
                    </tbody>

                </table>

            </div>

        </main>


        <!-- =====================================================
             MODAL THÊM SINH VIÊN
        ===================================================== -->

        <div id="studentModal" class="modal">

            <div class="modal-content">


                <!-- MODAL HEADER -->

                <div class="modal-header">

                    <h3>Thêm sinh viên</h3>

                    <button
                        class="modal-close"
                        onclick="closeModal()">
                        ×
                    </button>

                </div>


                <!-- MODAL BODY -->
                  <%
                        SinhVien sv = (SinhVien)request.getAttribute("sv");
                   %> 
                <div class="modal-body">

                   
                    <!-- MÃ SV + HỌ TÊN -->

                    <div class="form-row">

                        <div class="form-group small">

                            <label>
                                Mã sinh viên
                            </label>

                            <input
                                type="text"
                                value="<%= sv!=null? sv.getMasv():"" %>"
                                placeholder="Nhập mã SV">
                        </div>


                        <div class="form-group">

                            <label>
                                Họ tên
                            </label>

                            <input
                                type="text"
                                value="<%= sv!=null? sv.getHoten():"" %>"
                                placeholder="Nhập họ tên">

                        </div>

                    </div>


                    <!-- NGÀY SINH + GIỚI TÍNH -->

                    <div class="form-row">

                        <div class="form-group small">

                            <label>
                                Ngày sinh
                            </label>

                            <input
                                type="date"
                                value="<%= sv!=null? sv.getNgaysinh():"" %>"
                                >

                        </div>


                        <div class="form-group">

                            <label>
                                Giới tính
                            </label>

                            <div class="gender">

                                <label>

                                    <input
                                        type="radio"
                                        name="gioitinh"
                                        value="1"
                                        <%= sv!=null && sv.isGioitinh()? "checked":"" %>>

                                    Nam

                                </label>


                                <label>

                                    <input
                                        type="radio"
                                        name="gioitinh"
                                        value="0"
                                        <%= sv!=null && sv.isGioitinh()? "":"checked" %>
                                        >
                                    Nữ
                                </label>

                            </div>

                        </div>

                    </div>


                    <!-- ĐỊA CHỈ -->

                    <div class="form-row">

                        <div class="form-group">

                            <label>
                                Địa chỉ
                            </label>

                            <input
                                type="text"
                                value="<%= sv!=null? sv.getDiachi():"" %>"
                                placeholder="Nhập địa chỉ">

                        </div>

                    </div>


                    <!-- KHOA -->

                    <div class="form-row">

                        <div class="form-group">

                            <label>
                                Khoa
                            </label>

                            <select>

                                <option value="">
                                    -- Chọn khoa --
                                </option>

                                <option value="CNTT">
                                    Công nghệ thông tin
                                </option>

                                <option value="QTKD">
                                    Quản trị kinh doanh
                                </option>

                                <option value="DTVT">
                                    Điện tử viễn thông
                                </option>
                            </select>
                        </div>
                    </div>
                </div>
                <!-- MODAL FOOTER -->
                <div class="modal-footer">
                    <button class="btn-add">
                        Thêm
                    </button>
                    <button
                        class="btn-clear"
                        onclick="closeModal()">
                        Hủy
                    </button>
                </div>
            </div>

        </div>


        <!-- =====================================================
             FOOTER
        ===================================================== -->

        <footer class="footer">

            © 2026 - Hệ thống quản lý giáo vụ

        </footer>


        <!-- =====================================================
             JAVASCRIPT
        ===================================================== -->

        <script>

            function openModal() {

                document.getElementById("studentModal")
                        .classList.add("show");

            }


            function closeModal() {

                document
                        .getElementById("studentModal")
                        .classList.remove("show");

            }

        </script>

       <%       
           if(request.getAttribute("editMode")!=null)
           {
       %>
       <script>
            openModal();
       </script>
       <% } %>
    </body>

</html>