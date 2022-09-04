package com.example.tentententen.controller;

import com.example.tentententen.model.Item;
import com.example.tentententen.model.Shop;
import com.example.tentententen.service.item.IItemService;
import com.example.tentententen.service.item.ItemService;
import com.example.tentententen.service.shop.IShopService;
import com.example.tentententen.service.shop.ShopService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShopItemServlet", value = "/shopItems")
public class ShopItemServlet extends HttpServlet {
    IItemService itemService = new ItemService();
    IShopService iShopService=new ShopService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        List<Item> itemList = itemService.findAllItemByIdShop(id);
        Shop shop= iShopService.findById(id);
        req.setAttribute("shop",shop);
        req.setAttribute("items", itemList);
        req.getRequestDispatcher("client/assets/page/shopItem.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
