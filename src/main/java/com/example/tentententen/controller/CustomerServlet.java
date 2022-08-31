package com.example.tentententen.controller;

import com.example.tentententen.model.Customer;
import com.example.tentententen.service.IService;
import com.example.tentententen.service.customer.CustomerService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@WebServlet(name = "CustomerServlet" , value = "customers")
public class CustomerServlet extends HttpServlet {
    IService service= new CustomerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String action= req.getParameter("action");
       if(action==null){
           action="";
       }
       switch (action){
           case "create":
               showCreate(req,resp);
               break;
           case "edit":
               showEdit(req,resp);
               break;
           case "delete":
               deleteCustomer(req,resp);
               break;
           default:
               listCustomer(req,resp);
               break;
       }
    }

    private void deleteCustomer(HttpServletRequest req, HttpServletResponse resp) {
        int customer_id= Integer.parseInt(req.getParameter("id"));
        service.delete(customer_id);
    }

    private void showEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int customer_id= Integer.parseInt(req.getParameter("id"));
        Customer  customer= (Customer) service.findById(customer_id);
        req.setAttribute("customer",customer);
        RequestDispatcher requestDispatcher= req.getRequestDispatcher("");
        requestDispatcher.forward(req,resp);
    }

    private void showCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher= req.getRequestDispatcher("");
        requestDispatcher.forward(req,resp);
    }

    private void listCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Customer> customerList= service.fillAll();
        req.setAttribute("customers",customerList);
        RequestDispatcher requestDispatcher= req.getRequestDispatcher("");
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action= req.getParameter("acion");
        if(action==null){
            action="";
        }
        switch (action){
            case "create":
                createCustomer(req,resp);
                break;
            case "edit":
                editCustomer(req,resp);
                break;
            case "delete":
                deleteCustomer(req,resp);
                break;
        }
    }

    private void editCustomer(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int item_id = Integer.parseInt(req.getParameter("id"));
        String code= new String(req.getParameter("customer_code").getBytes("iso-8859-1"),"utf-8");
        String name= new String(req.getParameter("customer_name").getBytes("iso-8859-1"),"utf-8");
        String phone= new String(req.getParameter("customer_phone").getBytes("iso-8859-1"),"utf-8");
        String address= new String(req.getParameter("customer_address").getBytes("iso-8859-1"),"utf-8");
        String email= new String(req.getParameter("customer_email").getBytes("iso-8859-1"),"utf-8");
        String account= new String(req.getParameter("customer_account").getBytes("iso-8859-1"),"utf-8");
        String password= new String(req.getParameter("customer_password").getBytes("iso-8859-1"),"utf-8");
        Customer customer= new Customer(code,name,phone,address,email,account,password);
        service.edit(item_id,customer);
        req.setAttribute("message","Okeeee b");
        RequestDispatcher requestDispatcher= req.getRequestDispatcher("");
        requestDispatcher.forward(req,resp);
    }

    private void createCustomer(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String code= new String(req.getParameter("customer_code").getBytes("iso-8859-1"),"utf-8");
        String name= new String(req.getParameter("customer_name").getBytes("iso-8859-1"),"utf-8");
        String phone= new String(req.getParameter("customer_phone").getBytes("iso-8859-1"),"utf-8");
        String address= new String(req.getParameter("customer_address").getBytes("iso-8859-1"),"utf-8");
        String email= new String(req.getParameter("customer_email").getBytes("iso-8859-1"),"utf-8");
        String account= new String(req.getParameter("customer_account").getBytes("iso-8859-1"),"utf-8");
        String password= new String(req.getParameter("customer_password").getBytes("iso-8859-1"),"utf-8");
        Customer customer= new Customer(code,name,phone,address,email,account,password);
        service.insert(customer);
        req.setAttribute("message","Okeeee b");
        RequestDispatcher requestDispatcher= req.getRequestDispatcher("");
        requestDispatcher.forward(req,resp);
    }
}
