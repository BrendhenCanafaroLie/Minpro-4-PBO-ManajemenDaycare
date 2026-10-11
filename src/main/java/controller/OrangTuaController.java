package controller;

import dao.OrangTuaDAO;
import model.OrangTua;

import java.sql.SQLException;
import java.util.ArrayList;

// Controller: logika CRUD OrangTua. Data diambil/disimpan lewat OrangTuaDAO (database).
// Class ini tidak menampilkan apa-apa ke layar, itu tugas View.
public class OrangTuaController implements ManajemenData {
    private OrangTuaDAO dao = new OrangTuaDAO();

    // CREATE
    public void tambah(String id, String nama, String noHp, String alamat) {
        try {
            dao.tambah(new OrangTua(id, nama, noHp, alamat));
        } catch (SQLException e) {
            throw new RuntimeException("Gagal menyimpan data orang tua: " + e.getMessage(), e);
        }
    }

    // READ
    public ArrayList<OrangTua> getDaftar() {
        try {
            return dao.semua();
        } catch (SQLException e) {
            throw new RuntimeException("Gagal membaca data orang tua: " + e.getMessage(), e);
        }
    }

    public OrangTua cari(String id) {
        try {
            return dao.cari(id);
        } catch (SQLException e) {
            throw new RuntimeException("Gagal mencari data orang tua: " + e.getMessage(), e);
        }
    }

    // UPDATE
    public void perbarui(OrangTua orangTua, String nama, String noHp, String alamat) {
        orangTua.setNama(nama);
        orangTua.setNoHp(noHp);
        orangTua.setAlamat(alamat);
        try {
            dao.perbarui(orangTua);
        } catch (SQLException e) {
            throw new RuntimeException("Gagal memperbarui data orang tua: " + e.getMessage(), e);
        }
    }

    // DELETE
    public void hapus(OrangTua orangTua) {
        try {
            dao.hapus(orangTua.getId());
        } catch (SQLException e) {
            throw new RuntimeException("Gagal menghapus data orang tua: " + e.getMessage(), e);
        }
    }

    // Implementasi interface ManajemenData
    @Override
    public int getJumlahData() {
        return getDaftar().size();
    }

    @Override
    public boolean isKosong() {
        return getJumlahData() == 0;
    }
}
