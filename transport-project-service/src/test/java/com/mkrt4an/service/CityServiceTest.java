package com.mkrt4an.service;

import com.mkrt4an.dao.CityDao;
import com.mkrt4an.entity.CityEntity;
import com.mkrt4an.entity.OrderEntity;
import com.mkrt4an.entity.RoutePointEntity;
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
public class CityServiceTest {

    private CityDao cityDaoMock;
    private CityService cityService;

    @Before
    public void setUp() throws Exception {
        cityDaoMock = mock(CityDao.class);
        cityService = new CityService(cityDaoMock);
    }


    @Test
    public void testFindAll() throws Exception {
        CityEntity cityEntity1 = new CityEntity();
        CityEntity cityEntity2 = new CityEntity();

        List<CityEntity> cityEntityList = new ArrayList<>();
        cityEntityList.add(cityEntity1);
        cityEntityList.add(cityEntity2);

        when(cityDaoMock.getAllCities()).thenReturn(cityEntityList);

        Assert.assertEquals(cityEntityList, cityService.findAll());
    }

    @Test
    public void testFindByIdInteger() throws Exception {
        CityEntity cityEntity = new CityEntity();

        when(cityDaoMock.findCityById(1)).thenReturn(cityEntity);

        Assert.assertEquals(cityEntity, cityService.findById(1));
    }

    @Test
    public void testFindByIdString() throws Exception {
        CityEntity cityEntity = new CityEntity();

        when(cityDaoMock.findCityById(1)).thenReturn(cityEntity);

        Assert.assertEquals(cityEntity, cityService.findById("1"));
    }

    @Test
    public void testCalcDistance() throws Exception {
        CityEntity cityEntity1 = new CityEntity();
        cityEntity1.setX(2);
        cityEntity1.setY(3);
        CityEntity cityEntity2 = new CityEntity();
        cityEntity2.setX(4);
        cityEntity2.setY(5);

        Double calcDist = cityService.calcDistance(cityEntity1, cityEntity2);
        Assert.assertTrue(Math.abs(2.8284 - calcDist) < 0.01);
    }

    @Test
    public void testCalcOrderDistanceTwoCities() throws Exception {
        CityEntity cityEntity1 = new CityEntity();
        cityEntity1.setX(2);
        cityEntity1.setY(3);
        CityEntity cityEntity2 = new CityEntity();
        cityEntity2.setX(4);
        cityEntity2.setY(5);
        RoutePointEntity routePointEntity1 = new RoutePointEntity();
        routePointEntity1.setCity(cityEntity1);
        RoutePointEntity routePointEntity2 = new RoutePointEntity();
        routePointEntity2.setCity(cityEntity2);
        List<RoutePointEntity> routePointEntityList = new ArrayList<>();
        routePointEntityList.add(routePointEntity1);
        routePointEntityList.add(routePointEntity2);
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setRoutePointList(routePointEntityList);
        Double calcDist = cityService.calcOrderDistance(orderEntity);
        Assert.assertTrue(Math.abs(2.8284 - calcDist) < 0.01);
    }

    @Test
    public void testCalcOrderDistanceThreeCities() throws Exception {
        CityEntity cityEntity1 = new CityEntity();
        cityEntity1.setX(2);
        cityEntity1.setY(3);
        CityEntity cityEntity2 = new CityEntity();
        cityEntity2.setX(4);
        cityEntity2.setY(5);
        CityEntity cityEntity3 = new CityEntity();
        cityEntity3.setX(6);
        cityEntity3.setY(7);
        RoutePointEntity routePointEntity1 = new RoutePointEntity();
        routePointEntity1.setCity(cityEntity1);
        RoutePointEntity routePointEntity2 = new RoutePointEntity();
        routePointEntity2.setCity(cityEntity2);
        RoutePointEntity routePointEntity3 = new RoutePointEntity();
        routePointEntity3.setCity(cityEntity3);
        List<RoutePointEntity> routePointEntityList = new ArrayList<>();
        routePointEntityList.add(routePointEntity1);
        routePointEntityList.add(routePointEntity2);
        routePointEntityList.add(routePointEntity3);
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setRoutePointList(routePointEntityList);
        Double calcDist = cityService.calcOrderDistance(orderEntity);
        Assert.assertTrue(Math.abs(5.6568 - calcDist) < 0.01);
    }

    @Test
    public void testCalcOrderDistanceFourCities() throws Exception {
        CityEntity cityEntity1 = new CityEntity();
        cityEntity1.setX(2);
        cityEntity1.setY(3);
        CityEntity cityEntity2 = new CityEntity();
        cityEntity2.setX(4);
        cityEntity2.setY(5);
        CityEntity cityEntity3 = new CityEntity();
        cityEntity3.setX(6);
        cityEntity3.setY(7);
        CityEntity cityEntity4 = new CityEntity();
        cityEntity4.setX(8);
        cityEntity4.setY(9);
        RoutePointEntity routePointEntity1 = new RoutePointEntity();
        routePointEntity1.setCity(cityEntity1);
        RoutePointEntity routePointEntity2 = new RoutePointEntity();
        routePointEntity2.setCity(cityEntity2);
        RoutePointEntity routePointEntity3 = new RoutePointEntity();
        routePointEntity3.setCity(cityEntity3);
        RoutePointEntity routePointEntity4 = new RoutePointEntity();
        routePointEntity4.setCity(cityEntity4);
        List<RoutePointEntity> routePointEntityList = new ArrayList<>();
        routePointEntityList.add(routePointEntity1);
        routePointEntityList.add(routePointEntity2);
        routePointEntityList.add(routePointEntity3);
        routePointEntityList.add(routePointEntity4);
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setRoutePointList(routePointEntityList);
        Double calcDist = cityService.calcOrderDistance(orderEntity);
        Assert.assertTrue(Math.abs(8.4852 - calcDist) < 0.01);
    }

    @Test
    public void testCalcOrderTime() throws Exception {
        CityEntity cityEntity1 = new CityEntity();
        cityEntity1.setX(2);
        cityEntity1.setY(3);
        CityEntity cityEntity2 = new CityEntity();
        cityEntity2.setX(4);
        cityEntity2.setY(5);
        RoutePointEntity routePointEntity1 = new RoutePointEntity();
        routePointEntity1.setCity(cityEntity1);
        RoutePointEntity routePointEntity2 = new RoutePointEntity();
        routePointEntity2.setCity(cityEntity2);
        List<RoutePointEntity> routePointEntityList = new ArrayList<>();
        routePointEntityList.add(routePointEntity1);
        routePointEntityList.add(routePointEntity2);
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setRoutePointList(routePointEntityList);
        Double time = cityService.calcOrderTime(orderEntity);
        Assert.assertTrue(Math.abs(47.14 - time) < 0.1);
    }

}