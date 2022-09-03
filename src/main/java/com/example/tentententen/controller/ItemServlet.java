package com.example.tentententen.controller;

import com.example.tentententen.model.Item;
import com.example.tentententen.service.IService;
import com.example.tentententen.service.category.CategoryService;
import com.example.tentententen.service.category.ICategoryService;
import com.example.tentententen.service.item.IItemService;
import com.example.tentententen.service.item.ItemService;

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


@WebServlet (name = "ItemServlet", value = "/items")
public class ItemServlet extends HttpServlet {

    ICategoryService categoryService = new CategoryService();
    private IItemService itemService = new ItemService();
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
                case "edit":
                    showEdit(request,response);
                    break;
                case "delete":
                    deleteItem(request,response);
                    break;
                case "find":
                    searchItem(request,response);
                    break;
                default:
                    listItem(request,response);
                    break;
            }
        } catch (Exception e) {
           e.printStackTrace();
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
                case "edit":
                    editItem(request,response);
                    break;
                case "delete":
                    deleteItem(request,response);
                    break;
                case "find":
                    searchItem(request,response);
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("test/home.jsp");
        dispatcher.forward(request,response);
    }
    private void showCreate(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("test/create.jsp");
        request.setAttribute("categories", categoryService.fillAll());
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

        String[] categoriesStr = request.getParameterValues("categories");
        int[] categories = new int[categoriesStr.length];
        for (int i = 0; i < categoriesStr.length; i++) {
            categories[i] = Integer.parseInt(categoriesStr[i]);
        }

        Item item = new Item(item_code, shop_id,category_id,deal_id,item_name,item_price,item_description,item_image);

        itemService.save(item,categories);

        RequestDispatcher dispatcher = request.getRequestDispatcher("test/create.jsp");
        dispatcher.forward(request,response);
    }

    private void showEdit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        int item_id = Integer.parseInt(request.getParameter("id"));
        Item item = this.itemService.findById(item_id);
        request.setAttribute("item",item);
        RequestDispatcher dispatcher = request.getRequestDispatcher("test/edit.jsp");
        dispatcher.forward(request,response);
    }

    private void editItem(HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException, ServletException {
        int item_id = Integer.parseInt(request.getParameter("id"));
        String item_code = new String(request.getParameter("item_code").getBytes("iso-8859-1"),"utf-8");
        int shop_id = Integer.parseInt(request.getParameter("shop_id"));
        int category_id = Integer.parseInt(request.getParameter("category_id"));
        int deal_id = Integer.parseInt(request.getParameter("deal_id"));
        String item_name = new String(request.getParameter("item_name").getBytes("iso-8859-1"),"utf-8");
        double item_price = Double.parseDouble(request.getParameter("item_price"));
        String item_description = new String(request.getParameter("item_description").getBytes("iso-8859-1"),"utf-8");
        String item_image = new String(request.getParameter("item_image").getBytes("iso-8859-1"),"utf-8");

        Item item = new Item(item_code, shop_id, category_id,deal_id,item_name,item_price,item_description,item_image);
        itemService.edit(item_id,item);

        request.setAttribute("message","Okeeee b ");
        RequestDispatcher dispatcher = request.getRequestDispatcher("test/edit.jsp");
        dispatcher.forward(request,response);

    }

    private void deleteItem(HttpServletRequest request,HttpServletResponse response) throws SQLException, ServletException, IOException {
        int item_id = Integer.parseInt(request.getParameter("id"));
        itemService.delete(item_id);
        List<Item> items = itemService.fillAll();
        request.setAttribute("items",items);
        RequestDispatcher dispatcher = request.getRequestDispatcher("test/home.jsp");
        dispatcher.forward(request,response);
    }

    private void searchItem(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
//        String name = new String(request.getParameter("searchItemByName").getBytes("iso-8859-1"),"utf-8");
        String name = request.getParameter("searchItemByName");
                List<Item> items = itemService.selectItemByName(name);

        request.setAttribute("items",items);
        RequestDispatcher dispatcher = request.getRequestDispatcher("client/assets/page/find.jsp");
        dispatcher.forward(request,response);
    }


}
