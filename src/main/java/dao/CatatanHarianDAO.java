package dao;

import model.CatatanHarian;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

// Tempat query SQL untuk tabel catatan_harian 
public class CatatanHarianDAO {

    public String tambah(CatatanHarian cat) throws SQLException {
        String sql = "INSERT INTO catatan_harian (id_anak, tanggal, aktivitas) VALUES (?, ?, ?)";
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, cat.getIdAnak());
            ps.setString(2, cat.getTanggal());
            ps.setString(3, cat.getAktivitas());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                rs.next();
                return rs.getString(1);
            }
        }
    }

    public ArrayList<CatatanHarian> semua() throws SQLException {
        String sql = "SELECT * FROM catatan_harian ORDER BY id_catatan";
        ArrayList<CatatanHarian> hasil = new ArrayList<>();
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                hasil.add(ambil(rs));
            }
        }
        return hasil;
    }

    public ArrayList<CatatanHarian> cariPerAnak(String idAnak) throws SQLException {
        String sql = "SELECT * FROM catatan_harian WHERE id_anak = ? ORDER BY tanggal, id_catatan";
        ArrayList<CatatanHarian> hasil = new ArrayList<>();
        try (Connection c = Koneksi.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, idAnak);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    hasil.add(ambil(rs));
                }
            }
        }
        return hasil;
    }

    public ArrayList<CatatanHarian> cariPerAnakDanTanggal(String idAnak, String tanggal) throws SQLException {
        String sql = "SELECT * FROM catatan_harian WHERE id_anak = ? AND tanggal = ? ORDER BY id_catatan";
        ArrayList<CatatanHarian> hasil = new ArrayList<>();
        try (Connection c = Koneksi.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, idAnak);
            ps.setString(2, tanggal);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    hasil.add(ambil(rs));
                }
            }
        }
        return hasil;
    }

    public CatatanHarian cari(String idCatatan) throws SQLException {
        String sql = "SELECT * FROM catatan_harian WHERE id_catatan = ?";
        try (Connection c = Koneksi.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, idCatatan);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? ambil(rs) : null;
            }
        }
    }

    public void perbarui(CatatanHarian cat) throws SQLException {
        String sql = "UPDATE catatan_harian SET tanggal = ?, aktivitas = ? WHERE id_catatan = ?";
        try (Connection c = Koneksi.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, cat.getTanggal());
            ps.setString(2, cat.getAktivitas());
            ps.setString(3, cat.getIdCatatan());
            ps.executeUpdate();
        }
    }

    public void hapus(String idCatatan) throws SQLException {
        String sql = "DELETE FROM catatan_harian WHERE id_catatan = ?";
        try (Connection c = Koneksi.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, idCatatan);
            ps.executeUpdate();
        }
    }

    private CatatanHarian ambil(ResultSet rs) throws SQLException {
        return new CatatanHarian(rs.getString("id_catatan"), rs.getString("id_anak"),
                rs.getString("tanggal"), rs.getString("aktivitas"));
    }
}
