package com.example.tentententen.controller;

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

@WebServlet(name="HomeServlet" , value = "/home")
public class HomeServlet extends HttpServlet {
    IShopService shopService = new ShopService();
    ICategoryService categoryService = new CategoryService();
    IItemService itemService = new ItemService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String action = req.getParameter("action");
       if (action == null){
           action= "";
       }
       try{
           switch (action){
               case "login":
                   showlogin(req,resp);
                   break;
               default:
                   showHome(req,resp);
                   break;

           }
       } catch (ServletException e) {
           throw new RuntimeException(e);
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
    }

    private void showHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("client/assets/page/home.jsp");

        request.setAttribute("categories",categoryService.fillAll());
        request.setAttribute("items",itemService.fillAll());
        dispatcher.forward(request,response);

    }

    private void showlogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("client/assets/page/login.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }




}
