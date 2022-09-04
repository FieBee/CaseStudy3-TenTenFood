package com.example.tentententen.controller;

import com.example.tentententen.model.Deal;
import com.example.tentententen.service.IService;
import com.example.tentententen.service.deal.DealService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Dealservlet" ,value="/deals")
public class DealServlet extends HttpServlet {
    IService service= new DealService();
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
                    deleteDeal(req,resp);
                break;
            default:
                listDeal(req,resp);
                break;
        }
    }

    private void deleteDeal(HttpServletRequest req, HttpServletResponse resp) {
        int id= Integer.parseInt(req.getParameter("id"));
        try {
            service.delete(id);
            List<Deal> deals= service.fillAll();
            req.setAttribute("deals",deals);
            req.getRequestDispatcher("").forward(req,resp);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void showEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id= Integer.parseInt(req.getParameter("id"));
        Deal deal= (Deal) service.findById(id);
        req.setAttribute("deals",deal);
        req.getRequestDispatcher("").forward(req,resp);

    }

    private void showCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action==null){
            action="";
        }
        switch (action){
            case "create":
                createDeal(req,resp);
                break;
            case "edit":
                editDeal(req,resp);
                break;
            case "delete":
                deleteDeal(req,resp);
                break;

        }
    }

    private void listDeal(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Deal> dealList;
        dealList= service.fillAll();
        req.setAttribute("deals",dealList);
        req.getRequestDispatcher("").forward(req,resp);
    }

    private void editDeal(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        int id= Integer.parseInt(req.getParameter("id"));
        try {
            String code = new String(req.getParameter("deal_code").getBytes("iso-8859-1"),"utf-8");
        String name= new String(req.getParameter("deal_name").getBytes("iso-8859-1"),"utf-8");
        String start_date= req.getParameter("deal_startDate");
        String end_date= req.getParameter("deal_endDate");
        String description= new String(req.getParameter("deal_description").getBytes("iso-8859-1"),"utf-8");
        String image= req.getParameter("deal_image");
        Deal deal= new Deal(code,name,start_date,end_date,description,image);
            service.edit(id,deal);
            req.getRequestDispatcher("").forward(req,resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createDeal(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String code = new String(req.getParameter("deal_code").getBytes("iso-8859-1"),"utf-8");
        String name= new String(req.getParameter("deal_name").getBytes("iso-8859-1"),"utf-8");
        String start_date= req.getParameter("deal_startDate");
        String end_date= req.getParameter("deal_endDate");
        String description= new String(req.getParameter("deal_description").getBytes("iso-8859-1"),"utf-8");
        String image= req.getParameter("deal_image");
        Deal deal= new Deal(code,name,start_date,end_date,description,image);
        service.insert(deal);
        req.getRequestDispatcher("").forward(req,resp);
    }
}
