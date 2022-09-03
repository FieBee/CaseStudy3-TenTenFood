package com.example.tentententen.controller;

import com.example.tentententen.model.Bill;
import com.example.tentententen.model.Shop;
import com.example.tentententen.service.bill.BillService;
import com.example.tentententen.service.bill.IBillService;
import com.example.tentententen.service.bill_detail.Bill_DetailService;
import com.example.tentententen.service.bill_detail.IBill_DetailService;
import com.example.tentententen.service.shop.IShopService;
import com.example.tentententen.service.shop.ShopService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "AdminServlet", value = "/admin")
public class AdminServlet extends HttpServlet {


    IBillService billService = new BillService();
    IShopService shopService = new ShopService();
    ShopServlet shopServlet = new ShopServlet();

    IBill_DetailService bill_detailService = new Bill_DetailService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "shopManager":
                    break;
                case "edit":
                    break;
                case "delete":
                    deleteShop(request,response);
                    break;
                case "search":
                default:
                    homeAdmin(request,response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    break;
                case "edit":
                    break;
                case "delete":
                    deleteShop(request,response);
                    break;
                case "search":
                    break;
                default:
                    homeAdmin(request,response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showShopManager(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("client/assets/page/admin/shopManagement.jsp");
        request.setAttribute("shops",shopService.fillAll());
        dispatcher.forward(request,response);
    }
    public void homeAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("client/assets/page/admin/adminHome.jsp");
        dispatcher.forward(request,response);
    }


    private void deleteShop(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException {
        int shop_id = Integer.parseInt(request.getParameter("id"));
        shopService.delete(shop_id);

        List<Shop> shops = shopService.fillAll();
        request.setAttribute("shops",shops);
        RequestDispatcher dispatcher = request.getRequestDispatcher("client/assets/page/admin/adminHome.jsp");
        dispatcher.forward(request,response);
    }

    private void showEditShop(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int shop_id = Integer.parseInt(request.getParameter("id"));
        Shop shop = this.shopService.findById(shop_id);
        request.setAttribute("shop",shop);
        RequestDispatcher dispatcher = request.getRequestDispatcher("test/edit.jsp");
        dispatcher.forward(request,response);
    }
}

