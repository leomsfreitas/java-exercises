package prova03.simulado.service;

import prova03.simulado.model.Bulletin;
import prova03.simulado.persistence.BulletinDao;

import java.util.NoSuchElementException;
import java.util.Objects;

public class UpdateBulletinService {
    private final BulletinDao<Bulletin, Integer> bulletinDao;

    public UpdateBulletinService(BulletinDao<Bulletin, Integer> bulletinDao) {
        this.bulletinDao = bulletinDao;
    }

    public void update(Bulletin bulletin) {
        Objects.requireNonNull(bulletin, "Bulletin cannot be null.");

        if (!bulletinDao.existById(bulletin.getId())) {
            throw new NoSuchElementException("Entity not found.");
        }

        bulletinDao.update(bulletin);
    }
}