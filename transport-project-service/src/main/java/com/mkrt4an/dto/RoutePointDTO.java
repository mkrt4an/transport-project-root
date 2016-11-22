package com.mkrt4an.dto;

import java.util.List;

/**
 * Created by 123 on 15.11.2016.
 */

public class RoutePointDTO {

    private String city;

    //List<CargoDTO> loadList;
    private Integer loadList[];

    //List<CargoDTO> deliverList;
    private Integer deliverList[];

    private Integer ordinal;

    public Integer[] getLoadList() {
        return loadList;
    }

    public void setLoadList(Integer[] loadList) {
        this.loadList = loadList;
    }

    public Integer[] getDeliverList() {
        return deliverList;
    }

    public void setDeliverList(Integer[] deliverList) {
        this.deliverList = deliverList;
    }



    public RoutePointDTO() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }



    public Integer getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
    }



    @Override
    public String toString() {
        return "RoutePointDTO{" +
                "city='" + city + '\'' +
                ", loadList=" + loadList +
                ", deliverList=" + deliverList +
                ", ordinal=" + ordinal +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoutePointDTO that = (RoutePointDTO) o;

        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (loadList != null ? !loadList.equals(that.loadList) : that.loadList != null) return false;
        if (deliverList != null ? !deliverList.equals(that.deliverList) : that.deliverList != null) return false;
        return ordinal != null ? ordinal.equals(that.ordinal) : that.ordinal == null;

    }

    @Override
    public int hashCode() {
        int result = city != null ? city.hashCode() : 0;
        result = 31 * result + (loadList != null ? loadList.hashCode() : 0);
        result = 31 * result + (deliverList != null ? deliverList.hashCode() : 0);
        result = 31 * result + (ordinal != null ? ordinal.hashCode() : 0);
        return result;
    }
}
