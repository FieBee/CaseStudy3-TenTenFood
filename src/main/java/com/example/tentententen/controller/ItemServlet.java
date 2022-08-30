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
import java.sql.SQLException;
import java.util.List;


@WebServlet (name = "ItemServlet", value = "/home")
public class ItemServlet extends HttpServlet {

    private IService itemService = new ItemService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        try{
            switch (action){
                case "create":
                    showCreate(request,response);
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
                case "create":
                    createItem(request,response);
                    break;
                default:
                    listItem(request,response);
                    break;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void listItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Item> items = itemService.fillAll();
        request.setAttribute("items",items);
        RequestDispatcher dispatcher = request.getRequestDispatcher("client/home.jsp");
        dispatcher.forward(request,response);
    }
    private void showCreate(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("test/create.jsp");
        dispatcher.forward(request,response);
    }

    private void createItem(HttpServletRequest request,HttpServletResponse response) throws SQLException, ServletException, IOException {
        String item_code = new String(request.getParameter("item_code").getBytes("iso-8859-1"),"utf-8");
        int shop_id = Integer.parseInt(request.getParameter("shop_id"));
        int category_id = Integer.parseInt(request.getParameter("category_id"));
        int deal_id = Integer.parseInt(request.getParameter("deal_id"));
        String item_name = new String(request.getParameter("item_name").getBytes("iso-8859-1"),"utf-8");
        double item_price = Double.parseDouble(request.getParameter("item_price"));
        String item_description = new String(request.getParameter("item_description").getBytes("iso-8859-1"),"utf-8");
        String item_image = new String(request.getParameter("item_image").getBytes("iso-8859-1"),"utf-8");
        Item item = new Item(item_code, shop_id, category_id,deal_id,item_name,item_price,item_description,item_image);

        itemService.insert(item);

        RequestDispatcher dispatcher = request.getRequestDispatcher("test/create.jsp");
        dispatcher.forward(request,response);
    }

    private void showEdit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        int item_id = Integer.parseInt(request.getParameter("item_id"));
        Item item = (Item) this.itemService.findById(item_id);
        request.setAttribute("item",item);
        RequestDispatcher dispatcher = request.getRequestDispatcher("edit");
        dispatcher.forward(request,response);

    }
}
