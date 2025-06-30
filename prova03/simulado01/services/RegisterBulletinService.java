package prova03.simulado01.services;

import prova03.simulado01.exceptions.EntityAlreadyExistsException;
import prova03.simulado01.model.Bulletin;
import prova03.simulado01.persistence.BulletinDao;

import java.util.Objects;

public class RegisterBulletinService {
    private final BulletinDao<Bulletin, Integer> bulletinDao;

    public RegisterBulletinService(BulletinDao<Bulletin, Integer> bulletinDao) {
        this.bulletinDao = bulletinDao;
    }

    public void register(Bulletin bulletin) {
        Objects.requireNonNull(bulletin, "Bulletin must not be null");

        if (bulletinDao.existsById(bulletin.getId())) {
            throw new EntityAlreadyExistsException("Bulletin already exists");
        }

        bulletinDao.insert(bulletin);
    }
}
