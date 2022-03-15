package com.codegym.controller;

import com.codegym.model.Category;
import com.codegym.model.Product;
import com.codegym.service.category.CategoryService;
import com.codegym.service.category.ICategoryService;
import com.codegym.service.product.IProductService;
import com.codegym.service.product.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ProductServlet", value = "/products")
public class ProductServlet extends HttpServlet {
    IProductService productService = new ProductService();
    ICategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create": {
                showCreateProductForm(request, response);
                break;
            }
            case "edit": {
                showEditProductForm(request, response);
                break;
            }
            case "delete": {
                deleteProductForm(request, response);
                break;
            }
            default: {
                ShowProductList(request, response);
                break;
            }
        }
    }

    private void deleteProductForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findById(id);
        request.setAttribute("product", product);
        List<Category> categories = categoryService.findAll();
        request.setAttribute("categories", categories);
        Category productCategory = categoryService.findCategoryByProductId(id);
        request.setAttribute("productCategory", productCategory);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/product/delete.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditProductForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findById(id);
        request.setAttribute("product", product);
        List<Category> categories = categoryService.findAll();
        request.setAttribute("categories", categories);
        Category productCategory = categoryService.findCategoryByProductId(id);
        request.setAttribute("productCategory", productCategory);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/product/edit.jsp");
        requestDispatcher.forward(request, response);
    }

    private void showCreateProductForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = categoryService.findAll();
        request.setAttribute("categories", categories);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/product/create.jsp");
        requestDispatcher.forward(request, response);
    }

    private void ShowProductList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = productService.findAll();
        String q = request.getParameter("q");

        if (q != null) {
            products = productService.findProductByName(q);
        }
        request.setAttribute("products", products);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/product/list.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create": {
                createProduct(request, response);
                break;
            }
            case "edit": {
                editProduct(request, response);
                break;
            }
            case "delete": {
                deleteProduct(request, response);
                break;
            }
        }
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        productService.deleteById(id);
        response.sendRedirect("/products");
    }

    private void editProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        Product product = new Product(id, name, price, quantity, color, description, categoryId);

        productService.updateById(id, product);
        response.sendRedirect("/products");
    }

    private void createProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));

        Product product = new Product(name, price, quantity, color, description, categoryId);
        productService.create(product);

        response.sendRedirect("/products");
    }
}
