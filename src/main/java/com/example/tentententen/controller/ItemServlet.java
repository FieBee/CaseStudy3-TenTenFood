package com.example.tentententen.controller;

import com.example.tentententen.model.Item;
import com.example.tentententen.service.IService;
import com.example.tentententen.service.item.ItemService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet (name = "ItemServlet", value = "/home")
public class ItemServlet extends HttpServlet {

    IService itemService = new ItemService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        try{
            switch (action){
                case "asd":
                    break;
                default:
                    listItem(request,response);
                    break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        try{
            switch (action){
                case "asd":
                    break;
                default:
                    listItem(request,response);
                    break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void listItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Item> items = itemService.fillAll();
        request.setAttribute("items",items);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request,response);
    }
}
