package dao;

import model.Anak;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

// DAO versi ORM (Hibernate) untuk entitas Anak.
// Tidak ada SQL manual di sini: Hibernate yang membuat SQL-nya dari class Anak.
public class AnakOrmDAO {

    // Setara "SELECT * FROM anak", hasilnya langsung berupa objek Anak
    public ArrayList<Anak> semua() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return new ArrayList<>(session.createQuery("from Anak a order by a.id", Anak.class).list());
        }
    }

    // Mencari anak berdasarkan sebagian nama (HQL dengan parameter)
    public ArrayList<Anak> cariNama(String nama) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return new ArrayList<>(session
                    .createQuery("from Anak a where lower(a.nama) like :nama order by a.id", Anak.class)
                    .setParameter("nama", "%" + nama.toLowerCase() + "%")
                    .list());
        }
    }

    // Update lewat ORM: ambil objek, ubah nilainya lewat setter, lalu commit.
    // Hibernate otomatis membuat query UPDATE (dirty checking).
    // Mengembalikan false jika ID anak tidak ditemukan.
    public boolean perbaruiCatatanKesehatan(String id, String catatanBaru) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                Anak anak = session.get(Anak.class, id);
                if (anak == null) {
                    tx.rollback();
                    return false;
                }
                anak.setCatatanKesehatan(catatanBaru);
                tx.commit();
                return true;
            } catch (RuntimeException e) {
                tx.rollback();
                throw e;
            }
        }
    }
}
