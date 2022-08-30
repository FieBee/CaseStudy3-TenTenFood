package com.example.tentententen.service.shop;

import com.example.tentententen.model.Shop;
import com.example.tentententen.service.IService;

import java.sql.SQLException;
import java.util.List;

<<<<<<< HEAD
public interface IShopService{
    void addShop(Shop shop) throws SQLException;
=======
public interface IShopService extends IService<Shop> {
    Shop selectShopByName(String name);
>>>>>>> hiep

}
