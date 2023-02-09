package com.example.fashionapp.Model.Entity.search;

import com.google.gson.annotations.SerializedName;
import com.tickaroo.tikxml.annotation.Element;
import com.tickaroo.tikxml.annotation.Xml;

import org.intellij.lang.annotations.JdkConstants;

import java.util.List;

@Xml(name = "ProductSearchResponse")
public class ProductResponse {
    @Element(name = "Products")
    private Products products;

    public ProductRequest getRequest() {
        return request;
    }

    public void setRequest(ProductRequest request) {
        this.request = request;
    }

    @Element(name = "Request")
    private ProductRequest request;


    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }
}

