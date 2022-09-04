package com.example.tentententen.controller;

import com.example.tentententen.model.Item;
import com.example.tentententen.model.Order;
import com.example.tentententen.service.item.IItemService;
import com.example.tentententen.service.item.ItemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "AddToCartServlet" , value = "/customers/addToCart")
public class AddToCartServlet  extends HttpServlet {

    IItemService itemService = new ItemService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int quantity = 1;
        int id;
        if (request.getParameter("itemId")!=null){
            id = Integer.parseInt(request.getParameter("itemId"));
            Item item = itemService.findById(id);
            if (item != null){
                if (request.getParameter("quantity")!=null){
                    quantity = Integer.parseInt(request.getParameter("quantity"));
                }
                HttpSession session = request.getSession();
                if (session.getAttribute("order")==null){
                    Order order = new Order();
                    List<Item> itemList = new ArrayList<Item>();
                    itemList.add(item);
                    order.setItems(itemList);
                    session.setAttribute("order",order);
                }else {
                    Order order = (Order) session.getAttribute("order");
                    List<Item> itemList = order.getItems();
                    boolean check = false;
                    for (Item item1 = itemList){
                        if (item1.getItem_id() == item.getItem_id()){
//                            Nhap quantity
                            check = true;
                        }
                    }
                    if (check == false){
                        Item item1 = new Item();
                        itemList.add(item1);
                    }
                    session.setAttribute("order",order);
                }
            }
            response.sendRedirect(request.getContextPath()+"aa");

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
