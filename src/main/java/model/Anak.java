package model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

// Subclass dari Orang.
// Menambah atribut umur, catatanKesehatan, dan idOrangTua.
//
// ORM: @Entity + @Table memetakan class ini ke tabel "anak".
// @AttributeOverride dipakai karena kolom id di tabel bernama "id_anak",
// sedangkan atribut id di superclass Orang bernama "id".
@Entity
@Table(name = "anak")
@AttributeOverride(name = "id", column = @Column(name = "id_anak"))
public class Anak extends Orang implements Informasi {
    @Column(name = "umur")
    private int umur;

    @Column(name = "catatan_kesehatan")
    private String catatanKesehatan;

    @Column(name = "id_orang_tua")
    private String idOrangTua;

    // Constructor kosong, dibutuhkan Hibernate
    protected Anak() {
    }

    public Anak(String id, String nama, int umur, String catatanKesehatan, String idOrangTua) {
        super(id, nama);
        this.umur = umur;
        this.catatanKesehatan = catatanKesehatan;
        this.idOrangTua = idOrangTua;
    }

    public int getUmur() {
        return umur;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }

    public String getCatatanKesehatan() {
        return catatanKesehatan;
    }

    public void setCatatanKesehatan(String catatanKesehatan) {
        this.catatanKesehatan = catatanKesehatan;
    }

    public String getIdOrangTua() {
        return idOrangTua;
    }

    public void setIdOrangTua(String idOrangTua) {
        this.idOrangTua = idOrangTua;
    }

    // Overriding abstract method dari Orang
    @Override
    public String getPeran() {
        return "Anak";
    }

    // Implementasi interface Informasi
    @Override
    public String getRingkasan() {
        return getNama() + " (" + getPeran() + ", " + umur + " tahun)";
    }

    // Overriding
    @Override
    public String toString() {
        return super.toString() + " | Umur: " + umur + " tahun | Kesehatan: " + catatanKesehatan
                + " | ID Orang Tua: " + idOrangTua;
    }
}
