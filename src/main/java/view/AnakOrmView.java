package view;

import controller.AnakOrmController;
import helper.InputValidator;
import model.Anak;

import java.util.ArrayList;
import java.util.Scanner;

// View: menu untuk fitur ORM (Hibernate) pada data Anak.
public class AnakOrmView implements MenuView {
    private AnakOrmController controller;

    public AnakOrmView(AnakOrmController controller) {
        this.controller = controller;
    }

    @Override
    public void tampilkanMenu(Scanner scanner) {
        boolean kembali = false;
        while (!kembali) {
            System.out.println("\n----- Menu ORM (Hibernate) - Data Anak -----");
            System.out.println("1. Tampilkan Semua Anak (ORM)");
            System.out.println("2. Cari Anak Berdasarkan Nama (ORM)");
            System.out.println("3. Update Catatan Kesehatan Anak (ORM)");
            System.out.println("4. Kembali ke Menu Utama");

            int pilihan = InputValidator.ambilPilihanMenu(scanner, "Pilih menu (1-4): ", 1, 4);
            switch (pilihan) {
                case 1:
                    tampilkanSemua();
                    break;
                case 2:
                    cariNama(scanner);
                    break;
                case 3:
                    perbaruiCatatan(scanner);
                    break;
                case 4:
                    kembali = true;
                    break;
            }
        }
    }

    private void tampilkanSemua() {
        System.out.println("\n--- Daftar Anak (ORM) ---");
        cetak(controller.getDaftar());
    }

    private void cariNama(Scanner scanner) {
        System.out.println("\n--- Cari Anak Berdasarkan Nama (ORM) ---");
        String nama = InputValidator.ambilTeksWajibIsi(scanner, "Masukkan nama (atau sebagian nama): ");
        cetak(controller.cariNama(nama));
    }

    private void perbaruiCatatan(Scanner scanner) {
        System.out.println("\n--- Update Catatan Kesehatan (ORM) ---");
        String id = InputValidator.ambilTeksWajibIsi(scanner, "Masukkan ID Anak: ");
        String catatan = InputValidator.ambilTeksWajibIsi(scanner, "Catatan Kesehatan Baru: ");
        if (controller.perbaruiCatatanKesehatan(id, catatan)) {
            System.out.println("Catatan kesehatan berhasil diperbarui!");
        } else {
            System.out.println("Data tidak ditemukan!");
        }
    }

    private void cetak(ArrayList<Anak> daftar) {
        if (daftar.isEmpty()) {
            System.out.println("Tidak ada data anak.");
            return;
        }
        for (int i = 0; i < daftar.size(); i++) {
            System.out.println((i + 1) + ". " + daftar.get(i));
        }
    }
}
