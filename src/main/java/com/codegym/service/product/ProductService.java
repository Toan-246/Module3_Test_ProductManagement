package com.codegym.service.product;

import com.codegym.dao.product.IProductDao;
import com.codegym.dao.product.ProductDao;
import com.codegym.model.Product;

import java.util.List;
import java.util.Map;

public class ProductService implements IProductService{
    private IProductDao productDao = new ProductDao();
    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public Product findById(int id) {
        return productDao.findById(id);
    }

    @Override
    public boolean create(Product product) {
        return productDao.create(product);
    }

    @Override
    public boolean updateById(int id, Product product) {
        return productDao.updateById(id, product);
    }

    @Override
    public boolean deleteById(int id) {
        return productDao.deleteById(id);
    }


    @Override
    public List<Product> findProductByName(String name) {
        name = "%" + name + "%";
        return productDao.findProductByName(name);
    }
}
