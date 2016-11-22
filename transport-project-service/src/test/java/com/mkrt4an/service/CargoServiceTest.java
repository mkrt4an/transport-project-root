package com.mkrt4an.service;

import com.mkrt4an.dao.CargoDao;
import com.mkrt4an.entity.CargoEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.matchers.ArrayEquals;

import javax.validation.constraints.AssertTrue;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by 123 on 22.11.2016.
 */

public class CargoServiceTest {

    private CargoDao cargoDaoMock;
    private CargoService cargoService;

    @Before
    public void setUp() throws Exception {
            cargoDaoMock = mock(CargoDao.class);
            cargoService = new CargoService(cargoDaoMock);
    }

    @Test
    public void testAddNewCargoWithIntegerParameters() throws Exception {
        CargoEntity cargoEntity = new CargoEntity("cargo", 123, 0);
        when(cargoDaoMock.createCargo(cargoEntity)).thenReturn(1);

        Assert.assertEquals(Integer.valueOf(1), cargoService.addNew("cargo", 123, 0));
    }

    @Test
    public void testAddNewCargoWithStringParameters() throws Exception {
        CargoEntity cargoEntity = new CargoEntity("cargo", 123, 0);
        when(cargoDaoMock.createCargo(cargoEntity)).thenReturn(1);

        Assert.assertEquals(Integer.valueOf(1), cargoService.addNew("cargo", 123, 0));
    }

    @Test
    public void testCargoFindByStringId() throws Exception {
        CargoEntity cargoEntity = new CargoEntity("cargo", 123, 0);
        when(cargoDaoMock.findCargoById(1)).thenReturn(cargoEntity);

        Assert.assertEquals(cargoEntity, cargoService.findById(1));
    }

    @Test
    public void testCargoFindByIntegerId() throws Exception {
        CargoEntity cargoEntity = new CargoEntity("cargo", 123, 0);
        when(cargoDaoMock.findCargoById(1)).thenReturn(cargoEntity);

        Assert.assertEquals(cargoEntity, cargoService.findById("1"));
    }

    @Test
    public void testCargoFindAll() throws Exception {
        CargoEntity cargoEntity1 = new CargoEntity("cargo1", 123, 0);
        CargoEntity cargoEntity2 = new CargoEntity("cargo2", 456, 1);
        List<CargoEntity> cargoEntityList = new ArrayList<>();
        cargoEntityList.add(cargoEntity1);
        cargoEntityList.add(cargoEntity2);

        when(cargoDaoMock.getAllCargo()).thenReturn(cargoEntityList);

        Assert.assertEquals(cargoEntityList, cargoService.findAll());
    }

    @Test
    public void testCargoDeleteById() throws Exception {
        CargoEntity cargoEntity = new CargoEntity("cargo", 123, 0);

        when(cargoDaoMock.findCargoById(1)).thenReturn(cargoEntity);
        when(cargoDaoMock.deleteCargo(cargoEntity)).thenReturn(1);

        Assert.assertEquals(Integer.valueOf(1), cargoService.deleteById("1"));
    }

    @Test
    public void testCargoUpdateWithCargoEntityParameter() throws Exception {
        CargoEntity cargoEntity = new CargoEntity("cargo", 123, 0);

        when(cargoDaoMock.updateCargo(cargoEntity)).thenReturn(1);

        Assert.assertEquals(Integer.valueOf(1), cargoService.update(cargoEntity));
    }

    @Test
    public void testCargoUpdateWithStringParameters() throws Exception {
        CargoEntity cargoEntity = new CargoEntity("cargo", 123, 0);
        cargoEntity.setId(1);

        when(cargoDaoMock.findCargoById(1)).thenReturn(cargoEntity);
        when(cargoDaoMock.updateCargo(cargoEntity)).thenReturn(1);

        Integer id = cargoEntity.getId();
        String name = cargoEntity.getName();
        String weight = cargoEntity.getWeight().toString();
        String status = cargoEntity.getStatus().toString();

        Assert.assertEquals(Integer.valueOf(1), cargoService.update(id, name, weight, status));
    }

}