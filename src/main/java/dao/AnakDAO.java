package dao;

import model.Anak;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// Tempat query SQL untuk tabel anak 
public class AnakDAO {

    public void tambah(Anak a) throws SQLException {
        String sql = "INSERT INTO anak (id_anak, nama, umur, catatan_kesehatan, id_orang_tua) VALUES (?, ?, ?, ?, ?)";
        try (Connection c = Koneksi.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, a.getId());
            ps.setString(2, a.getNama());
            ps.setInt(3, a.getUmur());
            ps.setString(4, a.getCatatanKesehatan());
            ps.setString(5, a.getIdOrangTua());
            ps.executeUpdate();
        }
    }

    public ArrayList<Anak> semua() throws SQLException {
        String sql = "SELECT * FROM anak ORDER BY id_anak";
        ArrayList<Anak> hasil = new ArrayList<>();
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                hasil.add(ambil(rs));
            }
        }
        return hasil;
    }

    public Anak cari(String id) throws SQLException {
        String sql = "SELECT * FROM anak WHERE id_anak = ?";
        try (Connection c = Koneksi.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? ambil(rs) : null;
            }
        }
    }

    public void perbarui(Anak a) throws SQLException {
        String sql = "UPDATE anak SET nama = ?, umur = ?, catatan_kesehatan = ? WHERE id_anak = ?";
        try (Connection c = Koneksi.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, a.getNama());
            ps.setInt(2, a.getUmur());
            ps.setString(3, a.getCatatanKesehatan());
            ps.setString(4, a.getId());
            ps.executeUpdate();
        }
    }

    public void hapus(String id) throws SQLException {
        String sql = "DELETE FROM anak WHERE id_anak = ?";
        try (Connection c = Koneksi.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        }
    }

    private Anak ambil(ResultSet rs) throws SQLException {
        return new Anak(rs.getString("id_anak"), rs.getString("nama"), rs.getInt("umur"),
                rs.getString("catatan_kesehatan"), rs.getString("id_orang_tua"));
    }
}
