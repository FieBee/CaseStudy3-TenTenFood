package com.example.tentententen.controller;

import com.example.tentententen.model.Customer;
import com.example.tentententen.model.Deal;
import com.example.tentententen.model.Item;
import com.example.tentententen.model.Shop;
import com.example.tentententen.service.IService;
import com.example.tentententen.service.category.CategoryService;
import com.example.tentententen.service.category.ICategoryService;
import com.example.tentententen.service.customer.CustomerService;
import com.example.tentententen.service.customer.ICustomerService;
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
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CustomerServlet" , value = "/customers")
public class CustomerServlet extends HttpServlet {

    ICategoryService categoryService = new CategoryService();
    IShopService iShopService = new ShopService();
    IItemService itemService = new ItemService();

    ICustomerService customerService = new CustomerService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("account", LoginServlet.USER_ACCOUNT);
        String action= req.getParameter("action");
        if(action==null){
            action="";
        }
        switch (action){
            case "home":
                homeUser(req,resp);
                break;
            case "addToCart":
                showCart(req,resp);
                break;
            case "showShopItem":
                showShopItem(req,resp);
                break;
            case "delete":
                deleteCustomer(req,resp);
                break;
            default:
                showCustomerInfor(req,resp);
                break;
        }
    }

    private void deleteCustomer(HttpServletRequest req, HttpServletResponse resp) {
        int customer_id= Integer.parseInt(req.getParameter("id"));
        try {
            customerService.delete(customer_id);
            List<Customer> customerList= customerService.fillAll();
            req.setAttribute("customers",customerList);
            req.getRequestDispatcher("").forward(req,resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showShopItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        List<Item> itemList = itemService.findAllItemByIdShop(id);
        Shop shop= iShopService.findById(id);
        req.setAttribute("shop",shop);
        req.setAttribute("items", itemList);
        req.setAttribute("account", LoginServlet.USER_ACCOUNT);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/client/assets/page/customer/shopItem.jsp");

        requestDispatcher.forward(req,resp);
    }
    private void showEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int customer_id= Integer.parseInt(req.getParameter("id"));
        Customer  customer= (Customer) customerService.findById(customer_id);
        req.setAttribute("customer",customer);
        RequestDispatcher requestDispatcher= req.getRequestDispatcher("");
        requestDispatcher.forward(req,resp);
    }

    private void showCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher= req.getRequestDispatcher("");
        requestDispatcher.forward(req,resp);
    }

    private void listCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Customer> customerList= customerService.fillAll();
        req.setAttribute("customers",customerList);
        RequestDispatcher requestDispatcher= req.getRequestDispatcher("");
        requestDispatcher.forward(req,resp);
    }

    private void showCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/client/assets/page/customer/customerCart.jsp");
        dispatcher.forward(req,resp);
        req.setAttribute("account", LoginServlet.USER_ACCOUNT);
    }
    private void showCustomerInfor(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = LoginServlet.account;
        req.setAttribute("account", LoginServlet.USER_ACCOUNT);
        Customer customer = customerService.findByName(name);
        req.setAttribute("customer",customer);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/client/assets/page/customer/customerInfor.jsp");
        dispatcher.forward(req,resp);
//        req.setAttribute("account", LoginServlet.USER_ACCOUNT);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  ServletException, IOException {
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
            default:
                showCustomerInfor(req,resp);
                break;
        }
    }


    public void homeUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("client/assets/page/customer/customerHome.jsp");
        request.setAttribute("categories",categoryService.fillAll());
        request.setAttribute("shops",iShopService.fillAll());
        request.setAttribute("items",itemService.fillAll());
        request.setAttribute("account", LoginServlet.USER_ACCOUNT);
        dispatcher.forward(request,response);
    }

    private void editCustomer(HttpServletRequest req, HttpServletResponse resp) throws  ServletException, IOException {
        int item_id = Integer.parseInt(req.getParameter("id"));
        String code= new String(req.getParameter("customer_code").getBytes("iso-8859-1"),"utf-8");
        String name= new String(req.getParameter("customer_name").getBytes("iso-8859-1"),"utf-8");
        String phone= new String(req.getParameter("customer_phone").getBytes("iso-8859-1"),"utf-8");
        String address= new String(req.getParameter("customer_address").getBytes("iso-8859-1"),"utf-8");
        String email= new String(req.getParameter("customer_email").getBytes("iso-8859-1"),"utf-8");
        String account= new String(req.getParameter("customer_account").getBytes("iso-8859-1"),"utf-8");
        String password= new String(req.getParameter("customer_password").getBytes("iso-8859-1"),"utf-8");
        Customer customer= new Customer(code,name,phone,address,email,account,password);
        try {
            customerService.edit(item_id,customer);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("account", LoginServlet.USER_ACCOUNT);
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
        customerService.insert(customer);
        req.setAttribute("message","Okeeee b");
        RequestDispatcher requestDispatcher= req.getRequestDispatcher("");
        requestDispatcher.forward(req,resp);
        req.setAttribute("account", LoginServlet.USER_ACCOUNT);
    }
}