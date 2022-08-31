package com.example.tentententen.controller;

import com.example.tentententen.model.Item;
import com.example.tentententen.model.Shop;
import com.example.tentententen.service.shop.IShopService;
import com.example.tentententen.service.shop.ShopService;

import java.io.*;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "ShopServlet", value = "/shop")
public class ShopServlet extends HttpServlet {
    private IShopService shopService;

    public void init() {
        shopService = new ShopService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
       String action = request.getParameter("action");
       if (action == null) {
            action = "";
       }
       try {
            switch (action) {
                case "create":
                    createShop(request,response);
                    break;
                case "edit":
                    editShop(request,response);
                    break;
                case "delete":
                    deleteShop(request,response);
                    break;
                default:
                    listShop(request,response);
                    break;
            }
       } catch (Exception e) {
           e.printStackTrace();
       }
    }

    private void listShop(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Shop> shops = shopService.fillAll();
        request.setAttribute("shops",shops);
        RequestDispatcher dispatcher = request.getRequestDispatcher("test/home.jsp");
        dispatcher.forward(request,response);
    }

    private void deleteShop(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int shop_id = Integer.parseInt(request.getParameter("id"));
        shopService.delete(shop_id);
        List<Shop> shops = shopService.fillAll();
        request.setAttribute("shops",shops);
        RequestDispatcher dispatcher = request.getRequestDispatcher("test/home.jsp");
        dispatcher.forward(request,response);
    }

    private void editShop(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, SQLException {
        int shop_id = Integer.parseInt(request.getParameter("shop_code"));
        String shop_code = new String(request.getParameter("shop_code").getBytes("iso-8859-1"),"utf-8");
        String shop_name = new String(request.getParameter("shop_name").getBytes("iso-8859-1"),"utf-8");
        String shop_email = new String(request.getParameter("shop_email").getBytes("iso-8859-1"),"utf-8");
        int shop_phone = Integer.parseInt(request.getParameter("shop_phone"));
        String shop_address = new String(request.getParameter("shop_address").getBytes("iso-8859-1"),"utf-8");
        String shop_account = new String(request.getParameter("shop_account").getBytes("iso-8859-1"),"utf-8");
        String shop_password = new String(request.getParameter("shop_password").getBytes("iso-8859-1"),"utf-8");
        Shop shop = new Shop(shop_code, shop_name, shop_email, shop_phone, shop_address, shop_account, shop_password);
        shopService.edit(shop_id, shop);
    }

    private void createShop(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String shop_code = new String(request.getParameter("shop_code").getBytes("iso-8859-1"),"utf-8");
        String shop_name = new String(request.getParameter("shop_name").getBytes("iso-8859-1"),"utf-8");
        String shop_email = new String(request.getParameter("shop_email").getBytes("iso-8859-1"),"utf-8");
        int shop_phone = Integer.parseInt(request.getParameter("shop_phone"));
        String shop_address = new String(request.getParameter("shop_address").getBytes("iso-8859-1"),"utf-8");
        String shop_account = new String(request.getParameter("shop_account").getBytes("iso-8859-1"),"utf-8");
        String shop_password = new String(request.getParameter("shop_password").getBytes("iso-8859-1"),"utf-8");
        Shop shop = new Shop(shop_code, shop_name, shop_email, shop_phone, shop_address, shop_account, shop_password);

        shopService.insert(shop);

        RequestDispatcher dispatcher = request.getRequestDispatcher("test/create.jsp");
        dispatcher.forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "create":
                    showNewForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteShop(request, response);
                    break;
                default:
                    listShop(request, response);
                    break;
            }
        } catch (SQLException | IOException e) {
            throw new ServletException(e);
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int shop_id = Integer.parseInt(request.getParameter("id"));
        Shop shop = this.shopService.findById(shop_id);
        request.setAttribute("shop",shop);
        RequestDispatcher dispatcher = request.getRequestDispatcher("test/edit.jsp");
        dispatcher.forward(request,response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("test/create.jsp");
        dispatcher.forward(request, response);
    }

}