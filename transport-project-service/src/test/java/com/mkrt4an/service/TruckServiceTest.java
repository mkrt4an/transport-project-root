package com.mkrt4an.service;

import com.mkrt4an.dao.CityDao;
import com.mkrt4an.dao.TruckDao;
import com.mkrt4an.entity.CityEntity;
import com.mkrt4an.entity.TruckEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by 123 on 23.11.2016.
 */
public class TruckServiceTest {

    private TruckDao truckDaoMock;
    private CityDao cityDaoMock;
    private TruckService truckService;

    @Before
    public void setUp() throws Exception {
        truckDaoMock = mock(TruckDao.class);
        cityDaoMock = mock(CityDao.class);
        truckService = new TruckService(truckDaoMock, cityDaoMock);
    }

    @Test
    public void testAddNewTruck() throws Exception {
        CityEntity cityEntity = new CityEntity();
        cityEntity.setId(1);

        Integer id = 1;
        Integer dutySize = 3;
        Integer capacity = 5;
        Integer status = 0;
        String regNumber = "regNumber123";
        Integer cityId = cityEntity.getId();

        TruckEntity truckEntity = new TruckEntity();
        truckEntity.setDutySize(dutySize);
        truckEntity.setCapacity(capacity);
        truckEntity.setStatus(status);
        truckEntity.setRegNumber(regNumber);
        truckEntity.setCurrentCity(cityEntity);

        when(cityDaoMock.findCityById(1)).thenReturn(cityEntity);
        when(truckDaoMock.createTruck(truckEntity)).thenReturn(1);

        Assert.assertEquals(Integer.valueOf(1), truckService.addNew(dutySize, capacity, status, regNumber, cityId));
    }

    @Test
    public void testFindById() throws Exception {
        CityEntity cityEntity = new CityEntity();
        cityEntity.setId(1);
        cityEntity.setName("city");

        Integer id = 1;
        Integer dutySize = 3;
        Integer capacity = 5;
        Integer status = 0;
        String regNumber = "regNumber123";
        Integer cityId = cityEntity.getId();

        TruckEntity truckEntity = new TruckEntity();
        truckEntity.setDutySize(dutySize);
        truckEntity.setCapacity(capacity);
        truckEntity.setStatus(status);
        truckEntity.setRegNumber(regNumber);
        truckEntity.setCurrentCity(cityEntity);

        when(truckDaoMock.findTruckById(1)).thenReturn(truckEntity);

        Assert.assertEquals(truckEntity, truckService.findById("1"));
    }

    @Test
    public void testFindAllTrucks() throws Exception {

        TruckEntity truckEntity = new TruckEntity();
        TruckEntity truckEntity2 = new TruckEntity();

        List<TruckEntity> truckEntityList = new ArrayList<>();
        truckEntityList.add(truckEntity);
        truckEntityList.add(truckEntity2);

        when(truckDaoMock.getAllTrucks()).thenReturn(truckEntityList);

        Assert.assertEquals(truckEntityList, truckService.findAllTrucks());
    }

    @Test
    public void testDeleteById() throws Exception {
        TruckEntity truckEntity = new TruckEntity();

        when(truckDaoMock.findTruckById(1)).thenReturn(truckEntity);
        when(truckDaoMock.deleteTruck(truckEntity)).thenReturn(1);

        Assert.assertEquals(Integer.valueOf(1), truckService.deleteById("1"));
    }

    @Test
    public void testUpdate() throws Exception {

        CityEntity cityEntity = new CityEntity();
        cityEntity.setId(1);
        cityEntity.setName("city");

        Integer id = 1;
        Integer dutySize = 3;
        Integer capacity = 5;
        Integer status = 0;
        String regNumber = "regNumber123";
        Integer cityId = cityEntity.getId();

        TruckEntity truckEntity = new TruckEntity();
        truckEntity.setId(id);
        truckEntity.setDutySize(dutySize);
        truckEntity.setCapacity(capacity);
        truckEntity.setStatus(status);
        truckEntity.setRegNumber(regNumber);
        truckEntity.setCurrentCity(cityEntity);

        when(cityDaoMock.findCityById(1)).thenReturn(cityEntity);
        when(truckDaoMock.findTruckById(1)).thenReturn(truckEntity);
        when(truckDaoMock.updateTruck(truckEntity)).thenReturn(1);

        Assert.assertEquals(Integer.valueOf(1), truckService.update(id, dutySize, capacity, status, regNumber, cityId));
    }

}