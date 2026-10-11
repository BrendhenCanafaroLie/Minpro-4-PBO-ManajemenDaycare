import controller.AnakController;
import controller.AnakOrmController;
import controller.CatatanHarianController;
import controller.OrangTuaController;
import helper.InputValidator;
import view.AnakOrmView;
import view.AnakView;
import view.CatatanHarianView;
import view.MenuView;
import view.OrangTuaView;

import java.util.Scanner;

// Entry point program Sistem Manajemen Daycare.
// Membuat objek Controller dan View, lalu menampilkan menu utama
// terus-menerus sampai pengguna memilih Keluar.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Controller (data diambil dari database lewat DAO)
        OrangTuaController orangTuaController = new OrangTuaController();
        AnakController anakController = new AnakController();
        CatatanHarianController catatanController = new CatatanHarianController();
        AnakOrmController anakOrmController = new AnakOrmController(); // ORM (nilai tambah)

        // View
        OrangTuaView orangTuaView = new OrangTuaView(orangTuaController);
        AnakView anakView = new AnakView(anakController, orangTuaController);
        CatatanHarianView catatanView = new CatatanHarianView(catatanController, anakController);
        AnakOrmView anakOrmView = new AnakOrmView(anakOrmController);

        // Polymorphism lewat interface: semua View disimpan sebagai tipe MenuView
        MenuView[] daftarMenu = {orangTuaView, anakView, catatanView, anakOrmView};

        System.out.println("=================================================");
        System.out.println("   SELAMAT DATANG DI SISTEM MANAJEMEN DAYCARE");
        System.out.println("=================================================");

        boolean berjalan = true;
        while (berjalan) {
            System.out.println("\n=========== MENU UTAMA ===========");
            System.out.println("1. Menu Data Orang Tua");
            System.out.println("2. Menu Data Anak");
            System.out.println("3. Menu Catatan Harian");
            System.out.println("4. Menu ORM (Hibernate)");
            System.out.println("5. Keluar");

            int pilihan = InputValidator.ambilPilihanMenu(scanner, "Pilih menu (1-5): ", 1, 5);
            switch (pilihan) {
                case 1:
                case 2:
                case 3:
                case 4:
                    daftarMenu[pilihan - 1].tampilkanMenu(scanner);
                    break;
                case 5:
                    berjalan = false;
                    System.out.println("\nTerima kasih telah menggunakan Sistem Manajemen Daycare. Sampai jumpa!");
                    break;
            }
        }
        scanner.close();
    }
}
