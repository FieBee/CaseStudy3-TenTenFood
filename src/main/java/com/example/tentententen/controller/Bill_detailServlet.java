package com.example.tentententen.controller;

import com.example.tentententen.model.Bill_detail;
import com.example.tentententen.service.category.bill_detail.Bill_DetailService;
import com.example.tentententen.service.category.bill_detail.IBill_DetailService;

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

@WebServlet(name = "Bill_detailServlet", value = "/bill_details")
public class Bill_detailServlet extends HttpServlet {
    private IBill_DetailService bill_detailService;

    public void init() {
        bill_detailService = new Bill_DetailService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    createBill_detail(request,response);
                    break;
                case "edit":
                    editBill_detail(request,response);
                    break;
                case "delete":
                    deleteBill_detail(request,response);
                    break;
                case "search":
                    searchBill_detail(request, response);
                default:
                    listBill_service(request,response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void searchBill_detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void listBill_service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Bill_detail> bill_details = bill_detailService.fillAll();
        request.setAttribute("bill_details",bill_details);
        RequestDispatcher dispatcher = request.getRequestDispatcher("");
        dispatcher.forward(request,response);
    }

    private void deleteBill_detail(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int bill_detail_id = Integer.parseInt(request.getParameter("id"));
        bill_detailService.delete(bill_detail_id);
        List<Bill_detail> bill_details = bill_detailService.fillAll();
        request.setAttribute("bill_details",bill_details);
        RequestDispatcher dispatcher = request.getRequestDispatcher("");
        dispatcher.forward(request,response);
    }

    private void editBill_detail(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, SQLException {
        int bill_detail_id = Integer.parseInt(request.getParameter("bill_detail_id"));
        int bill_id = Integer.parseInt(request.getParameter("bill_id"));
        int item_id = Integer.parseInt(request.getParameter("item_id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double price = Double.parseDouble(request.getParameter("price"));

        Bill_detail bill_detail = new Bill_detail(bill_detail_id, bill_id, item_id, quantity, price);
        bill_detailService.edit(bill_detail_id, bill_detail);
    }

    private void createBill_detail(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int bill_detail_id = Integer.parseInt(request.getParameter("bill_detail_id"));
        int bill_id = Integer.parseInt(request.getParameter("bill_id"));
        int item_id = Integer.parseInt(request.getParameter("item_id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double price = Double.parseDouble(request.getParameter("price"));
        Bill_detail bill_detail = new Bill_detail(bill_detail_id, bill_id, item_id, quantity, price);

        bill_detailService.insert(bill_detail);

        RequestDispatcher dispatcher = request.getRequestDispatcher("");
        dispatcher.forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "create":
                    showNewForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteBill_detail(request, response);
                    break;
                case "search":
                    searchBill_detail(request, response);
                default:
                    listBill_service(request, response);
                    break;
            }
        } catch (SQLException | IOException e) {
            throw new ServletException(e);
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bill_detail_id = Integer.parseInt(request.getParameter("id"));
        Bill_detail bill_detail = this.bill_detailService.findById(bill_detail_id);
        request.setAttribute("bill_detail",bill_detail);
        RequestDispatcher dispatcher = request.getRequestDispatcher("");
        dispatcher.forward(request,response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("");
        dispatcher.forward(request, response);
    }
}
