package com.mkrt4an.dto;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * Created by 123 on 15.11.2016.
 */
public class UserDTO {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    private String name;

    private String password;

    @Override
    public String toString() {
        return "UserDTO{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", driver=" + driver +
                ", driverId=" + driverId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDTO userDTO = (UserDTO) o;

        if (driver != userDTO.driver) return false;
        if (name != null ? !name.equals(userDTO.name) : userDTO.name != null) return false;
        if (password != null ? !password.equals(userDTO.password) : userDTO.password != null) return false;
        return driverId != null ? driverId.equals(userDTO.driverId) : userDTO.driverId == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (driver ? 1 : 0);
        result = 31 * result + (driverId != null ? driverId.hashCode() : 0);
        return result;
    }

    public boolean isDriver() {
        return driver;
    }

    public void setDriver(boolean driver) {
        this.driver = driver;
    }

    private boolean driver;

    private Integer driverId;

    public UserDTO() {}



}
