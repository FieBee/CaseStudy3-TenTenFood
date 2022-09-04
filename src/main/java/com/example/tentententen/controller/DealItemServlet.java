package com.example.tentententen.controller;

import com.example.tentententen.model.Shop;
import com.example.tentententen.service.deal.DealService;
import com.example.tentententen.service.deal.IDealService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DealShopServlet", value = "/dealShops")
public class DealItemServlet extends HttpServlet {
    IDealService dealService= new DealService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id= Integer.parseInt(req.getParameter("id"));
        List<Item> dealList= dealService.find;
        req.setAttribute("dealLists",dealList);
        req.getRequestDispatcher("client/assets/page/itemDeal.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
