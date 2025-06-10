package prova03.simulado.main;

import prova03.simulado.model.Bulletin;
import prova03.simulado.model.State;
import prova03.simulado.persistence.SqlBulletinDao;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SqlBulletinDao dao = new SqlBulletinDao();

        Bulletin sanca = new Bulletin(0, "Sanca", State.SC, 30, 1, 0.3, LocalDate.now());
        dao.insert(sanca);
        System.out.println("Boletim inserido.");

        List<Bulletin> all = dao.findAll();
        all.forEach(System.out::println);

        Bulletin inserted = all.getLast();
        Integer id = inserted.getId();

        inserted.setCity("Sanca City");
        inserted.setDate(LocalDate.now().minusDays(1));
        inserted.setState(State.SC);
        inserted.setInfected(200);
        inserted.setDeaths(20);
        inserted.setIcuRatio(1.1);
        dao.update(inserted);
        System.out.println("Boletim atualizado.");

        boolean exists = dao.existById(id);
        System.out.println("Boletim existe? " + exists);

        List<Bulletin> updatedList = dao.findAll();
        System.out.println("Boletins no banco:");
        updatedList.forEach(System.out::println);

        dao.delete(id);
        System.out.println("Boletim removido.");

        boolean stillExists = dao.existById(id);
        System.out.println("Ainda existe? " + stillExists);
    }
}
