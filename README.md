# Minpro - PBO - Sistem Manajemen Daycare (Penitipan Anak)

## 1. Deskripsi Singkat Program

Program ini adalah aplikasi **CRUD (Create, Read, Update, Delete)** berbasis konsol (command line) yang mensimulasikan sistem rekap digital tempat penitipan anak (daycare). Program ini dibuat menggunakan bahasa **Java** dengan menerapkan konsep **Pemrograman Berorientasi Objek (PBO)**.

Program ini mengelola tiga entitas utama:

1. **OrangTua** — data wali yang mendaftarkan atau menjemput anak (`idOrangTua`, `namaOrangTua`, `noHp` bertipe `long`, `alamat`). Catatan: karena `noHp` disimpan sebagai tipe angka, angka `0` di paling depan nomor HP (misal `081234567890`) akan otomatis hilang menjadi `81234567890`.
2. **Anak** — data anak yang dititipkan di daycare (`idAnak`, `namaAnak`, `umur`, `catatanKesehatan`), yang juga direlasikan ke `idOrangTua` sebagai wali penanggung jawabnya.
3. **CatatanHarian** — jurnal harian yang diisi pengasuh untuk memantau kegiatan anak selama berada di daycare (`idCatatan`, `idAnak`, `tanggal`, `aktivitas`).

Seluruh data disimpan sementara di memori menggunakan `ArrayList` selama program berjalan (tanpa database eksternal), sehingga cocok digunakan sebagai simulasi pencatatan sederhana.

## 2. Struktur Program

```
src/
├── Main.java                      # Class entry point (menu utama)
├── model/
│   ├── OrangTua.java               # Entitas data orang tua/wali
│   ├── Anak.java                   # Entitas data anak
│   └── CatatanHarian.java          # Entitas jurnal harian
├── service/
│   ├── OrangTuaService.java        # Logic CRUD untuk OrangTua
│   ├── AnakService.java            # Logic CRUD untuk Anak
│   └── CatatanHarianService.java   # Logic CRUD untuk CatatanHarian
└── util/
    └── InputValidator.java         # Helper validasi input pengguna
```

Program dibagi menjadi 3 lapisan (di luar class entry point `Main`):
- **Model** — menyimpan struktur data (entitas).
- **Service** — menyimpan seluruh logika/fungsi CRUD serta ArrayList penampung data.
- **Util** — menyimpan fungsi bantu untuk validasi input.

## 3. Cara Menjalankan Program

Program dapat dikompilasi dan dijalankan menggunakan JDK (Java Development Kit) dari terminal:

```bash
# Masuk ke folder proyek
cd src

# Compile seluruh file .java
javac -d ../bin Main.java model/*.java service/*.java util/*.java

# Jalankan program
cd ../bin
java Main
```

## 4. Penjelasan Alur Program

1. Saat dijalankan, program menampilkan pesan selamat datang lalu masuk ke **Menu Utama** yang berisi 4 pilihan:
   1. Menu Data Orang Tua
   2. Menu Data Anak
   3. Menu Catatan Harian
   4. Keluar
2. Menu Utama akan terus muncul kembali (perulangan `while`) selama pengguna belum memilih opsi **Keluar**.
3. Setiap pilihan menu (1–3) akan mengarahkan pengguna ke **submenu** masing-masing entitas, yang juga memiliki perulangannya sendiri agar pengguna bisa melakukan banyak operasi berturut-turut sebelum kembali ke Menu Utama:
   - **Menu Data Orang Tua**: Tambah, Tampilkan Semua, Update, Hapus, Kembali.
   - **Menu Data Anak**: Daftarkan Anak Baru, Tampilkan Semua, Update, Hapus, Kembali.
   - **Menu Catatan Harian**: Input Laporan Harian, Lihat Riwayat Aktivitas Anak, Edit Laporan, Hapus Laporan, Kembali.
4. Pemilihan menu dilakukan sepenuhnya melalui **inputan angka** dari keyboard, diproses menggunakan struktur percabangan `switch-case`.
5. Setiap operasi Create/Update akan meminta input data satu per satu, dan setiap input divalidasi sebelum diterima oleh sistem (lihat bagian Nilai Tambah).
6. Relasi antar data dijaga secara sederhana:
   - Anak tidak bisa didaftarkan jika `idOrangTua` yang dimasukkan belum terdaftar di data OrangTua.
   - Catatan Harian tidak bisa dibuat jika `idAnak` yang dimasukkan belum terdaftar di data Anak.
7. Fungsi **Read** menampilkan data menggunakan perulangan `for`, baik untuk menampilkan seluruh daftar (anak/orang tua) maupun untuk menampilkan riwayat aktivitas harian milik seorang anak tertentu.
8. Program hanya berhenti ketika pengguna memilih menu **Keluar (4)** pada Menu Utama.

## 5. Penjelasan Penerapan Ketentuan Umum

| Ketentuan | Lokasi Penerapan |
|---|---|
| Minimal 3 class di luar entry point | `OrangTua`, `Anak`, `CatatanHarian` (model), `OrangTuaService`, `AnakService`, `CatatanHarianService` (service), `InputValidator` (util) — total 7 class di luar `Main` |
| Minimal 3 properties pada class entitas | `Anak` memiliki 5 atribut, `OrangTua` 4 atribut, `CatatanHarian` 4 atribut |
| Constructor | Setiap class model (`OrangTua`, `Anak`, `CatatanHarian`) memiliki constructor untuk inisialisasi objek |
| ArrayList | `ArrayList<OrangTua>`, `ArrayList<Anak>`, `ArrayList<CatatanHarian>` pada masing-masing class service |
| Percabangan menu | `switch-case` pada `Main.java` untuk memilih menu utama dan submenu |
| Pemilihan menu via inputan | Menggunakan `Scanner` dan `InputValidator.getMenuChoice()` |
| Perulangan agar program tidak berhenti | `while (berjalan)` pada `Main.java` (menu utama) dan `while (!kembali)` pada tiap submenu |
| Perulangan menampilkan data | `for` loop pada method `tampilkanSemuaOrangTua()`, `tampilkanSemuaAnak()`, dan `tampilkanRiwayatAnak()` |

## 6. Penjelasan Penerapan Nilai Tambah

### a. Access Modifier
Seluruh atribut pada class model (`OrangTua`, `Anak`, `CatatanHarian`) dideklarasikan sebagai `private`, sedangkan method yang perlu diakses dari luar class dideklarasikan `public`. Pada `Main.java`, method-method submenu (`menuOrangTua`, `menuAnak`, `menuCatatanHarian`, `tampilkanMenuUtama`) dideklarasikan `private static` karena hanya digunakan secara internal di dalam class `Main`.

### b. Encapsulation
Setiap class model menyembunyikan atributnya (`private`) dan hanya mengizinkan akses melalui `getter` dan `setter` publik. Contoh: atribut `catatanKesehatan` pada class `Anak` tidak bisa diubah langsung dari luar class, melainkan harus melalui `getCatatanKesehatan()` / `setCatatanKesehatan()`. Hal ini menjaga integritas data agar tidak diubah secara sembarangan dari class lain.

### c. Validasi Input
Seluruh input dari pengguna divalidasi melalui class `InputValidator` sebelum digunakan, di antaranya:
- **Input tidak boleh kosong** (`ambilTeksWajibIsi`) — digunakan untuk nama, alamat, ID, aktivitas, dll.
- **Angka positif** (`ambilAngkaPositif`) — digunakan untuk validasi umur anak, menolak input huruf atau angka negatif/nol.
- **Nomor HP** (`ambilNomorHp`) — memastikan input berupa angka murni dengan panjang 8–15 digit, lalu dikonversi ke tipe `long`.
- **Format tanggal** (`ambilTanggal`) — memastikan tanggal diinput dengan format `YYYY-MM-DD`.
- **Pilihan menu dalam rentang valid** (`ambilPilihanMenu`) — menolak input huruf atau angka di luar rentang pilihan menu yang tersedia.

Selain itu, terdapat validasi logika bisnis pada lapisan *service*, seperti:
- Mencegah pendaftaran ID yang sudah dipakai (ID Orang Tua/Anak harus unik).
- Mencegah pendaftaran anak dengan `idOrangTua` yang belum terdaftar.
- Mencegah pembuatan catatan harian dengan `idAnak` yang belum terdaftar.
- Menampilkan pesan yang jelas apabila data yang dicari (untuk update/hapus/lihat riwayat) tidak ditemukan.

## 7. Catatan Tambahan

- Data hanya tersimpan selama program berjalan (in-memory dengan `ArrayList`), sehingga akan hilang setiap kali program ditutup. Ini sesuai dengan cakupan tugas minpro yang berfokus pada penerapan konsep PBO, bukan persistensi data.
- ID Catatan Harian (`idCatatan`) dibuat otomatis oleh sistem (auto-increment, format `C1`, `C2`, dst.) agar pengasuh tidak perlu menghafal/mengetik ID secara manual.
