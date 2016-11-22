package com.mkrt4an.dao;

import com.mkrt4an.entity.UserEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import javax.transaction.Transactional;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
//@Transactional // TODO: 22.11.2016
public class UserDao {

    @PersistenceContext//(unitName = "NewPersistenceUnit")
    private EntityManager em;

    public UserDao() {
    }

    public UserDao(EntityManager em) {
        this.em = em;
    }


    // Find by id
    public UserEntity findById(int id) {
        return em.find(UserEntity.class, id);
    }

    //Get all cargo list
    public List<UserEntity> getAll() {
        return em.createQuery("select c from UserEntity c", UserEntity.class).getResultList();
    }

    //Create
    public void create(UserEntity entity) {
        em.persist(entity);
    }

    //Update
    public void update(UserEntity entity) {
        em.persist(em.contains(entity) ? entity : em.merge(entity));
    }

    //Delete
    public void delete(UserEntity entity) {
        em.remove(em.contains(entity) ? entity : em.merge(entity));
    }

}

