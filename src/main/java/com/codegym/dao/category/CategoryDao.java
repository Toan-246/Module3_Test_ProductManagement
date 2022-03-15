package com.codegym.dao.category;

import com.codegym.dao.DBConnection;
import com.codegym.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryDao implements ICategoryDao{
    public static final String SELECT_ALL_FROM_CATEGORIES = "SELECT * FROM categories";
    public static final String SELECT_CATEGORY_BY_PRODUCT_ID = "select C.id, C.name from product P join categories C on P.categoryId = C.id where p.id = ?";
    private Connection connection = DBConnection.getConnection();
    @Override
    public List<Category> findAll() {

        List<Category> categories = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FROM_CATEGORIES);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Category category = new Category(id, name);

                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public Category findById(int id) {
        return null;
    }

    @Override
    public boolean create(Category category) {
        return false;
    }

    @Override
    public boolean updateById(int id, Category category) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public Category findCategoryByProductId(int id) {
        Category category = new Category();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORY_BY_PRODUCT_ID);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int cId = rs.getInt("id");
                String name = rs.getString("name");
                category = new Category(cId, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }
}
