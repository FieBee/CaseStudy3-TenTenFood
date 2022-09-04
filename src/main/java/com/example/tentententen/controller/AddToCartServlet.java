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


@WebServlet(name = "AddToCartServlet" , value ="/addToCart")
public class AddToCartServlet  extends HttpServlet {

    IItemService itemService = new ItemService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int quantity = 1;
        int id;
        if (request.getParameter("id")!=null){
            id = Integer.parseInt(request.getParameter("id"));
            Item item = itemService.findById(id);
            if (item != null){
                if (request.getParameter("quantity")!=null){
                    quantity = Integer.parseInt(request.getParameter("quantity"));
                }
                HttpSession session = request.getSession();
                if (session.getAttribute("order")==null){
                    Order order = new Order();
                    List<Item> itemList = new ArrayList<Item>();
                    order.setQuantityItem(quantity);
                    itemList.add(item);
                    order.setItems(itemList);
                    session.setAttribute("order",order);
                    session.setAttribute("quantity",quantity);
                }else {
                    Order order = (Order) session.getAttribute("order");
                    List<Item> itemList = order.getItems();
                    boolean check = false;
                    for (Item item1 : itemList){
                        if (item1.getItem_id() == item.getItem_id()){
//                            Nhap quantity
                            order.setQuantityItem(order.getQuantityItem() + quantity);
                            check = true;
                        }
                    }
                    if (check == false){
                        order.setQuantityItem(quantity);
                        Item item1 = new Item();
                        itemList.add(item1);
                    }
                    session.setAttribute("order",order);
                    quantity = order.getQuantityItem();
                    session.setAttribute("quantity",quantity);
                }
            }
            response.sendRedirect("client/assets/page/customer/customerCart.jsp");

        }else {
            response.sendRedirect("/client/assets/page/customer/customerCart.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
