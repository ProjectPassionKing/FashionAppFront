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

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }
}

