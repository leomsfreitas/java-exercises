package prova03.simulado01.service;

import prova03.simulado01.model.Bulletin;
import prova03.simulado01.persistence.BulletinDao;

import java.util.Objects;
import java.util.NoSuchElementException;

public class RemoveBulletinService {
    private final BulletinDao<Bulletin, Integer> bulletinDao;

    public RemoveBulletinService(BulletinDao<Bulletin, Integer> bulletinDao) {
        this.bulletinDao = bulletinDao;
    }

    public void remove(Bulletin bulletin) {
        Objects.requireNonNull(bulletin, "Bulletin cannot be null.");

        if (!bulletinDao.existById(bulletin.getId())) {
            throw new NoSuchElementException("Entity not found.");
        }

        bulletinDao.delete(bulletin.getId());
    }
}
