package com.example.tentententen.controller;

import com.example.tentententen.model.Customer;
import com.example.tentententen.model.Order;
import com.example.tentententen.service.order.OrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet (name = "OrderServlet", value = "")
public class OrderServlet extends HttpServlet {
    OrderService orderService = new OrderService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("current_user");
        Order order = (Order) session.getAttribute("order");
        if (customer!= null && order != null){
            order.setCustomer(customer);
            orderService.insert(order);
        }
        session.removeAttribute("order");
        RequestDispatcher dispatcher = req.getRequestDispatcher("");
        dispatcher.forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
