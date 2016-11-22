package com.mkrt4an.dto;

/**
 * Created by 123 on 15.11.2016.
 */
public class CargoDTO {

    @Override
    public String toString() {
        return "CargoDTO{" +
                "id=" + number +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }

    private String name;

    private Integer weight;

    private Integer number;

    public CargoDTO() {}


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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer id) {
        this.number = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CargoDTO cargoDTO = (CargoDTO) o;

        if (name != null ? !name.equals(cargoDTO.name) : cargoDTO.name != null) return false;
        return weight != null ? weight.equals(cargoDTO.weight) : cargoDTO.weight == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        return result;
    }

}
