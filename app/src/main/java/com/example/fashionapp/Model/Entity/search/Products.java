package com.example.fashionapp.Model.Entity.search;

import com.tickaroo.tikxml.annotation.Element;
import com.tickaroo.tikxml.annotation.PropertyElement;
import com.tickaroo.tikxml.annotation.Xml;

import java.util.List;

@Xml(name = "Products")
public class Products {
    @Element(name = "Product")
    private List<Product> product;

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public List<Product> getProduct() {
        return product;
    }
}
