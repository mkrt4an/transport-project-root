package com.mkrt4an.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by 123 on 02.10.2016.
 */

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@Table(name = "cargo", schema = "transportProject")
public class CargoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "weight", nullable = false)
    private Integer weight;

    @Column(name = "status", nullable = false)
    private Integer status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "loading_rp_id")
    private RoutePointEntity loadingRoutePoint;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "delivery_rp_id")
    private RoutePointEntity deliveryRoutePoint;

//    @Column(name = "number", nullable = false)
//    private Integer number;

    // Constructors
    public CargoEntity() {
        this.status = 0;
    }

    public CargoEntity(String name, Integer weight, Integer status) {
        this.name = name;
        this.weight = weight;
        this.status = status;
//        this.routePoint = routePoint;
    }

    public CargoEntity(String name, Integer weight,
                       Integer status,
                       RoutePointEntity loadingRoutePoint,
                       RoutePointEntity deliveryRoutePoint
    ) {
        this.name = name;
        this.weight = weight;
        this.status = status;
        this.loadingRoutePoint = loadingRoutePoint;
        this.deliveryRoutePoint = deliveryRoutePoint;
    }


    // Getters & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public RoutePointEntity getDeliveryRoutePoint() {
        return deliveryRoutePoint;
    }

    public void setDeliveryRoutePoint(RoutePointEntity deliveryRoutePoint) {
        this.deliveryRoutePoint = deliveryRoutePoint;
    }

    public RoutePointEntity getLoadingRoutePoint() {
        return loadingRoutePoint;
    }

    public void setLoadingRoutePoint(RoutePointEntity loadingRoutePoint) {
        this.loadingRoutePoint = loadingRoutePoint;
    }

//    public Integer getNumber() {
//        return number;
//    }
//    public void setNumber(Integer number) {
//        this.number = number;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CargoEntity that = (CargoEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (weight != null ? !weight.equals(that.weight) : that.weight != null) return false;
        return status != null ? status.equals(that.status) : that.status == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CargoEntity{" +
                "id=" + id +
                ", name=" + name  +
                ", weight=" + weight +
                ", status=" + status +
                '}' + "\n";
    }
}
