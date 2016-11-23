package com.mkrt4an.service;

import com.mkrt4an.dao.CargoDao;
import com.mkrt4an.dao.CityDao;
import com.mkrt4an.dao.OrderDao;
import com.mkrt4an.dao.RoutePointDao;
import com.mkrt4an.entity.CargoEntity;
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
public class RoutePointServiceTest {

    private RoutePointDao routePointDaoMock;
    private CityDao cityDaoMock;
    private CargoDao cargoDaoMock;
    private OrderDao orderDaoMock;
    private RoutePointService routePointService;

    @Before
    public void setUp() throws Exception {
        routePointDaoMock = mock(RoutePointDao.class);
        cityDaoMock = mock(CityDao.class);
        cargoDaoMock = mock(CargoDao.class);
        orderDaoMock = mock(OrderDao.class);
        routePointService = new RoutePointService(routePointDaoMock, cityDaoMock, cargoDaoMock, orderDaoMock);
    }

    @Test
    public void testAddRoutePointBlank() throws Exception {

    }

    @Test
    public void testDeleteById() throws Exception {
        RoutePointEntity routePointEntity = new RoutePointEntity();

        when(routePointDaoMock.findRoutePointById(1)).thenReturn(routePointEntity);
        when(routePointDaoMock.deleteRoutePoint(routePointEntity)).thenReturn(1);

        Assert.assertEquals(Integer.valueOf(1), routePointService.deleteById(1));
        Assert.assertNotEquals(Integer.valueOf(1), routePointService.deleteById(2));
        Assert.assertNotEquals(Integer.valueOf(2), routePointService.deleteById(1));
    }

    @Test
    public void testAddRoutePoint() throws Exception {

    }

    @Test
    public void testAddCargoToLoadingList() throws Exception {

    }

    @Test
    public void testAddCargoToDeliverList() throws Exception {

    }

    @Test
    public void testUpdateRoutePoint() throws Exception {

    }

    @Test
    public void testFindById() throws Exception {
        RoutePointEntity routePointEntity = new RoutePointEntity();

        when(routePointDaoMock.findRoutePointById(1)).thenReturn(routePointEntity);

        Assert.assertEquals(routePointEntity, routePointService.findById(1));
    }


    @Test
    public void testFindAll() throws Exception {
        RoutePointEntity routePointEntity1 = new RoutePointEntity();
        RoutePointEntity routePointEntity2 = new RoutePointEntity();
        RoutePointEntity routePointEntity3 = new RoutePointEntity();

        List<RoutePointEntity> routePointEntityList = new ArrayList<>();
        routePointEntityList.add(routePointEntity1);
        routePointEntityList.add(routePointEntity2);
        routePointEntityList.add(routePointEntity3);

        when(routePointDaoMock.getAllRoutePoints()).thenReturn(routePointEntityList);

        Assert.assertEquals(routePointEntityList, routePointService.findAll());
    }

    @Test
    public void testFindMaxWeightOnRoute() throws Exception {

        CargoEntity cargoEntity1 = new CargoEntity();
        CargoEntity cargoEntity2 = new CargoEntity();
        CargoEntity cargoEntity3 = new CargoEntity();
        CargoEntity cargoEntity4 = new CargoEntity();

        cargoEntity1.setWeight(10);
        cargoEntity2.setWeight(20);
        cargoEntity3.setWeight(30);
        cargoEntity4.setWeight(40);

        List<CargoEntity> cargoToLoadList1 = new ArrayList<>();
        List<CargoEntity> cargoToLoadList2 = new ArrayList<>();
        List<CargoEntity> cargoToLoadList3 = new ArrayList<>();
        List<CargoEntity> cargoToLoadList4 = new ArrayList<>();
        List<CargoEntity> cargoToDeliverList1 = new ArrayList<>();
        List<CargoEntity> cargoToDeliverList2 = new ArrayList<>();
        List<CargoEntity> cargoToDeliverList3 = new ArrayList<>();
        List<CargoEntity> cargoToDeliverList4 = new ArrayList<>();

        // Make Rps
        RoutePointEntity routePointEntity1 = new RoutePointEntity();
        RoutePointEntity routePointEntity2 = new RoutePointEntity();
        RoutePointEntity routePointEntity3 = new RoutePointEntity();
        RoutePointEntity routePointEntity4 = new RoutePointEntity();

        // Rp 1
        cargoToLoadList1.add(cargoEntity1);
        routePointEntity1.setCargoToLoadList(cargoToLoadList1);
        routePointEntity1.setCargoToDeliverList(cargoToDeliverList1);

        // Rp 2
        cargoToLoadList2.add(cargoEntity2);
        cargoToLoadList2.add(cargoEntity3);
        cargoToDeliverList2.add(cargoEntity1);

        routePointEntity2.setCargoToLoadList(cargoToLoadList2);
        routePointEntity2.setCargoToDeliverList(cargoToDeliverList2);

        // Rp 3
        cargoToLoadList3.add(cargoEntity4);
        cargoToDeliverList3.add(cargoEntity2);
        cargoToDeliverList3.add(cargoEntity3);

        routePointEntity3.setCargoToLoadList(cargoToLoadList3);
        routePointEntity3.setCargoToDeliverList(cargoToDeliverList3);

        // Rp 4
        cargoToDeliverList4.add(cargoEntity4);

        routePointEntity4.setCargoToLoadList(cargoToLoadList4);
        routePointEntity4.setCargoToDeliverList(cargoToDeliverList4);


        // Make Rp list
        List<RoutePointEntity> routePointEntityList = new ArrayList<>();
        routePointEntityList.add(routePointEntity1);
        routePointEntityList.add(routePointEntity2);
        routePointEntityList.add(routePointEntity3);
        routePointEntityList.add(routePointEntity4);

        // Make order
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(1);
        orderEntity.setRoutePointList(routePointEntityList);

//        Assert.assertEquals(Integer.valueOf(), routePointService.findMaxWeightOnRoute(orderEntity)); // TODO: 23.11.2016  

    }

    @Test
    public void testGetOrderRoutePointList() throws Exception {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(1);

        OrderEntity orderEntity2 = new OrderEntity();
        orderEntity2.setId(2);

        RoutePointEntity routePointEntity1 = new RoutePointEntity();
        routePointEntity1.setOrder(orderEntity);

        RoutePointEntity routePointEntity2 = new RoutePointEntity();
        routePointEntity2.setOrder(orderEntity2);

        RoutePointEntity routePointEntity3 = new RoutePointEntity();
        routePointEntity3.setOrder(orderEntity);

        List<RoutePointEntity> routePointEntityListAll = new ArrayList<>();
        routePointEntityListAll.add(routePointEntity1);
        routePointEntityListAll.add(routePointEntity2);
        routePointEntityListAll.add(routePointEntity3);

        List<RoutePointEntity> routePointEntityListSelected = new ArrayList<>();
        routePointEntityListSelected.add(routePointEntity1);
        routePointEntityListSelected.add(routePointEntity3);

        when(routePointDaoMock.getAllRoutePoints()).thenReturn(routePointEntityListAll);

        Assert.assertEquals(routePointEntityListSelected, routePointService.getOrderRoutePointList(1));
        Assert.assertNotEquals(routePointEntityListSelected, routePointService.getOrderRoutePointList(2));
    }

}