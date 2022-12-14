package com.example.tentententen.controller;

import com.example.tentententen.model.Item;
import com.example.tentententen.service.category.CategoryService;
import com.example.tentententen.service.category.ICategoryService;
import com.example.tentententen.service.deal.DealService;
import com.example.tentententen.service.deal.IDealService;
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
import java.util.List;

@WebServlet(name="HomeServlet" , value = "/home")
public class HomeServlet extends HttpServlet {
    IItemService itemService = new ItemService();
    IShopService shopService = new ShopService();
    ICategoryService categoryService = new CategoryService();
IDealService dealService= new DealService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String action = req.getParameter("action");
       if (action == null){
           action= "";
       }
       try{
           switch (action){
               case "showByCategory":
                   showByCategory(req,resp);
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

        request.setAttribute("deals",dealService.fillAll());
        request.setAttribute("categories",categoryService.fillAll());
        request.setAttribute("shops",shopService.fillAll());
        request.setAttribute("items",itemService.fillAll());
        dispatcher.forward(request,response);
    }

    private void showByCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("client/page/category.jsp");

        request.setAttribute("categories",categoryService.fillAll());
        request.setAttribute("shops",shopService.fillAll());
        request.setAttribute("items",itemService.fillAll());
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null){
            action= "";
        }
        try{
            switch (action){
                case "showByCategory":
                    showByCategory(req,resp);
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




}
