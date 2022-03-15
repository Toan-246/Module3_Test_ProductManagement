package com.codegym.service.product;

import com.codegym.model.Product;
import com.codegym.service.IGeneralService;

import java.util.List;
import java.util.Map;

public interface IProductService extends IGeneralService<Product> {

    List<Product> findProductByName (String name);
}
