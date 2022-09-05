package com.example.tentententen.controller;

import com.example.tentententen.model.Customer;
import com.example.tentententen.service.category.CategoryService;
import com.example.tentententen.service.category.ICategoryService;
import com.example.tentententen.service.customer.CustomerService;
import com.example.tentententen.service.customer.ICustomerService;
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

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    static String account ="";
    static boolean checkLogin = false;

    static String USER_ACCOUNT = "";
    private final String ADMIN_ACCOUNT = "admin";
    private final String ADMIN_PASSWORD = "123456";
    IDealService dealService= new DealService();

    ICategoryService categoryService = new CategoryService();
    IItemService itemService = new ItemService();

    IShopService shopService = new ShopService();

    CustomerService customerService =  new CustomerService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null)
            action = "";

        switch (action) {
            default:
                showLogin(request,response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null)
            action = "";

        switch (action) {
            default:
                login(request,response);
                break;
        }
    }



    private void showLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("client/assets/page/login.jsp");
        request.setAttribute("message","Hoặc đăng nhập bằng tài khoản của bạn");
        dispatcher.forward(request,response);
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String account = request.getParameter("account");
        LoginServlet.account = account;
        String password = request.getParameter("password");
        List<Customer> customers = customerService.fillAll();
        RequestDispatcher dispatcher;
        request.setAttribute("deals",dealService.fillAll());
        request.setAttribute("categories",categoryService.fillAll());
        request.setAttribute("shops",shopService.fillAll());
        request.setAttribute("items",itemService.fillAll());
        request.setAttribute("message","Hoặc đăng nhập bằng tài khoản của bạn");
        for(int i = 0; i < customers.size(); i++){
            if (ADMIN_ACCOUNT.equals(account)
                    && ADMIN_PASSWORD.equals(password)){
                dispatcher = request.getRequestDispatcher("/client/assets/page/admin/adminHome.jsp");
                LoginServlet.checkLogin =true;
                dispatcher.forward(request,response);
                return;
            }
            if (customers.get(i).getCustomer_account().equals(account)
                    && customers.get(i).getCustomer_password().equals(password)){
                    LoginServlet.USER_ACCOUNT = account;
                    request.setAttribute("account",account);
                    dispatcher = request.getRequestDispatcher("client/assets/page/customer/customerHome.jsp");
                    LoginServlet.checkLogin =true;
                    dispatcher.forward(request,response);
                    return;
            }

        }
        request.setAttribute("message","Sai tài khoản hoặc mật khẩu!!");
        dispatcher = request.getRequestDispatcher("client/assets/page/login.jsp");
        dispatcher.forward(request,response);

    }
}
