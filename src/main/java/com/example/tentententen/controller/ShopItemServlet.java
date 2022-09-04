package com.example.tentententen.controller;

import com.example.tentententen.model.Item;
import com.example.tentententen.service.item.IItemService;
import com.example.tentententen.service.item.ItemService;

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        List<Item> itemList = itemService.findAllItemByIdShop(id);
        req.setAttribute("items", itemList);
        req.getRequestDispatcher("client/assets/page/customer/shopItem.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
