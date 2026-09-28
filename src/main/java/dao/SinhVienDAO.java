package dao;

import model.SinhVien;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SinhVienDAO {

    // =====================================================
    // LẤY DANH SÁCH SINH VIÊN
    // =====================================================
    public List<SinhVien> getAll() {

        List<SinhVien> list = new ArrayList<>();

        String sql = """
                SELECT MaSV, HoTen, NgaySinh,
                       GioiTinh, DiaChi, MaKhoa
                FROM SINHVIEN
                ORDER BY MaSV
                """;

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                SinhVien sv = new SinhVien();

                sv.setMasv(rs.getString("MaSV"));
                sv.setHoten(rs.getString("HoTen"));
                sv.setNgaysinh(rs.getDate("NgaySinh"));
                sv.setGioitinh(rs.getBoolean("GioiTinh"));
                sv.setDiachi(rs.getString("DiaChi"));
                sv.setMakhoa(rs.getString("MaKhoa"));

                list.add(sv);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    // =====================================================
    // LẤY SINH VIÊN THEO MÃ
    // =====================================================
    public SinhVien getById(String masv) {

        String sql = """
                SELECT MaSV, HoTen, NgaySinh,
                       GioiTinh, DiaChi, MaKhoa
                FROM SINHVIEN
                WHERE MaSV = ?
                """;

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, masv);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                SinhVien sv = new SinhVien();

                sv.setMasv(rs.getString("MaSV"));
                sv.setHoten(rs.getString("HoTen"));
                sv.setNgaysinh(rs.getDate("NgaySinh"));
                sv.setGioitinh(rs.getBoolean("GioiTinh"));
                sv.setDiachi(rs.getString("DiaChi"));
                sv.setMakhoa(rs.getString("MaKhoa"));

                return sv;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    // =====================================================
    // THÊM SINH VIÊN
    // =====================================================
    public boolean insert(SinhVien sv) {

        String sql = """
                INSERT INTO SINHVIEN
                (MaSV, HoTen, NgaySinh,
                 GioiTinh, DiaChi, MaKhoa)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, sv.getMasv());
            ps.setString(2, sv.getHoten());
            ps.setDate(3, sv.getNgaysinh());
            ps.setBoolean(4, sv.isGioitinh());
            ps.setString(5, sv.getDiachi());
            ps.setString(6, sv.getMakhoa());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // =====================================================
    // CẬP NHẬT SINH VIÊN
    // =====================================================
    public boolean update(SinhVien sv) {

        String sql = """
                UPDATE SINHVIEN
                SET HoTen = ?,
                    NgaySinh = ?,
                    GioiTinh = ?,
                    DiaChi = ?,
                    MaKhoa = ?
                WHERE MaSV = ?
                """;

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, sv.getHoten());
            ps.setDate(2, sv.getNgaysinh());
            ps.setBoolean(3, sv.isGioitinh());
            ps.setString(4, sv.getDiachi());
            ps.setString(5, sv.getMakhoa());
            ps.setString(6, sv.getMasv());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // =====================================================
    // XÓA SINH VIÊN
    // =====================================================
    public boolean delete(String masv) {

        String sql = """
                DELETE FROM SINHVIEN
                WHERE MaSV = ?
                """;

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, masv);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // =====================================================
    // TÌM KIẾM SINH VIÊN
    // =====================================================
    public List<SinhVien> search(String keyword) {

        List<SinhVien> list = new ArrayList<>();

        String sql = """
                SELECT MaSV, HoTen, NgaySinh,
                       GioiTinh, DiaChi, MaKhoa
                FROM SINHVIEN
                WHERE MaSV LIKE ?
                   OR HoTen LIKE ?
                ORDER BY MaSV
                """;

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            String key = "%" + keyword + "%";

            ps.setString(1, key);
            ps.setString(2, key);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                SinhVien sv = new SinhVien();

                sv.setMasv(rs.getString("MaSV"));
                sv.setHoten(rs.getString("HoTen"));
                sv.setNgaysinh(rs.getDate("NgaySinh"));
                sv.setGioitinh(rs.getBoolean("GioiTinh"));
                sv.setDiachi(rs.getString("DiaChi"));
                sv.setMakhoa(rs.getString("MaKhoa"));

                list.add(sv);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}