package helper;

import java.util.Scanner;

// Berisi semua validasi input.
public class InputValidator {

    // Input tidak boleh kosong
    public static String ambilTeksWajibIsi(Scanner pemindai, String pesan) {
        while (true) {
            System.out.print(pesan);
            String masukan = pemindai.nextLine().trim();
            if (!masukan.isEmpty()) {
                return masukan;
            }
            System.out.println("Input tidak boleh kosong!");
        }
    }

    // Input harus angka bulat lebih dari 0
    public static int ambilAngkaPositif(Scanner pemindai, String pesan) {
        while (true) {
            System.out.print(pesan);
            String masukan = pemindai.nextLine().trim();
            try {
                int nilai = Integer.parseInt(masukan);
                if (nilai > 0) {
                    return nilai;
                }
                System.out.println("Angka harus lebih dari 0!");
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka!");
            }
        }
    }

    // Nomor HP harus angka, 8-15 digit (disimpan String supaya angka 0 di depan tidak hilang)
    public static String ambilNomorHp(Scanner pemindai, String pesan) {
        while (true) {
            System.out.print(pesan);
            String masukan = pemindai.nextLine().trim();
            if (masukan.matches("\\d{8,15}")) {
                return masukan;
            }
            System.out.println("Nomor HP harus berupa angka 8-15 digit!");
        }
    }

    // Format tanggal harus YYYY-MM-DD
    public static String ambilTanggal(Scanner pemindai, String pesan) {
        while (true) {
            System.out.print(pesan);
            String masukan = pemindai.nextLine().trim();
            if (masukan.matches("\\d{4}-\\d{2}-\\d{2}")) {
                return masukan;
            }
            System.out.println("Format tanggal salah! Contoh: 2026-09-16");
        }
    }

    // Pilihan menu harus angka dalam rentang minimum - maksimum
    public static int ambilPilihanMenu(Scanner pemindai, String pesan, int minimum, int maksimum) {
        while (true) {
            System.out.print(pesan);
            String masukan = pemindai.nextLine().trim();
            try {
                int nilai = Integer.parseInt(masukan);
                if (nilai >= minimum && nilai <= maksimum) {
                    return nilai;
                }
                System.out.println("Pilihan harus antara " + minimum + " - " + maksimum + "!");
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka!");
            }
        }
    }
}
