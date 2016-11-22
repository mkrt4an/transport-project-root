package com.mkrt4an.dao;

import com.mkrt4an.entity.DriverEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
//import javax.transaction.Transactional;
import java.util.List;


@SuppressWarnings("JpaQlInspection")
@Repository
//@Transactional // TODO: 22.11.2016
public class    DriverDao {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    public DriverDao() {
    }

    public DriverDao(EntityManager em) {
        this.em = em;
    }

    // Find by id
    public DriverEntity findDriverById(int id) {
        return em.find(DriverEntity.class, id);
    }

    //Get all cargo list
    public List<DriverEntity> getAllDrivers() {
        return em.createQuery("select c from DriverEntity c", DriverEntity.class).getResultList();
    }

    //Create
    public Integer createDriver(DriverEntity entity) {
        em.persist(entity);
        return entity.getId();
    }

    //Update
    public Integer updateDriver(DriverEntity entity) {
        em.persist(em.contains(entity) ? entity : em.merge(entity));
        return entity.getId();
    }

    //Delete
    public Integer deleteDriver(DriverEntity entity) {
        em.remove(em.contains(entity) ? entity : em.merge(entity));
        return entity.getId();
    }

}
