# Minpro-4-PBO-ManajemenDaycare

## Brendhen Canafaro Lie 2509116033 (Kelas A)

---

## 1. Deskripsi Singkat Program

Program ini adalah aplikasi **CRUD (Create, Read, Update, Delete)** berbasis konsol (command line) yang mensimulasikan sistem rekap digital tempat penitipan anak (daycare). Program ini dibuat menggunakan bahasa **Java** dengan menerapkan konsep **Pemrograman Berorientasi Objek (PBO)**.

Program ini mengelola tiga entitas utama:

1. **OrangTua** — data wali yang mendaftarkan atau menjemput anak (`idOrangTua`, `namaOrangTua`, `noHp` bertipe `long`, `alamat`). Catatan: karena `noHp` disimpan sebagai tipe angka, angka `0` di paling depan nomor HP (misal `081234567890`) akan otomatis hilang menjadi `81234567890`.
2. **Anak** — data anak yang dititipkan di daycare (`idAnak`, `namaAnak`, `umur`, `catatanKesehatan`), yang juga direlasikan ke `idOrangTua` sebagai wali penanggung jawabnya.
3. **CatatanHarian** — jurnal harian yang diisi pengasuh untuk memantau kegiatan anak selama berada di daycare (`idCatatan`, `idAnak`, `tanggal`, `aktivitas`).

Program ini adalah pengembangan dari Mini Project 3, dengan tambahan JDBC, dan ORM.

---

## 2. Struktur Program

<p align="center">
    <img width="293" height="554" alt="image" src="https://github.com/user-attachments/assets/1143f4a4-7800-438a-96a6-ca303a9cb9c2" />
    <img width="212" height="204" alt="image" src="https://github.com/user-attachments/assets/24848759-f81a-4c27-8800-8b9baa2896eb" />
</p>

| Package | Fungsi |
|---|---|
| `model` | Menyimpan struktur data (entitas) |
| `dao` | **Satu-satunya** tempat yang berhubungan langsung dengan database (query SQL / Hibernate) |
| `controller` | Logika CRUD; memanggil DAO dan tidak menampilkan apa pun ke layar |
| `view` | Menampilkan menu, membaca input, dan menampilkan hasil |
| `helper` | Fungsi bantu validasi input |

---

## 3. Penjelasan Alur Program

1. Saat dijalankan, program menampilkan pesan selamat datang lalu masuk ke **Menu Utama** yang berisi 4 pilihan:

   <p align="center">
       <img width="360" height="195" alt="image" src="https://github.com/user-attachments/assets/d236f25b-764f-4ec6-ae6f-bbd7a3ef25fe"/>
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

   - **Menu ORM (Hibernate)**: 
<p align="center">
         <img width="318" height="109" alt="image" src="https://github.com/user-attachments/assets/ba035ebb-bbe7-4a56-bb8a-f123c2d20f1a" />
     </p>
     


3. Pemilihan menu dilakukan sepenuhnya melalui **inputan angka** dari keyboard, diproses menggunakan struktur percabangan `switch-case`.

4. Setiap operasi Create/Update akan meminta input data satu per satu, dan setiap input divalidasi sebelum diterima oleh sistem.

   <p align="center">
       <img width="350" height="163" alt="image" src="https://github.com/user-attachments/assets/8b4bf867-dea2-46d8-baf1-afb96b06a0b8" />
   </p>

5. Relasi antar data dijaga secara sederhana:

   - Anak tidak bisa didaftarkan jika `idOrangTua` yang dimasukkan belum terdaftar di data OrangTua.
   - Catatan Harian tidak bisa dibuat jika `idAnak` yang dimasukkan belum terdaftar di data Anak.

6. Fungsi **Read** menampilkan data menggunakan perulangan `for`, baik untuk menampilkan seluruh daftar (anak/orang tua) maupun untuk menampilkan riwayat aktivitas harian milik seorang anak tertentu.

   <p align="center">
       <img width="752" height="63" alt="image" src="https://github.com/user-attachments/assets/216e99ef-23fe-4427-aee0-a470c9a0ebc6" />
   </p>

7. Program hanya berhenti ketika pengguna memilih menu **Keluar (5)** pada Menu Utama.

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

### a. Koneksi ke Database

Program terhubung ke database **MySQL (`daycare_db`)** menggunakan **JDBC**. Koneksi dibuat di satu tempat saja, yaitu class `Koneksi.java` pada package `dao`, lalu dipakai oleh semua DAO.

<p align="center">
    <img width="213" height="130" alt="image" src="https://github.com/user-attachments/assets/238f0769-4db0-4c62-b0fd-b933f92187d9" />
</p>


### b. Pola DAO (Data Access Object)

Setiap tabel punya satu class DAO yang menjadi **satu-satunya tempat query SQL**:

| Tabel | DAO | Method |
|---|---|---|
| `orang_tua` | `OrangTuaDAO` | `tambah`, `semua`, `cari`, `perbarui`, `hapus` |
| `anak` | `AnakDAO` | `tambah`, `semua`, `cari`, `perbarui`, `hapus` |
| `catatan_harian` | `CatatanHarianDAO` | `tambah`, `semua`, `cari`, `cariPerAnak`, `cariPerAnakDanTanggal`, `perbarui`, `hapus` |

Controller **tidak menulis SQL**, hanya memanggil DAO. View **tidak tahu soal database**, hanya memanggil Controller. Dengan begitu setiap lapisan punya tugas yang jelas.

Alur satu operasi (contoh: menambah anak):

```
AnakView  ->  AnakController  ->  AnakDAO  ->  Koneksi  ->  MySQL
(input)      (logika)           (SQL)       (JDBC)
```

<p align="center">
    <img width="1040" height="219" alt="image" src="https://github.com/user-attachments/assets/5e4bb74d-8add-448b-bfbe-228116507959" />
</p>

### c. Prepared Statement

Semua query memakai **`PreparedStatement`**. Nilai tidak digabung langsung ke string SQL, tetapi diisi lewat tanda `?` dengan `setString()` / `setInt()`. Cara ini membuat program **aman dari SQL Injection** dan kode lebih rapi.

Contoh **CREATE** di `AnakDAO.java`:

<p align="center">
    <img width="521" height="242" alt="image" src="https://github.com/user-attachments/assets/24df053d-170a-44bd-80fe-25a90925d94e" />
</p>

Contoh **READ** (mengambil data dan mengubahnya menjadi objek `Anak`):


<p align="center">
    <img width="891" height="204" alt="image" src="https://github.com/user-attachments/assets/efc12361-febf-4b84-9bb4-579e173a8177" />
</p>

Contoh **UPDATE** dan **DELETE**:

<p align="center">
    <img width="891" height="154" alt="image" src="https://github.com/user-attachments/assets/a7aec798-4e8e-4bb0-9021-dd17140fe6e8" />
    <img width="858" height="253" alt="image" src="https://github.com/user-attachments/assets/8922ba53-9795-4a2e-9569-73d610dcece1" />
</p>


### d. Dummy Data Awal Lewat File SQL

Data awal **tidak diisi lewat kode Java**, tetapi lewat file **`daycare_db.sql`** yang ada di repository. File ini membuat database, 3 tabel (`orang_tua`, `anak`, `catatan_harian`) beserta relasi foreign key, lalu mengisi data awal (2 orang tua, 3 anak, dan 3 catatan harian). Karena itu, begitu program dijalankan dan menu *Tampilkan* dibuka, data langsung muncul tanpa input dari awal.

<p align="center">
    <img width="645" height="142" alt="image" src="https://github.com/user-attachments/assets/9c1c5648-aff4-4943-8d7c-01b0d76aefe3" />
</p>

<p align="center">
    <img width="686" height="106" alt="image" src="https://github.com/user-attachments/assets/a015e4a7-1fa5-44e3-bd0d-ec5fae9a2f09" />
</p>

<p align="center">
    <img width="745" height="208" alt="image" src="https://github.com/user-attachments/assets/809c5e8c-ca8d-491e-9360-49ecbb37aa43" />
</p>


---
## 7. Penjelasan Framework ORM

### a. Apa itu ORM?

ORM (Object Relational Mapping) adalah cara untuk menghubungkan class di Java dengan tabel di database. Jadi kita bisa ambil dan ubah data lewat objek, tidak perlu nulis query SQL sendiri. Framework ORM yang saya pakai di program ini adalah Hibernate.

Contohnya di program ini:
- class `Anak` terhubung ke tabel `anak`
- atribut `catatanKesehatan` terhubung ke kolom `catatan_kesehatan`
- satu objek `Anak` mewakili satu baris data di tabel

Tulisan `@Entity`, `@Column`, dan sejenisnya yang ada di kode adalah anotasi dari Jakarta Persistence. Anotasi itu dibaca Hibernate untuk tahu class mana yang jadi tabel mana. Hibernate sendiri tetap memakai JDBC untuk berhubungan dengan MySQL.

Di program ini CRUD utama (menu 1 sampai 3) tetap memakai JDBC dan DAO. ORM saya pakai di menu 4 (Menu ORM) untuk data anak.

### b. Dependency

Hibernate ditambahkan lewat `pom.xml`:

<p align="center">
    <img width="404" height="93" alt="image" src="https://github.com/user-attachments/assets/4816e59d-ff6a-4fc9-8f2e-963c64f200f0" />
</p>

### c. Konfigurasi

Pengaturan Hibernate ada di file `hibernate.cfg.xml` (folder `src/main/resources`). Isinya alamat database, username, password, dan class yang dipetakan.

<p align="center">
    <img width="841" height="217" alt="image" src="https://github.com/user-attachments/assets/756ebfb1-e985-47c1-9046-91c7ed31cac2" />
</p>

### d. Class Anak sebagai Entity

Di `Anak.java` ditambahkan anotasi supaya class ini terhubung ke tabel `anak`:


<p align="center">
    <img width="601" height="277" alt="image" src="https://github.com/user-attachments/assets/34b36051-e5d8-4b65-9adc-cdd4711fab40" />
</p>

Atribut `id` dan `nama` ada di class `Orang` (superclass), jadi di `Orang.java` dipakai `@MappedSuperclass` supaya atribut itu ikut masuk ke tabel `anak`. Karena nama kolom di tabel adalah `id_anak` sedangkan atributnya `id`, dipakai `@AttributeOverride` untuk menyambungkannya.

<p align="center">
    <img width="401" height="166" alt="image" src="https://github.com/user-attachments/assets/3c50381a-1cb2-41a6-b89d-63ddd0eb2561" />
</p>

Arti anotasi yang dipakai:
- `@Entity`: class ini dipetakan ke tabel
- `@Table`: nama tabelnya
- `@Column`: nama kolomnya
- `@Id`: primary key
- `@MappedSuperclass`: atribut superclass ikut dipetakan ke tabel subclass
- `@AttributeOverride`: mengganti nama kolom untuk atribut milik superclass

### e. HibernateUtil

Class `HibernateUtil` membaca `hibernate.cfg.xml` lalu membuat `SessionFactory`. Dari `SessionFactory` ini nanti kita buka `Session`, yang fungsinya mirip `Connection` di JDBC.

<p align="center">
    <img width="748" height="272" alt="image" src="https://github.com/user-attachments/assets/aefc5ee0-7c99-418b-a621-aaf9b0b2aa9f" />
</p>

### f. Cara Kerja di AnakOrmDAO

Di class ini tidak ada query SQL yang ditulis manual. Ada tiga fitur:

**1. Tampilkan semua anak.** Memakai HQL, yaitu bahasa query Hibernate yang menulis nama class dan atribut Java, bukan nama tabel dan kolom.

<p align="center">
    <img width="871" height="70" alt="image" src="https://github.com/user-attachments/assets/e9018654-2082-47b2-bbc9-ed409ffaa5f8" />
</p>

Hasilnya langsung berupa list objek `Anak`, jadi tidak perlu membaca `ResultSet` satu per satu seperti di JDBC.

**2. Cari anak berdasarkan nama.** Nilai pencarian dikirim lewat parameter `:nama`.

<p align="center">
    <img width="840" height="80" alt="image" src="https://github.com/user-attachments/assets/ca960505-f720-47ca-be65-0a3f0c86718a" />
</p>


**3. Update catatan kesehatan.** Data diambil dulu sebagai objek, diubah dengan setter, lalu di-commit. Query `UPDATE` dibuat sendiri oleh Hibernate karena dia mendeteksi ada atribut yang berubah.

<p align="center">
    <img width="408" height="193" alt="image" src="https://github.com/user-attachments/assets/26bde91f-9367-4889-9a42-7935f0bb2ea7" />
</p>

Transaction dipakai supaya kalau ada error, perubahan bisa dibatalkan dengan `rollback()`.


### g. Letak ORM di Program

| File | Keterangan |
|---|---|
| `pom.xml` | dependency Hibernate |
| `resources/hibernate.cfg.xml` | konfigurasi dan mapping class |
| `model/Anak.java` | entity dengan anotasi ORM |
| `model/Orang.java` | `@MappedSuperclass` dan `@Id` |
| `dao/HibernateUtil.java` | membuat `SessionFactory` |
| `dao/AnakOrmDAO.java` | query dan update lewat Hibernate |
| `controller/AnakOrmController.java` | controller untuk fitur ORM |
| `view/AnakOrmView.java` | tampilan Menu ORM |
| `Main.java` | menambahkan menu 4 di menu utama |

### h. Perbedaan JDBC dan ORM di Program Ini

| | JDBC (`AnakDAO`) | ORM (`AnakOrmDAO`) |
|---|---|---|
| Query SQL | ditulis sendiri | dibuat Hibernate |
| Hasil query jadi objek | manual lewat `ambil(rs)` | otomatis |
| Koneksi | `Koneksi.getConnection()` | `Session` dari `SessionFactory` |
| Update data | tulis `UPDATE ... SET ...` | ubah lewat setter lalu `commit()` |

### i. Hasil Menjalankan Menu ORM


<p align="center">
    <img width="333" height="114" alt="image" src="https://github.com/user-attachments/assets/b3662403-5798-4a3f-b389-219ca1a644be" />
</p>

<p align="center">
    <img width="745" height="185" alt="image" src="https://github.com/user-attachments/assets/08982955-8713-4565-bcb0-2501e3601ae9" />
</p>

<p align="center">
    <img width="340" height="206" alt="image" src="https://github.com/user-attachments/assets/6367394c-00bd-450b-b7e9-8c3e85a1e847" />
</p>

Perubahan Ddidalam database:

<p align="center">
    <img width="685" height="93" alt="image" src="https://github.com/user-attachments/assets/6b0571bb-b134-4d8c-aaa0-965a953531a6" />
</p>




