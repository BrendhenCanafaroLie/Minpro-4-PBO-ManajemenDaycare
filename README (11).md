# Minpro 4 - PBO - Sistem Manajemen Daycare (Penitipan Anak)

## 1. Deskripsi Singkat Program

Program ini adalah aplikasi **CRUD (Create, Read, Update, Delete)** berbasis konsol untuk mengelola data tempat penitipan anak (daycare). Dibuat dengan bahasa **Java** menggunakan konsep **Pemrograman Berorientasi Objek (PBO)**. Pada Mini Project 4, program yang sebelumnya menyimpan data di `ArrayList` dikembangkan agar **terhubung ke database MySQL** menggunakan **JDBC (Prepared Statement) dan pola DAO**, serta ditambah **framework ORM (Hibernate)** sebagai nilai tambah.

Program mengelola tiga data utama:

1. **OrangTua** — wali anak (`id_orang_tua`, `nama`, `no_hp`, `alamat`).
2. **Anak** — anak yang dititipkan (`id_anak`, `nama`, `umur`, `catatan_kesehatan`, `id_orang_tua`).
3. **CatatanHarian** — jurnal kegiatan harian anak (`id_catatan`, `id_anak`, `tanggal`, `aktivitas`). ID catatan dibuat otomatis oleh database (`AUTO_INCREMENT`).

Data awal (dummy data) diisi lewat file **`database/daycare_db.sql`**, bukan lewat kode, sehingga saat menu *Tampilkan* dijalankan data langsung muncul.

## 2. Cara Menjalankan

**Kebutuhan:** JDK 17+, Maven, MySQL.

1. Import database: `mysql -u root -p < database/daycare_db.sql` (atau lewat phpMyAdmin menu *Import*).
2. Sesuaikan username dan password MySQL di dua tempat:
   - `src/main/java/dao/Koneksi.java` (untuk JDBC)
   - `src/main/resources/hibernate.cfg.xml` (untuk Hibernate)
3. Jalankan dari folder project:
   ```bash
   mvn compile exec:java
   ```

## 3. Penjelasan Struktur Package

```
Minpro-4-PBO-ManajemenDaycare/
├── pom.xml                          # Dependency Maven (MySQL driver + Hibernate)
├── database/
│   └── daycare_db.sql               # Struktur tabel + dummy data awal
└── src/main/
    ├── java/
    │   ├── Main.java                # Entry point, menu utama
    │   ├── model/                   # Entitas / data
    │   │   ├── Orang.java           # Abstract class (induk)
    │   │   ├── OrangTua.java        # Subclass Orang
    │   │   ├── Anak.java            # Subclass Orang (juga entity ORM)
    │   │   ├── CatatanHarian.java
    │   │   └── Informasi.java       # Interface
    │   ├── dao/                     # Akses database
    │   │   ├── Koneksi.java         # Koneksi JDBC
    │   │   ├── OrangTuaDAO.java     # JDBC + Prepared Statement
    │   │   ├── AnakDAO.java         # JDBC + Prepared Statement
    │   │   ├── CatatanHarianDAO.java# JDBC + Prepared Statement
    │   │   ├── HibernateUtil.java   # ORM: pembuat SessionFactory
    │   │   └── AnakOrmDAO.java      # ORM: query lewat Hibernate
    │   ├── controller/              # Logika program
    │   │   ├── ManajemenData.java   # Interface
    │   │   ├── OrangTuaController.java
    │   │   ├── AnakController.java
    │   │   ├── CatatanHarianController.java
    │   │   └── AnakOrmController.java  # ORM
    │   ├── view/                    # Tampilan menu & input
    │   │   ├── MenuView.java        # Interface
    │   │   ├── OrangTuaView.java
    │   │   ├── AnakView.java
    │   │   ├── CatatanHarianView.java
    │   │   └── AnakOrmView.java     # ORM
    │   └── helper/
    │       └── InputValidator.java  # Validasi input
    └── resources/
        └── hibernate.cfg.xml        # Konfigurasi Hibernate
```

| Package | Fungsi |
|---|---|
| `model` | Menyimpan struktur data (entitas) |
| `dao` | **Satu-satunya** tempat yang berhubungan langsung dengan database (query SQL / Hibernate) |
| `controller` | Logika CRUD; memanggil DAO dan tidak menampilkan apa pun ke layar |
| `view` | Menampilkan menu, membaca input, dan menampilkan hasil |
| `helper` | Fungsi bantu validasi input |

## 4. Penjelasan Alur Program

1. Program dijalankan dari `Main`, yang membuat objek Controller dan View, lalu menampilkan **Menu Utama**:
   1. Menu Data Orang Tua
   2. Menu Data Anak
   3. Menu Catatan Harian
   4. Menu ORM (Hibernate)
   5. Keluar
2. Menu Utama terus muncul (`while`) sampai pengguna memilih **Keluar**.
3. Setiap pilihan 1-4 masuk ke submenu yang punya perulangan sendiri sampai pengguna memilih *Kembali*.
4. Alur satu operasi (contoh: tambah anak):
   `View` (input + validasi `InputValidator`) → `Controller` → `DAO` (Prepared Statement) → **MySQL**.
5. Saat membaca data, hasil query diubah DAO menjadi objek model, dikembalikan lewat Controller, lalu dicetak oleh View.
6. Relasi antar data dijaga:
   - Anak hanya bisa didaftarkan jika `id_orang_tua` sudah ada.
   - Catatan harian hanya bisa dibuat jika `id_anak` sudah ada.
   - Foreign key di database memakai `ON DELETE CASCADE`: jika orang tua dihapus, anaknya ikut terhapus; jika anak dihapus, catatannya ikut terhapus.
7. Semua input divalidasi `InputValidator`: tidak boleh kosong, angka positif (umur), nomor HP 8-15 digit, format tanggal `YYYY-MM-DD`, dan pilihan menu dalam rentang.

## 5. Penerapan Encapsulation dan Inheritance

### Encapsulation
- Semua atribut class model dibuat `private` (contoh: `umur`, `catatanKesehatan`, `idOrangTua` pada `Anak.java`) dan hanya bisa diakses lewat **getter/setter** `public`.
- Konfigurasi database pada `Koneksi.java` (`URL`, `USER`, `PASSWORD`) dibuat `private static final`.
- Method bantu seperti `ambil(ResultSet)` di setiap DAO dibuat `private` karena hanya dipakai di dalam class itu.
- Detail SQL disembunyikan di DAO, sehingga Controller dan View tidak tahu bagaimana data disimpan.

### Inheritance
- `Orang` adalah superclass yang menyimpan atribut `id` dan `nama`.
- `OrangTua` dan `Anak` adalah subclass (`extends Orang`). Keduanya memakai atribut dan method milik `Orang`, lalu menambah atribut sendiri (`noHp`, `alamat` pada `OrangTua`; `umur`, `catatanKesehatan`, `idOrangTua` pada `Anak`). Constructor subclass memanggil `super(id, nama)`.

## 6. Penerapan Polymorphism dan Abstraction

### Polymorphism
- **Overriding**: `getPeran()` di-override oleh `Anak` dan `OrangTua` (hasilnya berbeda), begitu juga `toString()` dan `getRingkasan()`.
- **Overloading**: `CatatanHarianController` memiliki dua method `getRiwayat`: `getRiwayat(idAnak)` dan `getRiwayat(idAnak, tanggal)`.
- **Polymorphism lewat interface**: di `Main.java`, semua View disimpan dalam satu array bertipe `MenuView[]`, lalu dipanggil dengan `daftarMenu[pilihan - 1].tampilkanMenu(scanner)`. Objek yang dijalankan menyesuaikan View yang dipilih.

### Abstraction
- **Abstract class** `Orang` tidak bisa dibuat objeknya langsung (`new Orang(...)` error) dan memiliki **abstract method** `getPeran()` yang wajib diisi subclass.
- **Interface** yang dipakai:
  - `Informasi` (`getRingkasan()`) — diimplementasikan `Anak`, `OrangTua`, `CatatanHarian`.
  - `ManajemenData` (`getJumlahData()`, `isKosong()`) — diimplementasikan semua Controller.
  - `MenuView` (`tampilkanMenu()`) — diimplementasikan semua View.

## 7. Penerapan JDBC

1. **Koneksi** — `dao/Koneksi.java` membuat koneksi ke MySQL dengan `DriverManager.getConnection(URL, USER, PASSWORD)` (database `daycare_db`).
2. **DAO (Data Access Object)** — setiap tabel punya satu DAO (`OrangTuaDAO`, `AnakDAO`, `CatatanHarianDAO`) yang berisi method `tambah`, `semua`, `cari`, `perbarui`, `hapus`. Controller memanggil DAO, tidak menulis SQL sendiri.
3. **Prepared Statement** — semua query memakai tanda `?` lalu diisi dengan `setString()` / `setInt()`, bukan digabung dengan operator `+`, sehingga aman dari SQL Injection. Contoh di `AnakDAO.java`:
   ```java
   String sql = "INSERT INTO anak (id_anak, nama, umur, catatan_kesehatan, id_orang_tua) VALUES (?, ?, ?, ?, ?)";
   try (Connection c = Koneksi.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
       ps.setString(1, a.getId());
       ps.setString(2, a.getNama());
       ps.setInt(3, a.getUmur());
       ...
       ps.executeUpdate();
   }
   ```
4. **try-with-resources** — `Connection`, `PreparedStatement`, dan `ResultSet` otomatis ditutup.
5. **Auto increment** — `CatatanHarianDAO.tambah()` memakai `Statement.RETURN_GENERATED_KEYS` untuk mengambil ID catatan yang dibuat database.
6. **Penanganan error** — `SQLException` ditangkap di Controller dan dibungkus menjadi `RuntimeException` dengan pesan yang jelas.
7. **Data awal lewat database** — dummy data (2 orang tua, 3 anak, 3 catatan harian) ada di `database/daycare_db.sql`, tidak ada data awal di dalam kode Java.

## 8. Penerapan Nilai Tambah: ORM (Hibernate)

**ORM (Object Relational Mapping)** memetakan class Java ke tabel database, sehingga data bisa diambil dan diubah sebagai objek **tanpa menulis SQL manual**. Framework yang dipakai adalah **Hibernate**.

Bagian JDBC pada poin 7 tetap dipakai untuk CRUD utama (sesuai ketentuan tugas). ORM ditambahkan sebagai **Menu 4 "Menu ORM (Hibernate)"** pada data **Anak**.

### Letak penerapan

| File | Peran ORM |
|---|---|
| `pom.xml` | Menambahkan dependency `hibernate-core` |
| `resources/hibernate.cfg.xml` | Konfigurasi koneksi Hibernate dan daftar class yang dipetakan (`<mapping class="model.Anak"/>`) |
| `model/Anak.java` | `@Entity`, `@Table(name = "anak")`, `@Column`, `@AttributeOverride` — memetakan class `Anak` ke tabel `anak` |
| `model/Orang.java` | `@MappedSuperclass` dan `@Id` — atribut `id` dan `nama` ikut dipetakan ke tabel subclass (inheritance tetap jalan) |
| `dao/HibernateUtil.java` | Membuat `SessionFactory` dari `hibernate.cfg.xml` |
| `dao/AnakOrmDAO.java` | Query lewat Hibernate (`Session`) |
| `controller/AnakOrmController.java` | Controller untuk fitur ORM (mengimplementasikan `ManajemenData`) |
| `view/AnakOrmView.java` | Tampilan menu ORM |
| `Main.java` | Menambahkan Menu 4 ke menu utama |

### Fitur Menu ORM

| Menu | Cara kerja di `AnakOrmDAO` |
|---|---|
| 1. Tampilkan semua anak | HQL `from Anak a order by a.id`, hasilnya langsung `List<Anak>` |
| 2. Cari anak berdasarkan nama | HQL dengan parameter `like :nama` |
| 3. Update catatan kesehatan | `session.get(Anak.class, id)`, ubah lewat `setCatatanKesehatan()`, lalu `commit()`. Hibernate otomatis membuat query `UPDATE` (dirty checking) |

### Perbandingan singkat

```java
// JDBC: SQL ditulis manual, hasil diubah ke objek manual
ps = c.prepareStatement("SELECT * FROM anak WHERE id_anak = ?");
ps.setString(1, id);
ResultSet rs = ps.executeQuery();
// ... new Anak(rs.getString(...), ...)

// ORM: cukup minta objeknya
Anak anak = session.get(Anak.class, id);
```

## 9. Catatan Tambahan

- Untuk menghapus data orang tua yang masih punya anak, database akan ikut menghapus data anak dan catatan hariannya (`ON DELETE CASCADE`).
- Mengimpor ulang `daycare_db.sql` akan mengembalikan seluruh data ke dummy data awal.
