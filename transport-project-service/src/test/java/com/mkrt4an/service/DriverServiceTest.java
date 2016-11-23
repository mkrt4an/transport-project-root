package com.mkrt4an.service;

import com.mkrt4an.dao.CityDao;
import com.mkrt4an.dao.DriverDao;
import com.mkrt4an.entity.CityEntity;
import com.mkrt4an.entity.DriverEntity;
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
public class DriverServiceTest {

    private DriverDao driverDaoMock;
    private CityDao cityDaoMock;
    private DriverService driverService;

    @Before
    public void setUp() throws Exception {
            driverDaoMock = mock(DriverDao.class);
            cityDaoMock = mock(CityDao.class);
            driverService = new DriverService(driverDaoMock, cityDaoMock);
    }

    @Test
    public void testAddNewDriver() throws Exception {

        CityEntity cityEntity = new CityEntity();
        cityEntity.setId(1);

        DriverEntity driverEntity = new DriverEntity();
        driverEntity.setFirstName("firstName");
        driverEntity.setLastName("lastName");
        driverEntity.setWorkedHours(123);
        driverEntity.setStatus(0);
        driverEntity.setCurrentCity(cityEntity);

        when(cityDaoMock.findCityById(1)).thenReturn(cityEntity);
        when(driverDaoMock.createDriver(driverEntity)).thenReturn(1);

        String firstName = driverEntity.getFirstName();
        String lastName = driverEntity.getLastName();
        Integer workedHours = driverEntity.getWorkedHours();
        Integer status = driverEntity.getStatus();
        Integer cityId = driverEntity.getCurrentCity().getId();

        Assert.assertEquals(Integer.valueOf(1), driverService.addNew(firstName, lastName, workedHours, status, cityId));
    }

    @Test
    public void testFindByIdInteger() throws Exception {
        DriverEntity driverEntity = new DriverEntity();

        when(driverDaoMock.findDriverById(1)).thenReturn(driverEntity);

        Assert.assertEquals(driverEntity, driverService.findById(1));
    }

    @Test
    public void testFindByIdString() throws Exception {
        DriverEntity driverEntity = new DriverEntity();

        when(driverDaoMock.findDriverById(1)).thenReturn(driverEntity);

        Assert.assertEquals(driverEntity, driverService.findById("1"));
    }

    @Test
    public void testFindByFirstNameAndLastName() throws Exception {
        DriverEntity driverEntity1 = new DriverEntity();
        driverEntity1.setFirstName("Mitch");
        driverEntity1.setLastName("Woody");

        DriverEntity driverEntity2 = new DriverEntity();
        driverEntity2.setFirstName("Dod");
        driverEntity2.setLastName("Rodes");

        DriverEntity driverEntity3 = new DriverEntity();
        driverEntity3.setFirstName("Bob");
        driverEntity3.setLastName("Johns");

        List<DriverEntity> driverEntityList = new ArrayList<>();
        driverEntityList.add(driverEntity1);
        driverEntityList.add(driverEntity2);
        driverEntityList.add(driverEntity3);

        when(driverDaoMock.getAllDrivers()).thenReturn(driverEntityList);

        Assert.assertEquals(driverEntity3, driverService.findByFirstNameAndLastName("Bob", "Johns"));
        Assert.assertEquals(null, driverService.findByFirstNameAndLastName("Tom", "Johns"));
        Assert.assertEquals(null, driverService.findByFirstNameAndLastName("Bob", "Xen"));
        Assert.assertEquals(null, driverService.findByFirstNameAndLastName("Din", "Xen"));
    }

    @Test
    public void testDeleteById() throws Exception {
        DriverEntity driverEntity = new DriverEntity();
        driverEntity.setId(1);

        when(driverDaoMock.findDriverById(1)).thenReturn(driverEntity);
        when(driverDaoMock.deleteDriver(driverEntity)).thenReturn(1);

        Assert.assertEquals(Integer.valueOf(1), driverService.deleteById("1"));
    }

    @Test
    public void findAllDrivers() throws Exception {
        DriverEntity driverEntity1 = new DriverEntity();
        DriverEntity driverEntity2 = new DriverEntity();
        DriverEntity driverEntity3 = new DriverEntity();

        List<DriverEntity> driverEntityList = new ArrayList<>();
        driverEntityList.add(driverEntity1);
        driverEntityList.add(driverEntity2);
        driverEntityList.add(driverEntity3);

        when(driverDaoMock.getAllDrivers()).thenReturn(driverEntityList);

        Assert.assertEquals(driverEntityList, driverService.findAllDrivers());
    }

    @Test
    public void testUpdateWithDriverEntityParameter() throws Exception {
        DriverEntity driverEntity = new DriverEntity();
        driverEntity.setId(1);

        when(driverDaoMock.updateDriver(driverEntity)).thenReturn(1);

        Assert.assertEquals(Integer.valueOf(1), driverService.update(driverEntity));
    }

    @Test
    public void testUpdate() throws Exception {

        CityEntity cityEntity = new CityEntity();
        cityEntity.setId(1);

        Integer id = 1;
        String firstName = "firstName";
        String lastName = "lastName";
        Integer workedHours = 123;
        Integer status = 0;
        Integer cityId = cityEntity.getId();

        DriverEntity driverEntity = new DriverEntity();
        driverEntity.setId(id);
        driverEntity.setFirstName(firstName);
        driverEntity.setLastName(lastName);
        driverEntity.setWorkedHours(workedHours);
        driverEntity.setStatus(status);
        driverEntity.setCurrentCity(cityEntity);

        when(driverDaoMock.findDriverById(1)).thenReturn(driverEntity);
        when(cityDaoMock.findCityById(1)).thenReturn(cityEntity);
        when(driverDaoMock.updateDriver(driverEntity)).thenReturn(1);

        Assert.assertEquals(Integer.valueOf(1), driverService.update(id, firstName, lastName, workedHours, status, cityId));
    }

}