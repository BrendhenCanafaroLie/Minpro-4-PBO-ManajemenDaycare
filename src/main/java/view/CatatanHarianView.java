package view;

import controller.AnakController;
import controller.CatatanHarianController;
import helper.InputValidator;
import model.CatatanHarian;

import java.util.ArrayList;
import java.util.Scanner;

// View: menampilkan menu dan meminta input untuk Catatan Harian.
// AnakController dipakai untuk mengecek apakah ID anak terdaftar.
public class CatatanHarianView implements MenuView {
    private CatatanHarianController controller;
    private AnakController anakController;

    public CatatanHarianView(CatatanHarianController controller, AnakController anakController) {
        this.controller = controller;
        this.anakController = anakController;
    }

    @Override
    public void tampilkanMenu(Scanner scanner) {
        boolean kembali = false;
        while (!kembali) {
            System.out.println("\n----- Menu Catatan Harian -----");
            System.out.println("1. Input Laporan Kegiatan Harian");
            System.out.println("2. Lihat Riwayat Aktivitas Anak");
            System.out.println("3. Lihat Riwayat Anak pada Tanggal Tertentu");
            System.out.println("4. Edit Laporan Aktivitas Harian");
            System.out.println("5. Hapus Laporan Aktivitas Harian");
            System.out.println("6. Kembali ke Menu Utama");

            int pilihan = InputValidator.ambilPilihanMenu(scanner, "Pilih menu (1-6): ", 1, 6);
            switch (pilihan) {
                case 1:
                    tambah(scanner);
                    break;
                case 2:
                    tampilkanRiwayat(scanner);
                    break;
                case 3:
                    tampilkanRiwayatPerTanggal(scanner);
                    break;
                case 4:
                    perbarui(scanner);
                    break;
                case 5:
                    hapus(scanner);
                    break;
                case 6:
                    kembali = true;
                    break;
            }
        }
    }

    private void tambah(Scanner scanner) {
        System.out.println("\n--- Input Laporan Kegiatan Harian ---");
        if (anakController.isKosong()) {
            System.out.println("Belum ada data anak! Daftarkan anak terlebih dahulu.");
            return;
        }

        String idAnak;
        while (true) {
            idAnak = InputValidator.ambilTeksWajibIsi(scanner, "ID Anak: ");
            if (anakController.cari(idAnak) != null) {
                break;
            }
            System.out.println("ID Anak tidak ditemukan!");
        }
        String tanggal = InputValidator.ambilTanggal(scanner, "Tanggal (YYYY-MM-DD): ");
        String aktivitas = InputValidator.ambilTeksWajibIsi(scanner, "Aktivitas: ");

        String idCatatan = controller.tambah(idAnak, tanggal, aktivitas);
        System.out.println("Catatan berhasil ditambahkan: " + controller.cari(idCatatan).getRingkasan());
    }

    private void tampilkanRiwayat(Scanner scanner) {
        System.out.println("\n--- Riwayat Aktivitas Anak ---");
        String idAnak = InputValidator.ambilTeksWajibIsi(scanner, "Masukkan ID Anak: ");
        if (anakController.cari(idAnak) == null) {
            System.out.println("Data anak tidak ditemukan!");
            return;
        }
        // Memanggil getRiwayat versi 1 (hanya idAnak)
        cetak(controller.getRiwayat(idAnak));
    }

    private void tampilkanRiwayatPerTanggal(Scanner scanner) {
        System.out.println("\n--- Riwayat Anak pada Tanggal Tertentu ---");
        String idAnak = InputValidator.ambilTeksWajibIsi(scanner, "Masukkan ID Anak: ");
        if (anakController.cari(idAnak) == null) {
            System.out.println("Data anak tidak ditemukan!");
            return;
        }
        String tanggal = InputValidator.ambilTanggal(scanner, "Tanggal (YYYY-MM-DD): ");
        // Memanggil getRiwayat versi 2 (idAnak dan tanggal)
        cetak(controller.getRiwayat(idAnak, tanggal));
    }

    private void cetak(ArrayList<CatatanHarian> daftar) {
        if (daftar.isEmpty()) {
            System.out.println("Tidak ada catatan.");
            return;
        }
        for (CatatanHarian c : daftar) {
            System.out.println(c);
        }
    }

    private void perbarui(Scanner scanner) {
        System.out.println("\n--- Edit Laporan Aktivitas ---");
        String id = InputValidator.ambilTeksWajibIsi(scanner, "Masukkan ID Catatan: ");
        CatatanHarian catatan = controller.cari(id);
        if (catatan == null) {
            System.out.println("Catatan tidak ditemukan!");
            return;
        }
        System.out.println("Data ditemukan -> " + catatan);

        String tanggal = InputValidator.ambilTanggal(scanner, "Tanggal Baru (YYYY-MM-DD): ");
        String aktivitas = InputValidator.ambilTeksWajibIsi(scanner, "Aktivitas Baru: ");

        controller.perbarui(catatan, tanggal, aktivitas);
        System.out.println("Catatan berhasil diperbarui!");
    }

    private void hapus(Scanner scanner) {
        System.out.println("\n--- Hapus Laporan Aktivitas ---");
        String id = InputValidator.ambilTeksWajibIsi(scanner, "Masukkan ID Catatan: ");
        CatatanHarian catatan = controller.cari(id);
        if (catatan == null) {
            System.out.println("Catatan tidak ditemukan!");
            return;
        }
        controller.hapus(catatan);
        System.out.println("Catatan berhasil dihapus!");
    }
}
