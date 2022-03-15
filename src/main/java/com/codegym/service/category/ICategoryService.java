package com.codegym.service.category;

import com.codegym.model.Category;
import com.codegym.service.IGeneralService;

import java.util.Map;

public interface ICategoryService extends IGeneralService <Category> {
    Category findCategoryByProductId (int id);
}
