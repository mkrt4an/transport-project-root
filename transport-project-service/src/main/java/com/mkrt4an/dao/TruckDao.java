package com.mkrt4an.dao;

import com.mkrt4an.entity.TruckEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import javax.transaction.Transactional;
import java.util.List;


@SuppressWarnings("JpaQlInspection")
@Repository
//@Transactional // TODO: 22.11.2016
public class TruckDao {

    @PersistenceContext//(unitName = "NewPersistenceUnit")
    private EntityManager em;

    public TruckDao() {
    }

    public TruckDao(EntityManager em) {
        this.em = em;
    }

    // Find by id
    public TruckEntity findTruckById(int id) {
        return em.find(TruckEntity.class, id);
    }

    //Get all cargo list
    public List<TruckEntity> getAllTrucks() {
        return em.createQuery("select c from TruckEntity c", TruckEntity.class).getResultList();
    }

    //Create
    public Integer createTruck(TruckEntity entity) {
        em.persist(entity);
        return entity.getId();
    }

    //Update
    public Integer updateTruck(TruckEntity entity) {
        em.persist(em.contains(entity) ? entity : em.merge(entity));
        return entity.getId();
    }

    //Delete
    public Integer deleteTruck(TruckEntity entity) {
        em.remove(em.contains(entity) ? entity : em.merge(entity));
        return entity.getId();
    }
}
