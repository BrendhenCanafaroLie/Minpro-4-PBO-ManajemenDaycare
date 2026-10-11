package dao;

import model.OrangTua;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// Tempat query SQL untuk tabel orang_tua
public class OrangTuaDAO {

    public void tambah(OrangTua ot) throws SQLException {
        String sql = "INSERT INTO orang_tua (id_orang_tua, nama, no_hp, alamat) VALUES (?, ?, ?, ?)";
        try (Connection c = Koneksi.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, ot.getId());
            ps.setString(2, ot.getNama());
            ps.setString(3, ot.getNoHp());
            ps.setString(4, ot.getAlamat());
            ps.executeUpdate();
        }
    }

    public ArrayList<OrangTua> semua() throws SQLException {
        String sql = "SELECT * FROM orang_tua ORDER BY id_orang_tua";
        ArrayList<OrangTua> hasil = new ArrayList<>();
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                hasil.add(ambil(rs));
            }
        }
        return hasil;
    }

    public OrangTua cari(String id) throws SQLException {
        String sql = "SELECT * FROM orang_tua WHERE id_orang_tua = ?";
        try (Connection c = Koneksi.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? ambil(rs) : null;
            }
        }
    }

    public void perbarui(OrangTua ot) throws SQLException {
        String sql = "UPDATE orang_tua SET nama = ?, no_hp = ?, alamat = ? WHERE id_orang_tua = ?";
        try (Connection c = Koneksi.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, ot.getNama());
            ps.setString(2, ot.getNoHp());
            ps.setString(3, ot.getAlamat());
            ps.setString(4, ot.getId());
            ps.executeUpdate();
        }
    }

    public void hapus(String id) throws SQLException {
        String sql = "DELETE FROM orang_tua WHERE id_orang_tua = ?";
        try (Connection c = Koneksi.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        }
    }

    private OrangTua ambil(ResultSet rs) throws SQLException {
        return new OrangTua(rs.getString("id_orang_tua"), rs.getString("nama"),
                rs.getString("no_hp"), rs.getString("alamat"));
    }
}
