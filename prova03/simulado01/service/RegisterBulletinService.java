package prova03.simulado01.service;

import prova03.simulado01.model.Bulletin;
import prova03.simulado01.persistence.BulletinDao;
import prova03.simulado01.persistence.EntityAlreadyExistException;

import java.util.Objects;

public class RegisterBulletinService {
    private final BulletinDao<Bulletin, Integer> bulletinDao;

    public RegisterBulletinService(BulletinDao<Bulletin, Integer> bulletinDao) {
        this.bulletinDao = bulletinDao;
    }

    public void register(Bulletin bulletin) throws EntityAlreadyExistException {
        Objects.requireNonNull(bulletin, "Bulletin cannot be null.");

        if (bulletinDao.existById(bulletin.getId())) {
            throw new EntityAlreadyExistException("Entity already exists.");
        }

        bulletinDao.insert(bulletin);
    }
}
