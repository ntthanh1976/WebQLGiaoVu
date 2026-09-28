/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.MonHoc;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MonHocDAO {

    // Lấy tất cả môn học
    public List<MonHoc> getAll() {

        List<MonHoc> list = new ArrayList<>();

        String sql = """
                SELECT mamh, tenmh, sotinchi
                FROM MonHoc
                ORDER BY mamh
                """;

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                MonHoc mh = new MonHoc();

                mh.setMamh(rs.getString("mamh"));
                mh.setTenmh(rs.getString("tenmh"));
                mh.setSotinchi(rs.getInt("sotinchi"));

                list.add(mh);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // Thêm môn học
    public boolean insert(MonHoc mh) {

        String sql = """
                INSERT INTO MonHoc(mamh, tenmh, sotinchi)
                VALUES (?, ?, ?)
                """;

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, mh.getMamh());
            ps.setString(2, mh.getTenmh());
            ps.setInt(3, mh.getSotinchi());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Cập nhật môn học
    public boolean update(MonHoc mh) {

        String sql = """
                UPDATE MonHoc
                SET tenmh = ?, sotinchi = ?
                WHERE mamh = ?
                """;

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, mh.getTenmh());
            ps.setInt(2, mh.getSotinchi());
            ps.setString(3, mh.getMamh());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Xóa môn học
    public boolean delete(String mamh) {

        String sql = "DELETE FROM MonHoc WHERE mamh = ?";

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, mamh);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Tìm kiếm môn học
    public List<MonHoc> search(String keyword) {

        List<MonHoc> list = new ArrayList<>();

        String sql = """
                SELECT mamh, tenmh, sotinchi
                FROM MonHoc
                WHERE mamh LIKE ?
                   OR tenmh LIKE ?
                ORDER BY mamh
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

                MonHoc mh = new MonHoc();

                mh.setMamh(rs.getString("mamh"));
                mh.setTenmh(rs.getString("tenmh"));
                mh.setSotinchi(rs.getInt("sotinchi"));

                list.add(mh);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // Tìm môn học theo mã
    public MonHoc getById(String mamh) {

        String sql = """
                SELECT mamh, tenmh, sotinchi
                FROM MonHoc
                WHERE mamh = ?
                """;

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, mamh);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new MonHoc(
                    rs.getString("mamh"),
                    rs.getString("tenmh"),
                    rs.getInt("sotinchi")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public static void main(String[] args) {
        MonHocDAO mhDAO = new MonHocDAO();
        for(var mh : mhDAO.getAll())
        {
            System.out.println(mh.getMamh() + "-" + mh.getTenmh());
        }
    }
}