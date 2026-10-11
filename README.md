# Minpro-3-PBO-ManajemenDaycare

## Brendhen Canafaro Lie 2509116033 (Kelas A)

---

## 1. Deskripsi Singkat Program

Program ini adalah aplikasi **CRUD (Create, Read, Update, Delete)** berbasis konsol (command line) yang mensimulasikan sistem rekap digital tempat penitipan anak (daycare). Program ini dibuat menggunakan bahasa **Java** dengan menerapkan konsep **Pemrograman Berorientasi Objek (PBO)**.

Program ini mengelola tiga entitas utama:

1. **OrangTua** — data wali yang mendaftarkan atau menjemput anak (`idOrangTua`, `namaOrangTua`, `noHp` bertipe `long`, `alamat`). Catatan: karena `noHp` disimpan sebagai tipe angka, angka `0` di paling depan nomor HP (misal `081234567890`) akan otomatis hilang menjadi `81234567890`.
2. **Anak** — data anak yang dititipkan di daycare (`idAnak`, `namaAnak`, `umur`, `catatanKesehatan`), yang juga direlasikan ke `idOrangTua` sebagai wali penanggung jawabnya.
3. **CatatanHarian** — jurnal harian yang diisi pengasuh untuk memantau kegiatan anak selama berada di daycare (`idCatatan`, `idAnak`, `tanggal`, `aktivitas`).

Program ini adalah pengembangan dari Mini Project 2, dengan tambahan abstract class, abstract method, polymorphism, dan Interface.

---

## 2. Struktur Program

<p align="center">
    <img width="319" height="399" alt="image" src="https://github.com/user-attachments/assets/5123c08f-e9d2-4381-985d-d0c92b581e01" />
</p>

| Package | Fungsi |
|---------|--------|
| `<default package>` | Berisi `Main.java`, titik awal program. Membuat objek Controller dan View, lalu menampilkan menu utama. |
| `model` | Berisi class data: `Orang` (abstract class), `OrangTua`, `Anak`, `CatatanHarian`, dan interface `Informasi`. |
| `view` | Menampilkan menu, meminta input dari pengguna, dan mencetak hasil. Berisi `OrangTuaView`, `AnakView`, `CatatanHarianView`, dan interface `MenuView`. |
| `controller` | Menyimpan data di `ArrayList` dan memproses tambah, cari, ubah, hapus. Tidak mencetak apa pun ke layar. Berisi `OrangTuaController`, `AnakController`, `CatatanHarianController`, dan interface `ManajemenData`. |
| `helper` | Berisi `InputValidator` untuk validasi semua input pengguna. |

---

## 3. Penjelasan Alur Program

1. Saat dijalankan, program menampilkan pesan selamat datang lalu masuk ke **Menu Utama** yang berisi 4 pilihan:

   <p align="center">
       <img width="377" height="182" alt="image" src="https://github.com/user-attachments/assets/2f2d0b73-8d6c-466a-865f-4a889cf99aff" />
   </p>

2. Menu Utama akan terus muncul kembali (perulangan `while`) selama pengguna belum memilih opsi **Keluar**.

3. Setiap pilihan menu (1–3) akan mengarahkan pengguna ke **submenu** masing-masing entitas, yang juga memiliki perulangannya sendiri agar pengguna bisa melakukan banyak operasi berturut-turut sebelum kembali ke Menu Utama:

   - **Menu Data Orang Tua**: Tambah, Tampilkan Semua, Update, Hapus, Kembali.

     <p align="center">
         <img width="250" height="130" alt="image" src="https://github.com/user-attachments/assets/2ddc222f-b49c-4f67-b1c9-db03e53553f2" />
     </p>

   - **Menu Data Anak**: Daftarkan Anak Baru, Tampilkan Semua, Update, Hapus, Kembali.

     <p align="center">
         <img width="222" height="121" alt="image" src="https://github.com/user-attachments/assets/5bab9e2e-3f42-4938-b5c2-ec661a33c25f" />
     </p>

   - **Menu Catatan Harian**: Input Laporan Harian, Lihat Riwayat Aktivitas Anak, Edit Laporan, Hapus Laporan, Kembali.

     <p align="center">
         <img width="328" height="150" alt="image" src="https://github.com/user-attachments/assets/592fb342-b1c3-40e3-94c1-981a50f1a7d7" />
     </p>

4. Pemilihan menu dilakukan sepenuhnya melalui **inputan angka** dari keyboard, diproses menggunakan struktur percabangan `switch-case`.

5. Setiap operasi Create/Update akan meminta input data satu per satu, dan setiap input divalidasi sebelum diterima oleh sistem.

   <p align="center">
       <img width="350" height="163" alt="image" src="https://github.com/user-attachments/assets/8b4bf867-dea2-46d8-baf1-afb96b06a0b8" />
   </p>

6. Relasi antar data dijaga secara sederhana:

   - Anak tidak bisa didaftarkan jika `idOrangTua` yang dimasukkan belum terdaftar di data OrangTua.
   - Catatan Harian tidak bisa dibuat jika `idAnak` yang dimasukkan belum terdaftar di data Anak.

7. Fungsi **Read** menampilkan data menggunakan perulangan `for`, baik untuk menampilkan seluruh daftar (anak/orang tua) maupun untuk menampilkan riwayat aktivitas harian milik seorang anak tertentu.

   <p align="center">
       <img width="752" height="63" alt="image" src="https://github.com/user-attachments/assets/216e99ef-23fe-4427-aee0-a470c9a0ebc6" />
   </p>

8. Program hanya berhenti ketika pengguna memilih menu **Keluar (4)** pada Menu Utama.

   <p align="center">
       <img width="529" height="241" alt="image" src="https://github.com/user-attachments/assets/5f666c17-6166-4b17-b965-838b406277dc" />
   </p>

---

## 4. Penjelasan Penerapan Encapsulation, Inheritance

### a. Encapsulation

Setiap class model menyembunyikan atributnya (`private`) dan hanya mengizinkan akses melalui `getter` dan `setter` publik. Contoh: atribut `catatanKesehatan` pada class `Anak` tidak bisa diubah langsung dari luar class, melainkan harus melalui `getCatatanKesehatan()` / `setCatatanKesehatan()`. Hal ini menjaga integritas data agar tidak diubah secara sembarangan dari class lain.

<p align="center">
    <img width="276" height="65" alt="image" src="https://github.com/user-attachments/assets/577dfade-2b06-4382-80a2-a40e0a617405" />
</p>

<p align="center">
    <img width="496" height="81" alt="image" src="https://github.com/user-attachments/assets/d8c247ea-0805-4f58-9b5f-63669904ac66" />
</p>

<p align="center">
    <img width="453" height="99" alt="image" src="https://github.com/user-attachments/assets/a2c071d9-1d52-49ef-b133-0a6abda79404" />
</p>

<p align="center">
    <img width="537" height="59" alt="image" src="https://github.com/user-attachments/assets/c5429180-4a85-4f31-8184-02b60960da69" />
</p>

### b. Inheritance

Proyek ini mengimplementasikan konsep **Inheritance** (Pewarisan Sifat) dalam Pemrograman Berbasis Objek (PBO). Konsep ini memungkinkan kelas anak (*Subclass*) untuk mewarisi atribut dari kelas induk (*Superclass*).

<p align="center">
    <img width="440" height="338" alt="image" src="https://github.com/user-attachments/assets/9164a051-1fad-4f80-91db-d00c7535886b" />
</p>

1. **Superclass (`Orang`)**
   - Merupakan kelas induk utama yang menyimpan atribut umum.
   - Atribut: `id`, `nama`.

2. **Subclass 1 (`OrangTua`) — `extends Orang`**
   - Mewarisi atribut `id` dan `nama` dari kelas `Orang`.
   - Memiliki atribut khusus: `noHp`, `alamat`.

3. **Subclass 2 (`Anak`) — `extends Orang`**
   - Mewarisi atribut `id` dan `nama` dari kelas `Orang`.
   - Memiliki atribut khusus: `catatanKesehatan`, `idOrangTua`.

---

## 5. Penjelasan Penerapan Polymorphism, Abstract

### a. Polymorphism

**Method Overriding**

Method di superclass atau interface ditulis ulang di subclass dengan isi yang berbeda.

| Method | Lokasi asal | Di-override di | Hasil |
|--------|-------------|----------------|-------|
| `getPeran()` | `Orang` (abstract) | `OrangTua`, `Anak` | Mengembalikan "Orang Tua" atau "Anak" |
| `toString()` | `Orang` | `OrangTua`, `Anak` | Menambahkan data khusus tiap class (No HP, alamat, umur, dst.) |
| `getRingkasan()` | `Informasi` (interface) | `Anak`, `OrangTua`, `CatatanHarian` | Ringkasan data dengan format berbeda-beda |

**1. Interface (`Informasi`)**

<p align="center">
    <img width="265" height="60" alt="image" src="https://github.com/user-attachments/assets/11b197a8-12ee-40fc-858e-a0a853142892" />
</p>

**2. Superclass (`Orang`)**

<p align="center">
    <img width="600" height="161" alt="image" src="https://github.com/user-attachments/assets/d2093cde-7ee5-4239-99fc-f32bb2976e89" />
</p>

**3. Subclass 1 (`OrangTua`)**

<p align="center">
    <img width="691" height="331" alt="image" src="https://github.com/user-attachments/assets/2aa80b84-bb05-485b-ad01-117f77183ec5" />
</p>

**4. Subclass 2 (`Anak`)**

<p align="center">
    <img width="839" height="352" alt="image" src="https://github.com/user-attachments/assets/88008990-579f-429e-bb01-93b839764762" />
</p>

**5. CatatanHarian**

<p align="center">
    <img width="732" height="225" alt="image" src="https://github.com/user-attachments/assets/c148d6db-25bf-42f0-b882-e119594ad08f" />
</p>

**Method Overloading**

Nama method sama, tetapi parameternya berbeda. Ada di `CatatanHarianController.java`:

- `getRiwayat(String idAnak)` menampilkan semua riwayat satu anak (Menu Catatan Harian nomor 2).

<p align="center">
    <img width="545" height="206" alt="image" src="https://github.com/user-attachments/assets/2e433abe-666c-41ec-8164-63404a0f6e6c" />
</p>

- `getRiwayat(String idAnak, String tanggal)` menampilkan riwayat anak pada tanggal tertentu (Menu Catatan Harian nomor 3).

<p align="center">
    <img width="749" height="206" alt="image" src="https://github.com/user-attachments/assets/ecfa3a21-efa4-4268-9982-62a46ce982c4" />
</p>

### b. Abstract

`Orang` dibuat sebagai **abstract class** karena di sistem ini tidak ada "orang" yang berdiri sendiri, yang ada hanya orang tua atau anak. Karena abstract, `new Orang(...)` tidak bisa dilakukan dan class ini hanya dipakai sebagai superclass.

<p align="center">
    <img width="280" height="24" alt="image" src="https://github.com/user-attachments/assets/1ba54035-a35c-4b81-9353-4ec15824f96b" />
</p>

Di dalamnya ada **abstract method** `getPeran()` yang tidak punya isi. `OrangTua` dan `Anak` wajib meng-override-nya dengan isi masing-masing. `toString()` di `Orang` adalah method biasa yang memanggil `getPeran()`, sehingga hasilnya menyesuaikan subclass-nya.


**1. Method Abstract Orang**


<img width="324" height="52" alt="image" src="https://github.com/user-attachments/assets/b3016d2e-5866-49a4-905c-e0433c59fdf8" />

===

**2. Method Abstract Anak**

<img width="378" height="102" alt="image" src="https://github.com/user-attachments/assets/a4686761-3f8c-4911-ab9c-31e7a039a257" />

===

**3. Method Abstract OrangTua**

<img width="375" height="102" alt="image" src="https://github.com/user-attachments/assets/880b88a2-09fc-457a-a178-c523d03679d8" />

---
## 6. Penjelasan Penerapan JDBC


---
## 7. Penjelasan Framework ORM


---

## 8. Penjelasan Penerapan Interface


Interface adalah nilai tambah pada project ini. Ada 3 interface, masing-masing berada di package sesuai fungsinya:

| Interface | Lokasi | Diimplementasikan oleh | Method yang diwajibkan |
|-----------|--------|------------------------|------------------------|
| `Informasi` | `model` | `Anak`, `OrangTua`, `CatatanHarian` | `getRingkasan()` |
| `ManajemenData` | `controller` | `OrangTuaController`, `AnakController`, `CatatanHarianController` | `getJumlahData()`, `isKosong()` |
| `MenuView` | `view` | `OrangTuaView`, `AnakView`, `CatatanHarianView` | `tampilkanMenu(Scanner)` |

Penggunaannya di program:

- `getRingkasan()` dipanggil di View untuk menampilkan ringkasan data setelah berhasil ditambahkan.
- `isKosong()` dipanggil di `AnakView` dan `CatatanHarianView` untuk mengecek apakah data orang tua atau anak sudah ada sebelum menambah anak atau catatan baru.
- `MenuView` dipakai di `Main` untuk memanggil menu ketiga View secara polimorfik.


**1. Informasi**

<img width="717" height="104" alt="image" src="https://github.com/user-attachments/assets/66e95cb5-5dd5-4ea1-9313-f16fcd8bcb69" />

<p align="center">
    Contoh Penggunaan:
</p>

<img width="596" height="109" alt="image" src="https://github.com/user-attachments/assets/7aa10a5a-bc60-43da-aed7-da463e014c51" />

===

**2. MenuView**

<img width="373" height="65" alt="image" src="https://github.com/user-attachments/assets/c70bca84-ae78-43dd-a167-7df2185d2501" />


<p align="center">
    Contoh Penggunaan:
</p>

<img width="417" height="83" alt="image" src="https://github.com/user-attachments/assets/49cc0715-62b6-4264-b6e9-f477c211720f" />

===

**3. ManajemenData**

<img width="298" height="108" alt="image" src="https://github.com/user-attachments/assets/aa274a48-4bb6-4b43-886f-a3cf7bc126e9" />


<p align="center">
    Contoh Penggunaan:
</p>

<img width="367" height="115" alt="image" src="https://github.com/user-attachments/assets/0a0d9dac-6256-404b-b76b-f239435942b8" />

