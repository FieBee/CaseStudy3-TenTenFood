package com.example.tentententen.controller;

import com.example.tentententen.model.Bill;
import com.example.tentententen.model.Item;
import com.example.tentententen.service.IService;
import com.example.tentententen.service.bill.BillService;
import com.example.tentententen.service.bill.IBillService;
import com.example.tentententen.service.item.ItemService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(name = "BillServlet", value = "/bill")
public class BillServlet extends HttpServlet {

    IBillService billService = new BillService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void listBill(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        List<Bill> billList = billService.fillAll();
        request.setAttribute("billList",billList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("");
        dispatcher.forward(request,response);
    }

    private void showInsert(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("");
        dispatcher.forward(request,response);
    }

    private void insertBill(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String bill_code = request.getParameter("bill_code");
        boolean status = Boolean.parseBoolean(request.getParameter("status"));
        LocalDateTime bill_date = LocalDateTime.now();
        double bill_totalCost = Double.parseDouble(request.getParameter("bill_totalCost"));
        int customer_id = Integer.parseInt(request.getParameter("customer_id"));
        int shop_id = Integer.parseInt(request.getParameter("shop_id"));

        Bill bill = new Bill(bill_code,status,bill_date,bill_totalCost,customer_id,shop_id);
        billService.insert(bill);

        RequestDispatcher dispatcher = request.getRequestDispatcher("");
        dispatcher.forward(request,response);
    }

    private void showEdit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        int bill_id = Integer.parseInt(request.getParameter("id"));
        Bill bill = (Bill) this.billService.findById(bill_id);
        request.setAttribute("bill",bill);
        RequestDispatcher dispatcher = request.getRequestDispatcher("");
        dispatcher.forward(request,response);
    }



}
