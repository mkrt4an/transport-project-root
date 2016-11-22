package com.mkrt4an.dao;

import com.mkrt4an.entity.RoutePointEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
//@Scope(value = "prototype")   // TODO: 22.11.2016
//@Transactional                // TODO: 22.11.2016
@Transactional(propagation = Propagation.MANDATORY)
public class RoutePointDao {

    @PersistenceContext//(unitName = "NewPersistenceUnit")
    private EntityManager em;

    public RoutePointDao() {
    }

    public RoutePointDao(EntityManager em) {
        this.em = em;
    }

    // Find by id
    public RoutePointEntity findRoutePointById(int id) {
        return em.find(RoutePointEntity.class, id);
    }

//    //Get order RP list by order id
//    public List<RoutePointEntity> getOrderRoutePointList(Integer orderId) {
//        TypedQuery query = em.createQuery("SELECT rp FROM RoutePointEntity rp WHERE rp.order = :orderId", RoutePointEntity.class);
//        query.setParameter("orderId", orderId);
//        List<RoutePointEntity> results = query.getResultList();
//
//        return results;
//
//    }

    //Get all cargo list
    public List<RoutePointEntity> getAllRoutePoints() {
        return em.createQuery("select c from RoutePointEntity c", RoutePointEntity.class).getResultList();
    }

    //Create
    public Integer createRoutePoint(RoutePointEntity entity) {
        em.persist(entity);
//        em.persist(em.contains(entity) ? entity : em.merge(entity));
//        em.flush();

        return entity.getId();
    }

    //Update
    public Integer updateRoutePoint(RoutePointEntity entity) {
        em.persist(em.contains(entity) ? entity : em.merge(entity));

        return entity.getId();
    }

    //Delete
    public void deleteRoutePoint(RoutePointEntity entity) {
        em.remove(em.contains(entity) ? entity : em.merge(entity));
    }

}
