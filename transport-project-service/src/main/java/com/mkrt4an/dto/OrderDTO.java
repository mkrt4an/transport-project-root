package com.mkrt4an.dto;

import java.util.List;

/**
 * Created by 123 on 15.11.2016.
 */
public class OrderDTO {

    String uid;

    List<RoutePointDTO> rpList;

    String status;

    List<CargoDTO> cargoList;

    public List<CargoDTO> getCargoList() {
        return cargoList;
    }

    public void setCargoList(List<CargoDTO> cargoList) {
        this.cargoList = cargoList;
    }



    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public List<RoutePointDTO> getRpList() {
        return rpList;
    }

    public void setRpList(List<RoutePointDTO> rpList) {
        this.rpList = rpList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public OrderDTO() {}

    @Override
    public String toString() {
        return "OrderDTO{" +
                "uid='" + uid + '\'' +
                ", rpList=" + rpList +
                ", status='" + status + '\'' +
                ", cargoList=" + cargoList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderDTO orderDTO = (OrderDTO) o;

        if (uid != null ? !uid.equals(orderDTO.uid) : orderDTO.uid != null) return false;
        if (rpList != null ? !rpList.equals(orderDTO.rpList) : orderDTO.rpList != null) return false;
        if (status != null ? !status.equals(orderDTO.status) : orderDTO.status != null) return false;
        return cargoList != null ? cargoList.equals(orderDTO.cargoList) : orderDTO.cargoList == null;

    }

    @Override
    public int hashCode() {
        int result = uid != null ? uid.hashCode() : 0;
        result = 31 * result + (rpList != null ? rpList.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (cargoList != null ? cargoList.hashCode() : 0);
        return result;
    }
}
