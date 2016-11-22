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
@Table(name = "routepoint", schema = "transportproject")
public class RoutePointEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

//    @Column(name = "type", nullable = false)
//    private Integer type;

    @Column(name = "ordinal", nullable = false)
    private Integer ordinal;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private CityEntity city;

//    @ManyToOne
//    @JoinColumn(name = "cargo_id")
//    private CargoEntity cargo;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "orders_id")
    private OrderEntity order;

    @OneToMany(mappedBy = "loadingRoutePoint", cascade = CascadeType.ALL, orphanRemoval=true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<CargoEntity> cargoToLoadList;

    @OneToMany(mappedBy = "deliveryRoutePoint", cascade = CascadeType.ALL, orphanRemoval=true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<CargoEntity> cargoToDeliverList;


    // Constructors
    public RoutePointEntity() {
    }

    public RoutePointEntity(List<CargoEntity> cargoToLoadList,
                            List<CargoEntity> cargoToDeliverList,
                            Integer ordinal,
                            CityEntity city,
                            OrderEntity order) {

        this.cargoToLoadList = cargoToLoadList;
        this.cargoToDeliverList = cargoToDeliverList;
        this.ordinal = ordinal;
        this.city = city;
        this.order = order;
//        this.type = 0;
    }

    public RoutePointEntity(CityEntity city, OrderEntity order) {
        this.order = order;
        this.city = city;
        this.ordinal = 1;
    }

    public RoutePointEntity(Integer type, CityEntity city, CargoEntity cargo, OrderEntity order) {
//        this.type = type;
        this.city = city;
//        this.cargo = cargo;
        this.order = order;
    }

    // Getters & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public Integer getType() {
//        return type;
//    }
//    public void setType(Integer type) {
//        this.type = type;
//    }

    public CityEntity getCity() {
        return city;
    }

    public void setCity(CityEntity city) {
        this.city = city;
    }

//    public CargoEntity getCargo() {
//        return cargo;
//    }
//    public void setCargo(CargoEntity cargo) {
//        this.cargo = cargo;
//    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public List<CargoEntity> getCargoToLoadList() {
        return cargoToLoadList;
    }

    public void setCargoToLoadList(List<CargoEntity> cargoToLoadList) {
        this.cargoToLoadList = cargoToLoadList;
    }

    public List<CargoEntity> getCargoToDeliverList() {
        return cargoToDeliverList;
    }

    public void setCargoToDeliverList(List<CargoEntity> cargoToDeliverList) {
        this.cargoToDeliverList = cargoToDeliverList;
    }

    public Integer getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
    }

    public void assignCargoToLoadList(List<CargoEntity> cargoToLoadList) {
        this.cargoToLoadList = cargoToLoadList;
        for (CargoEntity cargoEntity : cargoToLoadList) {
            cargoEntity.setLoadingRoutePoint(this);
        }
    }

    public void assignCargoToDeliverList(List<CargoEntity> cargoToDeliverList) {
        this.cargoToDeliverList = cargoToDeliverList;
        for (CargoEntity cargoEntity : cargoToDeliverList) {
            cargoEntity.setDeliveryRoutePoint(this);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoutePointEntity that = (RoutePointEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
//        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
//        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RoutePointEntity{" +
                "id=" + id +
                ", city=" + city.getId() + " " + city.getName() +
                ", cargoToLoadList=" + cargoToLoadList +
                ", cargoToDeliverList=" + cargoToDeliverList +
                ", ordinal=" + ordinal +
                ", order=" + (order == null ? "null" : order.getId()) +
                '}' + "\n";
    }
}
