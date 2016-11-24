package com.mkrt4an.dao;

import com.mkrt4an.entity.CargoEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

//import javax.transaction.Transactional;

@SuppressWarnings("JpaQlInspection")
@Repository
//@Transactional // TODO: 22.11.2016
public class CargoDao {

    @PersistenceContext//(type = PersistenceContextType.EXTENDED)// TODO: 22.11.2016
    private EntityManager em;

    public CargoDao() {
    }

    public CargoDao(EntityManager em) {
        this.em = em;
    }

    // Find by id
    public CargoEntity findCargoById(int id) {
        return em.find(CargoEntity.class, id);
    }

    //Get all cargo list
    public List<CargoEntity> getAllCargo() {
        return em.createQuery("select c from CargoEntity c", CargoEntity.class).getResultList();
    }

    //Create
    public Integer createCargo(CargoEntity entity)  {
        em.persist(entity);
        return entity.getId();
    }

    //Update
    public Integer updateCargo(CargoEntity entity) {
        em.persist(em.contains(entity) ? entity : em.merge(entity));
        return entity.getId();
    }

    //Delete
    public Integer deleteCargo(CargoEntity entity) {
        em.remove(em.contains(entity) ? entity : em.merge(entity));
        return entity.getId();
    }

}


