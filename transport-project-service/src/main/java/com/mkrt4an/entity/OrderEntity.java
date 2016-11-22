package com.mkrt4an.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by 123 on 02.10.2016.
 */

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@Table(name = "orders", schema = "transportproject")
public class OrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "uid", nullable = true)
    private Integer uid;

    @Column(name = "status", nullable = true)
    private Integer status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<RoutePointEntity> routePointList;

    @OneToMany(mappedBy = "order")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<DriverEntity> driverList;

    @ManyToOne
    @JoinColumn(name = "truck_id")
    private TruckEntity currentTruck;


    //  Constructors
    public OrderEntity() {
    }

    public OrderEntity(Integer uid) {
        this.uid = uid;
    }

    public OrderEntity(Integer uid, Integer status, List<RoutePointEntity> routePointList,
                       List<DriverEntity> driverList, TruckEntity currentTruck) {
        this.uid = uid;
        this.status = status;
        this.routePointList = routePointList;
        this.driverList = driverList;
        this.currentTruck = currentTruck;
    }

    public OrderEntity(Integer uid, Integer status, TruckEntity currentTruck) {
        this.uid = uid;
        this.status = status;
        this.currentTruck = currentTruck;
    }

    public OrderEntity(Integer uid, Integer status, List<RoutePointEntity> routePointList) {
        this.uid = uid;
        this.status = status;
        this.routePointList = routePointList;
    }

    public OrderEntity(Integer uid, List<RoutePointEntity> routePointList) {
        this.uid = uid;
        this.routePointList = routePointList;
    }

    public OrderEntity(Integer uid, Integer status, TruckEntity currentTruck, List<DriverEntity> driverList) {
        this.uid = uid;
        this.status = status;
        this.currentTruck = currentTruck;

    }


    // Getters & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setCurrentTruck(TruckEntity currentTruck) {
        this.currentTruck = currentTruck;
    }

    public TruckEntity getCurrentTruck() {
        return currentTruck;
    }

    public List<RoutePointEntity> getRoutePointList() {
        return routePointList;
    }

    public void setRoutePointList(List<RoutePointEntity> routePointList) {
        this.routePointList = routePointList;
    }

    public List<DriverEntity> getDriverList() {
        return driverList;
    }

    public void setDriverList(List<DriverEntity> driverList) {
        this.driverList = driverList;
    }


    public void assignRoutePointList(List<RoutePointEntity> routePointList) {
        this.routePointList = routePointList;
        for (RoutePointEntity routePointEntity : routePointList) {
            routePointEntity.setOrder(this);
        }
    }

    public void assignDriverList(List<DriverEntity> driverList) {
        this.driverList = driverList;
        for (DriverEntity driverEntity : driverList) {
            driverEntity.setOrder(this);
        }
    }

//    public void assignCurrentTruck(TruckEntity currentTruck) {
//        this.currentTruck = currentTruck;
//        currentTruck.setOrders(this);
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderEntity that = (OrderEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", uid=" + uid +
                ", status=" + status +
                ", routePointList=" + routePointList +
                ", driverList=" + driverList +
//                ", currentTruck=" + currentTruck.getRegNumber() +
                ", currentTruck=" + (currentTruck == null ? "null" : currentTruck.getRegNumber()) +
                '}' + "\n";
    }
}
