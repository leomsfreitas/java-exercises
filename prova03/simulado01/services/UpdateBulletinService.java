package prova03.simulado01.services;

import prova03.simulado01.model.Bulletin;
import prova03.simulado01.persistence.BulletinDao;

import java.util.NoSuchElementException;
import java.util.Objects;

public class UpdateBulletinService {
    private final BulletinDao<Bulletin, Integer> bulletinDao;

    public UpdateBulletinService(BulletinDao<Bulletin, Integer> bulletinDao) {
        this.bulletinDao = bulletinDao;
    }

    public void update(Bulletin bulletin) {
        Objects.requireNonNull(bulletin, "Bulletin must not be null");

        if (!bulletinDao.existsById(bulletin.getId())) {
            throw new NoSuchElementException("Bulletin don't exist");
        }

        bulletinDao.update(bulletin.getId(), bulletin);
    }
}
