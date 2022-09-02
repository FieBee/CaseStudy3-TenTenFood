package com.example.tentententen.controller;

import com.example.tentententen.model.Category;
import com.example.tentententen.model.Item;
import com.example.tentententen.service.category.CategoryService;
import com.example.tentententen.service.category.ICategoryService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet(name = "CategoryServlet", value = "/categories")
public class CategoryServlet extends HttpServlet {

    ICategoryService categoryService = new CategoryService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        try{
            switch (action){
                case "create":
                    createCategory(request,response);
                    break;
                case "edit":
                    editCategory(request,response);
                    break;
                case "delete":
                    deleteCategory(request,response);
                    break;
                default:
                    listCatagory(request,response);
                    break;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        try{
            switch (action){
                case "create":
                    createCategory(request,response);
                    break;
                case "edit":
                    editCategory(request,response);
                    break;
                case "delete":
                    deleteCategory(request,response);
                    break;
                case "view":
                    viewCategory(request,response);
                    break;
                default:
                    listCatagory(request,response);
                    break;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void listCatagory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = categoryService.fillAll();
        request.setAttribute("categories",categories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("");
        dispatcher.forward(request,response);
    }

    private void showCreate(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("");
        dispatcher.forward(request,response);
    }

    private void createCategory(HttpServletRequest request,HttpServletResponse response) throws SQLException, ServletException, IOException {
        String category_code = new String(request.getParameter("category_code").getBytes("iso-8859-1"),"utf-8");
        String category_name = new String(request.getParameter("category_name").getBytes("iso-8859-1"),"utf-8");
        String category_description = new String(request.getParameter("category_description").getBytes("iso-8859-1"),"utf-8");
        Category category = new Category(category_code, category_name, category_description);

        categoryService.insert(category);

        RequestDispatcher dispatcher = request.getRequestDispatcher("");
        dispatcher.forward(request,response);
    }

    private void showEdit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        int item_id = Integer.parseInt(request.getParameter(""));
        Category category = (Category) this.categoryService.findById(item_id);
        request.setAttribute("category",category);
        RequestDispatcher dispatcher = request.getRequestDispatcher("");
        dispatcher.forward(request,response);
    }

    private void editCategory(HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException, ServletException {
        String category_code = new String(request.getParameter("category_code").getBytes("iso-8859-1"),"utf-8");
        String category_name = new String(request.getParameter("category_name").getBytes("iso-8859-1"),"utf-8");
        String category_description = new String(request.getParameter("category_description").getBytes("iso-8859-1"),"utf-8");

        Category category = new Category(category_code,category_name,category_description);

        request.setAttribute("message","Okeeee b ");
        RequestDispatcher dispatcher = request.getRequestDispatcher("");
        dispatcher.forward(request,response);

    }

    private void deleteCategory(HttpServletRequest request,HttpServletResponse response) throws SQLException, ServletException, IOException {
        int category_id = Integer.parseInt(request.getParameter("id"));
        categoryService.delete(category_id);
        List<Category> categories = categoryService.fillAll();
        request.setAttribute("categories",categories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("");
        dispatcher.forward(request,response);
    }

    private void viewCategory(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String category_id = request.getParameter("id");
        Category category = categoryService.findById(Integer.parseInt(category_id));
        request.setAttribute("category",category);

        RequestDispatcher dispatcher = request.getRequestDispatcher("client/page/category.jsp");
        dispatcher.forward(request,response);


    }

}



