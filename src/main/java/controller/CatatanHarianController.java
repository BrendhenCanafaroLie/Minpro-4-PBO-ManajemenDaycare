package controller;

import dao.CatatanHarianDAO;
import model.CatatanHarian;

import java.sql.SQLException;
import java.util.ArrayList;

// Controller: logika CRUD CatatanHarian. Data diambil/disimpan lewat CatatanHarianDAO (database).
public class CatatanHarianController implements ManajemenData {
    private CatatanHarianDAO dao = new CatatanHarianDAO();

    // CREATE (ID catatan dibuat otomatis oleh database lalu dikembalikan)
    public String tambah(String idAnak, String tanggal, String aktivitas) {
        try {
            return dao.tambah(new CatatanHarian(null, idAnak, tanggal, aktivitas));
        } catch (SQLException e) {
            throw new RuntimeException("Gagal menyimpan catatan: " + e.getMessage(), e);
        }
    }

    // READ - semua riwayat satu anak
    public ArrayList<CatatanHarian> getRiwayat(String idAnak) {
        try {
            return dao.cariPerAnak(idAnak);
        } catch (SQLException e) {
            throw new RuntimeException("Gagal membaca catatan: " + e.getMessage(), e);
        }
    }

    // Overloading: nama method sama (getRiwayat), tapi ada tambahan parameter tanggal
    public ArrayList<CatatanHarian> getRiwayat(String idAnak, String tanggal) {
        try {
            return dao.cariPerAnakDanTanggal(idAnak, tanggal);
        } catch (SQLException e) {
            throw new RuntimeException("Gagal membaca catatan: " + e.getMessage(), e);
        }
    }

    public ArrayList<CatatanHarian> getDaftar() {
        try {
            return dao.semua();
        } catch (SQLException e) {
            throw new RuntimeException("Gagal membaca catatan: " + e.getMessage(), e);
        }
    }

    public CatatanHarian cari(String idCatatan) {
        try {
            return dao.cari(idCatatan);
        } catch (SQLException e) {
            throw new RuntimeException("Gagal mencari catatan: " + e.getMessage(), e);
        }
    }

    // UPDATE
    public void perbarui(CatatanHarian catatan, String tanggal, String aktivitas) {
        catatan.setTanggal(tanggal);
        catatan.setAktivitas(aktivitas);
        try {
            dao.perbarui(catatan);
        } catch (SQLException e) {
            throw new RuntimeException("Gagal memperbarui catatan: " + e.getMessage(), e);
        }
    }

    // DELETE
    public void hapus(CatatanHarian catatan) {
        try {
            dao.hapus(catatan.getIdCatatan());
        } catch (SQLException e) {
            throw new RuntimeException("Gagal menghapus catatan: " + e.getMessage(), e);
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
