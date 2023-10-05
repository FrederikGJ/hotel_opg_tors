package DAO;

import Model.HotelEntity;
import config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class HotelDao implements IDao<HotelEntity> {

    private EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
    private static HotelDao hotelDao = null;

    private HotelDao() {
    }

    public static HotelDao getInstance() {
        if (hotelDao == null) {
            hotelDao = new HotelDao();
        }
        return hotelDao;
    }

    @Override
    public List<HotelEntity> getAll() {
        try (EntityManager em = emf.createEntityManager()) {
            List<HotelEntity> hotelEntities = em.createQuery("SELECT h FROM HotelEntity h", HotelEntity.class).getResultList();
            return hotelEntities;
        }
    }

    @Override
    public HotelEntity getById(Integer id) {
        try (EntityManager em = emf.createEntityManager()) {
            HotelEntity hotel = em.find(HotelEntity.class, id);
            return hotel;
        }
    }

    @Override
    public HotelEntity create(HotelEntity hotelEntity) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(hotelEntity);
            em.getTransaction().commit();
            return hotelEntity;
        }
    }

    @Override
    public HotelEntity update(HotelEntity hotelEntity) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(hotelEntity);
            em.getTransaction().commit();
            return hotelEntity;
        }
    }

    @Override
    public void delete(Integer id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            HotelEntity hotel = getById(id);
            if (hotel != null) {
                em.remove(hotel);
            }
            em.getTransaction().commit();
        }
    }
}
