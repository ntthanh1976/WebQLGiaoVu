<%-- 
    Document   : course
    Created on : Sep 9, 2026, 8:35:13 PM
    Author     : THANH
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.MonHoc"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Quản lý môn học</title>

        <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css"/>
    </head>

    <body>

        <!-- HEADER -->

        <header class="header">

            <h2>Phòng Đào tạo</h2>

             <nav class="menu">
                <a href="#">Trang chủ</a>
                <a href="sinhvien" class="active">Quản lý sinh viên</a>
                <a href="monhoc">Quản lý môn học</a>
                <a href="#">Đăng xuất</a>
            </nav>

        </header>


        <!-- MAIN -->

        <div class="container">

            <!-- TITLE -->

            <div class="title">

                <h1>Quản lý môn học</h1>

                <p>
                    Quản lý thông tin các môn học trong chương trình đào tạo.
                </p>

            </div>


            <!-- FORM -->
            <form action="<%= request.getContextPath()%>/monhoc" method="post">
                <div class="form-box">

                    <%
                        MonHoc x = (MonHoc) request.getAttribute("monHoc");
                    %>
                    <h3>Thông tin môn học</h3>

                    <div class="form-row">

                        <div class="form-group small">

                            <label for="maMon">
                                Mã môn
                            </label>
                            <input
                                type="text"
                                id="maMon"
                                name ="mamh"
                                value="<%= x != null ? x.getMamh() : ""%>"
                                placeholder="Nhập mã môn">
                        </div>


                        <div class="form-group">

                            <label for="tenMon">
                                Tên môn
                            </label>

                            <input
                                type="text"
                                id="tenMon"
                                name="tenmh"
                                value="<%= x != null ? x.getTenmh() : ""%>"
                                placeholder="Nhập tên môn">

                        </div>

                        <div class="form-group small">

                            <label for="soTinChi">
                                Số tín chỉ
                            </label>

                            <input
                                type="number"
                                id="soTinChi"
                                name="sotinchi"
                                placeholder="Số tín chỉ"
                                value="<%= x != null ? x.getSotinchi() : ""%>"
                                min="1">
                        </div>

                    </div>


                    <div class="buttons">

                        <button
                            class="btn-add"
                            name="action"
                            value="insert"
                            >
                            Thêm
                        </button>


                        <button
                            class="btn-update"
                            name="action"
                            value="update"
                            >
                            Cập nhật
                        </button>
                        <button
                            class="btn-clear"
                            onclick="clearForm()">
                            Làm mới
                        </button>

                    </div>

                </div>
            </form>

            <!-- SEARCH -->
            <form action="<%= request.getContextPath()%>/monhoc" method="get">
                <div class="search-box">      
                    <input
                        type="text"
                        id="search"
                        name="keyword"
                        value="<%= request.getAttribute("keyword") == null ? "" : request.getAttribute("keyword")%>"
                        placeholder="Tìm kiếm theo mã môn hoặc tên môn">
                    <button
                        class="btn-search"
                        name="action"
                        value="search">             
                        Tìm kiếm
                    </button>

                </div>
            </form>

            <!-- TABLE -->

            <div class="table-box">

                <table>

                    <thead>

                        <tr>

                            <th style="width: 60px;">
                                STT
                            </th>

                            <th style="width: 150px;">
                                Mã môn
                            </th>

                            <th>
                                Tên môn
                            </th>

                            <th style="width: 150px;">
                                Số tín chỉ
                            </th>

                            <th style="width: 180px;">
                                Thao tác
                            </th>

                        </tr>

                    </thead>


                    <tbody id="courseTable">
                        <%
                            List<MonHoc> list = (ArrayList<MonHoc>) request.getAttribute("listMonHoc");
                        %>   
                        <%
                            int stt = 1;
                            for (var mh : list) {
                        %>
                        <tr>

                            <td><%= stt++%></td>

                            <td><%= mh.getMamh()%></td>

                            <td><%=mh.getTenmh()%></td>

                            <td><%= mh.getSotinchi()%></td>

                            <td>
                                <a href="<%= request.getContextPath()%>/monhoc?action=edit&mamh=<%= mh.getMamh()%>">
                                    <button class="action-edit">
                                        Sửa
                                    </button>
                                </a>
                                <a href="<%= request.getContextPath()%>/monhoc?action=delete&mamh=<%= mh.getMamh()%>"
                                   onclick="return confirm('Bạn có đồng ý xóa môn học <%= mh.getMamh() %>')"
                                   >
                                    <button
                                        class="action-delete"
                                        onclick="deleteCourse(this)">
                                        Xóa
                                    </button>
                                </a>
                            </td>

                        </tr>
                        <%
                            }
                        %>

                    </tbody>

                </table>

            </div>


            <div class="footer">
                Hệ thống quản lý giáo vụ - Phòng Đào tạo
            </div>

        </div>
    </body>
</html>