//package com.example.tentententen.controller;
//
//import com.example.tentententen.model.Customer;
//import com.example.tentententen.service.customer.CustomerService;
//import com.example.tentententen.service.customer.ICustomerService;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet(name = "LoginServlet", urlPatterns = "/login")
//public class LoginServlet extends HttpServlet {
//
//    CustomerService customerService =  new CustomerService();
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        action(request, response);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        action(request, response);
//    }
//
//    private void action(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String action = request.getParameter("action");
//        if (action == null)
//            action = "";
//
//        switch (action) {
//            case "registration":
////                registration(request, response);
////                break;
//            case "login":
//                login(request, response);
//                break;git
//        }
//    }
//
//
//    private void login(HttpServletRequest request, HttpServletResponse response){
//        String account = request.getParameter("account");
//        String password = request.getParameter("password");
//        Customer customer = new Customer(account,password);
//        int customer_id = customerService.findByUser(customer);
//
//    }
//}
