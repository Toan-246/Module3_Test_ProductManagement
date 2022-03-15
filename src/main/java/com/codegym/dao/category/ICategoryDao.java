package com.codegym.dao.category;

import com.codegym.dao.IGeneralDao;
import com.codegym.model.Category;

import java.util.Map;

public interface ICategoryDao extends IGeneralDao <Category> {
    Category findCategoryByProductId (int id);
}
