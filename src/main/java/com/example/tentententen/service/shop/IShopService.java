package com.example.tentententen.service.shop;

import com.example.tentententen.model.Shop;
import com.example.tentententen.service.IService;

import java.sql.SQLException;
import java.util.List;

public interface IShopService{
    void addShop(Shop shop) throws SQLException;

    Shop selectShop(int id);

    List<Shop> selectAllShops();

    boolean deleteShop(int id)throws SQLException;

    boolean updateShop(Shop shop)throws SQLException;
}
