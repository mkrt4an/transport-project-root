package com.mkrt4an.dao;

import com.mkrt4an.entity.CityEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import javax.transaction.Transactional;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
//@Transactional // TODO: 22.11.2016
public class CityDao {

    @PersistenceContext//(unitName = "NewPersistenceUnit")
    private EntityManager em;

    public CityDao() {
    }

    public CityDao(EntityManager em) {
        this.em = em;
    }


    // Find by id
    public CityEntity findCityById(int id) {
        return em.find(CityEntity.class, id);
    }

    //Get all Order list
    public List<CityEntity> getAllCities() {
        return em.createQuery("select c from CityEntity c", CityEntity.class).getResultList();
    }

    //Create
    public void createCity(CityEntity entity) {
        em.persist(entity);
    }

    //Update
    public void updateCity(CityEntity entity) {
        em.persist(em.contains(entity) ? entity : em.merge(entity));
    }

    //Delete
    public void deleteCity(CityEntity entity) {
        em.remove(em.contains(entity) ? entity : em.merge(entity));
    }
}