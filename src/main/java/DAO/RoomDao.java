package DAO;

import Model.RoomEntity;
import config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class RoomDao implements IDao<RoomEntity> {

    private EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
    private static RoomDao roomDao = null;

    private RoomDao() {
    }

    public static RoomDao getInstance() {
        if (roomDao == null) {
            roomDao = new RoomDao();
        }
        return roomDao;
    }

    @Override
    public List<RoomEntity> getAll() {
        try (EntityManager em = emf.createEntityManager()) {
            List<RoomEntity> locationEntities = em.createQuery("SELECT w FROM RoomEntity w", RoomEntity.class).getResultList();
            return locationEntities;
        }
    }

    @Override
    public RoomEntity getById(Integer id) {
        try (EntityManager em = emf.createEntityManager()) {
            RoomEntity getRoom = em.find(RoomEntity.class, id);
            return getRoom;
        }
    }


    @Override
    public RoomEntity create(RoomEntity roomEntity) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(roomEntity);
            em.getTransaction().commit();
            return roomEntity;
        }
    }

    @Override
    public RoomEntity update(RoomEntity roomEntity) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(roomEntity);
            em.getTransaction().commit();
            return roomEntity;
        }
    }

    @Override
    public void delete(Integer id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            RoomEntity room = getById(id);
            if (room != null) {
                em.remove(room);
            }
            em.getTransaction().commit();

        }
    }
}
