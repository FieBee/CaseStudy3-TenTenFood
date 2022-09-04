package com.example.tentententen.controller;

import com.example.tentententen.model.Deal;
import com.example.tentententen.model.Item;
import com.example.tentententen.model.Shop;
import com.example.tentententen.service.deal.DealService;
import com.example.tentententen.service.deal.IDealService;
import com.example.tentententen.service.item.IItemService;
import com.example.tentententen.service.item.ItemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DealShopServlet", value = "/dealShops")
public class DealItemServlet extends HttpServlet {
    IItemService itemService= new ItemService();
    IDealService dealService= new DealService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id= Integer.parseInt(req.getParameter("id"));
        List<Item> itemList=itemService.findAllItemByIdDeal(id);
        Deal deal= dealService.findById(id);
        req.setAttribute("deal",deal);
        req.setAttribute("itemLists",itemList);
        req.getRequestDispatcher("client/assets/page/customer/itemDeal.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
