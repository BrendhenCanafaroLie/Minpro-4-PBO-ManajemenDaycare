package view;

import controller.OrangTuaController;
import helper.InputValidator;
import model.OrangTua;

import java.util.ArrayList;
import java.util.Scanner;

// View: menampilkan menu dan meminta input untuk data Orang Tua.
// Pemrosesan datanya diserahkan ke OrangTuaController.
public class OrangTuaView implements MenuView {
    private OrangTuaController controller;

    public OrangTuaView(OrangTuaController controller) {
        this.controller = controller;
    }

    @Override
    public void tampilkanMenu(Scanner scanner) {
        boolean kembali = false;
        while (!kembali) {
            System.out.println("\n----- Menu Data Orang Tua -----");
            System.out.println("1. Tambah Data Orang Tua");
            System.out.println("2. Tampilkan Semua Orang Tua");
            System.out.println("3. Update Data Orang Tua");
            System.out.println("4. Hapus Data Orang Tua");
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
        System.out.println("\n--- Tambah Data Orang Tua ---");
        String id = InputValidator.ambilTeksWajibIsi(scanner, "ID Orang Tua: ");
        if (controller.cari(id) != null) {
            System.out.println("Gagal! ID sudah digunakan.");
            return;
        }
        String nama = InputValidator.ambilTeksWajibIsi(scanner, "Nama Orang Tua: ");
        String noHp = InputValidator.ambilNomorHp(scanner, "No HP: ");
        String alamat = InputValidator.ambilTeksWajibIsi(scanner, "Alamat: ");

        controller.tambah(id, nama, noHp, alamat);
        System.out.println("Data orang tua berhasil ditambahkan: " + controller.cari(id).getRingkasan());
    }

    private void tampilkanSemua() {
        System.out.println("\n--- Daftar Orang Tua ---");
        ArrayList<OrangTua> daftar = controller.getDaftar();
        if (daftar.isEmpty()) {
            System.out.println("Belum ada data orang tua.");
            return;
        }
        for (int i = 0; i < daftar.size(); i++) {
            System.out.println((i + 1) + ". " + daftar.get(i));
        }
    }

    private void perbarui(Scanner scanner) {
        System.out.println("\n--- Update Data Orang Tua ---");
        String id = InputValidator.ambilTeksWajibIsi(scanner, "Masukkan ID Orang Tua: ");
        OrangTua orangTua = controller.cari(id);
        if (orangTua == null) {
            System.out.println("Data tidak ditemukan!");
            return;
        }
        System.out.println("Data ditemukan -> " + orangTua);

        String nama = InputValidator.ambilTeksWajibIsi(scanner, "Nama Baru: ");
        String noHp = InputValidator.ambilNomorHp(scanner, "No HP Baru: ");
        String alamat = InputValidator.ambilTeksWajibIsi(scanner, "Alamat Baru: ");

        controller.perbarui(orangTua, nama, noHp, alamat);
        System.out.println("Data orang tua berhasil diperbarui!");
    }

    private void hapus(Scanner scanner) {
        System.out.println("\n--- Hapus Data Orang Tua ---");
        String id = InputValidator.ambilTeksWajibIsi(scanner, "Masukkan ID Orang Tua: ");
        OrangTua orangTua = controller.cari(id);
        if (orangTua == null) {
            System.out.println("Data tidak ditemukan!");
            return;
        }
        controller.hapus(orangTua);
        System.out.println("Data orang tua berhasil dihapus!");
    }
}
