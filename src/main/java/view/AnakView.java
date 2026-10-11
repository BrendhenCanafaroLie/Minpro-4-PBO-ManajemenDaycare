package view;

import controller.AnakController;
import controller.OrangTuaController;
import helper.InputValidator;
import model.Anak;

import java.util.ArrayList;
import java.util.Scanner;

// View: menampilkan menu dan meminta input untuk data Anak.
// OrangTuaController dipakai untuk mengecek apakah ID orang tua terdaftar.
public class AnakView implements MenuView {
    private AnakController controller;
    private OrangTuaController orangTuaController;

    public AnakView(AnakController controller, OrangTuaController orangTuaController) {
        this.controller = controller;
        this.orangTuaController = orangTuaController;
    }

    @Override
    public void tampilkanMenu(Scanner scanner) {
        boolean kembali = false;
        while (!kembali) {
            System.out.println("\n----- Menu Data Anak -----");
            System.out.println("1. Daftarkan Anak Baru");
            System.out.println("2. Tampilkan Semua Anak");
            System.out.println("3. Update Data Anak");
            System.out.println("4. Hapus Data Anak");
            System.out.println("5. Kembali ke Menu Utama");

            int pilihan = InputValidator.ambilPilihanMenu(scanner, "Pilih menu (1-5): ", 1, 5);
            switch (pilihan) {
                case 1:
                    tambah(scanner);
                    break;
                case 2:
                    tampilkanSemua();
                    break;
                case 3:
                    perbarui(scanner);
                    break;
                case 4:
                    hapus(scanner);
                    break;
                case 5:
                    kembali = true;
                    break;
            }
        }
    }

    private void tambah(Scanner scanner) {
        System.out.println("\n--- Daftarkan Anak Baru ---");
        if (orangTuaController.isKosong()) {
            System.out.println("Belum ada data orang tua! Daftarkan orang tua terlebih dahulu.");
            return;
        }

        String id = InputValidator.ambilTeksWajibIsi(scanner, "ID Anak: ");
        if (controller.cari(id) != null) {
            System.out.println("Gagal! ID sudah digunakan.");
            return;
        }
        String nama = InputValidator.ambilTeksWajibIsi(scanner, "Nama Anak: ");
        int umur = InputValidator.ambilAngkaPositif(scanner, "Umur Anak: ");
        String catatan = InputValidator.ambilTeksWajibIsi(scanner, "Catatan Kesehatan (isi '-' jika tidak ada): ");

        String idOrangTua;
        while (true) {
            idOrangTua = InputValidator.ambilTeksWajibIsi(scanner, "ID Orang Tua: ");
            if (orangTuaController.cari(idOrangTua) != null) {
                break;
            }
            System.out.println("ID Orang Tua tidak ditemukan!");
        }

        controller.tambah(id, nama, umur, catatan, idOrangTua);
        System.out.println("Data anak berhasil didaftarkan: " + controller.cari(id).getRingkasan());
    }

    private void tampilkanSemua() {
        System.out.println("\n--- Daftar Anak ---");
        ArrayList<Anak> daftar = controller.getDaftar();
        if (daftar.isEmpty()) {
            System.out.println("Belum ada data anak.");
            return;
        }
        for (int i = 0; i < daftar.size(); i++) {
            System.out.println((i + 1) + ". " + daftar.get(i));
        }
    }

    private void perbarui(Scanner scanner) {
        System.out.println("\n--- Update Data Anak ---");
        String id = InputValidator.ambilTeksWajibIsi(scanner, "Masukkan ID Anak: ");
        Anak anak = controller.cari(id);
        if (anak == null) {
            System.out.println("Data tidak ditemukan!");
            return;
        }
        System.out.println("Data ditemukan -> " + anak);

        String nama = InputValidator.ambilTeksWajibIsi(scanner, "Nama Baru: ");
        int umur = InputValidator.ambilAngkaPositif(scanner, "Umur Baru: ");
        String catatan = InputValidator.ambilTeksWajibIsi(scanner, "Catatan Kesehatan Baru: ");

        controller.perbarui(anak, nama, umur, catatan);
        System.out.println("Data anak berhasil diperbarui!");
    }

    private void hapus(Scanner scanner) {
        System.out.println("\n--- Hapus Data Anak ---");
        String id = InputValidator.ambilTeksWajibIsi(scanner, "Masukkan ID Anak: ");
        Anak anak = controller.cari(id);
        if (anak == null) {
            System.out.println("Data tidak ditemukan!");
            return;
        }
        controller.hapus(anak);
        System.out.println("Data anak berhasil dihapus!");
    }
}
