package controller;

import dao.AnakOrmDAO;
import model.Anak;

import java.util.ArrayList;

// Controller untuk fitur ORM pada data Anak. Data diambil lewat AnakOrmDAO (Hibernate).
public class AnakOrmController implements ManajemenData {
    private AnakOrmDAO dao = new AnakOrmDAO();

    public ArrayList<Anak> getDaftar() {
        return dao.semua();
    }

    public ArrayList<Anak> cariNama(String nama) {
        return dao.cariNama(nama);
    }

    public boolean perbaruiCatatanKesehatan(String id, String catatanBaru) {
        return dao.perbaruiCatatanKesehatan(id, catatanBaru);
    }

    // Implementasi interface ManajemenData
    @Override
    public int getJumlahData() {
        return getDaftar().size();
    }

    @Override
    public boolean isKosong() {
        return getJumlahData() == 0;
    }
}
