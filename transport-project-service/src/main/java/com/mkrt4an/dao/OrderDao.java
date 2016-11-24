package com.mkrt4an.dao;

import com.mkrt4an.entity.OrderEntity;
import com.mkrt4an.exception.DaoException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

//import javax.transaction.Transactional;


@SuppressWarnings("JpaQlInspection")
@Repository
//@Transactional // TODO: 22.11.2016
public class OrderDao {

    private static final Logger log = Logger.getLogger(OrderDao.class);


    @PersistenceContext//unitName = "NewPersistenceUnit")
    private EntityManager em;

    public OrderDao() {
    }

    public OrderDao(EntityManager em) {
        this.em = em;
    }

    // Find by id
    public OrderEntity findOrderById(int id) {
        return em.find(OrderEntity.class, id);
    }

    //Get all Order list
    public List<OrderEntity> getAllOrders() {
        return em.createQuery("select c from OrderEntity c", OrderEntity.class).getResultList();
    }

    //Create
    public Integer createOrder(OrderEntity entity) throws DaoException {
        try{
            em.persist(entity);
            return entity.getId();
        } catch (Exception e) {
            log.warn("createOrder DAO exception", e);
            throw new DaoException(e);
        }
    }

    //Update
    public Integer updateOrder(OrderEntity entity) {
        em.persist(em.contains(entity) ? entity : em.merge(entity));

        return entity.getId();
    }

    //Delete
    public Integer deleteOrder(OrderEntity entity) {
        em.remove(em.contains(entity) ? entity : em.merge(entity));

        return entity.getId();
    }

}
