package com.example.tentententen.controller;

import com.example.tentententen.model.Shop;
import com.example.tentententen.service.bill.BillService;
import com.example.tentententen.service.bill.IBillService;
import com.example.tentententen.service.bill_detail.Bill_DetailService;
import com.example.tentententen.service.bill_detail.IBill_DetailService;
import com.example.tentententen.service.category.CategoryService;
import com.example.tentententen.service.category.ICategoryService;
import com.example.tentententen.service.item.IItemService;
import com.example.tentententen.service.item.ItemService;
import com.example.tentententen.service.shop.IShopService;
import com.example.tentententen.service.shop.ShopService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "AdminServlet", value = "/admin")
public class AdminServlet extends HttpServlet {

    ICategoryService categoryService = new CategoryService();
    IItemService itemService = new ItemService();
    IShopService shopService = new ShopService();

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
                    showShopManager(request,response);
                    break;
                case "editShop":
                    showEditShop(request,response);
                    break;
                case "deleteShop":
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
                case "shopManager":
                    showShopManager(request,response);
                    break;
                case "create":
                    break;
                case "editShop":
                    editShop(request,response);
                    break;
                case "deleteShop":
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
        request.setAttribute("categories",categoryService.fillAll());
        request.setAttribute("shops",shopService.fillAll());
        request.setAttribute("items",itemService.fillAll());

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
        RequestDispatcher dispatcher = request.getRequestDispatcher("client/assets/page/admin/editShop.jsp");
        dispatcher.forward(request,response);
    }

    private void editShop(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int shop_id = Integer.parseInt(request.getParameter("shop_id"));
        String shop_code = new String(request.getParameter("shop_code").getBytes("iso-8859-1"),"utf-8");
        String shop_name = new String(request.getParameter("shop_name").getBytes("iso-8859-1"),"utf-8");
        String shop_email = new String(request.getParameter("shop_email").getBytes("iso-8859-1"),"utf-8");
        int shop_phone = Integer.parseInt(request.getParameter("shop_phone"));
        String shop_address = new String(request.getParameter("shop_address").getBytes("iso-8859-1"),"utf-8");
        String shop_account = new String(request.getParameter("shop_account").getBytes("iso-8859-1"),"utf-8");
        String shop_password = new String(request.getParameter("shop_password").getBytes("iso-8859-1"),"utf-8");
        String shop_image= request.getParameter("shop_image");
        Shop shop = new Shop(shop_code, shop_name, shop_email, shop_phone, shop_address, shop_account, shop_password, shop_image);
        shopService.edit(shop_id, shop);
        request.setAttribute("message","Sửa thông tin thành công!!");
        RequestDispatcher dispatcher = request.getRequestDispatcher("client/assets/page/admin/editShop.jsp");
        dispatcher.forward(request,response);
    }
}

