package controller;

// Interface: kontrak yang wajib dimiliki setiap Controller.
// Setiap Controller menghitung datanya dengan cara masing-masing.
public interface ManajemenData {
    int getJumlahData();

    boolean isKosong();
}
