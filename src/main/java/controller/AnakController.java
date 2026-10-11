package controller;

import dao.AnakDAO;
import model.Anak;

import java.sql.SQLException;
import java.util.ArrayList;

// Controller: logika CRUD Anak. Data diambil/disimpan lewat AnakDAO (database).
public class AnakController implements ManajemenData {
    private AnakDAO dao = new AnakDAO();

    // CREATE
    public void tambah(String id, String nama, int umur, String catatanKesehatan, String idOrangTua) {
        try {
            dao.tambah(new Anak(id, nama, umur, catatanKesehatan, idOrangTua));
        } catch (SQLException e) {
            throw new RuntimeException("Gagal menyimpan data anak: " + e.getMessage(), e);
        }
    }

    // READ
    public ArrayList<Anak> getDaftar() {
        try {
            return dao.semua();
        } catch (SQLException e) {
            throw new RuntimeException("Gagal membaca data anak: " + e.getMessage(), e);
        }
    }

    public Anak cari(String id) {
        try {
            return dao.cari(id);
        } catch (SQLException e) {
            throw new RuntimeException("Gagal mencari data anak: " + e.getMessage(), e);
        }
    }

    // UPDATE
    public void perbarui(Anak anak, String nama, int umur, String catatanKesehatan) {
        anak.setNama(nama);
        anak.setUmur(umur);
        anak.setCatatanKesehatan(catatanKesehatan);
        try {
            dao.perbarui(anak);
        } catch (SQLException e) {
            throw new RuntimeException("Gagal memperbarui data anak: " + e.getMessage(), e);
        }
    }

    // DELETE
    public void hapus(Anak anak) {
        try {
            dao.hapus(anak.getId());
        } catch (SQLException e) {
            throw new RuntimeException("Gagal menghapus data anak: " + e.getMessage(), e);
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
